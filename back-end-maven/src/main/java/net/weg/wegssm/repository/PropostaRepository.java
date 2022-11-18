package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /**
     * Método para encontrar uma proposta pelo código PPM
     * @param ppm
     * @return
     */
    Optional<Proposta> findByCodigoPPM(Long ppm);

    /**
     * Método para encontrar uma proposta por um título
     * @param titulo
     * @return
     */
//    List<Proposta> findByTitulo(String titulo);

    /**
     * Método para verificar se existe uma proposta com o código PPM
     * @param ppm
     * @return
     */
    Boolean existsByCodigoPPM(Long ppm);

}
