package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.model.entities.Escopo;

import javax.validation.Valid;

/**
 * Classe Util para o escopo
 */
public class EscopoUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Escopo
     *
     * @param escopoJSON - JSON do escopo
     * @return - Escopo convertido
     */
    public Escopo convertJsonToModel(String escopoJSON) {
        EscopoDTO escopoDTO = convertJsonToDTO(escopoJSON);
        return convertDTOToModel(escopoDTO);
    }

    /**
     * Função para converter um JSON em um objeto DTO do escopo
     *
     * @param escopoJSON - JSON do escopo
     * @return - DTO do escopo
     */
    private EscopoDTO convertJsonToDTO(String escopoJSON) {
        try {
            return this.objectMapper.readValue(escopoJSON, EscopoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model do escopo
     *
     * @param escopoDTO - DTO do escopo
     * @return - Model do escopo
     */
    private Escopo convertDTOToModel(@Valid EscopoDTO escopoDTO) {
        return this.objectMapper.convertValue(escopoDTO, Escopo.class);
    }

}
