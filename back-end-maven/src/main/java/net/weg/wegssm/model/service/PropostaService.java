package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.PropostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public List<Proposta> findAll() {
        return propostaRepository.findAll();
    }

    public Page<Proposta> findAll(Pageable pageable) {
        return propostaRepository.findAll(pageable);
    }

    public Optional<Proposta> findById(Long id) {
        return propostaRepository.findById(id);
    }

    public Proposta findByPpm(Long ppm) {
        return propostaRepository.findByCodigoPPM(ppm);
    }

    public Boolean existsById(Long id) {
        return propostaRepository.existsById(id);
    }

    public Boolean existsByPpm(Long ppm) {
        return propostaRepository.existsByCodigoPPM(ppm);
    }

    public <S extends Proposta> S save(S entity) {
        return propostaRepository.save(entity);
    }

    public void deleteById(Long id) {
        propostaRepository.deleteById(id);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, status, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(visibilidade, status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(visibilidade, status, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(visibilidade, status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(visibilidade, status, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(visibilidade, status, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(visibilidade, status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(visibilidade, status, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(visibilidade, status, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(visibilidade, status, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerente(Boolean visibilidade, Status status, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerente(visibilidade, status, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(visibilidade, status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(visibilidade, status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(visibilidade, status, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(visibilidade, status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(visibilidade, status, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(visibilidade, status, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForum(Boolean visibilidade, Status status, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForum(visibilidade, status, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(visibilidade, status, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(visibilidade, status, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(Boolean visibilidade, Status status, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(visibilidade, status, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(visibilidade, status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanho(Boolean visibilidade, Status status, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanho(visibilidade, status, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(visibilidade, status, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContaining(Boolean visibilidade, Status status, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContaining(visibilidade, status, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, status, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, status, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(visibilidade, status, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, status, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(visibilidade, status, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(visibilidade, status, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForum(Boolean visibilidade, Status status, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForum(visibilidade, status, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(visibilidade, status, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(visibilidade, status, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamento(Boolean visibilidade, Status status, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamento(visibilidade, status, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(visibilidade, status, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanho(Boolean visibilidade, Status status, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanho(visibilidade, status, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitante(Boolean visibilidade, Status status, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndSolicitante(visibilidade, status, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerente(Boolean visibilidade, Status status, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerente(visibilidade, status, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(visibilidade, status, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(visibilidade, status, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamento(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamento(visibilidade, status, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(visibilidade, status, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanho(Boolean visibilidade, Status status, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanho(visibilidade, status, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitante(Boolean visibilidade, Status status, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndSolicitante(visibilidade, status, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForum(Boolean visibilidade, Status status, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForum(visibilidade, status, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanho(visibilidade, status, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(visibilidade, status, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamento(Boolean visibilidade, Status status, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamento(visibilidade, status, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndSolicitante(visibilidade, status, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanho(Boolean visibilidade, Status status, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanho(visibilidade, status, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndSolicitante(Boolean visibilidade, Status status, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndSolicitante(visibilidade, status, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatus(Boolean visibilidade, Status status, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatus(visibilidade, status, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(visibilidade, codigoPPM, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(visibilidade, codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForum(visibilidade, codigoPPM, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(visibilidade, codigoPPM, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(visibilidade, codigoPPM, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(visibilidade, codigoPPM, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerente(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerente(visibilidade, codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamento(visibilidade, codigoPPM, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanho(visibilidade, codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndSolicitante(visibilidade, codigoPPM, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForum(visibilidade, codigoPPM, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamento(visibilidade, codigoPPM, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanho(visibilidade, codigoPPM, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContainingAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContainingAndSolicitante(visibilidade, codigoPPM, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloContaining(Boolean visibilidade, Long codigoPPM, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloContaining(visibilidade, codigoPPM, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(visibilidade, codigoPPM, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(visibilidade, codigoPPM, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(visibilidade, codigoPPM, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForum(visibilidade, codigoPPM, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(visibilidade, codigoPPM, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(visibilidade, codigoPPM, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(visibilidade, codigoPPM, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(visibilidade, codigoPPM, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(visibilidade, codigoPPM, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(visibilidade, codigoPPM, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerente(Boolean visibilidade, Long codigoPPM, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerente(visibilidade, codigoPPM, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(visibilidade, codigoPPM, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndTamanho(visibilidade, codigoPPM, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(visibilidade, codigoPPM, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForum(Boolean visibilidade, Long codigoPPM, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForum(visibilidade, codigoPPM, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(visibilidade, codigoPPM, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(visibilidade, codigoPPM, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamento(Boolean visibilidade, Long codigoPPM, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamento(visibilidade, codigoPPM, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(visibilidade, codigoPPM, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanho(Boolean visibilidade, Long codigoPPM, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTamanho(visibilidade, codigoPPM, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndSolicitante(visibilidade, codigoPPM, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPM(Boolean visibilidade, Long codigoPPM, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPM(visibilidade, codigoPPM, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, codigoPPM, status, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, codigoPPM, status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(b, codigoPPM, status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(b, codigoPPM, status, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(b, codigoPPM, status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForum(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForum(b, codigoPPM, status, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, codigoPPM, status, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, codigoPPM, status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamento(b, codigoPPM, status, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanho(b, codigoPPM, status, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndSolicitante(b, codigoPPM, status, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerente(boolean b, Long codigoPPM, Status status, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerente(b, codigoPPM, status, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(b, codigoPPM, status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, codigoPPM, status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamento(b, codigoPPM, status, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanho(b, codigoPPM, status, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndSolicitante(b, codigoPPM, status, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForum(boolean b, Long codigoPPM, Status status, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForum(b, codigoPPM, status, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanho(b, codigoPPM, status, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndSolicitante(b, codigoPPM, status, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamento(boolean b, Long codigoPPM, Status status, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamento(b, codigoPPM, status, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanhoAndSolicitante(b, codigoPPM, status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanho(boolean b, Long codigoPPM, Status status, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanho(b, codigoPPM, status, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndSolicitante(boolean b, Long codigoPPM, Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndSolicitante(b, codigoPPM, status, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTituloContaining(boolean b, Long codigoPPM, Status status, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContaining(b, codigoPPM, status, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(b, codigoPPM, status, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(b, codigoPPM, status, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamento(b, codigoPPM, status, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(b, codigoPPM, status, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanho(b, codigoPPM, status, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndSolicitante(b, codigoPPM, status, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForum(boolean b, Long codigoPPM, Status status, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForum(b, codigoPPM, status, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanho(b, codigoPPM, status, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndSolicitante(b, codigoPPM, status, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamento(boolean b, Long codigoPPM, Status status, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamento(b, codigoPPM, status, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanhoAndSolicitante(b, codigoPPM, status, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanho(boolean b, Long codigoPPM, Status status, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanho(b, codigoPPM, status, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndSolicitante(b, codigoPPM, status, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndGerente(boolean b, Long codigoPPM, Status status, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndGerente(b, codigoPPM, status, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanho(b, codigoPPM, status, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndSolicitante(b, codigoPPM, status, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamento(boolean b, Long codigoPPM, Status status, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamento(b, codigoPPM, status, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanhoAndSolicitante(b, codigoPPM, status, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanho(boolean b, Long codigoPPM, Status status, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanho(b, codigoPPM, status, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForumAndSolicitante(boolean b, Long codigoPPM, Status status, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndSolicitante(b, codigoPPM, status, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndForum(boolean b, Long codigoPPM, Status status, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndForum(b, codigoPPM, status, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanhoAndSolicitante(b, codigoPPM, status, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanho(boolean b, Long codigoPPM, Status status, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanho(b, codigoPPM, status, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndSolicitante(boolean b, Long codigoPPM, Status status, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndSolicitante(b, codigoPPM, status, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndDepartamento(boolean b, Long codigoPPM, Status status, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamento(b, codigoPPM, status, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTamanhoAndSolicitante(boolean b, Long codigoPPM, Status status, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTamanhoAndSolicitante(b, codigoPPM, status, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndTamanho(boolean b, Long codigoPPM, Status status, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndTamanho(b, codigoPPM, status, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatusAndSolicitante(boolean b, Long codigoPPM, Status status, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatusAndSolicitante(b, codigoPPM, status, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndStatus(boolean b, Long codigoPPM, Status status, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndStatus(b, codigoPPM, status, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamento(b, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanho(b, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitante(b, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForum(boolean b, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForum(b, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamento(boolean b, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamento(b, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanho(boolean b, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanho(b, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitante(boolean b, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndSolicitante(b, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerente(boolean b, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerente(b, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanho(b, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamento(boolean b, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamento(b, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitante(b, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanho(boolean b, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanho(b, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitante(boolean b, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndSolicitante(b, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForum(boolean b, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForum(b, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanho(boolean b, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanho(b, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitante(boolean b, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitante(b, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamento(boolean b, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamento(b, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitante(boolean b, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitante(b, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanho(boolean b, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanho(b, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitante(boolean b, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndSolicitante(b, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContaining(boolean b, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContaining(b, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanho(b, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitante(b, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamento(boolean b, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamento(b, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitante(b, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanho(boolean b, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanho(b, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitante(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndSolicitante(b, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForum(boolean b, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForum(b, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanho(b, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitante(b, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamento(boolean b, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamento(b, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndSolicitante(b, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanho(boolean b, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanho(b, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndSolicitante(boolean b, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndSolicitante(b, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerente(boolean b, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerente(b, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitante(b, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanho(boolean b, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanho(b, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitante(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndSolicitante(b, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamento(boolean b, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamento(b, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitante(boolean b, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndSolicitante(b, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanho(boolean b, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanho(b, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndSolicitante(boolean b, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndSolicitante(b, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForum(boolean b, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForum(b, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitante(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitante(b, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanho(boolean b, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanho(b, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitante(boolean b, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndSolicitante(b, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamento(boolean b, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamento(b, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitante(boolean b, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndSolicitante(b, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanho(boolean b, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanho(b, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndSolicitante(boolean b, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndSolicitante(b, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidade(boolean b, Pageable pageable) {
        return propostaRepository.findByVisibilidade(b, pageable);
    }

    public List<Proposta> findByTitulo(String titulo) {
        return propostaRepository.findByTitulo(titulo);
    }

    public Boolean existsByPPM(Long ppm) {
        return propostaRepository.existsByCodigoPPM(ppm);
    }

    public Proposta findByPPM(Long ppm) {
        return propostaRepository.findByCodigoPPM(ppm);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(b, status, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(b, status, analista, codigoPPM, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(b, status, analista, codigoPPM, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(b, status, analista, codigoPPM, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(b, status, analista, codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(b, status, analista, codigoPPM, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(b, status, analista, codigoPPM, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(b, status, analista, codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(b, status, analista, codigoPPM, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForum(b, status, analista, codigoPPM, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(b, status, analista, codigoPPM, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(b, status, analista, codigoPPM, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(b, status, analista, codigoPPM, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(b, status, analista, codigoPPM, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(b, status, analista, codigoPPM, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContaining(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContaining(b, status, analista, codigoPPM, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, codigoPPM, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(b, status, analista, codigoPPM, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(b, status, analista, codigoPPM, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(b, status, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(b, status, analista, codigoPPM, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForum(b, status, analista, codigoPPM, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(b, status, analista, codigoPPM, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(b, status, analista, codigoPPM, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(b, status, analista, codigoPPM, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanho(b, status, analista, codigoPPM, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(b, status, analista, codigoPPM, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerente(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerente(b, status, analista, codigoPPM, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(b, status, analista, codigoPPM, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamento(b, status, analista, codigoPPM, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(b, status, analista, codigoPPM, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanho(b, status, analista, codigoPPM, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitante(b, status, analista, codigoPPM, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForum(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForum(b, status, analista, codigoPPM, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, codigoPPM, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(b, status, analista, codigoPPM, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(b, status, analista, codigoPPM, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamento(b, status, analista, codigoPPM, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(b, status, analista, codigoPPM, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanho(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanho(b, status, analista, codigoPPM, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitante(b, status, analista, codigoPPM, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPM(boolean b, Status status, Usuario analista, Long codigoPPM, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPM(b, status, analista, codigoPPM, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(b, analista, codigoPPM, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(b, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(b, analista, codigoPPM, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(b, analista, codigoPPM, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, analista, codigoPPM, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, analista, codigoPPM, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(b, analista, codigoPPM, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(b, analista, codigoPPM, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(b, analista, codigoPPM, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(b, analista, codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(b, analista, codigoPPM, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, analista, codigoPPM, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(b, analista, codigoPPM, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(b, analista, codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(b, analista, codigoPPM, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForum(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForum(b, analista, codigoPPM, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(b, analista, codigoPPM, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(b, analista, codigoPPM, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(boolean b, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(b, analista, codigoPPM, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(b, analista, codigoPPM, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(b, analista, codigoPPM, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(b, analista, codigoPPM, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContaining(boolean b, Usuario analista, Long codigoPPM, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContaining(b, analista, codigoPPM, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(b, analista, codigoPPM, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(b, analista, codigoPPM, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(b, analista, codigoPPM, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(b, analista, codigoPPM, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(b, analista, codigoPPM, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(b, analista, codigoPPM, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForum(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForum(b, analista, codigoPPM, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(b, analista, codigoPPM, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(b, analista, codigoPPM, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(b, analista, codigoPPM, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(b, analista, codigoPPM, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanho(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanho(b, analista, codigoPPM, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(b, analista, codigoPPM, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndGerente(boolean b, Usuario analista, Long codigoPPM, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerente(b, analista, codigoPPM, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(b, analista, codigoPPM, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(b, analista, codigoPPM, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamento(b, analista, codigoPPM, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(b, analista, codigoPPM, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanho(b, analista, codigoPPM, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndSolicitante(b, analista, codigoPPM, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndForum(boolean b, Usuario analista, Long codigoPPM, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndForum(b, analista, codigoPPM, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(b, analista, codigoPPM, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(b, analista, codigoPPM, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamento(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamento(b, analista, codigoPPM, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(b, analista, codigoPPM, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanho(boolean b, Usuario analista, Long codigoPPM, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanho(b, analista, codigoPPM, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndSolicitante(b, analista, codigoPPM, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPM(boolean b, Usuario analista, Long codigoPPM, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPM(b, analista, codigoPPM, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(b, status, analista, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(b, status, analista, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(b, status, analista, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForum(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForum(b, status, analista, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(b, status, analista, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanho(b, status, analista, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitante(b, status, analista, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerente(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerente(b, status, analista, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(b, status, analista, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, status, analista, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamento(b, status, analista, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(b, status, analista, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanho(b, status, analista, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitante(b, status, analista, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForum(boolean b, Status status, Usuario analista, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForum(b, status, analista, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(b, status, analista, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(b, status, analista, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamento(b, status, analista, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(b, status, analista, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanho(boolean b, Status status, Usuario analista, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanho(b, status, analista, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitante(b, status, analista, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContaining(boolean b, Status status, Usuario analista, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContaining(b, status, analista, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamento(b, status, analista, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(b, status, analista, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanho(b, status, analista, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitante(b, status, analista, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForum(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForum(b, status, analista, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanho(b, status, analista, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitante(b, status, analista, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamento(b, status, analista, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitante(b, status, analista, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanho(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanho(b, status, analista, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitante(b, status, analista, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerente(boolean b, Status status, Usuario analista, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerente(b, status, analista, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanho(b, status, analista, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitante(b, status, analista, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamento(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamento(b, status, analista, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitante(b, status, analista, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanho(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanho(b, status, analista, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitante(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitante(b, status, analista, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForum(boolean b, Status status, Usuario analista, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForum(b, status, analista, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanho(b, status, analista, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitante(b, status, analista, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamento(boolean b, Status status, Usuario analista, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamento(b, status, analista, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitante(b, status, analista, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanho(boolean b, Status status, Usuario analista, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanho(b, status, analista, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitante(boolean b, Status status, Usuario analista, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndSolicitante(b, status, analista, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalista(boolean b, Status status, Usuario analista, Long codigoPPM, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalista(b, status, analista, codigoPPM, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, analista, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, analista, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(b, analista, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(b, analista, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(b, analista, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(b, analista, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForum(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForum(b, analista, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, analista, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamento(b, analista, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(b, analista, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanho(b, analista, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitante(b, analista, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(b, analista, codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(b, analista, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(b, analista, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamento(b, analista, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(b, analista, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(b, analista, codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitante(b, analista, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForum(boolean b, Usuario analista, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForum(b, analista, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(b, analista, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(b, analista, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamento(boolean b, Usuario analista, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamento(b, analista, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(b, analista, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanho(boolean b, Usuario analista, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanho(b, analista, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitante(boolean b, Usuario analista, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitante(b, analista, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContaining(boolean b, Usuario analista, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContaining(b, analista, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(b, analista, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(b, analista, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamento(b, analista, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(b, analista, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanho(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanho(b, analista, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitante(b, analista, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForum(boolean b, Usuario analista, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForum(b, analista, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, analista, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanho(b, analista, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitante(b, analista, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamento(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamento(b, analista, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitante(b, analista, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanho(boolean b, Usuario analista, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanho(b, analista, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitante(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndSolicitante(b, analista, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerente(boolean b, Usuario analista, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerente(b, analista, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanho(b, analista, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitante(b, analista, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamento(boolean b, Usuario analista, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamento(b, analista, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitante(b, analista, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanho(boolean b, Usuario analista, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanho(b, analista, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitante(boolean b, Usuario analista, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndSolicitante(b, analista, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForum(boolean b, Usuario analista, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForum(b, analista, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(b, analista, codigoPPM, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanho(boolean b, Usuario analista, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanho(b, analista, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitante(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitante(b, analista, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamento(boolean b, Usuario analista, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamento(b, analista, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitante(boolean b, Usuario analista, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitante(b, analista, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanho(boolean b, Usuario analista, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanho(b, analista, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitante(boolean b, Usuario analista, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndSolicitante(b, analista, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalista(boolean b, Usuario analista, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalista(b, analista, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitanteoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitanteoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, titulo, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitanteAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }


    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitanteAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitanteAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitanteAndPautaAndEmAta(b, status, analista, codigoPPM, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndPautaAndEmAta();
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitanteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitanteAndPautaAndEmAta();
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndPautaAndEmAta();
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndEmPautaAndEmAta(b, status, analista, codigoPPM, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndEmPautaAndEmAta(b, status, analista, codigoPPM, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitanteAndEmPautaAndEmAta(b, status, analista, codigoPPM, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Long codigoPPM, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndEmPautaAndEmAta(b, status, analista, codigoPPM, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Usuario analista, Long codigoPPM, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndEmPautaAndEmAta(b, analista, codigoPPM, titulo, gerente, emPauta, emAta, pageable);
    }
}
