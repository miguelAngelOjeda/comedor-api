/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.UsuarioMensaje;
import py.com.coomecipar.service.ejb.manager.UsuarioMensajeManager;
import javax.ejb.Stateless;



/**
 *
 * @author Miguel
 */
@Stateless
public class UsuarioMensajeManagerImpl extends GenericDaoImpl<UsuarioMensaje, Long>
		implements UsuarioMensajeManager{
        @Override
	protected Class<UsuarioMensaje> getEntityBeanType() {
		return UsuarioMensaje.class;
	}

    @Override
    public UsuarioMensaje findByUserPass(String username, String pass) {
        UsuarioMensaje usuario = new UsuarioMensaje();
        try {
            usuario.setApellido("OJEDA");
            usuario.setEmail("lanymicole@gmail.com");
            usuario.setNombre("MIGUEL");
            usuario.setSexo("MASCULINO");
            usuario.setUsername("MOJEDA");
            
        } catch (Exception e) {
        }
            return usuario;
    }
        
        
}
