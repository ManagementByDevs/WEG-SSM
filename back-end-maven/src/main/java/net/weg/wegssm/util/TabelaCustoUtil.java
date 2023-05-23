package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.TabelaCustoDTO;
import net.weg.wegssm.model.entities.TabelaCusto;

import javax.validation.Valid;

/**
 * Classe Util para a tabela de custo
 */
public class TabelaCustoUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public TabelaCusto convertJsonToModel(String tabelaCustoJSON) {
        TabelaCustoDTO tabelaCustoDTO = convertJsonToDTO(tabelaCustoJSON);
        return convertDTOToModel(tabelaCustoDTO);
    }

    private TabelaCustoDTO convertJsonToDTO(String tabelaCustoJSON) {
        try {
            return this.objectMapper.readValue(tabelaCustoJSON, TabelaCustoDTO.class);
        } catch (Exception e) {
            System.out.println("deu erro aq");
            throw new RuntimeException(e);
        }
    }

    public TabelaCusto convertJsonToModelDirect(String tabelaCustoJSON) {
        try {
            return this.objectMapper.readValue(tabelaCustoJSON, TabelaCusto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TabelaCusto convertDTOToModel(@Valid TabelaCustoDTO tabelaCustoDTO) {
        return this.objectMapper.convertValue(tabelaCustoDTO, TabelaCusto.class);
    }

}
