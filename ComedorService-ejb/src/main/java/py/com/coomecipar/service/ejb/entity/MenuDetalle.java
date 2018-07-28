/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

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
import javax.persistence.Transient;

/**
 *
 * @author mojeda
 */
@Entity
@Table(name = "MENU_DETALLE")
public class MenuDetalle extends Base {

    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_menu_detalle_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_dia", referencedColumnName = "id")
    private MenuDia menuDia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "OPCION")
    private Long opcion;

    public MenuDetalle() {
    }

    /**
     * @param id el id de Rol
     */
    public MenuDetalle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the opcion
     */
    public Long getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(Long opcion) {
        this.opcion = opcion;
    }
    
    
}
