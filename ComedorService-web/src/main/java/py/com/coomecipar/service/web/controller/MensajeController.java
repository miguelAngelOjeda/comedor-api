/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.coomecipar.service.ejb.entity.DireccionesCorreos;
import py.com.coomecipar.service.ejb.entity.Mensaje;
import py.com.coomecipar.service.ejb.entity.UsuarioMensaje;
import py.com.coomecipar.service.ejb.utils.MensajeDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/mensajes")
public class MensajeController extends BaseController {

    String atributos = "id,usuario,nombre,apellido,email,documento,telefono,sexo,rol.id,rol.nombre,especialidad,departamento.id,departamento.area,"
            + "activo";

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    MensajeDTO activar(@ModelAttribute("usuario") String nombre) {
        MensajeDTO retorno = new MensajeDTO();
        List<String> atributoInicio = new ArrayList<>();
        atributoInicio.add("fechaMensaje");
        List<Object> valorInicio = new ArrayList<>();
        valorInicio.add(new Date(System.currentTimeMillis()));
        List<Mensaje> listMensaje = new ArrayList<>();
        try {
            logger.info("USUARIO: " + nombre);
            inicializarMensajeManager();
            inicializarUsuarioManager();
            inicializarDireccionesCorreosManager();

            UsuarioMensaje ejUsuaio = new UsuarioMensaje();
            ejUsuaio.setUsuarioWin(nombre);

            ejUsuaio = usuarioManager.get(ejUsuaio);

            if (ejUsuaio != null) {
                logger.info("-------MENSAJE-------");
                Mensaje ejMensaje = new Mensaje();
                ejMensaje.setUsuario(ejUsuaio);
                ejMensaje.setRecibido(false);

                List<Mensaje> mensajes = mensajeManager.list(ejMensaje, true, 0, 0, "id".split(","), "asc".split(","),
                        true, true, null, null, null, null, null, atributoInicio, valorInicio,
                        null, true, null, null);
                
                for(Mensaje rpm : mensajes){
                    File f = new File(rpm.getTipoMensaje().getPath());

                    rpm.setImagen(encodeFileToBase64Binary(f));
                    rpm.setFechaStrMensaje(dateFormatHHMM.format(mensajes.get(0).getFechaMensaje()));
                    
                    listMensaje.add(rpm);
                    
                }

            } else {
                String[] registro = nombre.replace(".", "_").split("_");

                DireccionesCorreos ejDireccionesCorreos = new DireccionesCorreos();
                ejDireccionesCorreos.setDireccionCorreo(nombre.replace(".", "_").replace("_", ".") + "@coomecipar.coop.py");

                List<DireccionesCorreos> direcciones = direccionesCorreosManager.list(ejDireccionesCorreos, true);

                ejUsuaio = new UsuarioMensaje();
                if (!direcciones.isEmpty()) {
                    ejUsuaio.setUsuario(direcciones.get(0).getUsuario());
                    ejUsuaio.setEmail(direcciones.get(0).getDireccionCorreo());

                }

                ejUsuaio.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejUsuaio.setUsuarioWin(nombre);
                ejUsuaio.setNombre(registro[0].toUpperCase());
                ejUsuaio.setApellido(registro[1].toUpperCase());

                usuarioManager.save(ejUsuaio);
                
                Mensaje ejMensaje = new Mensaje();
                ejMensaje.setMensaje("Bienvenido a la app de notificaciones.");
                
                listMensaje.add(ejMensaje);

            }
            
            retorno.setError(false);
            retorno.setMensajes(listMensaje);
            
        } catch (Exception e) {
            System.out.println("Error " + e);
            retorno.setError(true);
        }

        return retorno;

    }

    @RequestMapping(value = "/recibido", method = RequestMethod.GET)
    public @ResponseBody
    MensajeDTO recibido(@ModelAttribute("id") Long id, @ModelAttribute("tiempo") Long tiempo) {
        MensajeDTO retorno = new MensajeDTO();
        String nombre = "";
        long curTimeInMs = new Timestamp(System.currentTimeMillis()).getTime();        
        try {
            logger.info("-------CERRAR MENSAJE-------");
            logger.info("ID: " + id);
            logger.info("TIEMPO: " + tiempo);

            inicializarMensajeManager();

            Mensaje ejMensaje = mensajeManager.get(id);

            if (ejMensaje != null) {
                if (tiempo == 0) {
                    ejMensaje.setRecibido(true);
                    ejMensaje.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                } else {
                    
                    Timestamp afterAddingMins = new Timestamp(curTimeInMs + (tiempo * 60000));
                    
                    ejMensaje.setFechaMensaje(afterAddingMins);
                    ejMensaje.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                }

                mensajeManager.update(ejMensaje);
            }

            retorno.setError(false);

        } catch (Exception e) {
            System.out.println("Error " + e);
            retorno.setError(true);
        }

        return retorno;

    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}
