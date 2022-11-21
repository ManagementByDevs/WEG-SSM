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

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForum(status, titulo, gerente, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndForum(status, titulo, gerente, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndGerente(status, titulo, gerente, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndDepartamento(status, titulo, forum, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndTamanho(status, titulo, forum, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForumAndSolicitante(status, titulo, forum, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndForum(Status status, String titulo, Forum forum, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndForum(status, titulo, forum, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndDepartamento(status, titulo, departamento, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndTamanho(Status status, String titulo, String tamanho, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndTamanho(status, titulo, tamanho, pageable);
    }

    public Page<Demanda> findByStatusAndTituloAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return demandaRepository.findByStatusAndTituloAndSolicitante(status, titulo, solicitante, pageable);
    }

    public Page<Demanda> findByStatusAndTitulo(Status status, String titulo, Pageable pageable) {
        return demandaRepository.findByStatusAndTitulo(status, titulo, pageable);
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
