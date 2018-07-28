/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Ingrediente;
import py.com.coomecipar.service.ejb.manager.IngredienteManager;
import javax.ejb.Stateless;


/**
 *
 * @author Miguel
 */
@Stateless
public class IngredienteManagerImpl extends GenericDaoImpl<Ingrediente, Long>
		implements IngredienteManager{
    @Override
    protected Class<Ingrediente> getEntityBeanType() {
            return Ingrediente.class;
    }
}
