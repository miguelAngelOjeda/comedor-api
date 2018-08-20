/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Mensaje;
import py.com.coomecipar.service.ejb.manager.MensajeManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class MensajeManagerImpl extends GenericDaoImpl<Mensaje, Long>
		implements MensajeManager{
    @Override
    protected Class<Mensaje> getEntityBeanType() {
            return Mensaje.class;
    }
}
