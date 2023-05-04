package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.model.entities.Beneficio;

import javax.validation.Valid;

public class BeneficioUtil {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Beneficio convertJsonToModel(String beneficioJSON) {
        BeneficioDTO beneficioDTO = convertJsonToDTO(beneficioJSON);
        return convertDTOToModel(beneficioDTO);
    }

    private BeneficioDTO convertJsonToDTO(String beneficioJSON) {
        try {
            return this.objectMapper.readValue(beneficioJSON, BeneficioDTO.class);
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

    private Beneficio convertDTOToModel(@Valid BeneficioDTO beneficioDTO) {
        return this.objectMapper.convertValue(beneficioDTO, Beneficio.class);
    }
}
