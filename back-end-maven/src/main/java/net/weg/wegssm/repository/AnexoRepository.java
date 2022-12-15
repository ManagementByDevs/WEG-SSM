package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {
    Anexo findByNome(String nome);

    void deleteByNome(String nome);
}
