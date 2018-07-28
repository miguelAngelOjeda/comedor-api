/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "ROL_PERMISO")
public class RolPermiso{
    
    private static final long serialVersionUID = 7986185608L;
    private static final String SECUENCIA = "seq_rol_permiso_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "permiso", referencedColumnName = "id")
    private Permiso permiso; 
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "rol", referencedColumnName = "id")
    private Rol rol;
    
    @Transient
    private String idRol;
    
    @Transient
    private List<String> permisos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       

    /**
     * @return the permiso
     */
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * @param permiso the permiso to set
     */
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    

    /**
     * @return the permisos
     */
    public List<String> getPermisos() {
        return permisos;
    }

    /**
     * @param permisos the permisos to set
     */
    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }
    
    
}
