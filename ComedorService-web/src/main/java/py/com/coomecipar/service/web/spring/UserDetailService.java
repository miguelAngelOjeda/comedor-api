package py.com.coomecipar.service.web.spring;



import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import py.com.coomecipar.service.ejb.entity.UsuarioMensaje;
import py.com.coomecipar.service.ejb.manager.UsuarioMensajeManager;


@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private Context context;
    private UsuarioMensajeManager usuarioManager;
//    protected FuncionariosManager funcionariosManager;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    public static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("asesores");

    @Override
    public User loadUserByUsername(String idUser) throws UsernameNotFoundException {
        User user = new User();
        try {
            inicializarUsuarioManager();
        } catch (Exception ex) {
            Logger.getLogger(UserDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        UsuarioMensaje ejPersona = new UsuarioMensaje();
        ejPersona.setId(Long.parseLong(idUser));

        // Fetch the account corresponding to the given username
        ejPersona = usuarioManager.get(ejPersona);

        // If the account doesn't exist
        if (ejPersona == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        user.setNombre(ejPersona.getNombre());
        user.setApellido(ejPersona.getApellido());
        user.setEmail(ejPersona.getEmail());
        //user.setNombreRol(ejPersona.getRol().getNombre());
        user.setId(ejPersona.getId());
        //user.setRol(ejPersona.getRol().getId()+"");
        user.setUsername(ejPersona.getUsuarioWin());
        user.setAuthorities(Collections.EMPTY_LIST);

        detailsChecker.check(user);

        return user;

    }

    protected void inicializarUsuarioManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (usuarioManager == null) {
            try {

                usuarioManager = (UsuarioMensajeManager) context.lookup("java:app/ComedorService-ejb/UsuarioMensajeManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }

}
