package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Historico;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe repository para o histórico
 */
@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    /**
     * Método para listar o histórico pelo id do usuário
     *
     * @param autor
     * @return
     */
    Optional<Historico> findByAutor(Usuario autor);

    /**
     * Método que verifica se existe um histórico com o id do usuário passado por parâmetro
     *
     * @param autor
     * @return
     */
    Boolean existsByAutor(Usuario autor);

}
