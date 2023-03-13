package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.repository.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    ForumRepository forumRepository;

    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

    public Optional<Forum> findById(Long id) {
        return forumRepository.findById(id);
    }

    public Boolean existsByNome(String nome) {
        return forumRepository.existsByNomeForum(nome);
    }

    public Boolean existsById(Long id) {
        return forumRepository.existsById(id);
    }

    public <S extends Forum> S save(S entity) {
        return forumRepository.save(entity);
    }

    public void deleteById(Long id) {
        forumRepository.deleteById(id);
    }

}
