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

    public List<Proposta> findAll(){
        return propostaRepository.findAll();
    }

    public Page<Proposta> findAll(Pageable pageable) {
        return propostaRepository.findAll(pageable);
    }

    public Optional<Proposta> findById(Long id){
        return propostaRepository.findById(id);
    }

    public Optional<Proposta> findByPpm(Long ppm){ return propostaRepository.findByCodigoPPM(ppm); }

//    public List<Proposta> findByTitulo(String titulo){ return propostaRepository.findByTitulo(titulo); }

    public Boolean existsById(Long id){ return propostaRepository.existsById(id); }

    public Boolean existsByPpm(Long ppm){ return propostaRepository.existsByCodigoPPM(ppm); }

    public <S extends Proposta> S save(S entity){
        return propostaRepository.save(entity);
    }

    public void deleteById(Long id){
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

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(visibilidade, codigoPPM, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanho(visibilidade, codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(visibilidade, codigoPPM, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForum(visibilidade, codigoPPM, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamento(visibilidade, codigoPPM, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanho(visibilidade, codigoPPM, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndSolicitante(visibilidade, codigoPPM, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerente(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerente(visibilidade, codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamento(visibilidade, codigoPPM, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanho(visibilidade, codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndSolicitante(visibilidade, codigoPPM, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForum(visibilidade, codigoPPM, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanho(visibilidade, codigoPPM, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndSolicitante(visibilidade, codigoPPM, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamento(visibilidade, codigoPPM, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndTamanhoAndSolicitante(visibilidade, codigoPPM, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndTamanho(visibilidade, codigoPPM, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndSolicitante(visibilidade, codigoPPM, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTitulo(Boolean visibilidade, Long codigoPPM, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTitulo(visibilidade, codigoPPM, titulo, pageable);
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
}
