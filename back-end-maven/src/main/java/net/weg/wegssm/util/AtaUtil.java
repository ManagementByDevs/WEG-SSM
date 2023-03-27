package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Ata;

public class AtaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Ata convertJsonToModelDirect(String ataJSON) {
        try {
            return this.objectMapper.readValue(ataJSON, Ata.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
