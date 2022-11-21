package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

}

