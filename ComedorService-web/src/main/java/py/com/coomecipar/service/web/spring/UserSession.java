/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.spring;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author Miguel
 */
public class UserSession implements AuthenticationProvider {

    private Context context;


    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserSession.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> autoridades = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            
            String userLogin = authentication.getPrincipal().toString();
            String passwordLogin = authentication.getCredentials().toString();

//            UsuarioLogin usuario = usuarioManager.findByUserPass(userLogin, passwordLogin);
//
//            if (usuario != null) {
//                
//                Tokens ejTokens = new Tokens();
//                ejTokens.setUsuario(userLogin);
//                
//                ejTokens = tokensManager.get(ejTokens);
//                
//                if(ejTokens != null){
//                    tokensManager.delete(ejTokens.getId());
//                }
                
                User userDetails = new User();
//                userDetails.setId(usuario.getId());
//                userDetails.setUsername(userLogin);
//                userDetails.setPassword(passwordLogin);
//                userDetails.setNombre(usuario.getNombre());
//                userDetails.setApellido(usuario.getApellido());
//                userDetails.setEmail(usuario.getEmail());
//                userDetails.setNombreRol(usuario.getRol());
                
                Authentication customAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                        passwordLogin, autoridades);
                
                return customAuthentication;
//            } else {
//                System.out.println("Usuario o Contraseña Inválidos.");
//                throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
//            }

        } catch (Exception ex) {
            log.error("Error en el login " + ex);
            throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    
}
