package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AtaRepository extends JpaRepository<Ata, Long> {

    /**
     * Método para listar a ata pelo número sequencial
     */
    Optional<Ata> findByNumeroSequencial(String numeroSequencial);

    /**
     * Método que verifica se existe uma ata com o número sequencial passado por parâmetro
     */
    Boolean existsByNumeroSequencial(String numeroSequencial);

    Ata findByPropostasContaining(Proposta proposta);
}

