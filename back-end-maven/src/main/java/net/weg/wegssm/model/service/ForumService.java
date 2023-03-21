package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.repository.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para os fóruns
 */
@Service
public class ForumService {

    /**
     * Classe repository dos fóruns
     */
    ForumRepository forumRepository;

    /**
     * Construtor da classe
     */
    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    /**
     * Função para buscar todos os fóruns salvos no banco de dados
     */
    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

}
