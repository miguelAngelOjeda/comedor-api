/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.AutenticacionTokens;
import py.com.coomecipar.service.ejb.manager.TokenAuthenticationManager;
import javax.ejb.Stateless;



/**
 *
 * @author Miguel
 */
@Stateless
public class TokenAuthenticationManagerImpl extends GenericDaoImpl<AutenticacionTokens, Long>
		implements TokenAuthenticationManager{
        @Override
	protected Class<AutenticacionTokens> getEntityBeanType() {
		return AutenticacionTokens.class;
	}

        
        
}
