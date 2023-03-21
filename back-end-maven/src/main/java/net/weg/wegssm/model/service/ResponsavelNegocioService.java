package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.ResponsavelNegocio;
import net.weg.wegssm.repository.ResponsavelNegocioRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para os responsáveis de negócio
 */
@Service
public class ResponsavelNegocioService {

    /**
     * Classe service dos responsáveis de negócio
     */
    private ResponsavelNegocioRepository responsavelNegocioRepository;

    /**
     * Construtor da classe
     */
    public ResponsavelNegocioService(ResponsavelNegocioRepository responsavelNegocioRepository) {
        this.responsavelNegocioRepository = responsavelNegocioRepository;
    }

    /**
     * Função booleana para verificação se um responsável de negócio existe pelo seu ID
     */
    public Boolean existsById(Long id) {
        return responsavelNegocioRepository.existsById(id);
    }

    /**
     * Função para salvar um responsável de negócio no banco de dados
     */
    public <S extends ResponsavelNegocio> S save(S entity) {
        return responsavelNegocioRepository.save(entity);
    }

    /**
     * Função para excluir um responsável de negócio pelo seu ID
     */
    public void deleteById(Long id) {
        responsavelNegocioRepository.deleteById(id);
    }

}
