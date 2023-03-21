package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Bu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Classe repository para as BUs */
@Repository
public interface BuRepository extends JpaRepository<Bu, Long> {

}