/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.utils;

import java.util.List;


/**
 *
 * @author Fernando Invernizzi <fernando.invernizzi@konecta.com.py>
 */
public class GuardarPropuestaRequest {
    
    String tipo;
    String socio;
    String usuario;
    String password;
    String propuesta;
    String contrato;
    private String path;
    private String userSftp;
    private String passSftp;
    private String archivo;
    private String nombreArchivo;
    private String entidad;
    private String server;


    public GuardarPropuestaRequest() {
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
             
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the userSftp
     */
    public String getUserSftp() {
        return userSftp;
    }

    /**
     * @param userSftp the userSftp to set
     */
    public void setUserSftp(String userSftp) {
        this.userSftp = userSftp;
    }

    /**
     * @return the passSftp
     */
    public String getPassSftp() {
        return passSftp;
    }

    /**
     * @param passSftp the passSftp to set
     */
    public void setPassSftp(String passSftp) {
        this.passSftp = passSftp;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }    
}
