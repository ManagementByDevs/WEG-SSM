package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.EscopoPropostaDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.EscopoProposta;

import javax.validation.Valid;

public class EscopoPropostaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public EscopoProposta convertJsonToModel(String escopoPropostaJson) {
        EscopoPropostaDTO escopoPropostaDTO = convertJsonToDTO(escopoPropostaJson);
        return convertDTOToModel(escopoPropostaDTO);
    }

    public EscopoProposta convertJsonToModelDirect(String escopoPropostaJson) {
        try {
            return this.objectMapper.readValue(escopoPropostaJson, EscopoProposta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EscopoPropostaDTO convertJsonToDTO(String escopoPropostaJson){
        try {
            return this.objectMapper.readValue(escopoPropostaJson, EscopoPropostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EscopoProposta convertDTOToModel(@Valid EscopoPropostaDTO escopoPropostaDTO){
        return this.objectMapper.convertValue(escopoPropostaDTO, EscopoProposta.class);
    }
}
