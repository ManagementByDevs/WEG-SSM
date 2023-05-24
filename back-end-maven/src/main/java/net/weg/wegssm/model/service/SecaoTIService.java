package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.SecaoTI;
import net.weg.wegssm.repository.SecaoTIRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para as seções de TI
 */
@Service
@AllArgsConstructor
public class SecaoTIService {

    /**
     * Classe repository das seções de TI
     */
    public SecaoTIRepository secaoTIRepository;

    /**
     * Função para buscar todas as seções de TI
     *
     * @return
     */
    public List<SecaoTI> findAll() {
        return secaoTIRepository.findAll();
    }

}
