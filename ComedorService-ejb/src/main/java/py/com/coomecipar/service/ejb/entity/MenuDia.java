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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author mojeda
 */
@Entity
@Table(name = "MENU_DIA")
public class MenuDia extends Base {
       
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_menu_dia_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_menu", referencedColumnName = "id")
    private TipoMenu tipoMenu;
    
    @NotNull
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "COMBO")
    private Boolean combo;
        


    public MenuDia() {
    }

    /**
     * @param id el id de Rol
     */
    public MenuDia(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    /**
     * @return the tipoMenu
     */
    public TipoMenu getTipoMenu() {
        return tipoMenu;
    }

    /**
     * @param tipoMenu the tipoMenu to set
     */
    public void setTipoMenu(TipoMenu tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    /**
     * @return the combo
     */
    public Boolean getCombo() {
        return combo;
    }

    /**
     * @param combo the combo to set
     */
    public void setCombo(Boolean combo) {
        this.combo = combo;
    }

    
            
}
