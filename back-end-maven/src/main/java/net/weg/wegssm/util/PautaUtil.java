package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Pauta;

/**
 * Classe Util para a pauta
 */
public class PautaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Pauta convertJsonToModelDirect(String pautaJSON) {
        try {
            return this.objectMapper.readValue(pautaJSON, Pauta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
