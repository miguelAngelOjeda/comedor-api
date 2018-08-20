///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package py.com.coomecipar.service.web.controller;
//
//
//import com.google.gson.Gson;
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Map;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import py.com.coomecipar.service.ejb.entity.Departamento;
//import py.com.coomecipar.service.ejb.entity.Rol;
//import py.com.coomecipar.service.ejb.entity.Usuario;
//import py.com.coomecipar.service.ejb.utils.RetornoDTO;
//import py.com.coomecipar.service.web.spring.User;
//import py.com.coomecipar.service.web.utils.FilterDTO;
//import py.com.coomecipar.service.web.utils.ReglaDTO;
//
///**
// *
// * @author miguel.ojeda
// */
//@Controller
//@RequestMapping(value = "/users")
//public class UsuarioController extends BaseController {
//
//    String atributos = "id,usuario,nombre,apellido,email,documento,telefono,sexo,rol.id,rol.nombre,especialidad,departamento.id,departamento.area,"
//            + "activo";
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody
//    RetornoDTO listar(@ModelAttribute("_search") boolean filtrar,
//            @ModelAttribute("filters") String filtros,
//            @ModelAttribute("page") Integer pagina,
//            @ModelAttribute("rows") Integer cantidad,
//            @ModelAttribute("sidx") String ordenarPor,
//            @ModelAttribute("sord") String sentidoOrdenamiento,
//            @ModelAttribute("todos") boolean todos) {
//
//        RetornoDTO retorno = new RetornoDTO();
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//
//        Usuario ejUsuario = new Usuario();
//        List<Map<String, Object>> listMapGrupos = null;
//        try {
//
//            inicializarUsuarioManager();
//
//            Gson gson = new Gson();
//            String camposFiltros = null;
//            String valorFiltro = null;
//
//            if (filtrar) {
//                FilterDTO filtro = gson.fromJson(filtros, FilterDTO.class);
//                if (filtro.getGroupOp().compareToIgnoreCase("OR") == 0) {
//                    for (ReglaDTO regla : filtro.getRules()) {
//                        if (camposFiltros == null) {
//                            camposFiltros = regla.getField();
//                            valorFiltro = regla.getData();
//                        } else {
//                            camposFiltros += "," + regla.getField();
//                        }
//                    }
//                } else {
//                    //ejemplo = generarEjemplo(filtro, ejemplo);
//                }
//
//            }
//            // ejemplo.setActivo("S");
//
//            pagina = pagina != null ? pagina : 1;
//            Long total = 0L;
//
//            if (!todos) {
//                total = Long.parseLong(usuarioManager.list(ejUsuario).size() + "");
//            }
//
//            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;
//
//            if (total < inicio) {
//                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
//                pagina = Integer.parseInt(total.toString()) / cantidad;
//            }
//
//            listMapGrupos = usuarioManager.listAtributos(ejUsuario, atributos.split(","), todos, inicio, cantidad,
//                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
//                    null, null, null, null, null, null, null, null, true);
//            
//            
//            for(Map<String, Object> rpm : listMapGrupos){
//                
//            }
//            
//            if (todos) {
//                total = Long.parseLong(listMapGrupos.size() + "");
//            }
//            Integer totalPaginas = Integer.parseInt(total.toString()) / cantidad;
//
//            retorno.setRecords(listMapGrupos.size());
//            retorno.setTotal(totalPaginas + 1);
//            retorno.setRows(listMapGrupos);
//            retorno.setPage(pagina);
//
//        } catch (Exception e) {
//
//            logger.error("Error al listar usuarios", e);
//        }
//
//        return retorno;
//    }
//
//    /**
//     * Mapping para el metodo POST de la vista crear. (crear Usuario)
//     *
//     * @param entidadRecibido entidad Usuario recibida de la vista
//     * @return
//     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public @ResponseBody
//    RetornoDTO crear(@ModelAttribute("Usuario") Usuario entidadRecibido) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO mensaje = new RetornoDTO();
//        Usuario ejUsuario = new Usuario();
//        try {
//            inicializarUsuarioManager();
//            
//            if (entidadRecibido != null && (entidadRecibido.getUsername() == null
//                    || entidadRecibido.getUsername().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo usuario no puede estar vacio.");
//                return mensaje;
//            }
//
//            if (entidadRecibido != null && (entidadRecibido.getNombre() == null
//                    || entidadRecibido.getNombre().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo nombre no puede estar vacio.");
//                return mensaje;
//            }
//
//            if (entidadRecibido != null && (entidadRecibido.getApellido() == null
//                    || entidadRecibido.getApellido().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo apellido no puede estar vacio.");
//                return mensaje;
//            }
//
//            if (entidadRecibido != null && (entidadRecibido.getEspecialidad() == null
//                    || entidadRecibido.getEspecialidad().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo especialidad no puede estar vacio.");
//                return mensaje;
//            }
//
//            if (entidadRecibido != null && (entidadRecibido.getDocumento() == null
//                    || entidadRecibido.getDocumento().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo documento no puede estar vacio.");
//                return mensaje;
//            }
//
//            if (entidadRecibido != null && (entidadRecibido.getRol() == null
//                    || entidadRecibido.getRol().getId().toString().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo rol no puede estar vacio.");
//                return mensaje;
//            }
//            
//            if (entidadRecibido != null && (entidadRecibido.getDepartamento() == null
//                    || entidadRecibido.getDepartamento().getId().toString().compareToIgnoreCase("") == 0)) {
//                mensaje.setError(true);
//                mensaje.setMensaje("El campo departmento no puede estar vacio.");
//                return mensaje;
//            }
//
//            ejUsuario.setUsername(entidadRecibido.getUsername());
//
//            Map<String, Object> desUsuario = usuarioManager.getLike(ejUsuario, "id".split(","));
//
//            if (desUsuario != null) {
//
//                mensaje.setError(true);
//                mensaje.setMensaje("Ya se encuentra un registro con el mismo usuario.");
//                return mensaje;
//
//            }
//
//            ejUsuario = new Usuario();
//            ejUsuario.setDocumento(entidadRecibido.getDocumento());
//
//            Map<String, Object> desEntidad = usuarioManager.getLike(ejUsuario, "id".split(","));
//
//            if (desEntidad != null) {
//
//                mensaje.setError(true);
//                mensaje.setMensaje("Ya se encuentra un registro con el mismo documento.");
//                return mensaje;
//
//            }
//
//            ejUsuario = new Usuario();
//            ejUsuario.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
//            ejUsuario.setActivo("S");
//            ejUsuario.setIdUsuarioCreacion(userDetail.getId());
//            ejUsuario.setApellido(entidadRecibido.getApellido());
//            ejUsuario.setDocumento(entidadRecibido.getDocumento());
//            ejUsuario.setEspecialidad(entidadRecibido.getEspecialidad());
//            ejUsuario.setEmail(entidadRecibido.getEmail());
//            ejUsuario.setNombre(entidadRecibido.getNombre());
//            ejUsuario.setRol(new Rol(entidadRecibido.getRol().getId()));
//            ejUsuario.setDepartamento(new Departamento(entidadRecibido.getDepartamento().getId()));
//            ejUsuario.setSexo(entidadRecibido.getSexo());
//            ejUsuario.setTelefono(entidadRecibido.getTelefono());
//            //ejUsuario.setUsuario(entidadRecibido.getUsuario());
//
//            usuarioManager.save(ejUsuario);
//
//
//            mensaje.setError(false);
//            mensaje.setMensaje("El usuario " + entidadRecibido.getUsername()+ " se creo exitosamente.");
//            return mensaje;
//
//        } catch (Exception e) {
//            mensaje.setError(true);
//            mensaje.setMensaje("Error al guardar el usuario.");
//            System.out.println("Error" + e);
//        }
//
//        return mensaje;
//    }
//
//}
