/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.MenuDia;
import py.com.coomecipar.service.ejb.manager.MenuDiaManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class MenuDiaManagerImpl extends GenericDaoImpl<MenuDia, Long>
		implements MenuDiaManager{
    @Override
    protected Class<MenuDia> getEntityBeanType() {
            return MenuDia.class;
    }
}
