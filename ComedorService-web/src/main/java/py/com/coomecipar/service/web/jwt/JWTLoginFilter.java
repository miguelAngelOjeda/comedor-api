package py.com.coomecipar.service.web.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.Context;
import org.slf4j.LoggerFactory;
import py.com.coomecipar.service.ejb.entity.UsuarioMensaje;
import py.com.coomecipar.service.web.jwtService.TokenAuthenticationService;
import py.com.coomecipar.service.web.jwtService.TokenAuthenticationServiceImpl;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private Context context;

    private final TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
    
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        // Retrieve username and password from the http request and save them in an Account object.
        UsuarioMensaje account = new ObjectMapper().readValue(req.getInputStream(), UsuarioMensaje.class);

        // Verify if the correctness of login details.
        // If correct, the successfulAuthentication() method is executed.
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        account.getUsuarioWin(),
                        account.getPassword(),
                        Collections.EMPTY_LIST
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        // Pass authenticated user data to the tokenAuthenticationService in order to add a JWT to the http response.
        
        tokenAuthenticationService.addAuthentication(res, auth);
    }
    

}
