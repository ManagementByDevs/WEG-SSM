package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para a ata
 */
@Repository
public interface AtaRepository extends JpaRepository<Ata, Long> {

    /**
     * Método para listar a ata pelo número sequencial
     *
     * @param numeroSequencial
     * @return
     */
    Optional<Ata> findByNumeroSequencial(String numeroSequencial);

    /**
     * Método que verifica se existe uma ata com o número sequencial passado por parâmetro
     *
     * @param numeroSequencial
     * @return
     */
    Boolean existsByNumeroSequencial(String numeroSequencial);

    /**
     * Método que retorna uma ata que contenha a proposta passada por parâmetro
     *
     * @param proposta
     * @return
     */
    Ata findByPropostasContaining(Proposta proposta);

    List<Ata> findByPublicadaDgNot(Boolean publicadaDg);
}

