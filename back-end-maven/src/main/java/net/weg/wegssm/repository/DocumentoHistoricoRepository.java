package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.DocumentoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoHistoricoRepository extends JpaRepository<DocumentoHistorico, Long> {
}
