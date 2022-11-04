package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

    /**
     * Método que verifica se existe um fórum com o nome passado por parâmetro
     * @param nome
     * @return
     */
    Boolean existsByNome(String nome);

}
