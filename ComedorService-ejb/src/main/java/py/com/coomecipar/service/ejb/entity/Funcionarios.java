/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "FUNCIONARIOS" , schema = "COOMECIPAR")
public class Funcionarios implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMERO_LEGAJO")
    private Integer numeroLegajo;
    @Basic(optional =     false)
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name =     "FECHA_EGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEgreso;
    @Basic(optional = false)
    @Column(name = "TIPO_FUNCIONARIO")
    private short tipoFuncionario;
    @Column(name = "ID_AHORRO")
    private String idAhorro;
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "REGIMEN_BONIFICACION")
    private Short regimenBonificacion;
    @Column(name = "CATEGORIA_HORARIA")
    private String categoriaHoraria;
    @Column(name = "ANTIG_ADIC_MESES")
    private Short antigAdicMeses;
    @Column(name = "TIPO_HORARIO")
    private String tipoHorario;
    @Column(name = "TIPO_CTA_PAGO")
    private String tipoCtaPago;
//    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "IDENTIFICADOR")
//    @ManyToOne(optional = false)
//    private Personas idPersona;
//    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
//    @ManyToOne
//    private Empresas idEmpresa;
//    @JoinColumns({
//        @JoinColumn(name = "DATO_MOTIVO_RETIRO", referencedColumnName = "TIPO_DATO"),
//        @JoinColumn(name = "NUMERO_MOTIVO_RETIRO", referencedColumnName = "NUMERO")})
//    @ManyToOne
//    private DatosMotivos datosMotivos;

    public Funcionarios() {
    }

    public Funcionarios(Integer numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public Funcionarios(Integer numeroLegajo, short tipoFuncionario, Date fechaIngreso) {
        this.numeroLegajo = numeroLegajo;
        this.tipoFuncionario = tipoFuncionario;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(Integer numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public short getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(short tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(String idAhorro) {
        this.idAhorro = idAhorro;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Short getRegimenBonificacion() {
        return regimenBonificacion;
    }

    public void setRegimenBonificacion(Short regimenBonificacion) {
        this.regimenBonificacion = regimenBonificacion;
    }

    public String getCategoriaHoraria() {
        return categoriaHoraria;
    }

    public void setCategoriaHoraria(String categoriaHoraria) {
        this.categoriaHoraria = categoriaHoraria;
    }

    public Short getAntigAdicMeses() {
        return antigAdicMeses;
    }

    public void setAntigAdicMeses(Short antigAdicMeses) {
        this.antigAdicMeses = antigAdicMeses;
    }

    public String getTipoHorario() {
        return tipoHorario;
    }

    public void setTipoHorario(String tipoHorario) {
        this.tipoHorario = tipoHorario;
    }

    public String getTipoCtaPago() {
        return tipoCtaPago;
    }

    public void setTipoCtaPago(String tipoCtaPago) {
        this.tipoCtaPago = tipoCtaPago;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLegajo != null ? numeroLegajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionarios)) {
            return false;
        }
        Funcionarios other = (Funcionarios) object;
        if ((this.numeroLegajo == null && other.numeroLegajo != null) || (this.numeroLegajo != null && !this.numeroLegajo.equals(other.numeroLegajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.ncode.informconf.mavenproject2.Funcionarios[ numeroLegajo=" + numeroLegajo + " ]";
    }

}
