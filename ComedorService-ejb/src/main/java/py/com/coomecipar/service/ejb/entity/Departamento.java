/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento extends Base{
    
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_departamento_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotNull
    @NotEmpty
    @Column(name = "area")
    private String area;
    
    @NotNull
    @NotEmpty
    @Column(name = "area_detalle")
    private String areaDetalle;
    
    
    public Departamento() {
        
    }

    public Departamento(Long id) {
        this.id = id;
    }    

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the areaDetalle
     */
    public String getAreaDetalle() {
        return areaDetalle;
    }

    /**
     * @param areaDetalle the areaDetalle to set
     */
    public void setAreaDetalle(String areaDetalle) {
        this.areaDetalle = areaDetalle;
    }    
    
}
