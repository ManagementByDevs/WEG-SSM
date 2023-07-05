package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.EscopoProposta;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe repository para o escopo da proposta
 */
@Repository
public interface EscopoPropostaRepository extends JpaRepository<EscopoProposta, Long> {

    /**
     * Método para listar um escopo proposta a partir de um solicitante, com paginação
     *
     * @param usuario - usuário
     * @param pageable - paginação
     * @return - lista de escopos
     */
    Page<EscopoProposta> findBySolicitante(Usuario usuario, Pageable pageable);

    /**
     * Método para listar um escopo proposta a partir de um solicitante e um título, com paginação
     *
     * @param usuario - usuário
     * @param titulo - título
     * @param pageable - paginação
     * @return - lista de escopos
     */
    Page<EscopoProposta> findBySolicitanteAndTitulo(Usuario usuario, String titulo, Pageable pageable);

    /**
     * Método para listar um escopo a partir de uma demanda
     *
     * @param demanda - demanda
     * @return - lista de escopos
     */
    List<EscopoProposta> findByDemanda(Demanda demanda);

}
