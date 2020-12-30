package com.springboot.apisecretaria.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springboot.apisecretaria.model.dto.EmailDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.apisecretaria.model.Usuario;
import com.springboot.apisecretaria.service.TokenService;
import com.springboot.apisecretaria.service.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenRequest(request);
        if (tokenService.isValid(token)) {
            authRequest(token);
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }

    private void authRequest(String token) {
        Long idUsuario = tokenService.getIdUsuarioToken(token);
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
