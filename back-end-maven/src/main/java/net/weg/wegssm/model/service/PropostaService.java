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
     * Função para buscar uma proposta por demanda
     *
     * @param id
     * @return
     */
    public Optional<Proposta> findByDemandaId(Long id) {
        return propostaRepository.findByDemandaId(id);
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
     * Função para verificar se existe uma proposta com aquela demanda
     *
     * @param id
     * @return
     */
    public boolean existsByDemandaId(Long id) {
        return propostaRepository.existsByDemandaId(id);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(visibilidade, status, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(visibilidade, status, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(visibilidade, status, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(visibilidade, status, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(visibilidade, status, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(Boolean visibilidade, Status status, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(visibilidade, status, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(visibilidade, status, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(visibilidade, status, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(Boolean visibilidade, Status status, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(visibilidade, status, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(visibilidade, status, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(Boolean visibilidade, Status status, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(visibilidade, status, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(visibilidade, status, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerente(Boolean visibilidade, Status status, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerente(visibilidade, status, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(visibilidade, status, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(visibilidade, status, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(Boolean visibilidade, Status status, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(visibilidade, status, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(visibilidade, status, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(Boolean visibilidade, Status status, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(visibilidade, status, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(Boolean visibilidade, Status status, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(visibilidade, status, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForum(Boolean visibilidade, Status status, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForum(visibilidade, status, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(visibilidade, status, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(Boolean visibilidade, Status status, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(visibilidade, status, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(Boolean visibilidade, Status status, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(visibilidade, status, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamento(Boolean visibilidade, Status status, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamento(visibilidade, status, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(Boolean visibilidade, Status status, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(visibilidade, status, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanho(Boolean visibilidade, Status status, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanho(visibilidade, status, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitante(Boolean visibilidade, Status status, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitante(visibilidade, status, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCase(Boolean visibilidade, Status status, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCase(visibilidade, status, titulo, pageable);
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

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(b, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(b, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(b, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(b, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(b, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(b, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForum(b, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(b, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(b, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(b, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(b, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanho(b, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(b, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerente(boolean b, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerente(b, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(b, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(b, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(b, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamento(b, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(b, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanho(b, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitante(b, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForum(boolean b, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForum(b, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(b, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(b, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(b, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamento(boolean b, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamento(b, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(b, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanho(boolean b, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanho(b, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitante(boolean b, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitante(b, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCase(boolean b, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCase(b, titulo, pageable);
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

    public List<Proposta> findByTituloContainingIgnoreCase(String titulo) {
        return propostaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Boolean existsByPPM(Long ppm) {
        return propostaRepository.existsByCodigoPPM(ppm);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(b, status, analista, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(b, status, analista, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(b, status, analista, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(b, status, analista, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(b, status, analista, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(b, status, analista, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(b, status, analista, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(b, status, analista, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(b, status, analista, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerente(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerente(b, status, analista, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(b, status, analista, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(b, status, analista, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(b, status, analista, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(b, status, analista, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(b, status, analista, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(b, status, analista, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForum(boolean b, Status status, Usuario analista, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForum(b, status, analista, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(b, status, analista, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(b, status, analista, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(b, status, analista, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(b, status, analista, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(b, status, analista, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(boolean b, Status status, Usuario analista, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(b, status, analista, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(b, status, analista, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCase(boolean b, Status status, Usuario analista, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCase(b, status, analista, titulo, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(b, analista, titulo, gerente, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(b, analista, titulo, gerente, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(b, analista, titulo, gerente, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(b, analista, titulo, gerente, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(b, analista, titulo, gerente, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(b, analista, titulo, gerente, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(b, analista, titulo, gerente, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, gerente, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(b, analista, titulo, gerente, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(b, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(b, analista, titulo, gerente, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(b, analista, titulo, gerente, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(b, analista, titulo, gerente, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(b, analista, titulo, gerente, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerente(boolean b, Usuario analista, String titulo, Usuario gerente, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerente(b, analista, titulo, gerente, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, forum, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(b, analista, titulo, forum, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(b, analista, titulo, forum, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(b, analista, titulo, forum, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(b, analista, titulo, forum, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(b, analista, titulo, forum, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(b, analista, titulo, forum, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForum(boolean b, Usuario analista, String titulo, Forum forum, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForum(b, analista, titulo, forum, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(b, analista, titulo, departamento, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(b, analista, titulo, departamento, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(b, analista, titulo, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(boolean b, Usuario analista, String titulo, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(b, analista, titulo, departamento, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(b, analista, titulo, tamanho, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(boolean b, Usuario analista, String titulo, String tamanho, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(b, analista, titulo, tamanho, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(boolean b, Usuario analista, String titulo, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(b, analista, titulo, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCase(boolean b, Usuario analista, String titulo, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCase(b, analista, titulo, pageable);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(b, status, analista, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(b, status, analista, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(b, status, analista, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(b, status, analista, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(b, status, analista, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(b, status, analista, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(b, status, analista, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, analista, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(b, status, analista, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(b, status, analista, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(b, status, analista, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Status status, Usuario analista, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(b, status, analista, titulo, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(b, analista, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(b, analista, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(b, analista, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(b, analista, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(b, analista, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(b, analista, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(b, analista, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(b, analista, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(b, analista, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(b, analista, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Usuario analista, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(b, analista, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(b, analista, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(b, analista, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Usuario analista, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(b, analista, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(b, analista, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Usuario analista, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(b, analista, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Usuario analista, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(b, analista, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Usuario analista, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(b, analista, titulo, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(b, status, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndPresenteEm(b, status, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(b, status, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(b, status, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(b, status, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(b, status, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(b, status, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(b, status, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(b, status, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(b, status, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(b, status, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(b, status, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, status, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(b, status, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, Status status, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(b, status, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, Status status, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(b, status, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, Status status, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndPresenteEm(b, status, titulo, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(b, titulo, gerente, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(b, titulo, gerente, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(b, titulo, gerente, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, gerente, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, gerente, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(boolean b, String titulo, Usuario gerente, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(b, titulo, gerente, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, gerente, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(boolean b, String titulo, Usuario gerente, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(b, titulo, gerente, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario gerente, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(b, titulo, gerente, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(boolean b, String titulo, Usuario gerente, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(b, titulo, gerente, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, forum, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, forum, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(boolean b, String titulo, Forum forum, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(b, titulo, forum, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, forum, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(boolean b, String titulo, Forum forum, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(b, titulo, forum, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(boolean b, String titulo, Forum forum, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(b, titulo, forum, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndPresenteEm(boolean b, String titulo, Forum forum, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndPresenteEm(b, titulo, forum, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, departamento, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(boolean b, String titulo, Departamento departamento, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(b, titulo, departamento, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(boolean b, String titulo, Departamento departamento, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(b, titulo, departamento, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(boolean b, String titulo, Departamento departamento, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(b, titulo, departamento, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(boolean b, String titulo, String tamanho, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(b, titulo, tamanho, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(boolean b, String titulo, String tamanho, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(b, titulo, tamanho, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(boolean b, String titulo, Usuario solicitante, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(b, titulo, solicitante, presenteEm, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndTituloContainingIgnoreCaseAndPresenteEm(boolean b, String titulo, String presenteEm, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndTituloContainingIgnoreCaseAndPresenteEm(b, titulo, presenteEm, pageable);
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

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Usuario solicitante, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(b, status, analista, titulo, gerente, departamento, solicitante, pageable);
    }

    public Page<Proposta> findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(boolean b, Status status, Usuario analista, String titulo, Usuario gerente, Departamento departamento, Pageable pageable) {
        return propostaRepository.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(b, status, analista, titulo, gerente, departamento, pageable);
    }
}
