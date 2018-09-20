/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.DireccionesCorreos;
import py.com.coomecipar.service.ejb.manager.DireccionesCorreosManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class DireccionesCorreosManagerImpl extends GenericDaoImpl<DireccionesCorreos, Long>
		implements DireccionesCorreosManager{
    @Override
    protected Class<DireccionesCorreos> getEntityBeanType() {
            return DireccionesCorreos.class;
    }
}
