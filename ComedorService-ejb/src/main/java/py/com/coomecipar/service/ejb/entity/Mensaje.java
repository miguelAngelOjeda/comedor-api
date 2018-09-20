/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.entity;


import java.sql.Timestamp;
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
@Table(name = "MENSAJE", schema = "COOMECIPAR")
public class Mensaje extends Base {
       
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "COOMECIPAR.seq_mensaje_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;   
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "APELLIDO")
    private String apellido;    
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "MENSAJE")
    private String mensaje;
    
    @Column(name = "RECIBIDO")
    private Boolean recibido;
    
    @Column(name = "fecha_Mensaje")
    private Timestamp fechaMensaje;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "USUARIO_MENSAJE", referencedColumnName = "id")
    private UsuarioMensaje usuario;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_MENSAJE", referencedColumnName = "id")
    private TipoMensaje tipoMensaje;
    
    @Transient
    private String imagen;
    
    @Transient
    private String fechaStrMensaje;
       

    public Mensaje() {
    }

    /**
     * @param id el id de Rol
     */
    public Mensaje(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public UsuarioMensaje getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioMensaje usuario) {
        this.usuario = usuario;
    }    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the recibido
     */
    public Boolean getRecibido() {
        return recibido;
    }

    /**
     * @param recibido the recibido to set
     */
    public void setRecibido(Boolean recibido) {
        this.recibido = recibido;
    }

    /**
     * @return the tipoMensaje
     */
    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * @param tipoMensaje the tipoMensaje to set
     */
    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public Timestamp getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(Timestamp fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }    

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaStrMensaje() {
        return fechaStrMensaje;
    }

    public void setFechaStrMensaje(String fechaStrMensaje) {
        this.fechaStrMensaje = fechaStrMensaje;
    }
    
            
}
