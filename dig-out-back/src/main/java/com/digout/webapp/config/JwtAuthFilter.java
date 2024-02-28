package com.digout.webapp.config;

import com.digout.webapp.service.service.AppUserService;
import com.digout.webapp.web.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final AppUserService appUserService;

    @Autowired
    public JwtAuthFilter(JwtUtils jwtUtils, AppUserService appUserService) {
        this.jwtUtils = jwtUtils;
        this.appUserService = appUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null || authorizationHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        final var token = authorizationHeader.substring(7);
        final var nickname = jwtUtils.extractNickname(token);
        if(nickname != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = appUserService.loadUserByUsername(nickname);
            if(jwtUtils.isTokenValid(token, nickname)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
