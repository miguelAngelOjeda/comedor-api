/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.controller;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.coomecipar.service.ejb.entity.Departamento;
import py.com.coomecipar.service.ejb.entity.Mensaje;
import py.com.coomecipar.service.ejb.entity.Rol;
import py.com.coomecipar.service.ejb.entity.Usuario;
import py.com.coomecipar.service.ejb.utils.MensajeDTO;
import py.com.coomecipar.service.ejb.utils.RetornoDTO;
import py.com.coomecipar.service.web.spring.User;
import py.com.coomecipar.service.web.utils.FilterDTO;
import py.com.coomecipar.service.web.utils.ReglaDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
public class MensajeController extends BaseController {

    String atributos = "id,usuario,nombre,apellido,email,documento,telefono,sexo,rol.id,rol.nombre,especialidad,departamento.id,departamento.area,"
            + "activo";

    @RequestMapping(value = "/mensaje/{usuario}", method = RequestMethod.GET)
    public @ResponseBody
    MensajeDTO activar(@PathVariable("usuario") String nombre) {
        MensajeDTO retorno = new MensajeDTO();
        try {
            inicializarMensajeManager();

            Mensaje ejMensaje = new Mensaje();
            ejMensaje.setUsername(nombre);
            ejMensaje.setRecibido(false);

            List<Mensaje> mensajes = mensajeManager.list(ejMensaje);

            if (!mensajes.isEmpty()) {
                retorno.setError(false);
                retorno.setMensaje(mensajes.get(0).getMensaje());
                retorno.setId(mensajes.get(0).getId());
            } else {
                retorno.setError(false);
                retorno.setMensaje(null);
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
            retorno.setError(true);
            retorno.setMensaje("Error al tratar de enviar el mensaje.");
        }

        return retorno;

    }

    @RequestMapping(value = "/mensaje/recibido/{id}", method = RequestMethod.GET)
    public @ResponseBody
    MensajeDTO recibido(@PathVariable("id") Long id) {
        MensajeDTO retorno = new MensajeDTO();
        String nombre = "";
        try {
            inicializarMensajeManager();

            Mensaje ejMensaje = mensajeManager.get(id);

            if (ejMensaje != null) {
                ejMensaje.setRecibido(true);
                mensajeManager.update(ejMensaje);
            }

            retorno.setError(false);
            retorno.setMensaje("Mensaje recibido.");

        } catch (Exception e) {
            System.out.println("Error " + e);
            retorno.setError(true);
            retorno.setMensaje("Error al tratar de activar el rol.");
        }

        return retorno;

    }

}
