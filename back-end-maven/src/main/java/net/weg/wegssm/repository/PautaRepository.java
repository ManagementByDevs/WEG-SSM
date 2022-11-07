package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    /**
     * Método para retornar se existe uma pauta com o numero sequencial passado por parâmetro
     * @param numeroSequencial
     * @return
     */
    Boolean existsByNumeroSequencial(Long numeroSequencial);

    /**
     * Método para retornar se existe uma pauta com a data de fim passada por parâmetro
     * @param dataFim
     * @return
     */
    Boolean existsByDataFim(Date dataFim);

    /**
     * Método para retornar se existe uma pauta com a data de inicio passada por parâmetro
     * @param dataInicio
     * @return
     */
    Boolean existsByDataInicio(Date dataInicio);

    /**
     * Método para retornar uma pauta com o numero sequencial passado por parâmetro
     * @param numeroSequencial
     * @return
     */
    Optional<Pauta> findByNumeroSequencial(Long numeroSequencial);

    /**
     * Método para retornar uma pauta com a data de fim passada por parâmetro
     * @param dataFim
     * @return
     */
    Optional<Pauta> findByDataFim(Date dataFim);

    /**
     * Método para retornar uma pauta com a data de inicio passada por parâmetro
     * @param dataInicio
     * @return
     */
    Optional<Pauta> findByDataInicio(Date dataInicio);

}
