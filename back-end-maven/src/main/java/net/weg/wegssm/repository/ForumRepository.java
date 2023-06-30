package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository para os fóruns
 */
@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> { }
