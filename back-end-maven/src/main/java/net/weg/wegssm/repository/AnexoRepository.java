package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe repository para os anexos
 */
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    /**
     * Função para buscar um anexo pelo seu nome
     */
    Anexo findByNome(String nome);

    /**
     * Função para excluir um anexo pelo seu nome
     */
    void deleteByNome(String nome);
}
