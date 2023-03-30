package net.weg.wegssm.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe usada para filtrar as requisições, definindo se precisarão de tokens de acesso e caso positivo, realizando a validação deles
 */
@AllArgsConstructor
public class AutenticacaoFiltro extends OncePerRequestFilter {

    /**
     * TokenUtils usado para a validação dos tokens e busca de cookies
     */
    private TokenUtils tokenUtils;

    /**
     * Serviço usado para pesquisa do usuário presente no token, caso esteja válido
     */
    private JpaService jpaService;

    /**
     * Função para filtrar uma requisição e definir se ela pode ser acessada ou não
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/weg_ssm/login") || request.getRequestURI().equals("/weg_ssm/login/auth") || request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenUtils.buscarCookie(request);
        Boolean valido = tokenUtils.validarToken(token);

        if (valido) {
            String usuarioEmail = tokenUtils.getUsuarioEmail(token);
            UserDetails usuario = jpaService.loadUserByUsername(usuarioEmail);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
            return;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
