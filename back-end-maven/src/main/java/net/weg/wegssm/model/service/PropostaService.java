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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalista(boolean b, Status status, Usuario analista, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalista(b, status, analista, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(boolean b, Usuario analista, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(b, analista, titulo, gerente, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(b, analista, titulo, forum, tamanho, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(b, analista, departamento, tamanho, solicitante, pageable);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, status, analista, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(b, status, analista, titulo, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, titulo, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(b, status, analista, titulo, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(b, status, analista, titulo, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(b, status, analista, titulo, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(b, status, analista, titulo, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmPautaAndEmAta(b, status, analista, titulo, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(b, status, analista, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, status, analista, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(b, status, analista, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, status, analista, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmPautaAndEmAta(b, status, analista, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, analista, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmPautaAndEmAta(b, status, analista, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, analista, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndEmPautaAndEmAta(b, status, analista, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, analista, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmPautaAndEmAta(b, status, analista, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, analista, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmPautaAndEmAta(b, status, analista, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmPautaAndEmAta(b, status, analista, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndEmPautaAndEmAta(boolean b, Status status, Usuario analista, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndEmPautaAndEmAta(b, status, analista, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(b, analista, titulo, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, analista, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndEmPautaAndEmAta(b, analista, titulo, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(b, analista, titulo, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(b, analista, titulo, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(b, analista, titulo, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, titulo, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, analista, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, analista, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(b, analista, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, analista, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(b, analista, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, analista, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndEmPautaAndEmAta(b, analista, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(b, analista, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndAndEmPautaAndEmAta(b, analista, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(b, analista, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndEmPautaAndEmAta(boolean b, Usuario analista, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndEmPautaAndEmAta(b, analista, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, analista, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, analista, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario analista, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndEmPautaAndEmAta(b, analista, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, analista, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario analista, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndEmPautaAndEmAta(b, analista, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario analista, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndSolicitanteAndEmPautaAndEmAta(b, analista, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndEmPautaAndEmAta(boolean b, Usuario analista, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndEmPautaAndEmAta(b, analista, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, status, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, status, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmPautaAndEmAta(b, status, titulo, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(b, status, titulo, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, titulo, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, titulo, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmPautaAndEmAta(b, status, titulo, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, status, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, status, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndEmPautaAndEmAta(boolean b, Status status, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndEmPautaAndEmAta(b, status, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndSolicitanteAndEmPautaAndEmAta(b, status, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndEmPautaAndEmAta(b, status, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndEmPautaAndEmAta(b, status, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndEmPautaAndEmAta(b, status, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndEmPautaAndEmAta(b, status, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndEmPautaAndEmAta(b, status, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmPautaAndEmAta(b, status, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, status, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndEmPautaAndEmAta(b, status, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, status, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, status, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndEmPautaAndEmAta(boolean b, Status status, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndEmPautaAndEmAta(b, status, titulo, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmPautaAndEmAta(b, status, titulo, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(b, status, titulo, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, status, titulo, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, status, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, titulo, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, titulo, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(b, titulo, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, titulo, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(b, titulo, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, titulo, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndEmPautaAndEmAta(boolean b, String titulo, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndEmPautaAndEmAta(b, titulo, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, titulo, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, titulo, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(b, titulo, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(b, titulo, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(b, titulo, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndEmPautaAndEmAta(boolean b, String titulo, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndEmPautaAndEmAta(b, titulo, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, titulo, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, titulo, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(boolean b, String titulo, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(b, titulo, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, titulo, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndEmPautaAndEmAta(boolean b, String titulo, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndEmPautaAndEmAta(b, titulo, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(boolean b, String titulo, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(b, titulo, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndEmPautaAndEmAta(boolean b, String titulo, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndEmPautaAndEmAta(b, titulo, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, gerente, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, gerente, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(b, gerente, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(b, gerente, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(b, gerente, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndEmPautaAndEmAta(boolean b, Usuario gerente, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndEmPautaAndEmAta(b, gerente, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, gerente, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, gerente, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndEmPautaAndEmAta(boolean b, Usuario gerente, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndEmPautaAndEmAta(b, gerente, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, gerente, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndEmPautaAndEmAta(boolean b, Usuario gerente, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndEmPautaAndEmAta(b, gerente, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario gerente, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndSolicitanteAndEmPautaAndEmAta(b, gerente, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndEmPautaAndEmAta(boolean b, Usuario gerente, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndEmPautaAndEmAta(b, gerente, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndEmPautaAndEmAta(boolean b, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndEmPautaAndEmAta(b, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndSolicitanteAndEmPautaAndEmAta(boolean b, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndSolicitanteAndEmPautaAndEmAta(b, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndEmPautaAndEmAta(boolean b, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndEmPautaAndEmAta(b, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndEmPautaAndEmAta(boolean b, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndEmPautaAndEmAta(b, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndEmPautaAndEmAta(boolean b, Forum forum, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndEmPautaAndEmAta(b, forum, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndSolicitanteAndEmPautaAndEmAta(b, forum, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndEmPautaAndEmAta(boolean b, Forum forum, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndEmPautaAndEmAta(b, forum, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, forum, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndEmPautaAndEmAta(b, forum, departamento, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(b, forum, departamento, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(b, forum, departamento, tamanho, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emPauta, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(b, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmAta(b, status, analista, titulo, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(b, status, analista, titulo, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(b, status, analista, titulo, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, status, analista, titulo, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(b, status, analista, titulo, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(b, status, analista, titulo, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(b, status, analista, titulo, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, analista, titulo, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(b, status, analista, titulo, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, titulo, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(b, status, analista, titulo, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmAta(b, status, analista, titulo, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(b, status, analista, titulo, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(b, status, analista, titulo, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(b, status, analista, titulo, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, titulo, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, analista, titulo, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(b, status, analista, titulo, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String titulo, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmAta(b, status, analista, titulo, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(b, status, analista, titulo, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmAta(boolean b, Status status, Usuario analista, String titulo, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmAta(b, status, analista, titulo, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, analista, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(b, status, analista, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, analista, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(b, status, analista, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(b, status, analista, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmAta(b, status, analista, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, status, analista, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmAta(b, status, analista, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, status, analista, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmAta(b, status, analista, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmAta(b, status, analista, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmAta(boolean b, Status status, Usuario analista, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmAta(b, status, analista, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, analista, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, analista, titulo, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmAta(b, analista, titulo, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(b, analista, titulo, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(b, analista, titulo, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(b, analista, titulo, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, analista, titulo, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, analista, titulo, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndEmAta(boolean b, Status status, Usuario analista, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndEmAta(b, status, analista, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmAta(b, status, analista, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmAta(b, status, analista, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmAta(b, status, analista, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmAta(b, status, analista, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmAta(b, status, analista, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, analista, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndEmAta(b, status, analista, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmAta(b, status, analista, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmAta(b, status, analista, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, analista, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmAta(b, status, analista, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, analista, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndEmAta(boolean b, Usuario analista, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndEmAta(b, analista, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, analista, titulo, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmAta(b, analista, titulo, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmAta(b, analista, titulo, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmAta(b, analista, titulo, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmAta(boolean b, Usuario analista, String titulo, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmAta(b, analista, titulo, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(b, analista, titulo, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(b, analista, titulo, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmAta(b, analista, titulo, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmAta(b, analista, titulo, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmAta(b, analista, titulo, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmAta(boolean b, Usuario analista, String titulo, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmAta(b, analista, titulo, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(b, analista, titulo, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(b, analista, titulo, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(boolean b, Usuario analista, String titulo, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmAta(b, analista, titulo, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(b, analista, titulo, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmAta(boolean b, Usuario analista, String titulo, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmAta(b, analista, titulo, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(boolean b, Usuario analista, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmAta(b, analista, titulo, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndEmAta(boolean b, Usuario analista, String titulo, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndEmAta(b, analista, titulo, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, analista, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, analista, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmAta(b, analista, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, analista, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmAta(b, analista, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmAta(b, analista, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmAta(boolean b, Usuario analista, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmAta(b, analista, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, analista, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, analista, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmAta(b, analista, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, analista, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmAta(boolean b, Usuario analista, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmAta(b, analista, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmAta(b, analista, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndEmAta(b, status, titulo, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(b, status, titulo, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(b, status, titulo, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmAta(boolean b, Status status, String titulo, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndEmAta(b, status, titulo, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndEmAta(b, status, titulo, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndEmAta(b, status, titulo, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndEmAta(b, status, titulo, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, titulo, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, titulo, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndEmAta(b, status, titulo, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndEmAta(b, status, titulo, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndEmAta(b, status, titulo, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndEmAta(b, status, titulo, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, status, titulo, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, status, titulo, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndEmAta(b, status, titulo, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(b, status, titulo, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(b, status, titulo, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(b, status, titulo, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, titulo, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, titulo, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndEmAta(boolean b, Usuario analista, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndEmAta(b, analista, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndEmAta(boolean b, Usuario analista, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndSolicitanteAndEmAta(b, analista, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndEmAta(boolean b, Usuario analista, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndEmAta(b, analista, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmAta(b, analista, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndEmAta(boolean b, Usuario analista, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndEmAta(b, analista, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmAta(b, analista, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmAta(b, analista, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndEmAta(boolean b, Usuario analista, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndEmAta(b, analista, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmAta(b, analista, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndEmAta(b, analista, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmAta(b, analista, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmAta(b, analista, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmAta(b, analista, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmAta(b, analista, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, analista, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(b, status, titulo, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmAta(boolean b, Status status, String titulo, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndEmAta(b, status, titulo, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmAta(boolean b, Status status, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndEmAta(b, status, titulo, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndEmAta(boolean b, Status status, String titulo, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndEmAta(b, status, titulo, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndEmAta(b, status, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndEmAta(b, status, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndEmAta(b, status, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndEmAta(boolean b, Status status, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndEmAta(b, status, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndEmAta(boolean b, Status status, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndEmAta(b, status, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndSolicitanteAndEmAta(b, status, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndEmAta(boolean b, Status status, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndEmAta(b, status, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndEmAta(b, status, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndEmAta(b, status, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndEmAta(b, status, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndEmAta(b, status, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndEmAta(boolean b, Status status, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndEmAta(b, status, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndEmAta(b, status, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndEmAta(b, status, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, status, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndEmAta(b, status, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, status, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, status, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndEmAta(b, titulo, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmAta(b, titulo, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndEmAta(b, titulo, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, titulo, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmAta(b, titulo, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, titulo, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, titulo, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, titulo, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndEmAta(boolean b, Status status, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndEmAta(b, status, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndEmAta(boolean b, Status status, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndSolicitanteAndEmAta(b, status, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndEmAta(boolean b, Status status, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndEmAta(b, status, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndEmAta(b, status, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndEmAta(boolean b, Status status, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndEmAta(b, status, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmAta(boolean b, Status status, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndEmAta(b, status, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndEmAta(b, status, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, status, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, titulo, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, titulo, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, titulo, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmAta(boolean b, String titulo, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndEmAta(b, titulo, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, titulo, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmAta(boolean b, String titulo, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndEmAta(b, titulo, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmAta(boolean b, String titulo, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndEmAta(b, titulo, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndEmAta(boolean b, String titulo, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndEmAta(b, titulo, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, titulo, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmAta(b, titulo, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmAta(b, titulo, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmAta(boolean b, String titulo, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndEmAta(b, titulo, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmAta(b, titulo, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmAta(boolean b, String titulo, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndEmAta(b, titulo, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmAta(boolean b, String titulo, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndEmAta(b, titulo, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndEmAta(boolean b, String titulo, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndEmAta(b, titulo, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, titulo, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(boolean b, String titulo, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndEmAta(b, titulo, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(boolean b, String titulo, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndEmAta(b, titulo, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndEmAta(boolean b, String titulo, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndEmAta(b, titulo, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(boolean b, String titulo, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndEmAta(b, titulo, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndEmAta(boolean b, String titulo, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndEmAta(b, titulo, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndEmAta(boolean b, String titulo, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndSolicitanteAndEmAta(b, titulo, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndEmAta(boolean b, String titulo, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndEmAta(b, titulo, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, gerente, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndEmAta(b, gerente, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmAta(b, gerente, forum, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmAta(boolean b, Usuario gerente, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndEmAta(b, gerente, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndEmAta(b, gerente, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmAta(boolean b, Usuario gerente, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndEmAta(b, gerente, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmAta(boolean b, Usuario gerente, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndSolicitanteAndEmAta(b, gerente, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndEmAta(boolean b, Usuario gerente, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndEmAta(b, gerente, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, gerente, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmAta(boolean b, Usuario gerente, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndEmAta(b, gerente, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmAta(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndEmAta(b, gerente, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndEmAta(boolean b, Usuario gerente, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndEmAta(b, gerente, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmAta(boolean b, Usuario gerente, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndEmAta(b, gerente, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndEmAta(boolean b, Usuario gerente, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndEmAta(b, gerente, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndEmAta(boolean b, Usuario gerente, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndSolicitanteAndEmAta(b, gerente, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndEmAta(boolean b, Usuario gerente, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndEmAta(b, gerente, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, forum, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmAta(boolean b, Forum forum, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndEmAta(b, forum, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndEmAta(boolean b, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndEmAta(b, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndSolicitanteAndEmAta(boolean b, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndSolicitanteAndEmAta(b, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndEmAta(boolean b, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndEmAta(b, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndEmAta(boolean b, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndSolicitanteAndEmAta(b, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndEmAta(boolean b, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndEmAta(b, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndEmAta(boolean b, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndSolicitanteAndEmAta(b, departamento, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndEmAta(boolean b, Departamento departamento, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndEmAta(b, departamento, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(boolean b, Departamento departamento, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndEmAta(b, departamento, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndEmAta(boolean b, Forum forum, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndEmAta(b, forum, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndEmAta(boolean b, Forum forum, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndSolicitanteAndEmAta(b, forum, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndEmAta(boolean b, Forum forum, String tamanho, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndEmAta(b, forum, tamanho, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmAta(boolean b, Forum forum, String tamanho, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndEmAta(b, forum, tamanho, solicitante, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndEmAta(boolean b, Forum forum, Departamento departamento, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndEmAta(b, forum, departamento, emAta, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmAta(boolean b, Forum forum, Departamento departamento, Usuario solicitante, Boolean emAta, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndEmAta(b, forum, departamento, solicitante, emAta, pageable);
    }
}
