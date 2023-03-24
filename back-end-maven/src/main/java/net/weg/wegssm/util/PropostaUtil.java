package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.PropostaDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;

import javax.validation.Valid;

public class PropostaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Proposta convertJsonToModel(String propostaJSON) {
        PropostaDTO propostaDTO = convertJsonToDTO(propostaJSON);
        return convertDTOToModel(propostaDTO);
    }

    private PropostaDTO convertJsonToDTO(String propostaJSON) {
        try {
            return this.objectMapper.readValue(propostaJSON, PropostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Proposta convertJsonToModelDirect(String propostaJSON) {
        try {
            return this.objectMapper.readValue(propostaJSON, Proposta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Proposta convertDTOToModel(@Valid PropostaDTO propostaDTO){
        return this.objectMapper.convertValue(propostaDTO, Proposta.class);
    }

}
