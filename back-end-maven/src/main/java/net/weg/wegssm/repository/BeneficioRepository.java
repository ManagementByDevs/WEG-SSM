package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.TipoBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {

    /**
     * Método para verificar se existe algum benefício com o tipo de benefício informado
     * @param tipoBeneficio
     * @return
     */
    Boolean existsByTipoBeneficio(TipoBeneficio tipoBeneficio);

}

