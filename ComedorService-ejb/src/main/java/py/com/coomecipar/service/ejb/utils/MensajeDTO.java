/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.ejb.utils;

import java.util.List;

/**
 *
 * @author Miguel
 */
public class MensajeDTO<T> {     
    
    private boolean error;
    
    private List<T> mensajes;
    
    public MensajeDTO() {
            super();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<T> mensajes) {
        this.mensajes = mensajes;
    }

    
}
