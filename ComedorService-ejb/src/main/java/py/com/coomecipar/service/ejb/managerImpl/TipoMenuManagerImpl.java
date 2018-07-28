/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.TipoMenu;
import py.com.coomecipar.service.ejb.manager.TipoMenuManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoMenuManagerImpl extends GenericDaoImpl<TipoMenu, Long>
		implements TipoMenuManager{
    @Override
    protected Class<TipoMenu> getEntityBeanType() {
            return TipoMenu.class;
    }
}
