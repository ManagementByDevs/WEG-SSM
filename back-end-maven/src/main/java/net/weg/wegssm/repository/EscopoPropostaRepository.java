package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.EscopoProposta;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscopoPropostaRepository extends JpaRepository<EscopoProposta, Long> {

    Page<EscopoProposta> findBySolicitante(Usuario usuario, Pageable pageable);

    Page<EscopoProposta> findBySolicitanteAndTitulo(Usuario usuario, String titulo, Pageable pageable);
}
