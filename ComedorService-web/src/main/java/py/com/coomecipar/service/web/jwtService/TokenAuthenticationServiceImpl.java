package py.com.coomecipar.service.web.jwtService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.core.GrantedAuthority;
import py.com.coomecipar.service.ejb.entity.AutenticacionTokens;
import py.com.coomecipar.service.ejb.manager.TokenAuthenticationManager;
import py.com.coomecipar.service.web.jwt.TokenHandler;
import py.com.coomecipar.service.web.spring.User;
import py.com.coomecipar.service.web.utils.LoginResponse;


@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private Context context;

    protected TokenAuthenticationManager tokenAuthentication;

    private final TokenHandler tokenHandler = new TokenHandler();
    Gson gson = new GsonBuilder().create();

    /**
     * When a user successfully logs into the application, create a token for
     * that user.
     *
     * @param res	An http response that will be filled with an 'Authentication'
     * header containing the token.
     * @param authentication
     */
    @Override
    public void addAuthentication(HttpServletResponse res, Authentication authentication) {
        AutenticacionTokens ejTokens = new AutenticacionTokens();
        LoginResponse response = new LoginResponse();
        try {
            inicializarTokenAuthenticationManager();
            
            User userDetail = ((User) authentication.getPrincipal());
            userDetail.setPassword("xxxxxxx");
            
            AutenticacionTokens JWT = tokenHandler.build(userDetail.getId()+"");
            
            response.setToken(JWT);
            response.setUsuario(userDetail);

            ejTokens.setAccessToken(JWT.getAccessToken());
            ejTokens.setRefreshToken(JWT.getRefreshToken());
            ejTokens.setExpiresToken(JWT.getExpiresToken());
            ejTokens.setTypeToken(JWT.getTypeToken());
            ejTokens.setUsuario(userDetail.getUsername());
            ejTokens.setIdUsuario(userDetail.getId());
            
            tokenAuthentication.save(ejTokens);

            res.addHeader(tokenHandler.HEADER_STRING, tokenHandler.TOKEN_PREFIX + " " + JWT.getAccessToken());
            //res.setHeader("Content-Type", "application/json;charset=UTF-8");
            res.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter writer = res.getWriter();           
            writer.write(gson.toJson(response));
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * The JWTAuthenticationFilter calls this method to verify the user
     * authentication. If the token is not valid, the authentication fails and
     * the request will be refused.
     *
     * @param request	An http request that will be check for authentication
     * token to verify.
     * @return
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        List<GrantedAuthority> autoridades = new ArrayList<>();       
        String token = request.getHeader(tokenHandler.HEADER_STRING);

        if (token != null && token.startsWith(tokenHandler.TOKEN_PREFIX)) {
            // Parse the token.
            User user = null;
            AutenticacionTokens ejTokens = new AutenticacionTokens();
            try {
                inicializarTokenAuthenticationManager();
                
                user = tokenHandler.parse(token);
                
                ejTokens.setUsuario(user.getUsername());
                
                ejTokens = tokenAuthentication.get(ejTokens);
                
                String tokens = token.replace(tokenHandler.TOKEN_PREFIX, "").replace(" ", "");
                if(ejTokens.getAccessToken().compareToIgnoreCase(tokens) != 0){
                    user = null;
                }
                
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(TokenAuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            } else {
                return null;
            }

        }

        return null;

    }

    protected void inicializarTokenAuthenticationManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tokenAuthentication == null) {
            try {

                tokenAuthentication = (TokenAuthenticationManager) context.lookup("java:app/ComedorService-ejb/TokenAuthenticationManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }

}
