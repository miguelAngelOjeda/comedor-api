/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.managerImpl;


import py.com.coomecipar.service.ejb.entity.Departamento;
import javax.ejb.Stateless;
import py.com.coomecipar.service.ejb.manager.DepartamentoManager;



/**
 *
 * @author Miguel
 */
@Stateless
public class DepartamentoManagerImpl extends GenericDaoImpl<Departamento, Long>
		implements DepartamentoManager{
        @Override
	protected Class<Departamento> getEntityBeanType() {
		return Departamento.class;
	}
        
        
}
