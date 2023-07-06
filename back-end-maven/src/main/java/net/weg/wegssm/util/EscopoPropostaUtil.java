package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.EscopoPropostaDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.EscopoProposta;

import javax.validation.Valid;

/**
 * Classe Util para o escopo da proposta
 */
public class EscopoPropostaUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um EscopoProposta
     *
     * @param escopoPropostaJson - JSON do escopo da proposta
     * @return - EscopoProposta convertido
     */
    public EscopoProposta convertJsonToModel(String escopoPropostaJson) {
        EscopoPropostaDTO escopoPropostaDTO = convertJsonToDTO(escopoPropostaJson);
        return convertDTOToModel(escopoPropostaDTO);
    }

    /**
     * Função para converter um JSON em um EscopoProposta sem passar pelo DTO
     *
     * @param escopoPropostaJson - JSON do escopo da proposta
     * @return - EscopoProposta convertido
     */
    public EscopoProposta convertJsonToModelDirect(String escopoPropostaJson) {
        try {
            return this.objectMapper.readValue(escopoPropostaJson, EscopoProposta.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um JSON para um objeto DTO do escopo da proposta
     *
     * @param escopoPropostaJson - JSON do escopo da proposta
     * @return - DTO do escopo da proposta
     */
    private EscopoPropostaDTO convertJsonToDTO(String escopoPropostaJson) {
        try {
            return this.objectMapper.readValue(escopoPropostaJson, EscopoPropostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO para um objeto Model do escopo da proposta
     *
     * @param escopoPropostaDTO - DTO do escopo da proposta
     * @return - Model do escopo da proposta
     */
    private EscopoProposta convertDTOToModel(@Valid EscopoPropostaDTO escopoPropostaDTO) {
        return this.objectMapper.convertValue(escopoPropostaDTO, EscopoProposta.class);
    }

}
