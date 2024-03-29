package net.weg.wegssm.security;

import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.security.dto.UserDTO;
import net.weg.wegssm.security.util.CookieUtils;
import net.weg.wegssm.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Controller usado para as requisições de autenticação
 */
@Controller
@RequestMapping("/weg_ssm/login")
public class AutenticacaoController {

    /**
     * TokenUtils utilizado para geração de tokens na autenticação
     */
    private TokenUtils tokenUtils = new TokenUtils();

    /**
     * CookieUtils utilizado para geração de cookies na autenticação
     */
    private CookieUtils cookieUtils = new CookieUtils();

    /**
     * UsuarioService para o salvamento do estado da senha
     */
    private UsuarioService usuarioService;

    /**
     * AuthenticationManager usado para realizar o processo de autenticação (Criado pela AutenticacaoConfig)
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Método POST para a realização da autenticação, recebendo um userDTO como body da requisição
     *
     * @param userDTO  - UserDTO com os dados de autenticação
     * @param response - HttpServletResponse usado para a geração dos cookies
     * @return - ResponseEntity com o usuário autenticado ou um erro de autenticação
     */
    @PostMapping("/auth")
    public ResponseEntity<Object> autenticacao(@RequestBody @Valid UserDTO userDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getSenha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            UserJpa userJpa = (UserJpa) authentication.getPrincipal();
            response.addCookie(cookieUtils.gerarTokenCookie(userJpa, 3600));
            response.addCookie(cookieUtils.gerarUserCookie(userJpa, 3600));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userJpa.getUsername(),
                            userJpa.getPassword(), userJpa.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(
                    usernamePasswordAuthenticationToken);

            return ResponseEntity.status(HttpStatus.OK).body(userJpa.getUsuario());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Método POST para a realização da autenticação com "lembrar senha", recebendo um userDTO como body da requisição
     *
     * @param userDTO  - UserDTO com os dados de autenticação
     * @param response - HttpServletResponse usado para a geração dos cookies
     * @return - ResponseEntity com o usuário autenticado ou um erro de autenticação
     */
    @PostMapping("/auth/lembrar-senha")
    public ResponseEntity<Object> autenticacaoLembrarSenha(@RequestBody @Valid UserDTO userDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getSenha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            UserJpa userJpa = (UserJpa) authentication.getPrincipal();
            response.addCookie(cookieUtils.gerarTokenCookie(userJpa, 259200));
            response.addCookie(cookieUtils.gerarUserCookie(userJpa, 259200));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userJpa.getUsername(),
                            userJpa.getPassword(), userJpa.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(
                    usernamePasswordAuthenticationToken);

            return ResponseEntity.status(HttpStatus.OK).body(userJpa.getUsuario());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
