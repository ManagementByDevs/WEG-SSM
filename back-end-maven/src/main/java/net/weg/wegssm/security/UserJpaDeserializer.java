package net.weg.wegssm.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import net.weg.wegssm.model.factory.UsuarioFactory;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para deserializar um usuário recebido no cookie de autenticação para a classe Usuario
 */
public class UserJpaDeserializer extends JsonDeserializer<UserJpa> {

    /**
     * Função para transformar o usuário recebido no cookie para a classe Usuario
     *
     * @param p
     * @param ctxt
     * @return
     * @throws IOException
     */
    @Override
    public UserJpa deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        ArrayNode authoritiesNode = (ArrayNode) node.get("authorities");
        for (JsonNode authorityNode : authoritiesNode) {
            String authority = authorityNode.get("authority").asText();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
            authorities.add(simpleGrantedAuthority);
        }
        Usuario usuario = mapper.convertValue(node.get("usuario"), Usuario.class);
        SimpleGrantedAuthority authority = authorities.get(0);
        usuario = new UsuarioFactory().getUsuario(authority, usuario);
        return new UserJpa(usuario);
    }

}
