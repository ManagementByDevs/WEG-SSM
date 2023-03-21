package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.TabelaCusto;
import net.weg.wegssm.repository.TabelaCustoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe service para as tabelas de custos
 */
@Service
public class TabelaCustoService {

    /**
     * Classe repository dos custos
     */
    private TabelaCustoRepository tabelaCustoRepository;

    /**
     * Construtor da classe
     */
    public TabelaCustoService(TabelaCustoRepository tabelaCustoRepository) {
        this.tabelaCustoRepository = tabelaCustoRepository;
    }

    /**
     * Função para salvar uma tabela de custos no banco de dados
     */
    public TabelaCusto save(TabelaCusto tabelaCusto) {
        return tabelaCustoRepository.save(tabelaCusto);
    }

    /**
     * Função booleana para verificação se uma tabela de custos existe pelo seu ID
     */
    public Boolean existsById(Long id) {
        return tabelaCustoRepository.existsById(id);
    }

    /**
     * Função para excluir uma tabela de custos pelo seu ID
     */
    public void deleteById(Long id) {
        tabelaCustoRepository.deleteById(id);
    }

    /**
     * Função para buscar uma tabela de custos pelo seu ID
     */
    public Optional<TabelaCusto> findById(Long aLong) {
        return tabelaCustoRepository.findById(aLong);
    }
}
