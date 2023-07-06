package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.MensagemDTO;
import net.weg.wegssm.model.entities.Mensagem;

import javax.validation.Valid;

/**
 * Classe Util para a mensagem
 */
public class MensagemUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em uma Mensagem
     *
     * @param mensagemJson - JSON da mensagem
     * @return - Mensagem convertida
     */
    public Mensagem convertJsonToModel(String mensagemJson) {
        MensagemDTO mensagemDTO = convertJsonToDTO(mensagemJson);
        return convertDTOToModel(mensagemDTO);
    }

    /**
     * Função para converter um JSON em um obeto DTO da mensagem
     *
     * @param mensagemJson - JSON da mensagem
     * @return - Mensagem convertida
     */
    private MensagemDTO convertJsonToDTO(String mensagemJson) {
        try {
            return this.objectMapper.readValue(mensagemJson, MensagemDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model da mensagem
     *
     * @param mensagemDTO - DTO da mensagem
     * @return - Mensagem convertida
     */
    private Mensagem convertDTOToModel(@Valid MensagemDTO mensagemDTO) {
        return this.objectMapper.convertValue(mensagemDTO, Mensagem.class);
    }

}
