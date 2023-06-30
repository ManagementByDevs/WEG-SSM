package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.TipoBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository para os benefícios
 */
@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> { }

