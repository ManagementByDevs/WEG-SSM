package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AtaRepository extends JpaRepository<Ata, Long> {

    Optional<Ata> findByNumeroSequencial(String numeroSequencial);

//    Optional<Ata> findByData(Date data);

    Boolean existsByNumeroSequencial(String numeroSequencial);

}

