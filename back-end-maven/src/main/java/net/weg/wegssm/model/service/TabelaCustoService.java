package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.TabelaCusto;
import net.weg.wegssm.repository.TabelaCustoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe service para as tabelas de custos
 */
@Service
@AllArgsConstructor
public class TabelaCustoService {

    /**
     * Classe repository dos custos
     */
    private TabelaCustoRepository tabelaCustoRepository;

    /**
     * Função para salvar uma tabela de custos no banco de dados
     * @param tabelaCusto
     * @return
     */
    public TabelaCusto save(TabelaCusto tabelaCusto) {
        return tabelaCustoRepository.save(tabelaCusto);
    }

    /**
     * Função booleana para verificação se uma tabela de custos existe pelo seu ID
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return tabelaCustoRepository.existsById(id);
    }

    /**
     * Função para excluir uma tabela de custos pelo seu ID
     * @param id
     */
    public void deleteById(Long id) {
        tabelaCustoRepository.deleteById(id);
    }

    /**
     * Função para buscar uma tabela de custos pelo seu ID
     * @param aLong
     * @return
     */
    public Optional<TabelaCusto> findById(Long aLong) {
        return tabelaCustoRepository.findById(aLong);
    }

}
