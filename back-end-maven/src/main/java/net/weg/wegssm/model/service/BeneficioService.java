package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.TipoBeneficio;
import net.weg.wegssm.repository.BeneficioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe service para os benefícios
 */
@Service
@AllArgsConstructor
public class BeneficioService {

    /**
     * Classe repository dos benefícios
     */
    private BeneficioRepository beneficioRepository;

    /**
     * Função para buscar um benefício pelo ID recebido
     *
     * @param id - ID do benefício buscado
     * @return - Retorno do benefício com o ID recebido
     */
    public Optional<Beneficio> findById(Long id) {
        return beneficioRepository.findById(id);
    }

    /**
     * Função booleana para verificar se um benefício com o ID recebido existe ou não
     *
     * @param id - ID do benefício a ser verificado
     * @return - Retorno booleano da verificação
     */
    public boolean existsById(Long id) {
        return beneficioRepository.existsById(id);
    }

    /**
     * Função para salvar um benefício recebido no banco de dados
     *
     * @param entity - Benefício a ser salvo
     * @param <S>    - Tipo do benefício
     * @return - Retorno do benefício salvo
     */
    public <S extends Beneficio> S save(S entity) {
        return beneficioRepository.save(entity);
    }

    /**
     * Função para excluir um benefício com o ID recebido
     *
     * @param id - ID do benefício a ser excluído
     */
    public void deleteById(Long id) {
        beneficioRepository.deleteById(id);
    }

}
