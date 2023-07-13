package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.PropostaDTO;
import net.weg.wegssm.dto.PropostaJaCriadaDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;

import javax.validation.Valid;

/**
 * Classe Util para a proposta
 */
public class PropostaUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em uma Proposta
     *
     * @param propostaJSON - JSON da proposta
     * @return - Proposta convertida
     */
    public Proposta convertJsonToModel(String propostaJSON) {
        PropostaDTO propostaDTO = convertJsonToDTO(propostaJSON);
        return convertDTOToModel(propostaDTO);
    }

    /**
     * Função para converter um JSON de uma proposta já criada para uma model de proposta
     * @param propostaJSON
     * @return
     */
    public Proposta convertJsonToModelDirect(String propostaJSON) {
        try {
            return this.objectMapper.readValue(propostaJSON, Proposta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um JSON em um obeto DTO da proposta
     *
     * @param propostaJSON - JSON da proposta
     * @return - Proposta convertida
     */
    private PropostaDTO convertJsonToDTO(String propostaJSON) {
        try {
            return this.objectMapper.readValue(propostaJSON, PropostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model da proposta
     *
     * @param propostaDTO - DTO da proposta
     * @return - Proposta convertida
     */
    private Proposta convertDTOToModel(@Valid PropostaDTO propostaDTO) {
        return this.objectMapper.convertValue(propostaDTO, Proposta.class);
    }

}
