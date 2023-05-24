package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.Demanda;

import javax.validation.Valid;

/**
 * Classe Util para a demanda
 */
public class DemandaUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Demanda convertJsonToModel(String demandaJSON) {
        DemandaDTO demandaDTO = convertJsonToDTO(demandaJSON);
        return convertDTOToModel(demandaDTO);
    }

    public Demanda convertJsonToModelDirect(String demandaJSON) {
        try {
            return this.objectMapper.readValue(demandaJSON, Demanda.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DemandaDTO convertJsonToDTO(String demandaJSON) {
        try {
            return this.objectMapper.readValue(demandaJSON, DemandaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Demanda convertDTOToModel(@Valid DemandaDTO demandaDTO) {
        return this.objectMapper.convertValue(demandaDTO, Demanda.class);
    }

}
