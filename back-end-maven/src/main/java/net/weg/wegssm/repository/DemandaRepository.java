package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
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

    /**
     * Método para listar as demandas a partir de um forum ( id )
     *
     * @param forum
     * @return
     */
    List<Demanda> findByForum(Forum forum);

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
     * Método para listar todas as demandas a partir de um título
     *
     * @param titulo
     * @return
     */

//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);
//
//    List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable);
}

