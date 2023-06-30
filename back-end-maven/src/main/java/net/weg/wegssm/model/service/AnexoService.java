package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.repository.AnexoRepository;
import org.springframework.stereotype.Service;

/**
 * Classe service para os anexos
 */
@Service
@AllArgsConstructor
public class AnexoService {

    /**
     * Classe repository dos anexos
     */
    private AnexoRepository anexoRepository;

    /**
     * Função para buscar um anexo pelo ID informado
     * @param id ID do anexo buscado
     * @return Anexo com o ID semelhante
     */
    public Anexo findById(Long id) {
        return anexoRepository.findById(id).get();
    }

    /**
     * Função para verificar se existe um anexo salvo com o ID recebido
     * @param id ID do anexo a ser verificado
     * @return Boolean de verificação se o anexo existe
     */
    public Boolean existsById(Long id) {
        return anexoRepository.existsById(id);
    }

    /**
     * Função para excluir um anexo pelo ID
     * @param id ID do anexo a ser excluído
     */
    public void deleteById(Long id) {
        anexoRepository.deleteById(id);
    }

    /**
     * Função para salvar um anexo no banco de dados
     * @param anexo Objeto do anexo a ser salvo
     * @return Anexo já salvo no banco de dados
     */
    public Anexo save(Anexo anexo) {
        return anexoRepository.save(anexo);
    }

}
