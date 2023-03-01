package net.weg.wegssm.model.service;

import net.weg.wegssm.dto.DocumentoHistoricoDTO;
import net.weg.wegssm.model.entities.DocumentoHistorico;
import net.weg.wegssm.repository.DocumentoHistoricoRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentoHistoricoService {

    private DocumentoHistoricoRepository documentoHistoricoRepository;

    public DocumentoHistoricoService(DocumentoHistoricoRepository documentoHistoricoRepository) {
        this.documentoHistoricoRepository = documentoHistoricoRepository;
    }

    public DocumentoHistorico save(DocumentoHistorico documentoHistorico) {
        return documentoHistoricoRepository.save(documentoHistorico);
    }
}
