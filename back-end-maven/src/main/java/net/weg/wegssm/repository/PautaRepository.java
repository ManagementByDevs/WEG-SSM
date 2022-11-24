package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    /**
     * Método para retornar se existe uma pauta com o numero sequencial passado por parâmetro
     */
    Boolean existsByNumeroSequencial(Long numeroSequencial);

    /**
     * Método para retornar uma pauta com o numero sequencial passado por parâmetro
     */
    Optional<Pauta> findByNumeroSequencial(Long numeroSequencial);


    Pauta findByPropostasContaining(Proposta proposta);
}
