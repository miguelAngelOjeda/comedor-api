/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Transaccion;
import py.com.coomecipar.service.ejb.manager.TransaccionManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class TransaccionManagerImpl extends GenericDaoImpl<Transaccion, Long>
		implements TransaccionManager{
    @Override
    protected Class<Transaccion> getEntityBeanType() {
            return Transaccion.class;
    }
}
