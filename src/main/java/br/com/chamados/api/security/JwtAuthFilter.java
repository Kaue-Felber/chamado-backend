package br.com.chamados.api.security;

import br.com.chamados.api.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.print.DocFlavor;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
           throws ServletException, IOException{

        String path = request.getRequestURI();

        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        // Espera: Authorization: Berar <token>
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            if (JwtUtil.tokenValido(token)){
                Claims claims = JwtUtil.validarToken(token);
                request.setAttribute("idUsuario", claims.get("id"));
                request.setAttribute("usuario", claims.getSubject());
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            // Sem token ou formato errado
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }
}
