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
     * Utils para gerenciamento e uso dos tokens de autenticação
     */
    private final TokenUtils tokenUtils = new TokenUtils();

    /**
     * Função para gerar um cookie armazenando o token de autenticação
     *
     * @param userJpa UserJpa usado para a geração do token JWT
     * @return Cookie com o token JWT
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
     *
     * @param request Request para a busca do cookie
     * @return String com o valor do cookie buscado
     */
    public String getTokenCookie(HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request, "jwt");
            return cookie.getValue();
        } catch (Exception e) {
            try {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt")) {
                        return cookie.getValue();
                    }
                }
                throw new RuntimeException(e);
            } catch (Exception b) {
                throw new RuntimeException(b);
            }
        }
    }

    /**
     * Função para gerar um cookie armazenando o usuário autenticado
     *
     * @param userJpa UserJpa usado como o usuário presente no cookie
     * @return Cookie com o usuário recebido
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
     *
     * @param request Request para a busca do cookie
     * @return UserJpa contido no cookie buscado
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
     * Função para renovar o tempo de expiração do cookie de usuário
     *
     * @param request Request recebida
     * @return Cookie do usuário renovado
     */
    public Cookie renovarCookieUser(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "user");
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        return cookie;
    }

    /**
     * Função para renovar o tempo de expiração do cookie JWT, criando um novo token
     *
     * @param userJpa UserJpa usado para a criação do token
     * @return Cookie JWT renovado
     */
    public Cookie renovarCookieToken(UserJpa userJpa) {
        Cookie cookie = new Cookie("jwt", tokenUtils.gerarToken(userJpa));
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        return cookie;
    }

}
