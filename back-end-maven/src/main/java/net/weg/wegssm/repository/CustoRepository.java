package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Custo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustoRepository extends JpaRepository<Custo, Long> {

}

