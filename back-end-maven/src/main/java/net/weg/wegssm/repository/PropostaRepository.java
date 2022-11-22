package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    /**
     * Método para encontrar uma proposta pelo código PPM
     */
    Optional<Proposta> findByCodigoPPM(Long ppm);

    /**
     * Método para verificar se existe uma proposta com o código PPM
     */
    Boolean existsByCodigoPPM(Long ppm);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndForum(Status status, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndTamanho(Status status, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContainingAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTituloContaining(Status status, String titulo, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndDepartamento(Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndTamanho(Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForumAndSolicitante(Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndForum(Status status, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndDepartamentoAndTamanho(Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndDepartamentoAndSolicitante(Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndDepartamento(Status status, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndTamanhoAndSolicitante(Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndTamanho(Status status, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndGerenteAndSolicitante(Status status, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndGerente(Status status, Usuario gerente, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndDepartamentoAndTamanho(Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndDepartamentoAndSolicitante(Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndDepartamento(Status status, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndTamanhoAndSolicitante(Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndTamanho(Status status, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndForumAndSolicitante(Status status, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndForum(Status status, Forum forum, Pageable pageable);

    Page<Proposta> findByStatusAndDepartamentoAndTamanhoAndSolicitante(Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndDepartamentoAndTamanho(Status status, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndDepartamentoAndSolicitante(Status status, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndDepartamento(Status status, Departamento departamento, Pageable pageable);

    Page<Proposta> findByStatusAndTamanhoAndSolicitante(Status status, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatusAndTamanho(Status status, String tamanho, Pageable pageable);

    Page<Proposta> findByStatusAndSolicitante(Status status, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByStatus(Status status, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndTamanho(Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndForum(Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndDepartamento(Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndTamanho(Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerenteAndSolicitante(Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndGerente(Long codigoPPM, String titulo, Usuario gerente, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndDepartamento(Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndTamanho(Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForumAndSolicitante(Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndForum(Long codigoPPM, String titulo, Forum forum, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndDepartamentoAndTamanho(Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndDepartamentoAndSolicitante(Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndDepartamento(Long codigoPPM, String titulo, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndTamanhoAndSolicitante(Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndTamanho(Long codigoPPM, String titulo, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTituloAndSolicitante(Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTitulo(Long codigoPPM, String titulo, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndDepartamento(Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndTamanho(Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForumAndSolicitante(Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndForum(Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndDepartamentoAndTamanho(Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndDepartamentoAndSolicitante(Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndDepartamento(Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndTamanhoAndSolicitante(Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndTamanho(Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerenteAndSolicitante(Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndGerente(Long codigoPPM, Usuario gerente, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndDepartamentoAndTamanho(Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndDepartamentoAndSolicitante(Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndDepartamento(Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndTamanhoAndSolicitante(Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndTamanho(Long codigoPPM, Forum forum, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForumAndSolicitante(Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndForum(Long codigoPPM, Forum forum, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndDepartamentoAndTamanho(Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndDepartamentoAndSolicitante(Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndDepartamento(Long codigoPPM, Departamento departamento, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTamanhoAndSolicitante(Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndTamanho(Long codigoPPM, String tamanho, Pageable pageable);

    Page<Proposta> findByCodigoPPMAndSolicitante(Long codigoPPM, Usuario solicitante, Pageable pageable);

    Page<Proposta> findByCodigoPPM(Long codigoPPM, Pageable pageable);
}
