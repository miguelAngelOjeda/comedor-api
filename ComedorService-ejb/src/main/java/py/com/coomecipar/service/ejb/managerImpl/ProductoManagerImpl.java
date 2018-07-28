/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Producto;
import py.com.coomecipar.service.ejb.manager.ProductoManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class ProductoManagerImpl extends GenericDaoImpl<Producto, Long>
		implements ProductoManager{
    @Override
    protected Class<Producto> getEntityBeanType() {
            return Producto.class;
    }
}
