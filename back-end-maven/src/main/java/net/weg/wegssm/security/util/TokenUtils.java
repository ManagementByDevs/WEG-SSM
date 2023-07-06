package net.weg.wegssm.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.weg.wegssm.security.UserJpa;

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
     *
     * @param usuario - Usuário que será autenticado
     * @return - Token gerado
     */
    public String gerarToken(UserJpa usuario) {
        return Jwts.builder()
                .setIssuer("WEG SSM")
                .setSubject(usuario.getUsuario().getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS256, senhaForte)
                .compact();
    }

    /**
     * Função que valida se um token recebido é válido para autenticação do usuário
     *
     * @param token - Token recebido
     */
    public void validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(senhaForte).parseClaimsJws(token);
        } catch (Exception e) {
            throw new RuntimeException("Token Inválido!");
        }
    }

}
