/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.coomecipar.service.ejb.manager;


import py.com.coomecipar.service.ejb.entity.Departamento;
import javax.ejb.Local;

/**
 *
 * @author Miguel
 */
@Local
public interface DepartamentoManager extends GenericDao<Departamento, Long>{
    
}
