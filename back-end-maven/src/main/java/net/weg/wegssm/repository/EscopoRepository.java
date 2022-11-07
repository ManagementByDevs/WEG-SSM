package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Escopo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscopoRepository extends JpaRepository<Escopo, Long> {

        /**
         * Método para retornar se existe um escopo com o título passado por parâmetro
         * @param titulo
         * @return
         */
        Boolean existsByTitulo(String titulo);

        /**
         * Método para retornar se existe um escopo com a porcentagem passada por parâmetro
         * @param porcentagem
         * @return
         */
        Boolean existsByPorcentagem(Long porcentagem);

        /**
         * Método para retornar um escopo com o título passado por parâmetro
         * @param titulo
         * @return
         */
        Optional<Escopo> findByTitulo(String titulo);

        /**
         * Método para retornar um escopo com a porcentagem passada por parâmetro
         * @param porcentagem
         * @return
         */
        Optional<Escopo> findByPorcentagem(Long porcentagem);
}
