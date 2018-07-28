/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 *
 * @author Miguel
 */
@MappedSuperclass
public class Base implements Serializable {

    private static final long serialVersionUID = -9149680520407250259L;

    @Size(max = 1)
    @Column(name = "activo")
    private String activo;

    @Column(name = "fecha_Creacion")
    private Timestamp fechaCreacion;

    @Column(name = "cod_Usuario_Creacion")
    private Long idUsuarioCreacion;

    @Column(name = "fecha_Modificacion")
    private Timestamp fechaModificacion;

    @Column(name = "cod_Usuario_Modificacion")
    private Long idUsuarioModificacion;

    @Column(name = "fecha_Eliminacion")
    private Timestamp fechaEliminacion;

    @Column(name = "cod_Usuario_Eliminacion")
    private Long idUsuarioEliminacion;

    public Base() {

    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getIdUsuarioModificacion() {
        return idUsuarioModificacion;
    }

    public void setIdUsuarioModificacion(Long idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Timestamp getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Timestamp fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public Long getIdUsuarioEliminacion() {
        return idUsuarioEliminacion;
    }

    public void setIdUsuarioEliminacion(Long idUsuarioEliminacion) {
        this.idUsuarioEliminacion = idUsuarioEliminacion;
    }

}
