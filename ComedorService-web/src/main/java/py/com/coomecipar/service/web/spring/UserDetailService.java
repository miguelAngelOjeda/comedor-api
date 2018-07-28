package py.com.coomecipar.service.web.spring;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private Context context;
//    private UsuarioManager usuarioManager;
//    protected FuncionariosManager funcionariosManager;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    public static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("asesores");

    @Override
    public User loadUserByUsername(String idUser) throws UsernameNotFoundException {
        User user = new User();
        try {
        } catch (Exception ex) {
            Logger.getLogger(UserDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Personas ejPersona = new Personas();
//        ejPersona.setIdentificador(Integer.parseInt(idUser));
//
//        Funcionarios ejFuncionarios = new Funcionarios();
//        ejFuncionarios.setIdPersona(ejPersona);
//        // Fetch the account corresponding to the given username
//        ejFuncionarios = funcionariosManager.get(ejFuncionarios);
//
//        // If the account doesn't exist
//        if (ejFuncionarios == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
        
//        user.setNombre(ejFuncionarios.getIdPersona().getPrimerNombre());
//        user.setApellido(ejFuncionarios.getIdPersona().getPrimerApellido());
//        user.setEmail(idUser);
//        user.setNombreRol(idUser);
//        user.setId(Long.parseLong(ejFuncionarios.getIdPersona().getIdentificador()+""));
//        user.setRol(idUser);
//        user.setUsername(ejFuncionarios.getNombreUsuario());
//        user.setAuthorities(Collections.EMPTY_LIST);

        detailsChecker.check(user);

        return user;

    }

   

}
