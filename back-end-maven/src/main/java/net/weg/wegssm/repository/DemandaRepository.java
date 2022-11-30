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
}