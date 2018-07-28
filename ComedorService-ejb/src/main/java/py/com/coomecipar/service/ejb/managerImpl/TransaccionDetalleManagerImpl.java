/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.TransaccionDetalle;
import py.com.coomecipar.service.ejb.manager.TransaccionDetalleManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class TransaccionDetalleManagerImpl extends GenericDaoImpl<TransaccionDetalle, Long>
		implements TransaccionDetalleManager{
    @Override
    protected Class<TransaccionDetalle> getEntityBeanType() {
            return TransaccionDetalle.class;
    }
}
