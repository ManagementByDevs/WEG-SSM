package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para a proposta
 */
@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /**
     * Método para encontrar uma proposta pelo id
     *
     * @param id
     * @return
     */
    Optional<Proposta> findByDemandaId(Long id);

    /**
     * Método para verificar se existe uma proposta com o id de uma demanda
     *
     * @param id
     * @return
     */
    Boolean existsByDemandaId(Long id);

    /**
     * Método para encontrar uma proposta pelo código PPM
     *
     * @param ppm
     * @param pageable
     * @return
     */
    Page<Proposta> findByCodigoPPM(Long ppm, Pageable pageable);

    /**
     * Método para verificar se existe uma proposta com o código PPM
     *
     * @param ppm
     * @return
     */
    Boolean existsByCodigoPPM(Long ppm);

    /**
     * Métodos utilizados pelo filtro
     */

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerente(Boolean visibilidade, Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForum(Boolean visibilidade, Status status, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamento(Boolean visibilidade, Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanho(Boolean visibilidade, Status status, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCase(Boolean visibilidade, Status status, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForum(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamento(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanho(Boolean visibilidade, Status status, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerente(Boolean visibilidade, Status status, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamento(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanho(Boolean visibilidade, Status status, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitante(Boolean visibilidade, Status status, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForum(Boolean visibilidade, Status status, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamento(Boolean visibilidade, Status status, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanho(Boolean visibilidade, Status status, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndSolicitante(Boolean visibilidade, Status status, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatus(Boolean visibilidade, Status status, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerente(boolean b, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForum(boolean b, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamento(boolean b, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanho(boolean b, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitante(boolean b, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCase(boolean b, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamento(boolean b, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanho(boolean b, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitante(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForum(boolean b, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamento(boolean b, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanho(boolean b, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndSolicitante(boolean b, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerente(boolean b, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanho(boolean b, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitante(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamento(boolean b, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitante(boolean b, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanho(boolean b, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndSolicitante(boolean b, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForum(boolean b, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitante(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanho(boolean b, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitante(boolean b, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamento(boolean b, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitante(boolean b, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanho(boolean b, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndSolicitante(boolean b, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidade(boolean b, Pageable pageable);

    List<Proposta> findByTituloContainingIgnoreCase(String titulo);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerente(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForum(boolean b, Status status, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(boolean b, Status status, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCase(boolean b, Status status, Usuario analista, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForum(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerente(boolean b, Status status, Usuario analista, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamento(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanho(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForum(boolean b, Status status, Usuario analista, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamento(boolean b, Status status, Usuario analista, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanho(boolean b, Status status, Usuario analista, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitante(boolean b, Status status, Usuario analista, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalista(boolean b, Status status, Usuario analista, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerente(boolean b, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForum(boolean b, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(boolean b, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(boolean b, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(boolean b, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCase(boolean b, Usuario analista, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForum(boolean b, Usuario analista, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamento(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanho(boolean b, Usuario analista, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitante(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerente(boolean b, Usuario analista, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamento(boolean b, Usuario analista, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanho(boolean b, Usuario analista, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitante(boolean b, Usuario analista, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForum(boolean b, Usuario analista, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanho(boolean b, Usuario analista, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitante(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamento(boolean b, Usuario analista, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitante(boolean b, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanho(boolean b, Usuario analista, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitante(boolean b, Usuario analista, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalista(boolean b, Usuario analista, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndPresenteEm(boolean b, Status status, Usuario analista, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndPresenteEm(boolean b, Usuario analista, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndPresenteEm(boolean b, Usuario analista, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndPresenteEm(boolean b, Usuario analista, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Usuario analista, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Status status, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndPresenteEm(boolean b, Status status, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndPresenteEm(boolean b, Status status, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndPresenteEm(boolean b, Status status, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndPresenteEm(boolean b, Status status, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndPresenteEm(boolean b, Status status, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndPresenteEm(boolean b, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndPresenteEm(boolean b, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndPresenteEm(boolean b, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndPresenteEm(boolean b, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndPresenteEm(boolean b, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndPresenteEm(boolean b, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndSolicitanteAndPresenteEm(boolean b, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndPresenteEm(boolean b, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndPresenteEm(boolean b, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndPresenteEm(boolean b, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    /**
     * Função utilizada para buscar uma lista de propostas que não estejam com o status passado como parâmetro
     *
     * @param status
     * @param statusSecundario
     * @return
     */
    @Query("SELECT d FROM Proposta d LEFT JOIN FETCH d.beneficios WHERE d.status != :status and d.status != :status_secundario")
    List<Proposta> findByStatusNotAndStatusNot(@Param("status") Status status, @Param("status_secundario") Status statusSecundario);

}
