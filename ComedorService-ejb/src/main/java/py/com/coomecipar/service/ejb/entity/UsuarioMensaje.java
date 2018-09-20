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
@Table(name = "USUARIO_MENSAJE", schema = "COOMECIPAR")
public class UsuarioMensaje extends Base {
       
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "COOMECIPAR.seq_usuario_mensaje_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "USUARIO_WIN")
    private String usuarioWin;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "CLAVE_ACCESO")
    private String password;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "APELLIDO")
    private String apellido;
    
    @Column(name = "ESPECIALIDAD")
    private String especialidad;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "DOCUMENTO")
    private String documento;
    
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Column(name = "SEXO")
    private String sexo;
    
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "rol", referencedColumnName = "id")
//    private Rol rol;
//    
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "departamento", referencedColumnName = "id")
//    private Departamento departamento;


    public UsuarioMensaje() {
    }

    /**
     * @param id el id de Rol
     */
    public UsuarioMensaje(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

//    /**
//     * @return the rol
//     */
//    public Rol getRol() {
//        return rol;
//    }
//
//    /**
//     * @param rol the rol to set
//     */
//    public void setRol(Rol rol) {
//        this.rol = rol;
//    }

    
    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getUsuarioWin() {
        return usuarioWin;
    }

    public void setUsuarioWin(String usuarioWin) {
        this.usuarioWin = usuarioWin;
    }

    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the username
     */
    public void setUsuario(String usuario) {    
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
            
}
