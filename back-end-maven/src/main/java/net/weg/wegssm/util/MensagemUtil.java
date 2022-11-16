package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.MensagemDTO;
import net.weg.wegssm.model.entities.Mensagem;

import javax.validation.Valid;

public class MensagemUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Mensagem convertJsonToModel(String mensagemJson) {
        MensagemDTO mensagemDTO = convertJsonToDTO(mensagemJson);
        return convertDTOToModel(mensagemDTO);
    }

    private MensagemDTO convertJsonToDTO(String mensagemJson){
        try {
            return this.objectMapper.readValue(mensagemJson, MensagemDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Mensagem convertDTOToModel(@Valid MensagemDTO mensagemDTO){
        return this.objectMapper.convertValue(mensagemDTO, Mensagem.class);
    }

}
