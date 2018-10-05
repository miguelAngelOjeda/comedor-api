package py.com.coomecipar.service.web.jwt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import py.com.coomecipar.service.ejb.manager.TokenAuthenticationManager;
import py.com.coomecipar.service.web.jwtService.TokenAuthenticationService;
import py.com.coomecipar.service.ejb.managerImpl.TokenAuthenticationServiceImpl;

public class JWTAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // Delegates authentication to the TokenAuthenticationService
        Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request);

        // Apply the authentication to the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Go on processing the request
        filterChain.doFilter(request, response);

        // Clears the context from authentication
        SecurityContextHolder.getContext().setAuthentication(null);

    }

}
