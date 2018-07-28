/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Usuario;
import py.com.coomecipar.service.ejb.manager.UsuarioManager;
import javax.ejb.Stateless;



/**
 *
 * @author Miguel
 */
@Stateless
public class UsuarioManagerImpl extends GenericDaoImpl<Usuario, Long>
		implements UsuarioManager{
        @Override
	protected Class<Usuario> getEntityBeanType() {
		return Usuario.class;
	}
        
        
}
