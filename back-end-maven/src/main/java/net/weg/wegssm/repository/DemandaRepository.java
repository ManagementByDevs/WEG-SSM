package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para as demandas
 */
@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    /**
     * Método para listar as demandas pelo status
     *
     * @param status
     * @return
     */
    List<Demanda> findByStatus(Status status);

    /**
     * Método para listar a demanda pelo status, com paginação
     *
     * @param status
     * @param pageable
     * @return
     */
    Page<Demanda> findByStatus(Status status, Pageable pageable);

    /**
     * Método para lista a demanda por id, com paginação
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<Demanda> findById(Long id, Pageable pageable);

    /**
     * Método para listar as demandas a partir de um forum ( id )
     *
     * @param forum
     * @return
     */
    List<Demanda> findByForum(Forum forum);

    /**
     * Método para listar as demandas pelo fórum, com paginação
     *
     * @param forum
     * @param pageable
     * @return
     */
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

    /**
     * Método para listar as demandas pelo departamento, com paginação
     *
     * @param departamento
     * @param pageable
     * @return
     */
    Page<Demanda> findByDepartamento(Departamento departamento, Pageable pageable);

    /**
     * Métodos utilizados no filtro
     */

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

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndForum(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamento(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerenteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndGerente(Long id, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndDepartamento(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndTamanho(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForumAndStatus(Long id, Usuario analista, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndForum(Long id, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndDepartamento(Long id, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndTamanhoAndStatus(Long id, Usuario analista, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndTamanho(Long id, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndSolicitante(Long id, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingAndStatus(Long id, Usuario analista, String titulo, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContaining(Long id, Usuario analista, String titulo, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndDepartamento(Long id, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndTamanho(Long id, Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndSolicitante(Long id, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForumAndStatus(Long id, Usuario analista, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndForum(Long id, Usuario analista, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanho(Long id, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndSolicitante(Long id, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamentoAndStatus(Long id, Usuario analista, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndDepartamento(Long id, Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndTamanhoAndSolicitante(Long id, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndTamanhoAndStatus(Long id, Usuario analista, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndTamanho(Long id, Usuario analista, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndSolicitanteAndStatus(Long id, Usuario analista, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndSolicitante(Long id, Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerenteAndStatus(Long id, Usuario analista, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndGerente(Long id, Usuario analista, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamentoAndStatus(Long id, Usuario analista, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndDepartamento(Long id, Usuario analista, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndTamanhoAndStatus(Long id, Usuario analista, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndTamanho(Long id, Usuario analista, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndSolicitanteAndStatus(Long id, Usuario analista, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndSolicitante(Long id, Usuario analista, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForumAndStatus(Long id, Usuario analista, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndForum(Long id, Usuario analista, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndTamanho(Long id, Usuario analista, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndSolicitante(Long id, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamentoAndStatus(Long id, Usuario analista, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndDepartamento(Long id, Usuario analista, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTamanhoAndSolicitante(Long id, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTamanhoAndStatus(Long id, Usuario analista, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTamanho(Long id, Usuario analista, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndSolicitanteAndStatus(Long id, Usuario analista, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndSolicitante(Long id, Usuario analista, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndStatus(Long id, Usuario analista, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalista(Long id, Usuario analista, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndDepartamento(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndTamanho(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForumAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndForum(Long id, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Long id, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamentoAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndDepartamento(Long id, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndTamanho(Long id, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndSolicitante(Long id, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerenteAndStatus(Long id, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndGerente(Long id, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndTamanho(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndSolicitante(Long id, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamentoAndStatus(Long id, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndDepartamento(Long id, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndTamanhoAndSolicitante(Long id, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndTamanhoAndStatus(Long id, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndTamanho(Long id, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndSolicitante(Long id, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForumAndStatus(Long id, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndForum(Long id, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndTamanho(Long id, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndSolicitante(Long id, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamentoAndStatus(Long id, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndDepartamento(Long id, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndTamanhoAndSolicitante(Long id, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndTamanhoAndStatus(Long id, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndTamanho(Long id, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndSolicitanteAndStatus(Long id, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndSolicitante(Long id, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingAndStatus(Long id, String titulo, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContaining(Long id, String titulo, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndTamanho(Long id, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamentoAndStatus(Long id, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndDepartamento(Long id, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndTamanhoAndSolicitante(Long id, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndTamanhoAndStatus(Long id, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndTamanho(Long id, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndSolicitanteAndStatus(Long id, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndSolicitante(Long id, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForumAndStatus(Long id, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndForum(Long id, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndTamanho(Long id, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndSolicitante(Long id, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamentoAndStatus(Long id, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndDepartamento(Long id, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndTamanhoAndSolicitante(Long id, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndTamanhoAndStatus(Long id, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndTamanho(Long id, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndSolicitanteAndStatus(Long id, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndSolicitante(Long id, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndGerenteAndStatus(Long id, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndGerente(Long id, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndTamanho(Long id, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndSolicitante(Long id, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamentoAndStatus(Long id, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndDepartamento(Long id, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndTamanhoAndSolicitante(Long id, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndForumAndTamanhoAndStatus(Long id, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndTamanho(Long id, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndForumAndSolicitanteAndStatus(Long id, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForumAndSolicitante(Long id, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndForumAndStatus(Long id, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndForum(Long id, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndTamanhoAndSolicitante(Long id, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndTamanhoAndStatus(Long id, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndTamanho(Long id, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndSolicitanteAndStatus(Long id, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndSolicitante(Long id, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndDepartamentoAndStatus(Long id, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndDepartamento(Long id, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTamanhoAndSolicitanteAndStatus(Long id, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTamanhoAndSolicitante(Long id, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTamanhoAndStatus(Long id, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTamanho(Long id, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndSolicitanteAndStatus(Long id, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndSolicitante(Long id, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndStatus(Long id, Status status, Pageable pageable);
}