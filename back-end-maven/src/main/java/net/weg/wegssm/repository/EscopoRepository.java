package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para os escopos
 */
@Repository
public interface EscopoRepository extends JpaRepository<Escopo, Long> {

    /**
     * Método para buscar um escopo a partir de um usuário, com paginação
     *
     * @param usuario - usuário
     * @param pageable - paginação
     * @return - escopo
     */
    Page<Escopo> findByUsuario(Usuario usuario, Pageable pageable);

    /**
     * Método para listar um escopo a partir de um usuário e de um título, com paginação
     *
     * @param usuario - usuário
     * @param titulo - título
     * @param pageable - paginação
     * @return - escopo
     */
    Page<Escopo> findByUsuarioAndTituloContaining(Usuario usuario, String titulo, Pageable pageable);

}
