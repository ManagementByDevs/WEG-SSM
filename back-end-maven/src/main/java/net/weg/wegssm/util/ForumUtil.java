package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Usuario;

public class ForumUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Forum convertJsonToModel(String forumJson) {
        try {
            return this.objectMapper.readValue(forumJson, Forum.class);
        } catch (Exception e) {
            return null;
        }
    }
}
