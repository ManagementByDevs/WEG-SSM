package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.Demanda;

import javax.validation.Valid;

/**
 * Classe Util para a demanda
 */
public class DemandaUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Demanda
     *
     * @param demandaJSON - JSON da demanda
     * @return - Demanda convertida
     */
    public Demanda convertJsonToModel(String demandaJSON) {
        DemandaDTO demandaDTO = convertJsonToDTO(demandaJSON);
        return convertDTOToModel(demandaDTO);
    }

    /**
     * Função para converter um JSON em um Demanda sem passar pelo DTO
     *
     * @param demandaJSON - JSON da demanda
     * @return - Demanda convertida
     */
    public Demanda convertJsonToModelDirect(String demandaJSON) {
        try {
            return this.objectMapper.readValue(demandaJSON, Demanda.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um JSON para um objeto DTO da demanda
     *
     * @param demandaJSON - JSON da demanda
     * @return - DTO da demanda
     */
    private DemandaDTO convertJsonToDTO(String demandaJSON) {
        try {
            return this.objectMapper.readValue(demandaJSON, DemandaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO para um objeto Model da demanda
     *
     * @param demandaDTO - DTO da demanda
     * @return - Model da demanda
     */
    private Demanda convertDTOToModel(@Valid DemandaDTO demandaDTO) {
        return this.objectMapper.convertValue(demandaDTO, Demanda.class);
    }

}
