package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.ResponsavelNegocio;
import net.weg.wegssm.repository.ResponsavelNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe service para os responsáveis de negócio
 */
@Service
@AllArgsConstructor
public class ResponsavelNegocioService {

    /**
     * Classe service dos responsáveis de negócio
     */
    private ResponsavelNegocioRepository responsavelNegocioRepository;

    /**
     * Função booleana para verificação se um responsável de negócio existe pelo seu ID
     *
     * @param id - Id do responsável de negócio
     * @return - Verificando se existe um responsável de negócio ou não
     */
    public Boolean existsById(Long id) {
        return responsavelNegocioRepository.existsById(id);
    }

    /**
     * Função para salvar um responsável negócio
     *
     * @param entity - Responsável negócio a ser salvo
     * @param <S>    - Responsável negócio
     * @return - Responsável negócio salvo
     */
    public <S extends ResponsavelNegocio> S save(S entity) {
        return responsavelNegocioRepository.save(entity);
    }

    /**
     * Função para excluir um responsável de negócio pelo seu ID
     *
     * @param id - Id do responsável de negócio
     */
    public void deleteById(Long id) {
        responsavelNegocioRepository.deleteById(id);
    }

    /**
     * Função para buscar um responsável negócio pelo seu ID
     *
     * @param id - Id do responsável negócio
     * @return - Responsável negócio encontrado
     */
    public Optional<ResponsavelNegocio> findById(Long id) {
        return responsavelNegocioRepository.findById(id);
    }

}
