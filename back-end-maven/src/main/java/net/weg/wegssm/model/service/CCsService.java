package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.repository.CCsRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para as CCs
 */
@Service
@AllArgsConstructor
public class CCsService {

    /**
     * Classe repository das CCs
     */
    private CCsRepository ccsRepository;

    /**
     * Função para salvar uma CC no banco
     *
     * @param cc - CC a ser salvo
     * @return - CC salvo
     */
    public CC save(CC cc) {
        return ccsRepository.save(cc);
    }

    /**
     * Função booleana para verificar se uma CC existe ou não, pelo seu ID
     *
     * @param id - ID do CC a ser verificado
     * @return - Retorno booleano da verificação
     */
    public Boolean existsById(Long id) {
        return ccsRepository.existsById(id);
    }

    /**
     * Função para excluir uma CC pelo seu ID
     *
     * @param id - ID do CC a ser excluído
     */
    public void deleteById(Long id) {
        ccsRepository.deleteById(id);
    }

}
