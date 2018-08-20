///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package py.com.coomecipar.service.web.controller;
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
////import com.sistem.proyecto.utils.DatosDTO;
////import com.sistem.proyecto.utils.MensajeDTO;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import py.com.coomecipar.service.ejb.entity.Permiso;
//import py.com.coomecipar.service.ejb.entity.Rol;
//import py.com.coomecipar.service.ejb.entity.RolPermiso;
//import py.com.coomecipar.service.ejb.utils.RetornoDTO;
//import py.com.coomecipar.service.web.spring.User;
//import py.com.coomecipar.service.web.utils.FilterDTO;
//import py.com.coomecipar.service.web.utils.ReglaDTO;
//
//
///**
// *
// * @author daniel
// */
//@Controller
//@RequestMapping(value = "/roles")
//public class RolController extends BaseController {
//
//    String atributos = "id,nombre,activo";
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
//        Rol ejRol = new Rol();
//        List<Map<String, Object>> listMapGrupos = null;
//        try {
//            inicializarRolManager();
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
//            Integer total = 0;
//
//            if (!todos) {
//                total = rolManager.list(ejRol, true).size();
//            }
//
//            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;
//
//            if (total < inicio) {
//                inicio = total - total % cantidad;
//                pagina = total / cantidad;
//            }
//
//            listMapGrupos = rolManager.listAtributos(ejRol, atributos.split(","), todos, inicio, cantidad,
//                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
//                    null, null, null, null, null, null, null, null, true);
//
//            if (todos) {
//                total = listMapGrupos.size();
//            }
//            Integer totalPaginas = total / cantidad;
//
//            retorno.setRecords(listMapGrupos.size());
//            retorno.setTotal(totalPaginas + 1);
//            retorno.setRows(listMapGrupos);
//            retorno.setPage(pagina);
//
//        } catch (Exception e) {
//
//            logger.error("Error al listar servicios ", e);
//        }
//
//        return retorno;
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public @ResponseBody
//    RetornoDTO guardar(@ModelAttribute("Rol") Rol rolRecibido) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO retorno = new RetornoDTO();
//        Rol rol = new Rol();
//        try {
//            inicializarRolManager();
//            
//            if (rolRecibido.getNombre() == null || rolRecibido.getNombre() != null
//                    && rolRecibido.getNombre().compareToIgnoreCase("") == 0) {
//                retorno.setError(true);
//                retorno.setMensaje("El campo nombre no puede estar vacio.");
//                return retorno;
//            }
//            rol.setNombre(rolRecibido.getNombre());
//            
//            Map<String, Object> desEntidad = rolManager.getLike(rol, "id".split(","));
//
//            if (desEntidad != null && !desEntidad.isEmpty()
//                    && desEntidad.get("id")
//                            .toString().compareToIgnoreCase(rolRecibido.getId().toString()) != 0) {
//
//                retorno.setError(true);
//                retorno.setMensaje("Ya se encuentra registrada un rol con el mismo nombre.");
//                return retorno;
//
//            }
//
//            rol.setActivo("S");
//            rol.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
//            rol.setIdUsuarioCreacion(userDetail.getId());
//            rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//
//            rolManager.save(rol);
//
//            retorno.setMensaje("El rol " + rolRecibido.getNombre() + " se creo exitosamente.");
//            return retorno;
//
//        } catch (Exception ex) {
//            System.out.println("Error " + ex);
//            retorno.setError(true);
//            retorno.setMensaje("Error al crear el registro.");
//
//        }
//        return retorno;
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public @ResponseBody
//    RetornoDTO editar(@ModelAttribute("Rol") Rol rolRecibido) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO retorno = new RetornoDTO();
//        try {
//            inicializarRolManager();
//
//            if (rolRecibido.getNombre() == null || rolRecibido.getNombre() != null
//                    && rolRecibido.getNombre().compareToIgnoreCase("") == 0) {
//                retorno.setError(true);
//                retorno.setMensaje("El campo nombre no puede estar vacio.");
//                return retorno;
//            }
//            
//            Rol ejRol = new Rol();
//            ejRol.setNombre(rolRecibido.getNombre());
//            Map<String, Object> desEntidad = rolManager.getLike(ejRol, "id".split(","));
//
//            if (desEntidad != null && !desEntidad.isEmpty()
//                    && desEntidad.get("id")
//                            .toString().compareToIgnoreCase(rolRecibido.getId().toString()) != 0) {
//
//                retorno.setError(true);
//                retorno.setMensaje("Ya se encuentra registrada un rol con el mismo nombre.");
//                return retorno;
//
//            }
//
//            Rol rol = rolManager.get(rolRecibido.getId());
//            rol.setNombre(rolRecibido.getNombre());
//            rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//            rol.setIdUsuarioModificacion(userDetail.getId());
//            rol.setNombre(rolRecibido.getNombre());
//
//            rolManager.update(rol);
//
//            retorno.setError(false);
//            retorno.setMensaje("El rol " +rolRecibido.getNombre()+" se modifico exitosamente.");
//            return retorno;
//
//        } catch (Exception ex) {
//            System.out.println("Error " + ex);
//            retorno.setError(true);
//            retorno.setMensaje("Error al tratar de modificar el registro.");
//
//        }
//        return retorno;
//    }
//
//    @RequestMapping(value = "/act/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    RetornoDTO activar(@PathVariable("id") Long id) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO retorno = new RetornoDTO();
//        String nombre = "";
//        try {
//            inicializarRolManager();
//
//            Rol rol = rolManager.get(id);
//
//            if (rol != null) {
//                nombre = rol.getNombre().toString();
//            }
//
//            if (rol != null && rol.getActivo().toString()
//                    .compareToIgnoreCase("S") == 0) {
//                retorno.setError(true);
//                retorno.setMensaje("El rol " + nombre + " ya se encuentra activada.");
//                return retorno;
//            }
//
//            rol.setActivo("S");
//            rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//            rol.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));
//            rol.setIdUsuarioModificacion(userDetail.getId());
//
//            rolManager.update(rol);
//
//            retorno.setError(false);
//            retorno.setMensaje("El rol " + nombre + " se activo exitosamente.");
//
//        } catch (Exception e) {
//            System.out.println("Error " + e);
//            retorno.setError(true);
//            retorno.setMensaje("Error al tratar de activar el rol.");
//        }
//
//        return retorno;
//
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    RetornoDTO desactivar(@PathVariable("id") Long id, Model model) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO retorno = new RetornoDTO();
//        String nombre = "";
//        try {
//            inicializarRolManager();
//
//            Rol rol = rolManager.get(id);
//
//            if (rol != null) {
//                nombre = rol.getNombre().toString();
//            }
//
//            if (rol != null && rol.getActivo().toString()
//                    .compareToIgnoreCase("N") == 0) {
//                retorno.setError(true);
//                retorno.setMensaje("El rol " + nombre + " ya se encuentra desactivada.");
//                return retorno;
//            }
//
//            rol.setActivo("N");
//            rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//            rol.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));
//            rol.setIdUsuarioEliminacion(userDetail.getId());
//
//            rolManager.update(rol);
//
//            retorno.setError(false);
//            retorno.setMensaje("El rol " + nombre + " se desactivo exitosamente.");
//
//        } catch (Exception e) {
//            System.out.println("Error " + e);
//            retorno.setError(true);
//            retorno.setMensaje("Error al tratar de desactivar el rol.");
//        }
//        return retorno;
//
//    }
//
//    @RequestMapping(value = "/add/{id}/permisos", method = RequestMethod.PUT, produces = "application/json")
//    public @ResponseBody
//    RetornoDTO asignarPermisos(@RequestBody String peticionStr,@PathVariable("id") Long id) {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        RetornoDTO retorno = new RetornoDTO();
//        String nombre = "";
//        try {
//            inicializarRolPermisoManager();
//            inicializarRolManager();
//
//            Gson gson = new GsonBuilder().create();
//
//            RolPermiso permisos = gson.fromJson(peticionStr,
//                    RolPermiso.class);
//
//            if (permisos != null
//                    && permisos.getPermisos() != null) {
//
//                Rol ejRol = new Rol();
//                ejRol = rolManager.get(id);
//
//                RolPermiso ejRolPer = new RolPermiso();
//                ejRolPer.setRol(ejRol);
//
//                List<Map<String, Object>> listMapRolPermisos = rolPermisoManager.listAtributos(ejRolPer, "id,rol.id".split(","), true);
//
//                if (!listMapRolPermisos.isEmpty()) {
//                    for (Map<String, Object> rpm : listMapRolPermisos) {
//                        rolPermisoManager.delete(Long.parseLong(rpm.get("id").toString()));
//                    }
//
//                }
//
//                for (String rp : permisos.getPermisos()) {
//                    ejRolPer = new RolPermiso();
//                    ejRolPer.setRol(ejRol);
//                    ejRolPer.setPermiso(new Permiso(Long.parseLong(rp)));
//
//                    rolPermisoManager.save(ejRolPer);
//                }
//
//            } else {
//                retorno.setError(true);
//                retorno.setMensaje("Debe seleccionar algun permiso para el rol.");
//                return retorno;
//            }
//            retorno.setError(false);
//            retorno.setMensaje("Los permisos se asignaron exitosamente.");
//
//        } catch (Exception e) {
//            System.out.println("Error " + e);
//            retorno.setError(true);
//            retorno.setMensaje("Error al asignar los permisos.");
//            return retorno;
//        }
//        return retorno;
//
//    }
//
//
//    @RequestMapping(value = "/{id}/permisos", method = RequestMethod.GET)
//    public @ResponseBody
//    RetornoDTO listarPermisos(@PathVariable("id") Long id) {
//        List<Long> permisos = new ArrayList<Long>();
//        RetornoDTO retorno = new RetornoDTO();        
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        try {
//            inicializarRolPermisoManager();
//
//            RolPermiso ejemplo = new RolPermiso();
//            ejemplo.setRol(new Rol(id));
//
//            List<Map<String, Object>> listMapPermisos = rolPermisoManager.listAtributos(ejemplo, "id,rol.id,permiso.id".split(","), true);
//            
//            for (Map<String, Object> rpm : listMapPermisos) {
//                permisos.add(Long.parseLong(rpm.get("permiso.id").toString()));
//            }
//            retorno.setRows(permisos);
//            retorno.setError(false);
//
//        } catch (Exception ex) {
//            System.out.println("Error " + ex);
//            retorno.setError(true);
//        }
//
//        return retorno;
//    }
//}
