package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Usuario;

/**
 * Classe Util para o forum
 */
public class ForumUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Forum
     *
     * @param forumJson - JSON do forum
     * @return - Forum convertido
     */
    public Forum convertJsonToModel(String forumJson) {
        try {
            return this.objectMapper.readValue(forumJson, Forum.class);
        } catch (Exception e) {
            return null;
        }
    }

}
