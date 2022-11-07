package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Escopo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscopoRepository extends JpaRepository<Escopo, Long> {

        Boolean existsByTitle(String titulo);

        Boolean existsByPercentagem(Long porcentagem);

        Optional<Escopo> findByTitle(String titulo);

        Optional<Escopo> findByPercentagem(Long porcentagem);
}
