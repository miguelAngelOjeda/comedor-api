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
@Table(name = "TRANSACCION")
public class Transaccion extends Base {
       
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_transaccion_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_dia", referencedColumnName = "id")
    private MenuDia menuDia;
    
    @NotNull
    @Column(name = "menu_fecha")
    private Date menuFecha;
    

    public Transaccion() {
    }

    /**
     * @param id el id de Rol
     */
    public Transaccion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the menuDia
     */
    public MenuDia getMenuDia() {
        return menuDia;
    }

    /**
     * @param menuDia the menuDia to set
     */
    public void setMenuDia(MenuDia menuDia) {
        this.menuDia = menuDia;
    }

    /**
     * @return the menuFecha
     */
    public Date getMenuFecha() {
        return menuFecha;
    }

    /**
     * @param menuFecha the menuFecha to set
     */
    public void setMenuFecha(Date menuFecha) {
        this.menuFecha = menuFecha;
    }   
            
}
