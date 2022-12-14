//package net.weg.wegssm.security;
//
//import lombok.AllArgsConstructor;
//import net.weg.wegssm.model.entities.Usuario;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@AllArgsConstructor
//public class AutenticacaoFiltro extends OncePerRequestFilter {
//
//    private AutenticacaoService autenticacaoService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//
//        if (token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        } else {
//            token = null;
//        }
//
//        Boolean tokenValido = autenticacaoService.validarToken(token);
//
//        if (tokenValido) {
//            Usuario usuario = autenticacaoService.getUsuario(token);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        } else {
//            response.setStatus(401);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//}
