/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.MenuDetalle;
import py.com.coomecipar.service.ejb.manager.MenuDetalleManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class MenuDetalleManagerImpl extends GenericDaoImpl<MenuDetalle, Long>
		implements MenuDetalleManager{
    @Override
    protected Class<MenuDetalle> getEntityBeanType() {
            return MenuDetalle.class;
    }
}
