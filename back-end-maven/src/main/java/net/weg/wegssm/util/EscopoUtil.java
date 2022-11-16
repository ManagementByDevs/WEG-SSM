package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.model.entities.Escopo;

import javax.validation.Valid;

public class EscopoUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Escopo convertJsonToModel(String escopoJSON) {
        EscopoDTO escopoDTO = convertJsonToDTO(escopoJSON);
        return convertDTOToModel(escopoDTO);
    }

    private EscopoDTO convertJsonToDTO(String escopoJSON){
        try {
            return this.objectMapper.readValue(escopoJSON, EscopoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Escopo convertDTOToModel(@Valid EscopoDTO escopoDTO){
        return this.objectMapper.convertValue(escopoDTO, Escopo.class);
    }

}
