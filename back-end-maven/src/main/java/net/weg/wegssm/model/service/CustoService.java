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
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Custo> S save(S entity) {
        return custoRepository.save(entity);
    }

    /**
     * Função booleana para verificar se um custo existe pelo seu ID
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return custoRepository.existsById(id);
    }

    /**
     * Função para excluir um custo pelo seu ID
     * @param id
     */
    public void deleteById(Long id) {
        custoRepository.deleteById(id);
    }

}
