package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.TabelaCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaCustoRepository extends JpaRepository<TabelaCusto, Long> {
}
