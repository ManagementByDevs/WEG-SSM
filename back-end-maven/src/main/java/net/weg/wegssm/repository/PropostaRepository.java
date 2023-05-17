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

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndEmPautaAndEmAta(boolean b, Usuario analista, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndEmPautaAndEmAta(boolean b, Status status, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndEmPautaAndEmAta(boolean b, Status status, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndEmPautaAndEmAta(boolean b, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndEmPautaAndEmAta(boolean b, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndEmPautaAndEmAta(boolean b, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndEmPautaAndEmAta(boolean b, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndEmPautaAndEmAta(boolean b, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndEmPautaAndEmAta(boolean b, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmAta(boolean b, Status status, Usuario analista, String titulo, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndEmAta(boolean b, Status status, Usuario analista, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndEmAta(boolean b, Usuario analista, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndEmAta(boolean b, Usuario analista, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndEmAta(boolean b, Usuario analista, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndEmAta(boolean b, Usuario analista, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmAta(boolean b, Status status, String titulo, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndEmAta(boolean b, Usuario analista, String titulo, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndEmAta(boolean b, Usuario analista, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndEmAta(boolean b, Status status, String titulo, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmAta(boolean b, Status status, String titulo, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndEmAta(boolean b, Status status, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndEmAta(boolean b, Status status, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndEmAta(boolean b, Status status, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndEmAta(boolean b, Status status, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndEmAta(boolean b, Status status, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndEmAta(boolean b, Status status, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndEmAta(boolean b, Status status, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndEmAta(boolean b, String titulo, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndEmAta(boolean b, String titulo, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndEmAta(boolean b, String titulo, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndEmAta(boolean b, String titulo, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndEmAta(boolean b, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTituloContainingAndEmAta(boolean b, String titulo, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndEmAta(boolean b, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndEmAta(boolean b, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndEmAta(boolean b, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndEmAta(boolean b, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndEmAta(boolean b, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndEmAta(boolean b, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndDepartamentoAndEmAta(boolean b, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndEmAta(boolean b, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndTamanhoAndEmAta(boolean b, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndSolicitanteAndEmAta(boolean b, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndEmAta(boolean b, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndEmAta(boolean b, Usuario gerente, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndEmAta(boolean b, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndEmAta(boolean b, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable);

    Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable);
}
