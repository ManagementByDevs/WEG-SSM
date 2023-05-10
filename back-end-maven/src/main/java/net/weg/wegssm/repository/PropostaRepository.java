package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /**
     * Método para encontrar uma proposta pelo código PPM
     */
    Proposta findByCodigoPPM(Long ppm);

    /**
     * Método para verificar se existe uma proposta com o código PPM
     */
    Boolean existsByCodigoPPM(Long ppm);

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

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerente(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContaining(Boolean visibilidade, Long codigoPPM, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerente(Boolean visibilidade, Long codigoPPM, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndForum(Boolean visibilidade, Long codigoPPM, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamento(Boolean visibilidade, Long codigoPPM, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanho(Boolean visibilidade, Long codigoPPM, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPM(Boolean visibilidade, Long codigoPPM, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForum(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerente(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForum(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContaining(boolean b, Long codigoPPM, Status status, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForum(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamento(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerente(boolean b, Long codigoPPM, Status status, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanho(boolean b, Long codigoPPM, Status status, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForum(boolean b, Long codigoPPM, Status status, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamento(boolean b, Long codigoPPM, Status status, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTamanho(boolean b, Long codigoPPM, Status status, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatus(boolean b, Long codigoPPM, Status status, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContaining(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerente(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPM(boolean b, Status status, Usuario analista, Long codigoPPM, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForum(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContaining(boolean b, Usuario analista, Long codigoPPM, String titulo, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForum(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerente(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForum(boolean b, Usuario analista, Long codigoPPM, Forum forum, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanho(boolean b, Usuario analista, Long codigoPPM, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPM(boolean b, Usuario analista, Long codigoPPM, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndStatusAndAnalista(boolean b, Status status, Usuario analista, Long codigoPPM, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable);

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

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanho(boolean b, Usuario analista, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitante(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamento(boolean b, Usuario analista, Departamento departamento, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitante(boolean b, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanho(boolean b, Usuario analista, String tamanho, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitante(boolean b, Usuario analista, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalista(boolean b, Usuario analista, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);
}
