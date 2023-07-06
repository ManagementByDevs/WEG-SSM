package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.model.entities.Beneficio;

import javax.validation.Valid;

/**
 * Classe Util para o beneficio
 */
public class BeneficioUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Beneficio
     *
     * @param beneficioJSON - JSON que será convertido
     * @return - Beneficio convertido
     */
    public Beneficio convertJsonToModel(String beneficioJSON) {
        BeneficioDTO beneficioDTO = convertJsonToDTO(beneficioJSON);
        return convertDTOToModel(beneficioDTO);
    }

    /**
     * Função para converter um Beneficio em um objeto DTO
     *
     * @param beneficioJson - Beneficio que será convertido
     * @return - DTO convertido
     */
    private BeneficioDTO convertJsonToDTO(String beneficioJson) {
        try {
            return this.objectMapper.readValue(beneficioJson, BeneficioDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um Beneficio
     *
     * @param beneficioDTO - DTO que será convertido
     * @return - Beneficio convertido
     */
    private Beneficio convertDTOToModel(@Valid BeneficioDTO beneficioDTO) {
        return this.objectMapper.convertValue(beneficioDTO, Beneficio.class);
    }

}
