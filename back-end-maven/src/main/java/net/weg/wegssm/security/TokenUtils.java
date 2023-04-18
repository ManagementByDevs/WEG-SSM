package net.weg.wegssm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Classe para funções pequenas envolvendo tokens e cookies de autenticação
 */
public class TokenUtils {

    /**
     * Senha forte usada para geração dos tokens
     */
    private final String senhaForte = "05a9e62653eb0eaa116a1b8bbc06dd30ab0df73ab8ae16a500c80875e6e6c8a9";

    /**
     * Função que gera os tokens de autenticação, com o email usuário sendo o "subject" principal do token
     */
    public String gerarToken(Authentication authentication) {
        UserJpa userJpa = (UserJpa) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("WEG SSM")
                .setSubject(userJpa.getUsuario().getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 36000000))
                .signWith(SignatureAlgorithm.HS256, senhaForte)
                .compact();
    }

    /**
     * Função para gerar um cookie de autenticação a partir de um token criado na função "gerarToken"
     */
    public Cookie gerarCookie(Authentication authentication) {
        Cookie cookie = new Cookie("jwt", gerarToken(authentication));
        cookie.setPath("/");
        cookie.setMaxAge(64800);
        return cookie;
    }

    /**
     * Função que valida se um token recebido é válido para autenticação do usuário
     */
    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(senhaForte).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Função para buscar o email de um usuário através de um token de autenticação recebido, pegando seu "subject"
     */
    public String getUsuarioEmail(String token) {
        return Jwts.parser()
                .setSigningKey(senhaForte)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Função para buscar o cookie de autenticação salvo na requisição
     */
    public String buscarCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "jwt");

        if (cookie != null) {
            return cookie.getValue();
        }

        throw new RuntimeException("Cookie não encontrado!");
    }
}
