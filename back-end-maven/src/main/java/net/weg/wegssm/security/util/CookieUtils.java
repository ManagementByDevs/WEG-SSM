package net.weg.wegssm.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.security.UserJpa;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Classe para funções pequenas envolvendo cookies de autenticação
 */
public class CookieUtils {

    /**
     * utils para gerenciamento e uso dos tokens de autenticação
     */
    private final TokenUtils tokenUtils = new TokenUtils();

    /**
     * Função para gerar um cookie armazenando o token de autenticação
     */
    public Cookie gerarTokenCookie(UserJpa userJpa) {
        String token = tokenUtils.gerarToken(userJpa);
        Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        return cookie;
    }

    /**
     * Função para pegar o cookie com o token de autenticação
     */
    public String getTokenCookie(HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request, "jwt");
            return cookie.getValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para gerar um cookie armazenando o usuário autenticado
     */
    public Cookie gerarUserCookie(UserJpa userJpa) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = URLEncoder.encode(
                    objectMapper.writeValueAsString(userJpa),
                    StandardCharsets.UTF_8);
            Cookie cookie = new Cookie("user", userJson);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            return cookie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para pegar o cookie com o usuário autenticado
     */
    public UserJpa getUserCookie(HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request, "user");
            String jsonUser = URLDecoder.decode(
                    cookie.getValue(),
                    StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonUser, UserJpa.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para renovar o tempo de um cookie, recebendo o nome dele como parâmetro
     */
    public Cookie renovarCookie(HttpServletRequest request, String nome) {
        Cookie cookie = WebUtils.getCookie(request, nome);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        return cookie;
    }
}
