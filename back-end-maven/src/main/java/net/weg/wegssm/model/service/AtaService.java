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
     * @return
     */
    public List<Ata> findAll() {
        return ataRepository.findAll();
    }

    /**
     * Função para buscar todas as atas com paginação
     *
     * @param pageable
     * @return
     */
    public Page<Ata> findAll(Pageable pageable) {
        return ataRepository.findAll(pageable);
    }

    /**
     * Função para buscar uma ata pelo número sequencial
     *
     * @param numeroSequencial
     * @return
     */
    public Page<Ata> findByNumeroSequencial(String numeroSequencial, Pageable pageable) {
        return ataRepository.findByNumeroSequencial(numeroSequencial, pageable);
    }

    /**
     * Função para buscar uma ata pelo ID
     *
     * @param id
     * @return
     */
    public Optional<Ata> findById(Long id) {
        return ataRepository.findById(id);
    }

    /**
     * Função para verificar se existe uma ata com o ID informado
     *
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return ataRepository.existsById(id);
    }

    /**
     * Função para verificar se existe uma ata com o número sequencial informado
     *
     * @param numeroSequencial
     * @return
     */
    public Boolean existsByNumeroSequencial(String numeroSequencial) {
        return ataRepository.existsByNumeroSequencial(numeroSequencial);
    }

    /**
     * Função para salvar uma ata
     *
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Ata> S save(S entity) {
        return ataRepository.save(entity);
    }

    /**
     * Função para excluir uma ata pelo ID
     *
     * @param id
     */
    public void deleteById(Long id) {
        ataRepository.deleteById(id);
    }

    /**
     * Função para buscar uma ata através de uma proposta
     *
     * @param proposta
     * @return
     */
    public Ata findByPropostasContaining(Proposta proposta) {
        return ataRepository.findByPropostasContaining(proposta);
    }

}
