package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.TabelaCustoDTO;
import net.weg.wegssm.model.entities.TabelaCusto;

import javax.validation.Valid;

/**
 * Classe Util para a tabela de custo
 */
public class TabelaCustoUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em uma tabela de custo
     *
     * @param tabelaCustoJSON - JSON da tabela de custo
     * @return - Tabela de custo convertida
     */
    public TabelaCusto convertJsonToModel(String tabelaCustoJSON) {
        TabelaCustoDTO tabelaCustoDTO = convertJsonToDTO(tabelaCustoJSON);
        return convertDTOToModel(tabelaCustoDTO);
    }

    /**
     * Função para converter um JSON em um objeto DTO da tabela de custo
     *
     * @param tabelaCustoJSON - JSON da tabela de custo
     * @return - Tabela de custo convertida
     */
    private TabelaCustoDTO convertJsonToDTO(String tabelaCustoJSON) {
        try {
            return this.objectMapper.readValue(tabelaCustoJSON, TabelaCustoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model da tabela de custo
     *
     * @param tabelaCustoDTO - DTO da tabela de custo
     * @return - Tabela de custo convertida
     */
    private TabelaCusto convertDTOToModel(@Valid TabelaCustoDTO tabelaCustoDTO) {
        return this.objectMapper.convertValue(tabelaCustoDTO, TabelaCusto.class);
    }

}
