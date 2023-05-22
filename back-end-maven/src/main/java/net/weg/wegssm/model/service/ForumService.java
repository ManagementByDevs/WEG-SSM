package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.repository.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para os fóruns
 */
@Service
@AllArgsConstructor
public class ForumService {

    /**
     * Classe repository dos fóruns
     */
    ForumRepository forumRepository;

    /**
     * Função para buscar todos os fóruns salvos no banco de dados
     */
    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

}
