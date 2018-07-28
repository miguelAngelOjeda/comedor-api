/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;


import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *
 * @author mojeda
 */
@Entity
@Table(name = "TRANSACCION_DETALLE")
public class TransaccionDetalle extends Base {
       
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_transaccion_detalle_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "transaccion", referencedColumnName = "id")
    private Transaccion transaccion;  
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "producto", referencedColumnName = "id")
    private Producto producto;
    
    @NotNull
    @Column(name = "menu_fecha")
    private Date menuFecha;
    

    public TransaccionDetalle() {
    }

    /**
     * @param id el id de Rol
     */
    public TransaccionDetalle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

            
}
