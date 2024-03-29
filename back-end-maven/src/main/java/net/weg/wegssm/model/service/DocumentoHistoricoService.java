package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DocumentoHistoricoDTO;
import net.weg.wegssm.model.entities.DocumentoHistorico;
import net.weg.wegssm.repository.DocumentoHistoricoRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para o documento histórico
 */
@Service
@AllArgsConstructor
public class DocumentoHistoricoService {

    /**
     * Classe repository do documento histórico
     */
    private DocumentoHistoricoRepository documentoHistoricoRepository;

    /**
     * Função para salvar um documento histórico
     *
     * @param documentoHistorico - Documento histórico a ser salvo
     * @return - Documento histórico salvo
     */
    public DocumentoHistorico save(DocumentoHistorico documentoHistorico) {
        return documentoHistoricoRepository.save(documentoHistorico);
    }

}
