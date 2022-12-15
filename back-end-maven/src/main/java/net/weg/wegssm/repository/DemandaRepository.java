package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    /**
     * Método para listar as demandas pelo status
     *
     * @param status
     * @return
     */
    List<Demanda> findByStatus(Status status);

    Page<Demanda> findByStatus(Status status, Pageable pageable);

    /**
     * Método para listar as demandas a partir de um forum ( id )
     *
     * @param forum
     * @return
     */
    List<Demanda> findByForum(Forum forum);

    Page<Demanda> findByForum(Forum forum, Pageable pageable);

    /**
     * Método para listar as demandas a partir de um usuário ( id )
     *
     * @param usuario
     * @return
     */
//    List<Demanda> findByUsuario(Usuario usuario);

    /**
     * Método para listar as demandas a partir de um departamento ( id )
     *
     * @param departamento
     * @return
     */
    List<Demanda> findByDepartamento(Departamento departamento);

    Page<Demanda> findByDepartamento(Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndTamanho(Status status, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContaining(Status status, String titulo, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndForum(Status status, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndDepartamento(Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndTamanho(Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForumAndSolicitante(Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndForum(Status status, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndDepartamentoAndTamanho(Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndDepartamentoAndSolicitante(Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndDepartamento(Status status, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndTamanhoAndSolicitante(Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndTamanho(Status status, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndGerenteAndSolicitante(Status status, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndGerente(Status status, Usuario gerente, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndDepartamentoAndTamanho(Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndDepartamentoAndSolicitante(Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndDepartamento(Status status, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndTamanhoAndSolicitante(Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndTamanho(Status status, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndForumAndSolicitante(Status status, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndForum(Status status, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndDepartamentoAndTamanhoAndSolicitante(Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndDepartamentoAndTamanho(Status status, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndDepartamentoAndSolicitante(Status status, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndDepartamento(Status status, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTamanhoAndSolicitante(Status status, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTamanho(Status status, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndSolicitante(Status status, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndDepartamento(String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndTamanho(String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForumAndSolicitante(String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndForum(String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndDepartamentoAndTamanho(String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndDepartamentoAndSolicitante(String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndDepartamento(String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndTamanhoAndSolicitante(String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndTamanho(String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerenteAndSolicitante(String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndGerente(String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndDepartamentoAndTamanho(String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndDepartamentoAndSolicitante(String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndDepartamento(String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndTamanhoAndSolicitante(String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndTamanho(String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForumAndSolicitante(String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndForum(String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByTituloContainingAndDepartamentoAndTamanhoAndSolicitante(String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndDepartamentoAndTamanho(String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndDepartamentoAndSolicitante(String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndDepartamento(String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingAndTamanhoAndSolicitante(String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingAndTamanho(String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingAndSolicitante(String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContaining(String titulo, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndDepartamentoAndTamanho(Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndDepartamentoAndSolicitante(Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndDepartamento(Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndTamanhoAndSolicitante(Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndTamanho(Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByGerenteAndForumAndSolicitante(Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndForum(Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByGerenteAndDepartamentoAndTamanhoAndSolicitante(Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndDepartamentoAndTamanho(Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByGerenteAndDepartamentoAndSolicitante(Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndDepartamento(Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByGerenteAndTamanhoAndSolicitante(Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerenteAndTamanho(Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByGerenteAndSolicitante(Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByGerente(Usuario gerente, Pageable pageable);

    Page<Demanda> findByForumAndDepartamentoAndTamanhoAndSolicitante(Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByForumAndDepartamentoAndTamanho(Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByForumAndDepartamentoAndSolicitante(Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByForumAndDepartamento(Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByForumAndTamanhoAndSolicitante(Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByForumAndTamanho(Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByForumAndSolicitante(Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByDepartamentoAndTamanhoAndSolicitante(Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByDepartamentoAndTamanho(Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByDepartamentoAndSolicitante(Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTamanhoAndSolicitante(String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTamanho(String tamanho, Pageable pageable);

    Page<Demanda> findBySolicitante(Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForum(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForumAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndForum(Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndDepartamento(Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndTamanho(Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndGerente(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerenteAndStatus(Usuario analista, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndGerente(Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndDepartamento(Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndTamanho(Usuario analista, String titulo, Usuario solicitante, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndTamanho(Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndForum(Usuario analista, String titulo, Usuario solicitante, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForumAndStatus(Usuario analista, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndForum(Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndDepartamentoAndTamanho(Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndDepartamentoAndStatus(Usuario analista, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndDepartamento(Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndTamanho(Usuario analista, String titulo, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndTamanhoAndStatus(Usuario analista, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndTamanho(Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitanteAndStatus(Usuario analista, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndSolicitante(Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingAndStatus(Usuario analista, String titulo, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContaining(Usuario analista, String titulo, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndTamanhoAndStatus(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndTamanho(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamentoAndStatus(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndDepartamento(Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndSolicitanteAndTamanhoAndStatus(Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndSolicitanteAndTamanho(Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndTamanhoAndStatus(Usuario analista, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndTamanho(Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndSolicitanteAndStatus(Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndSolicitante(Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForumAndStatus(Usuario analista, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndForum(Usuario analista, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndTamanhoAndStatus(Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndTamanho(Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndTamanhoAndStatus(Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndTamanho(Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndStatus(Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndSolicitante(Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamentoAndStatus(Usuario analista, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndDepartamento(Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndSolicitanteAndTamanhoAndStatus(Usuario analista, Usuario gerente, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndSolicitanteAndTamanho(Usuario analista, Usuario gerente, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndTamanhoAndStatus(Usuario analista, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndTamanho(Usuario analista, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndSolicitanteAndStatus(Usuario analista, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndSolicitante(Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerenteAndStatus(Usuario analista, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndGerente(Usuario analista, Usuario gerente, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndTamanhoAndStatus(Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndTamanho(Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndTamanho(Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndStatus(Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndSolicitante(Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamentoAndStatus(Usuario analista, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndDepartamento(Usuario analista, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndSolicitanteAndTamanhoAndStatus(Usuario analista, Forum forum, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndSolicitanteAndTamanho(Usuario analista, Forum forum, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndTamanhoAndStatus(Usuario analista, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndTamanho(Usuario analista, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndSolicitanteAndStatus(Usuario analista, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndSolicitante(Usuario analista, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForumAndStatus(Usuario analista, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndForum(Usuario analista, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndSolicitanteAndTamanhoAndStatus(Usuario analista, Departamento departamento, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndSolicitanteAndTamanho(Usuario analista, Departamento departamento, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndTamanhoAndStatus(Usuario analista, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndTamanho(Usuario analista, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndSolicitanteAndStatus(Usuario analista, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndSolicitante(Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamentoAndStatus(Usuario analista, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndDepartamento(Usuario analista, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndSolicitanteAndTamanhoAndStatus(Usuario analista, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndSolicitanteAndTamanho(Usuario analista, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTamanhoAndStatus(Usuario analista, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTamanho(Usuario analista, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndSolicitanteAndStatus(Usuario analista, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndSolicitante(Usuario analista, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndStatus(Usuario analista, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalista(Usuario analista, Pageable pageable);
}