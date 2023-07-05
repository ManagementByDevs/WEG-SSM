package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.model.entities.Beneficio;
import javax.validation.Valid;

/**
 * Classe Util para o beneficio
 */
public class BeneficioUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Beneficio convertJsonToModel(String beneficioJSON) {
        BeneficioDTO beneficioDTO = convertJsonToDTO(beneficioJSON);
        return convertDTOToModel(beneficioDTO);
    }

    private BeneficioDTO convertJsonToDTO(String beneficioJson) {
        try {
            return this.objectMapper.readValue(beneficioJson, BeneficioDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Beneficio convertDTOToModel(@Valid BeneficioDTO beneficioDTO) {
        return this.objectMapper.convertValue(beneficioDTO, Beneficio.class);
    }

}
