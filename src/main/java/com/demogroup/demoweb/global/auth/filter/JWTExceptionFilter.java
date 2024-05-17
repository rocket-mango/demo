package com.demogroup.demoweb.global.auth.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JWTExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request,response);
        }catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"JWT Error\": \""+e.getMessage()+"\"}");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("JWTExceptionFilter.shouldNotFilter");
        String[] excludePath = {
                "/swagger-ui/","/swagger-resources/", "/v3/api-docs/","/v3/api-docs"};
        String path = request.getRequestURI();
        System.out.println(path);
        System.out.println(Arrays.stream(excludePath).anyMatch(path::startsWith));
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
