package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.SecaoTI;
import net.weg.wegssm.repository.SecaoTIRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Classe service para as seções de TI */
@Service
public class SecaoTIService {

    /** Classe repository das seções de TI */
    public SecaoTIRepository secaoTIRepository;

    /** Construtor da classe */
    public SecaoTIService(SecaoTIRepository secaoTIRepository) {
        this.secaoTIRepository = secaoTIRepository;
    }

    /** Função para buscar todas as seções de TI salvas no banco */
    public List<SecaoTI> findAll() {
        return secaoTIRepository.findAll();
    }
}
