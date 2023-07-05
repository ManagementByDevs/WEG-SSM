package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.repository.CustoRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para os custos
 */
@Service
@AllArgsConstructor
public class CustoService {

    /**
     * Classe repository dos custos
     */
    private CustoRepository custoRepository;

    /**
     * Função para salvar um custo no banco de dados
     *
     * @param entity - Objeto do custo a ser salvo
     * @param <S> - Tipo do custo
     * @return - Retorno do custo salvo
     */
    public <S extends Custo> S save(S entity) {
        return custoRepository.save(entity);
    }

    /**
     * Função booleana para verificar se um custo existe pelo seu ID
     *
     * @param id - ID do custo a ser verificado
     * @return - Retorno booleano da verificação
     */
    public Boolean existsById(Long id) {
        return custoRepository.existsById(id);
    }

    /**
     * Função para excluir um custo pelo seu ID
     *
     * @param id - ID do custo a ser excluído
     */
    public void deleteById(Long id) {
        custoRepository.deleteById(id);
    }

}
