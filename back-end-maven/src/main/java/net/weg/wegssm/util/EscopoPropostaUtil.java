package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.EscopoProposta;

public class EscopoPropostaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public EscopoProposta convertJsonToModel(String escopoPropostaJSON) {
        try {
            return this.objectMapper.readValue(escopoPropostaJSON, EscopoProposta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
