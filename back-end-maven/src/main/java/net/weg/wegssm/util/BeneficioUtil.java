package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.dto.BeneficioPropostaDTO;
import net.weg.wegssm.model.entities.Beneficio;

import javax.validation.Valid;

public class BeneficioUtil {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Beneficio convertJsonToModel(String beneficioJSON) {
        BeneficioPropostaDTO beneficioDTO = convertJsonToDTO(beneficioJSON);
        return convertDTOToModel(beneficioDTO);
    }

    private BeneficioPropostaDTO convertJsonToDTO(String beneficioJSON) {
        try {
            return this.objectMapper.readValue(beneficioJSON, BeneficioPropostaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Beneficio convertJsonToModelDirect(String beneficioJSON) {
        try {
            return this.objectMapper.readValue(beneficioJSON, Beneficio.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Beneficio convertDTOToModel(@Valid BeneficioPropostaDTO beneficioDTO) {
        return this.objectMapper.convertValue(beneficioDTO, Beneficio.class);
    }
}
