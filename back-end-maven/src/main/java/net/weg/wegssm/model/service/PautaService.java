package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.PautaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service para a entidade Pauta
 */
@Service
@AllArgsConstructor
public class PautaService {

    /**
     * Classe repository da pauta
     */
    private PautaRepository pautaRepository;

    /**
     * Função para buscar todas as pautas
     *
     * @return - Lista de pautas
     */
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    /**
     * Função para buscar todas as pautas com paginação
     *
     * @param pageable - Componente de Paginação
     * @return - Lista de pautas
     */
    public Page<Pauta> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable);
    }

    /**
     * Função para buscar uma pauta por id
     *
     * @param id - Id da pauta
     * @return - Pauta encontrada
     */
    public Optional<Pauta> findById(Long id) {
        return pautaRepository.findById(id);
    }

    /**
     * Função para buscar uma pauta por número sequencial
     *
     * @param numeroSequencial - Número sequencial da pauta
     * @return - Pauta encontrada
     */
    public Page<Pauta> findByNumeroSequencial(String numeroSequencial, Pageable pageable) {
        return pautaRepository.findByNumeroSequencial(numeroSequencial, pageable);
    }

    /**
     * Funçao para verificar se uma pauta existe por id
     *
     * @param id - Id da pauta
     * @return - Booleano
     */
    public Boolean existsById(Long id) {
        return pautaRepository.existsById(id);
    }

    /**
     * Função para salvar uma pauta
     *
     * @param entity - Pauta a ser salva
     * @param <S>    - Pauta
     * @return - Pauta salva
     */
    public <S extends Pauta> S save(S entity) {
        return pautaRepository.save(entity);
    }

    /**
     * Função para deletar uma pauta por id
     *
     * @param id - Id da pauta
     */
    public void deleteById(Long id) {
        pautaRepository.deleteById(id);
    }

    /**
     * Função para buscar uma pauta por proposta
     *
     * @param proposta - Proposta
     * @return - Pauta encontrada
     */
    public Pauta findByPropostasContaining(Proposta proposta) {
        return pautaRepository.findByPropostasContaining(proposta);
    }

}
