package py.com.coomecipar.service.web.controller;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package py.com.coomecipar.service.web.controller;
//
//
//import com.google.gson.Gson;
//import java.util.List;
//import java.util.Map;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//import py.com.coomecipar.service.ejb.entity.Permiso;
//import py.com.coomecipar.service.ejb.utils.RetornoDTO;
//import py.com.coomecipar.service.web.spring.User;
//import py.com.coomecipar.service.web.utils.FilterDTO;
//import py.com.coomecipar.service.web.utils.ReglaDTO;
//
///**
// *
// * @author Miguel
// */
//@Controller
//@RequestMapping(value = "/permits")
//public class PermisoController extends BaseController {
//
//    String atributos = "id,nombre";
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView listarPermisos(Model model) {
//        ModelAndView retorno = new ModelAndView();
//
//        model.addAttribute("nombre_js", "permisos.js");
//
//        retorno.setViewName("listar");
//
//        return retorno;
//    }
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
//        Permiso ejPermiso = new Permiso();
//        List<Map<String, Object>> listMapGrupos = null;
//        try {
//
//            inicializarPermisoManager();
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
//                total = permisoManager.list(ejPermiso, true).size();
//            }
//
//            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;
//
//            if (total < inicio) {
//                inicio = total - total % cantidad;
//                pagina = total / cantidad;
//            }
//
//            listMapGrupos = permisoManager.listAtributos(ejPermiso, atributos.split(","), todos, inicio, cantidad,
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
//}
