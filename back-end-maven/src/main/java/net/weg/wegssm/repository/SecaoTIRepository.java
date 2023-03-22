package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.SecaoTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Classe repository para as seções de TI */
@Repository
public interface SecaoTIRepository extends JpaRepository<SecaoTI, Long> {
}
