/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.utils;

import java.util.List;

import py.com.coomecipar.service.ejb.entity.AutenticacionTokens;
import py.com.coomecipar.service.web.spring.User;
/**
 *
 * @author mojeda
 */
public class LoginResponse {
    
    private AutenticacionTokens token;
    
    private User usuario;

    /**
     * @return the token
     */
    public AutenticacionTokens getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(AutenticacionTokens token) {
        this.token = token;
    }

    /**
     * @return the usuario
     */
    public User getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    
    
    
}
