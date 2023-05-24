package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para a proposta
 */
@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

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

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerente(Boolean visibilidade, Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForum(Boolean visibilidade, Status status, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(Boolean visibilidade, Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanho(Boolean visibilidade, Status status, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContaining(Boolean visibilidade, Status status, String titulo, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForum(boolean b, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamento(boolean b, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanho(boolean b, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitante(boolean b, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerente(boolean b, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamento(boolean b, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanho(boolean b, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitante(boolean b, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForum(boolean b, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanho(boolean b, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitante(boolean b, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamento(boolean b, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitante(boolean b, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanho(boolean b, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitante(boolean b, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContaining(boolean b, String titulo, Pageable pageable);

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

    List<Proposta> findByTitulo(String titulo);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForum(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerente(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForum(boolean b, Status status, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanho(boolean b, Status status, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContaining(boolean b, Status status, Usuario analista, String titulo, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForum(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(boolean b, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForum(boolean b, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamento(boolean b, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanho(boolean b, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitante(boolean b, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContaining(boolean b, Usuario analista, String titulo, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String presenteEm, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndPresenteEm(boolean b, Usuario analista, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndPresenteEm(boolean b, Status status, String titulo, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndPresenteEm(boolean b, Status status, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndPresenteEm(boolean b, Status status, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndPresenteEm(boolean b, Status status, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndPresenteEm(boolean b, String titulo, Usuario gerente, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndPresenteEm(boolean b, String titulo, Forum forum, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, String titulo, Departamento departamento, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndPresenteEm(boolean b, String titulo, String tamanho, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario solicitante, String presenteEm, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndPresenteEm(boolean b, String titulo, String presenteEm, Pageable pageable);

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

    List<Proposta> findByStatusNotAndStatusNot(Status status, Status statusSecundario);
}
