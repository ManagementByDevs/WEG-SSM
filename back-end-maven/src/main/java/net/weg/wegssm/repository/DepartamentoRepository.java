package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository para os departamentos
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
