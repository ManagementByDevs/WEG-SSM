package net.weg.wegssm.security;

import lombok.AllArgsConstructor;
import net.weg.wegssm.security.util.CookieUtils;
import net.weg.wegssm.security.util.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * CookieUtils usado para a busca de cookies
     */
    private CookieUtils cookieUtils;

    /**
     * CookieUtils usado para a validação dos tokens
     */
    private TokenUtils tokenUtils;

    /**
     * Serviço usado para pesquisa do usuário presente no token, caso esteja válido
     */
    private JpaService jpaService;

    /**
     * Função para filtrar uma requisição e definir se ela pode ser acessada ou não
     *
     * @param request - Requisição a ser filtrada
     * @param response - Resposta a ser enviada
     * @param filterChain - Filtro a ser aplicado
     * @throws ServletException - Exceção de Servlet
     * @throws IOException - Exceção de IO
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/weg_ssm/login") || request.getRequestURI().equals("/weg_ssm/login/auth") || request.getRequestURI().equals("/weg_ssm/login/auth/lembrar-senha") || request.getRequestURI().equals("/login") || request.getRequestURI().equals("/weg_ssm/usuario/email") || request.getRequestURI().equals("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = cookieUtils.getTokenCookie(request);
            tokenUtils.validarToken(token);
            UserJpa user = cookieUtils.getUserCookie(request);

            response.addCookie(cookieUtils.renovarCookieToken(user));
            response.addCookie(cookieUtils.renovarCookieUser(request));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
