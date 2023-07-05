package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.AtaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe service para a ata
 */
@Service
@AllArgsConstructor
public class AtaService {

    /**
     * Repository da ata
     */
    private AtaRepository ataRepository;

    /**
     * Função para buscar todas as atas
     *
     * @return - Retorno da Lista de atas
     */
    public List<Ata> findAll() {
        return ataRepository.findAll();
    }

    /**
     * Função para buscar todas as atas com paginação
     *
     * @param pageable - Componente de paginação
     * @return - Retorno da Lista de atas com paginação
     */
    public Page<Ata> findAll(Pageable pageable) {
        return ataRepository.findAll(pageable);
    }

    /**
     * Função para buscar uma ata pelo número sequencial
     *
     * @param numeroSequencial - Número sequencial da ata
     * @return - Retorno da ata com o número sequencial
     */
    public Page<Ata> findByNumeroSequencial(String numeroSequencial, Pageable pageable) {
        return ataRepository.findByNumeroSequencial(numeroSequencial, pageable);
    }

    /**
     * Função para buscar uma ata pelo ID
     *
     * @param id - ID da ata
     * @return - Retorno da ata com o ID
     */
    public Optional<Ata> findById(Long id) {
        return ataRepository.findById(id);
    }

    /**
     * Função para verificar se existe uma ata com o ID informado
     *
     * @param id - ID da ata
     * @return - Retorno da verificação se existe uma ata com o ID informado
     */
    public boolean existsById(Long id) {
        return ataRepository.existsById(id);
    }

    /**
     * Função para verificar se existe uma ata com o número sequencial informado
     *
     * @param numeroSequencial - Número sequencial da ata
     * @return - Retorno da verificação se existe uma ata com o número sequencial informado
     */
    public Boolean existsByNumeroSequencial(String numeroSequencial) {
        return ataRepository.existsByNumeroSequencial(numeroSequencial);
    }

    /**
     * Função para salvar uma ata
     *
     * @param entity - Objeto da ata a ser salva
     * @param <S>    - Tipo da ata
     * @return - Retorno da ata salva
     */
    public <S extends Ata> S save(S entity) {
        return ataRepository.save(entity);
    }

    /**
     * Função para excluir uma ata pelo ID
     *
     * @param id - ID da ata a ser excluída
     */
    public void deleteById(Long id) {
        ataRepository.deleteById(id);
    }

    /**
     * Função para buscar uma ata através de uma proposta
     *
     * @param proposta - Proposta da ata
     * @return - Retorno da ata com a proposta
     */
    public Ata findByPropostasContaining(Proposta proposta) {
        return ataRepository.findByPropostasContaining(proposta);
    }

    /**
     * Função para buscar uma ata pelo número sequencial e se a ata está publicada pela dg
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicadaDg      - Verificação se a ata está publicada pela dg
     * @param pageable         - Componente de paginação
     * @return - Retorno da ata com o número sequencial e se a ata está publicada pela dg
     */
    public Page<Ata> findByNumeroSequencialAndPublicadaDg(String numeroSequencial, Boolean publicadaDg, Pageable pageable) {
        return ataRepository.findByNumeroSequencialAndPublicadaDg(numeroSequencial, publicadaDg, pageable);
    }

    /**
     * Função para buscar uma ata se estiver publicada pela dg
     *
     * @param publicadaDg - Verificação se a ata está publicada pela dg
     * @param pageable    - Componente de paginação
     * @return - Retorno da ata se estiver publicada pela dg
     */
    public Page<Ata> findByPublicadaDg(Boolean publicadaDg, Pageable pageable) {
        return ataRepository.findByPublicadaDg(publicadaDg, pageable);
    }

    /**
     * Função para buscar uma ata pelo número sequencial e se a ata está publicada pela dg e pela comissão
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicadaDg      - Verificação se a ata está publicada pela dg
     * @param publicada        - Verificação se a ata está publicada pela comissão
     * @param pageable         - Componente de paginação
     * @return
     */
    public Page<Ata> findByNumeroSequencialAndPublicadaDgAndPublicada(String numeroSequencial, Boolean publicadaDg, Boolean publicada, Pageable pageable) {
        return ataRepository.findByNumeroSequencialAndPublicadaDgAndPublicada(numeroSequencial, publicadaDg, publicada, pageable);
    }

    /**
     * Função para buscar uma ata pelo número sequencial e se a ata está publicada pela comissão
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicada        - Verificação se a ata está publicada pela comissão
     * @param pageable         - Componente de paginação
     * @return - Retorno da ata pelo número sequencial e se a ata está publicada pela comissão
     */
    public Page<Ata> findByNumeroSequencialAndPublicada(String numeroSequencial, Boolean publicada, Pageable pageable) {
        return ataRepository.findByNumeroSequencialAndPublicada(numeroSequencial, publicada, pageable);
    }

    /**
     * Função para buscar uma ata se estiver publicada pela dg e pela comissão
     *
     * @param publicadaDg - Verificação se a ata está publicada pela dg
     * @param publicada   - Verificação se a ata está publicada pela comissão
     * @param pageable    - Componente de paginação
     * @return - Retorno da ata se estiver publicada pela dg e pela comissão
     */
    public Page<Ata> findByPublicadaDgAndPublicada(Boolean publicadaDg, Boolean publicada, Pageable pageable) {
        return ataRepository.findByPublicadaDgAndPublicada(publicadaDg, publicada, pageable);
    }

    /**
     * Função para buscar uma ata se estiver publicada pela comissão
     *
     * @param publicada - Verificação se a ata está publicada pela comissão
     * @param pageable  - Componente de paginação
     * @return - Retorno da ata se estiver publicada pela comissão
     */
    public Page<Ata> findByPublicada(Boolean publicada, Pageable pageable) {
        return ataRepository.findByPublicada(publicada, pageable);
    }

}
