package com.acme.ideogo.config;

import com.acme.ideogo.service.UserDetailsServiceImpl;
import com.acme.ideogo.util.jwt.JwtCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtCenter tokenCenter;
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }

}
