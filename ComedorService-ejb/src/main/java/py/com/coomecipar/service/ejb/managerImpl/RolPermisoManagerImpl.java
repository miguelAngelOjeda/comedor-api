/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.RolPermiso;
import py.com.coomecipar.service.ejb.manager.RolPermisoManager;
import javax.ejb.Stateless;




/**
 *
 * @author Miguel
 */
@Stateless
public class RolPermisoManagerImpl extends GenericDaoImpl<RolPermiso, Long>
		implements RolPermisoManager{
    @Override
    protected Class<RolPermiso> getEntityBeanType() {
            return RolPermiso.class;
    }
}
