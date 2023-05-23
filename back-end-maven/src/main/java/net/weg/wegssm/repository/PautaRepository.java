package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe repository para a pauta
 */
@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    /**
     * @param numeroSequencial
     * @return
     */
    Boolean existsByNumeroSequencial(Long numeroSequencial);

    /**
     * Método para retornar uma pauta com o numero sequencial passado por parâmetro
     *
     * @param numeroSequencial
     * @return
     */
    Optional<Pauta> findByNumeroSequencial(Long numeroSequencial);

    /**
     * Método para buscar uma pauta através de uma proposta
     *
     * @param proposta
     * @return
     */
    Pauta findByPropostasContaining(Proposta proposta);

}
