/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.coomecipar.service.ejb.manager;



import javax.ejb.Local;
import py.com.coomecipar.service.ejb.entity.Rol;

/**
 *
 * @author Miguel
 */
@Local
public interface RolManager extends GenericDao<Rol, Long>{
    
}
