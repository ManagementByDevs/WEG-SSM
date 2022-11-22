package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandaService {

    private DemandaRepository demandaRepository;

    public DemandaService(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    public List<Demanda> findAll() {
        return demandaRepository.findAll();
    }

    public Page<Demanda> findAll(Pageable pageable) {
        return demandaRepository.findAll(pageable);
    }

    public Optional<Demanda> findById(Long id) {
        return demandaRepository.findById(id);
    }

    public List<Demanda> findByStatus(Status status) {
        return demandaRepository.findByStatus(status);
    }

    public Page<Demanda> findByStatus(Status status, Pageable pageable) {
        return demandaRepository.findByStatus(status, pageable);
    }

    public List<Demanda> findByForum(Forum forum) {
        return demandaRepository.findByForum(forum);
    }

    public List<Demanda> findByDepartamento(Departamento departamento) {
        return demandaRepository.findByDepartamento(departamento);
    }

    public boolean existsById(Long id) {
        return demandaRepository.existsById(id);
    }

    public <S extends Demanda> S save(S entity) {
        return demandaRepository.save(entity);
    }

    public void deleteById(Long id) {
        demandaRepository.deleteById(id);
    }
    
    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForum(status, titulo, gerente, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndForum(status, titulo, gerente, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndGerente(status, titulo, gerente, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndDepartamento(status, titulo, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndTamanho(status, titulo, forum, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForumAndSolicitante(status, titulo, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndForum(Status status, String titulo, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndForum(status, titulo, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndDepartamento(status, titulo, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndTamanho(Status status, String titulo, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndTamanho(status, titulo, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContainingAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContainingAndSolicitante(status, titulo, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloContaining(Status status, String titulo, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloContaining(status, titulo, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(status, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(status, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndDepartamento(Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndDepartamento(status, gerente, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(status, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndTamanho(Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndTamanho(status, gerente, forum, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForumAndSolicitante(Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForumAndSolicitante(status, gerente, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndForum(Status status, Usuario gerente, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndForum(status, gerente, forum, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndDepartamentoAndTamanho(Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndDepartamentoAndTamanho(status, gerente, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndDepartamentoAndSolicitante(Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndDepartamentoAndSolicitante(status, gerente, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndDepartamento(Status status, Usuario gerente, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndDepartamento(status, gerente, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndTamanhoAndSolicitante(Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndTamanhoAndSolicitante(status, gerente, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndTamanho(Status status, Usuario gerente, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndTamanho(status, gerente, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndGerenteAndSolicitante(Status status, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndGerenteAndSolicitante(status, gerente, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndGerente(Status status, Usuario gerente, Pageable pageable) {
        return demandaRepository.findByStatusAndGerente(status, gerente, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(status, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndDepartamentoAndTamanho(Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndDepartamentoAndTamanho(status, forum, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndDepartamentoAndSolicitante(Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndDepartamentoAndSolicitante(status, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndDepartamento(Status status, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndDepartamento(status, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndTamanhoAndSolicitante(Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndTamanhoAndSolicitante(status, forum, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndTamanho(Status status, Forum forum, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndTamanho(status, forum, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndForumAndSolicitante(Status status, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndForumAndSolicitante(status, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndForum(Status status, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndForum(status, forum, pageable);
    }

    public Page<Demanda> findByStatusAndDepartamentoAndTamanhoAndSolicitante(Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndDepartamentoAndTamanhoAndSolicitante(status, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndDepartamentoAndTamanho(Status status, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndDepartamentoAndTamanho(status, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndDepartamentoAndSolicitante(Status status, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndDepartamentoAndSolicitante(status, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndDepartamento(Status status, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndDepartamento(status, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTamanhoAndSolicitante(Status status, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTamanhoAndSolicitante(status, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTamanho(Status status, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTamanho(status, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndSolicitante(Status status, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndSolicitante(status, solicitante, pageable);
    }

}
