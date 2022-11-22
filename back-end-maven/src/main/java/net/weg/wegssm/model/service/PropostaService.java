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

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(status, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerente(Status status, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerente(status, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(status, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(status, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(status, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForum(Status status, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForum(status, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(Status status, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(status, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanho(Status status, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanho(status, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(status, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContaining(Status status, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContaining(status, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(status, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(status, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(Status status, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(status, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(status, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(Status status, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(status, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(Status status, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(status, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForum(Status status, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForum(status, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(Status status, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(status, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(Status status, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(status, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamento(Status status, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamento(status, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(Status status, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(status, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanho(Status status, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanho(status, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitante(Status status, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndSolicitante(status, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerente(Status status, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerente(status, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(status, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(status, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(status, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamento(Boolean visibilidade, Status status, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamento(status, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(status, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanho(Boolean visibilidade, Status status, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanho(status, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitante(Boolean visibilidade, Status status, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndSolicitante(status, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForum(Boolean visibilidade, Status status, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForum(status, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(status, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanho(Boolean visibilidade, Status status, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanho(status, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(status, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamento(Boolean visibilidade, Status status, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamento(status, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndSolicitante(status, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanho(Boolean visibilidade, Status status, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanho(status, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndSolicitante(Boolean visibilidade, Status status, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndSolicitante(status, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatus(Boolean visibilidade, Status status, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatus(status, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(codigoPPM, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanho(codigoPPM, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(codigoPPM, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForum(codigoPPM, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(codigoPPM, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(codigoPPM, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamento(codigoPPM, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(codigoPPM, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanho(codigoPPM, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndSolicitante(codigoPPM, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndGerente(Boolean visibilidade, Long codigoPPM, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndGerente(codigoPPM, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(codigoPPM, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(codigoPPM, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamento(codigoPPM, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(codigoPPM, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanho(codigoPPM, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndSolicitante(codigoPPM, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndForum(Boolean visibilidade, Long codigoPPM, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndForum(codigoPPM, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanho(codigoPPM, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndSolicitante(codigoPPM, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndDepartamento(Boolean visibilidade, Long codigoPPM, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamento(codigoPPM, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndTamanhoAndSolicitante(codigoPPM, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndTamanho(Boolean visibilidade, Long codigoPPM, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndTamanho(codigoPPM, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTituloAndSolicitante(Boolean visibilidade, Long codigoPPM, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTituloAndSolicitante(codigoPPM, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTitulo(Boolean visibilidade, Long codigoPPM, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTitulo(codigoPPM, titulo, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(codigoPPM, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(codigoPPM, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(codigoPPM, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(codigoPPM, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(codigoPPM, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(codigoPPM, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndForum(Boolean visibilidade, Long codigoPPM, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndForum(codigoPPM, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(codigoPPM, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(codigoPPM, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(Boolean visibilidade, Long codigoPPM, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(codigoPPM, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(codigoPPM, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(Boolean visibilidade, Long codigoPPM, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(codigoPPM, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(codigoPPM, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndGerente(Boolean visibilidade, Long codigoPPM, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndGerente(codigoPPM, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(codigoPPM, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(codigoPPM, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(Boolean visibilidade, Long codigoPPM, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(codigoPPM, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(codigoPPM, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndTamanho(Boolean visibilidade, Long codigoPPM, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndTamanho(codigoPPM, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(Boolean visibilidade, Long codigoPPM, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(codigoPPM, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndForum(Boolean visibilidade, Long codigoPPM, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndForum(codigoPPM, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(codigoPPM, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(Boolean visibilidade, Long codigoPPM, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(codigoPPM, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(Boolean visibilidade, Long codigoPPM, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(codigoPPM, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndDepartamento(Boolean visibilidade, Long codigoPPM, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndDepartamento(codigoPPM, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(Boolean visibilidade, Long codigoPPM, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(codigoPPM, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndTamanho(Boolean visibilidade, Long codigoPPM, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndTamanho(codigoPPM, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPMAndSolicitante(Boolean visibilidade, Long codigoPPM, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPMAndSolicitante(codigoPPM, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndCodigoPPM(Boolean visibilidade, Long codigoPPM, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndCodigoPPM(codigoPPM, pageable);
    }
}
