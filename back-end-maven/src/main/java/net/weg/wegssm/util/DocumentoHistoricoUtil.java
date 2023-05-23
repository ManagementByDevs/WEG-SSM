package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DocumentoHistoricoDTO;
import net.weg.wegssm.model.entities.DocumentoHistorico;

import javax.validation.Valid;

/**
 * Classe Util para o documento histórico
 */
public class DocumentoHistoricoUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public DocumentoHistorico convertJsonToModel(String documentoHistoricoJSON) {
        DocumentoHistoricoDTO documentoHistoricoDTO = convertJsonToDTO(documentoHistoricoJSON);
        return convertDTOToModel(documentoHistoricoDTO);
    }

    private DocumentoHistoricoDTO convertJsonToDTO(String documentoHistoricoJSON) {
        try {
            return this.objectMapper.readValue(documentoHistoricoJSON, DocumentoHistoricoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DocumentoHistorico convertDTOToModel(@Valid DocumentoHistoricoDTO documentoHistoricoDTO) {
        return this.objectMapper.convertValue(documentoHistoricoDTO, DocumentoHistorico.class);
    }

}
