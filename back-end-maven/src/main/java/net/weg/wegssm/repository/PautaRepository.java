package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para a pauta
 */
@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    /**
     * Método para retornar uma pauta com o numero sequencial passado por parâmetro
     *
     * @param numeroSequencial - numero sequencial
     * @return - pauta
     */
    Page<Pauta> findByNumeroSequencial(String numeroSequencial, Pageable pageable);

    /**
     * Método para buscar uma pauta através de uma proposta
     *
     * @param proposta - proposta
     * @return - pauta
     */
    Pauta findByPropostasContaining(Proposta proposta);

    /**
     * Função para retornar as propostas que pertencem a uma pauta, utilizado par ao calculo do score
     *
     * @return - lista de propostas
     */
    @Query("SELECT p FROM Pauta p LEFT JOIN FETCH p.propostas")
    List<Pauta> findAllScore();
}
