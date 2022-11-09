package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustoRepository extends JpaRepository<Custo, Long> {

    /**
     * Método para listar os custos a partir de uma proposta ( id )
     * @param proposta
     * @return
     */
//    List<Custo> findByProposta(Proposta proposta);
}

