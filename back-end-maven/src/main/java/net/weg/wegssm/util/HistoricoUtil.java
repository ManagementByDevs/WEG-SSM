package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.Historico;

import javax.validation.Valid;

public class HistoricoUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Historico convertJsonToModel(String historicoJson) {
        HistoricoDTO historicoDTO = convertJsonToDTO(historicoJson);
        return convertDTOToModel(historicoDTO);
    }

    private HistoricoDTO convertJsonToDTO(String historicoJson){
        try {
            return this.objectMapper.readValue(historicoJson, HistoricoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Historico convertDTOToModel(@Valid HistoricoDTO historicoDTO){
        return this.objectMapper.convertValue(historicoDTO, Historico.class);
    }
}
