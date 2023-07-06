package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.Historico;

import javax.validation.Valid;

/**
 * Classe Util para o histórico
 */
public class HistoricoUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Histórico
     *
     * @param historicoJson - JSON do histórico
     * @return - Histórico convertido
     */
    public Historico convertJsonToModel(String historicoJson) {
        HistoricoDTO historicoDTO = convertJsonToDTO(historicoJson);
        return convertDTOToModel(historicoDTO);
    }

    /**
     * Função para converter um JSON em um objeto DTO do histórico
     *
     * @param historicoJson - DTO do histórico
     * @return - Model do histórico
     */
    private HistoricoDTO convertJsonToDTO(String historicoJson) {
        try {
            return this.objectMapper.readValue(historicoJson, HistoricoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model do histórico
     *
     * @param historicoDTO - DTO do histórico
     * @return - Model do histórico
     */
    private Historico convertDTOToModel(@Valid HistoricoDTO historicoDTO) {
        return this.objectMapper.convertValue(historicoDTO, Historico.class);
    }

}
