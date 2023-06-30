package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Bu;
import net.weg.wegssm.repository.BuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para as buscas de BUs
 */
@Service
@AllArgsConstructor
public class BuService {

    /**
     * Classe repository das BUs
     */
    private BuRepository buRepository;

    /**
     * Função para buscar todas as BUs salvas no banco
     * @return
     */
    public List<Bu> findAll() {
        return buRepository.findAll();
    }

}
