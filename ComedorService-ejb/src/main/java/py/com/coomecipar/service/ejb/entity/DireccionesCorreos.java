/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "DIRECCIONES_CORREOS" , schema = "COOMECIPAR")
public class DireccionesCorreos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DIRECCION_CORREO")
    private String direccionCorreo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "NUMERO_LEGAJO")
    private BigDecimal numeroLegajo;
    @Column(name = "ALIAS")
    private String alias;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "JEFE")
    private String jefe;
    @Column(name = "LEGAJO_JEFATURA")
    private Integer legajoJefatura;
    @Column(name = "LEGAJO_GERENCIA")
    private Integer legajoGerencia;
    @Column(name = "RUTA_FIRMA_USUARIO")
    private String rutaFirmaUsuario;
//    @JoinColumn(name = "NUMERO_LEGAJO", referencedColumnName = "NUMERO_LEGAJO", insertable = false, updatable = false)
//    @OneToOne
//    private Funcionarios funcionarios;

    public DireccionesCorreos() {
    }

    public DireccionesCorreos(BigDecimal numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public DireccionesCorreos(BigDecimal numeroLegajo, String direccionCorreo) {
        this.numeroLegajo = numeroLegajo;
        this.direccionCorreo = direccionCorreo;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public BigDecimal getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(BigDecimal numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public Integer getLegajoJefatura() {
        return legajoJefatura;
    }

    public void setLegajoJefatura(Integer legajoJefatura) {
        this.legajoJefatura = legajoJefatura;
    }

    public Integer getLegajoGerencia() {
        return legajoGerencia;
    }

    public void setLegajoGerencia(Integer legajoGerencia) {
        this.legajoGerencia = legajoGerencia;
    }

    public String getRutaFirmaUsuario() {
        return rutaFirmaUsuario;
    }

    public void setRutaFirmaUsuario(String rutaFirmaUsuario) {
        this.rutaFirmaUsuario = rutaFirmaUsuario;
    }

//    public Funcionarios getFuncionarios() {
//        return funcionarios;
//    }
//
//    public void setFuncionarios(Funcionarios funcionarios) {
//        this.funcionarios = funcionarios;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLegajo != null ? numeroLegajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesCorreos)) {
            return false;
        }
        DireccionesCorreos other = (DireccionesCorreos) object;
        if ((this.numeroLegajo == null && other.numeroLegajo != null) || (this.numeroLegajo != null && !this.numeroLegajo.equals(other.numeroLegajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ncode.informconf.mavenproject1.DireccionesCorreos[ numeroLegajo=" + numeroLegajo + " ]";
    }
    
}
