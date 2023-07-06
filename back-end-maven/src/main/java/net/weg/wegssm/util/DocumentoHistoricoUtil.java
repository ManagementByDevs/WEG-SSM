package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DocumentoHistoricoDTO;
import net.weg.wegssm.model.entities.DocumentoHistorico;

import javax.validation.Valid;

/**
 * Classe Util para o documento histórico
 */
public class DocumentoHistoricoUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um DocumentoHistorico
     *
     * @param documentoHistoricoJSON - JSON do documento histórico
     * @return - DocumentoHistorico convertido
     */
    public DocumentoHistorico convertJsonToModel(String documentoHistoricoJSON) {
        DocumentoHistoricoDTO documentoHistoricoDTO = convertJsonToDTO(documentoHistoricoJSON);
        return convertDTOToModel(documentoHistoricoDTO);
    }

    /**
     * Função para converter um JSON em um objeto DTO do documento histórico
     *
     * @param documentoHistoricoJSON - JSON do documento histórico
     * @return - DTO do documento histórico
     */
    private DocumentoHistoricoDTO convertJsonToDTO(String documentoHistoricoJSON) {
        try {
            return this.objectMapper.readValue(documentoHistoricoJSON, DocumentoHistoricoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para converter um DTO em um objeto Model do documento histórico
     *
     * @param documentoHistoricoDTO - DTO do documento histórico
     * @return - Model do documento histórico
     */
    private DocumentoHistorico convertDTOToModel(@Valid DocumentoHistoricoDTO documentoHistoricoDTO) {
        return this.objectMapper.convertValue(documentoHistoricoDTO, DocumentoHistorico.class);
    }

}
