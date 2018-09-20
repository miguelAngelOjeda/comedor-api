/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.stereotype.Controller;
import py.com.coomecipar.service.ejb.manager.DireccionesCorreosManager;
import py.com.coomecipar.service.ejb.manager.MensajeManager;
import py.com.coomecipar.service.ejb.manager.UsuarioMensajeManager;
//import py.com.coomecipar.service.ejb.manager.PermisoManager;
//import py.com.coomecipar.service.ejb.manager.RolManager;
//import py.com.coomecipar.service.ejb.manager.RolPermisoManager;
//import py.com.coomecipar.service.ejb.manager.TokenAuthenticationManager;
//import py.com.coomecipar.service.ejb.manager.UsuarioManager;


/**
 *
 * @author Miguel
 */
@Controller
public class BaseController {

    private Context context;
    public static final Logger logger = LoggerFactory.getLogger("COMEDOR");
    
    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static final DateFormat dateFormatHHMM = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    protected static final DecimalFormat numFormat = new DecimalFormat("#,###,###");
     
    
//    protected TokenAuthenticationManager tokenAuthenticationManager;
    protected UsuarioMensajeManager usuarioManager;
//    protected RolManager rolManager;
//    protected PermisoManager permisoManager;
//    protected RolPermisoManager rolPermisoManager;
    protected MensajeManager mensajeManager;
    protected DireccionesCorreosManager direccionesCorreosManager;
    
    
    
    protected void inicializarMensajeManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (mensajeManager == null) {
            try {

                mensajeManager = (MensajeManager) context.lookup("java:app/ComedorService-ejb/MensajeManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarDireccionesCorreosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (direccionesCorreosManager == null) {
            try {

                direccionesCorreosManager = (DireccionesCorreosManager) context.lookup("java:app/ComedorService-ejb/DireccionesCorreosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    
//    protected void inicializarTokensManager() throws Exception {
//        if (context == null) {
//            try {
//                context = new InitialContext();
//            } catch (NamingException e1) {
//                throw new RuntimeException("No se puede inicializar el contexto", e1);
//            }
//        }
//        if (tokenAuthenticationManager == null) {
//            try {
//
//                tokenAuthenticationManager = (TokenAuthenticationManager) context.lookup("java:app/ComedorService-ejb/TokenAuthenticationManagerImpl");
//            } catch (NamingException ne) {
//                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
//            }
//        }
//    }
    
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


//    protected void inicializarRolManager() throws Exception {
//        if (context == null) {
//            try {
//                context = new InitialContext();
//            } catch (NamingException e1) {
//                throw new RuntimeException("No se puede inicializar el contexto", e1);
//            }
//        }
//        if (rolManager == null) {
//            try {
//
//                rolManager = (RolManager) context.lookup("java:app/ComedorService-ejb/RolManagerImpl");
//            } catch (NamingException ne) {
//                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
//            }
//        }
//    }
//
//    protected void inicializarPermisoManager() throws Exception {
//        if (context == null) {
//            try {
//                context = new InitialContext();
//            } catch (NamingException e1) {
//                throw new RuntimeException("No se puede inicializar el contexto", e1);
//            }
//        }
//        if (permisoManager == null) {
//            try {
//
//                permisoManager = (PermisoManager) context.lookup("java:app/ComedorService-ejb/PermisoManagerImpl");
//            } catch (NamingException ne) {
//                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
//            }
//        }
//
//    }
//    
//    protected void inicializarRolPermisoManager() throws Exception {
//        if (context == null) {
//            try {
//                context = new InitialContext();
//            } catch (NamingException e1) {
//                throw new RuntimeException("No se puede inicializar el contexto", e1);
//            }
//        }
//        if (rolPermisoManager == null) {
//            try {
//
//                rolPermisoManager = (RolPermisoManager) context.lookup("java:app/ComedorService-ejb/RolPermisoManagerImpl");
//            } catch (NamingException ne) {
//                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
//            }
//        }
//    }
//        
    
}
