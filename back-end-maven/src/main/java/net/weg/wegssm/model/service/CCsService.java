package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.repository.CCsRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para as CCs
 */
@Service
public class CCsService {

    /**
     * Classe repository das CCs
     */
    private CCsRepository ccsRepository;

    /**
     * Construtor da classe
     */
    public CCsService(CCsRepository ccsRepository) {
        this.ccsRepository = ccsRepository;
    }

    /**
     * Função para salvar uma CC no banco
     */
    public CC save(CC cc) {
        return ccsRepository.save(cc);
    }

    /**
     * Função booleana para verificar se uma CC existe ou não, pelo seu ID
     */
    public Boolean existsById(Long id) {
        return ccsRepository.existsById(id);
    }

    /**
     * Função para excluir uma CC pelo seu ID
     */
    public void deleteById(Long id) {
        ccsRepository.deleteById(id);
    }
}
