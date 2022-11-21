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

//    public List<Demanda> findByUsuario(Usuario usuario) {
//        return demandaRepository.findByUsuario(usuario);
//    }

    public List<Demanda> findByForum(Forum forum) {
        return demandaRepository.findByForum(forum);
    }

    public List<Demanda> findByDepartamento(Departamento departamento) { return demandaRepository.findByDepartamento(departamento); }

    public boolean existsById(Long id) {
        return demandaRepository.existsById(id);
    }

    public <S extends Demanda> S save(S entity) {
        return demandaRepository.save(entity);
    }

    public void deleteById(Long id) {
        demandaRepository.deleteById(id);
    }

//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, forum, departamento, numero, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumero(status, titulo, gerente, forum, departamento, numero, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumeroAndSolicitante(status, titulo, gerente, forum, departamento, numero, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Long numero, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumero(status, titulo, gerente, forum, departamento, numero, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, forum, numero, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumero(status, titulo, gerente, forum, numero, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndNumeroAndSolicitante(status, titulo, gerente, forum, numero, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndNumero(Status status, String titulo, Usuario gerente, Forum forum, Long numero, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndNumero(status, titulo, gerente, forum, numero, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndForum(status, titulo, gerente, forum, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, departamento, numero, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumero(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumero(status, titulo, gerente, departamento, numero, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable);
//    }
//
//    public List<Demanda> findByStatusAndTituloAndGerenteAndDepartamentoAndNumeroAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Long numero, Usuario solicitante, Pageable pageable) {
//        return demandaRepository.findByStatusAndTituloAndGerenteAndDepartamentoAndNumeroAndSolicitante(status, titulo, gerente, departamento, numero, solicitante, pageable);
//    }

}
