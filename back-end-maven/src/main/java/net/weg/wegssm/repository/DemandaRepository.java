package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe repository para as demandas
 */
@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {

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
     * Método para listar as demandas pelo fórum, com paginação
     *
     * @param forum Fórum para a filtragem da demanda
     * @param pageable Pageable para ordenação das demandas
     * @return Página ordenada com as demandas filtradas
     */
    Page<Demanda> findByForum(Forum forum, Pageable pageable);

    /**
     * Método para listar as demandas pelo departamento, com paginação
     *
     * @param departamento Departamento para filtragem das demandas
     * @param pageable Pageable para ordenação das demandas
     * @return Página com as demandas filtradas e ordenadas
     */
    Page<Demanda> findByDepartamento(Departamento departamento, Pageable pageable);

    /**
     * Função para buscar todas as demandas excluindo dois status recebidos como parâmetros, assim como recebendo as demandas contendo
     * seus respectivos benefícios por conta do "LEFT JOIN"
     * @param status Primeiro status para filtragem da demanda
     * @param statusSecundario Segundo status para filtragem da demanda
     * @return Lista com as demandas filtradas
     */
    @Query("SELECT d FROM Demanda d LEFT JOIN FETCH d.beneficios WHERE d.status != :status and d.status != :status_secundario")
    List<Demanda> findByStatusNotAndStatusNot(@Param("status") Status status, @Param("status_secundario") Status statusSecundario);

    /**
     * Métodos utilizados no filtro
     */

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndTamanho(Status status, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCase(Status status, String titulo, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndForum(Status status, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

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

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndForum(String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndDepartamento(String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndTamanho(String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerenteAndSolicitante(String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndGerente(String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndDepartamento(String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndTamanho(String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForumAndSolicitante(String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndForum(String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndDepartamentoAndTamanho(String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndDepartamento(String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndTamanhoAndSolicitante(String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndTamanho(String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCaseAndSolicitante(String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

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

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForum(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(Usuario analista, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndTamanho(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndStatus(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerente(Usuario analista, String titulo, Usuario solicitante, Usuario gerente, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndStatus(Usuario analista, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndGerente(Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(Usuario analista, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndTamanho(Usuario analista, String titulo, Usuario solicitante, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(Usuario analista, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndStatus(Usuario analista, String titulo, Usuario solicitante, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForum(Usuario analista, String titulo, Usuario solicitante, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndStatus(Usuario analista, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndForum(Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndTamanho(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(Usuario analista, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndStatus(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamento(Usuario analista, String titulo, Usuario solicitante, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(Usuario analista, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamento(Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndTamanhoAndStatus(Usuario analista, String titulo, Usuario solicitante, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndTamanho(Usuario analista, String titulo, Usuario solicitante, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndStatus(Usuario analista, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndTamanho(Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(Usuario analista, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitante(Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCaseAndStatus(Usuario analista, String titulo, Status status, Pageable pageable);

    Page<Demanda> findPageByAnalistaAndTituloContainingIgnoreCase(Usuario analista, String titulo, Pageable pageable);

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

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(Long id, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Long id, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Long id, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(Long id, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndStatus(Long id, Usuario analista, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerente(Long id, Usuario analista, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(Long id, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(Long id, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(Long id, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndStatus(Long id, Usuario analista, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForum(Long id, Usuario analista, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Long id, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(Long id, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(Long id, Usuario analista, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(Long id, Usuario analista, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(Long id, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndStatus(Long id, Usuario analista, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(Long id, Usuario analista, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(Long id, Usuario analista, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(Long id, Usuario analista, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCaseAndStatus(Long id, Usuario analista, String titulo, Status status, Pageable pageable);

    Page<Demanda> findByIdAndAnalistaAndTituloContainingIgnoreCase(Long id, Usuario analista, String titulo, Pageable pageable);

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

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Long id, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(Long id, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(Long id, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(Long id, String titulo, Usuario gerente, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndForum(Long id, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(Long id, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(Long id, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(Long id, String titulo, Usuario gerente, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Long id, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(Long id, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(Long id, String titulo, Usuario gerente, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Long id, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndStatus(Long id, String titulo, Usuario gerente, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(Long id, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerenteAndStatus(Long id, String titulo, Usuario gerente, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndGerente(Long id, String titulo, Usuario gerente, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Long id, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(Long id, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(Long id, String titulo, Forum forum, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamento(Long id, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Forum forum, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(Long id, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(Long id, String titulo, Forum forum, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndTamanho(Long id, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndStatus(Long id, String titulo, Forum forum, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndSolicitante(Long id, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForumAndStatus(Long id, String titulo, Forum forum, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndForum(Long id, String titulo, Forum forum, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(Long id, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(Long id, String titulo, Departamento departamento, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Long id, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndStatus(Long id, String titulo, Departamento departamento, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(Long id, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(Long id, String titulo, Departamento departamento, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndDepartamento(Long id, String titulo, Departamento departamento, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndStatus(Long id, String titulo, String tamanho, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(Long id, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndTamanhoAndStatus(Long id, String titulo, String tamanho, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndTamanho(Long id, String titulo, String tamanho, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(Long id, String titulo, Usuario solicitante, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndSolicitante(Long id, String titulo, Usuario solicitante, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCaseAndStatus(Long id, String titulo, Status status, Pageable pageable);

    Page<Demanda> findByIdAndTituloContainingIgnoreCase(Long id, String titulo, Pageable pageable);

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