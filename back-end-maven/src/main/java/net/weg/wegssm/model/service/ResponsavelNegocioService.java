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
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return responsavelNegocioRepository.existsById(id);
    }

    /**
     * Função para salvar um responsável negócio
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends ResponsavelNegocio> S save(S entity) {
        return responsavelNegocioRepository.save(entity);
    }

    /**
     * Função para excluir um responsável de negócio pelo seu ID
     * @param id
     */
    public void deleteById(Long id) {
        responsavelNegocioRepository.deleteById(id);
    }

    /**
     * Função para buscar um responsável negócio pelo seu ID
     * @param id
     * @return
     */
    public Optional<ResponsavelNegocio> findById(Long id) {
        return responsavelNegocioRepository.findById(id);
    }

}
