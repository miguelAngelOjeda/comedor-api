/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.TipoProducto;
import py.com.coomecipar.service.ejb.manager.TipoProductoManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoProductoManagerImpl extends GenericDaoImpl<TipoProducto, Long>
		implements TipoProductoManager{
    @Override
    protected Class<TipoProducto> getEntityBeanType() {
            return TipoProducto.class;
    }
}
