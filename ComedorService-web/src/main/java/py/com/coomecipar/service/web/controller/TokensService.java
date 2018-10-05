package py.com.coomecipar.service.web.controller;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package py.com.coomecipar.service.web.controller;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import py.com.coomecipar.service.ejb.entity.AutenticacionTokens;
//import py.com.coomecipar.service.web.jwt.TokenHandler;
//import py.com.coomecipar.service.web.spring.User;
//
///**
// *
// * @author mojeda
// */
//@Controller
//public class TokensService extends BaseController {
//
//    private final TokenHandler tokenHandler = new TokenHandler();
//
//    /**
//     * Mapping para el metodo POST de la vista Update Token.
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "sessions", method = RequestMethod.GET)
//    public @ResponseBody
//    AutenticacionTokens updateToken() {
//        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        AutenticacionTokens response = new AutenticacionTokens();
//        try {
//
//            inicializarTokensManager();
//
//            AutenticacionTokens ejTokens = new AutenticacionTokens();
//            ejTokens.setIdUsuario(userDetail.getId());
//
//            ejTokens = tokenAuthenticationManager.get(ejTokens);
//
//            AutenticacionTokens JWT = tokenHandler.build(userDetail.getId().toString());
//
//            if (ejTokens != null) {
//                
//                ejTokens.setAccessToken(JWT.getAccessToken());
//                
//                tokenAuthenticationManager.update(ejTokens);
//            }
//
//            return JWT;
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return response;
//    }
//}
