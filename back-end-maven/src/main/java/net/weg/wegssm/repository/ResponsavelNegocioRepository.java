package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.ResponsavelNegocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository para os responsáveis de negócio
 */
@Repository
public interface ResponsavelNegocioRepository extends JpaRepository<ResponsavelNegocio, Long> {

}

