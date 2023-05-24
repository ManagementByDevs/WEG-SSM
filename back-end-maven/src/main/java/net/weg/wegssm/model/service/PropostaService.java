package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.PropostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;


/**
 * Service para a entidade Proposta
 */
@Service
@AllArgsConstructor
public class PropostaService {

    private PropostaRepository propostaRepository;

    /**
     * Função para buscar todas as propostas
     *
     * @return
     */
    public List<Proposta> findAll() {
        return propostaRepository.findAll();
    }

    /**
     * Função para buscar todas as propostas com paginação
     *
     * @param pageable
     * @return
     */
    public Page<Proposta> findAll(Pageable pageable) {
        return propostaRepository.findAll(pageable);
    }

    /**
     * Função para buscar uma proposta por id
     *
     * @param id
     * @return
     */
    public Optional<Proposta> findById(Long id) {
        return propostaRepository.findById(id);
    }

    /**
     * Função para buscar uma proposta por código ppm
     *
     * @param ppm
     * @param pageable
     * @return
     */
    public Page<Proposta> findByPpm(Long ppm, Pageable pageable) {
        return propostaRepository.findByCodigoPPM(ppm, pageable);
    }

    /**
     * Função para verificar se existe uma proposta por id
     *
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return propostaRepository.existsById(id);
    }

    /**
     * Função para verificar se existe uma proposta por ppm
     *
     * @param ppm
     * @return
     */
    public Boolean existsByPpm(Long ppm) {
        return propostaRepository.existsByCodigoPPM(ppm);
    }

    /**
     * Função para salvar uma proposta
     *
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Proposta> S save(S entity) {
        return propostaRepository.save(entity);
    }

    /**
     * Função para deletar uma proposta por id
     *
     * @param id
     */
    public void deleteById(Long id) {
        propostaRepository.deleteById(id);
    }

    /**
     * Funções utilizadas para o filtro
     */

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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(b, status, analista, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(b, status, analista, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(b, status, analista, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(b, status, analista, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndPresenteEm(b, status, analista, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(b, status, analista, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(b, status, analista, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(b, status, analista, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(b, status, analista, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndPresenteEm(b, status, analista, titulo, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, analista, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, analista, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, analista, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndPresenteEm(b, status, analista, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(b, status, analista, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndPresenteEm(b, status, analista, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(b, status, analista, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndPresenteEm(b, status, analista, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndPresenteEm(b, analista, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(b, analista, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(b, analista, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndPresenteEm(boolean b, Status status, Usuario analista, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndPresenteEm(b, status, analista, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndPresenteEm(b, status, analista, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndPresenteEm(b, status, analista, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndPresenteEm(b, status, analista, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndPresenteEm(b, status, analista, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndPresenteEm(b, status, analista, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndPresenteEm(b, status, analista, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndPresenteEm(b, status, analista, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndPresenteEm(b, analista, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(b, analista, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(b, analista, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndPresenteEm(b, analista, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndPresenteEm(b, analista, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndPresenteEm(b, analista, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndPresenteEm(b, analista, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndPresenteEm(b, analista, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndPresenteEm(b, analista, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndPresenteEm(b, analista, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndPresenteEm(b, analista, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingAndPresenteEm(boolean b, Usuario analista, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingAndPresenteEm(b, analista, titulo, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(b, analista, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(b, analista, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(b, analista, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndForumAndPresenteEm(b, analista, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, analista, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(b, analista, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, analista, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndPresenteEm(b, analista, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(b, analista, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndPresenteEm(b, status, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndPresenteEm(b, status, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitanteAndPresenteEm(b, status, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndPresenteEm(b, status, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndPresenteEm(b, status, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndPresenteEm(b, status, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(b, status, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(b, status, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(b, status, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndPresenteEm(b, status, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndPresenteEm(boolean b, Usuario analista, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndPresenteEm(b, analista, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndSolicitanteAndPresenteEm(b, analista, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndPresenteEm(boolean b, Usuario analista, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndPresenteEm(b, analista, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(b, analista, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndPresenteEm(b, analista, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(b, analista, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndPresenteEm(boolean b, Usuario analista, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndPresenteEm(b, analista, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndPresenteEm(b, analista, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndPresenteEm(b, analista, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndPresenteEm(b, analista, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndPresenteEm(b, status, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndSolicitanteAndPresenteEm(b, status, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingAndPresenteEm(boolean b, Status status, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingAndPresenteEm(b, status, titulo, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndForumAndPresenteEm(b, status, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndPresenteEm(boolean b, Status status, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndPresenteEm(b, status, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndSolicitanteAndPresenteEm(b, status, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndPresenteEm(b, status, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndPresenteEm(b, status, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndPresenteEm(boolean b, Status status, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndPresenteEm(b, status, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndPresenteEm(b, status, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndPresenteEm(b, status, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndPresenteEm(b, status, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndPresenteEm(b, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndPresenteEm(b, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndPresenteEm(b, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndPresenteEm(boolean b, Status status, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndPresenteEm(b, status, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndSolicitanteAndPresenteEm(b, status, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndPresenteEm(boolean b, Status status, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndPresenteEm(b, status, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndPresenteEm(b, status, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndPresenteEm(boolean b, Status status, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndPresenteEm(b, status, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndPresenteEm(b, status, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndPresenteEm(b, status, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndPresenteEm(b, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndPresenteEm(b, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndSolicitanteAndPresenteEm(b, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndGerenteAndPresenteEm(boolean b, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndGerenteAndPresenteEm(b, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndPresenteEm(b, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndPresenteEm(b, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndSolicitanteAndPresenteEm(b, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndForumAndPresenteEm(boolean b, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndForumAndPresenteEm(b, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndDepartamentoAndPresenteEm(boolean b, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndDepartamentoAndPresenteEm(b, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndTamanhoAndPresenteEm(boolean b, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndTamanhoAndPresenteEm(b, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndSolicitanteAndPresenteEm(b, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingAndPresenteEm(boolean b, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingAndPresenteEm(b, titulo, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndPresenteEm(b, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndTamanhoAndPresenteEm(b, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndSolicitanteAndPresenteEm(b, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndForumAndPresenteEm(boolean b, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndForumAndPresenteEm(b, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndDepartamentoAndPresenteEm(b, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndTamanhoAndPresenteEm(b, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndSolicitanteAndPresenteEm(b, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndGerenteAndPresenteEm(boolean b, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndGerenteAndPresenteEm(b, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndPresenteEm(boolean b, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndPresenteEm(b, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndSolicitanteAndPresenteEm(boolean b, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndSolicitanteAndPresenteEm(b, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndPresenteEm(boolean b, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndPresenteEm(b, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTamanhoAndSolicitanteAndPresenteEm(b, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndPresenteEm(boolean b, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndPresenteEm(b, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndSolicitanteAndPresenteEm(b, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndPresenteEm(b, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndPresenteEm(boolean b, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndPresenteEm(b, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndSolicitanteAndPresenteEm(boolean b, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndSolicitanteAndPresenteEm(b, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndPresenteEm(boolean b, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndPresenteEm(b, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndPresenteEm(boolean b, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndPresenteEm(b, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(b, status, analista, titulo, gerente, departamento, pageable);
    }
}
