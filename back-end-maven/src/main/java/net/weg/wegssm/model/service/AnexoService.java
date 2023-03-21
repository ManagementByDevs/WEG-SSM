package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.repository.AnexoRepository;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para os anexos
 */
@Service
public class AnexoService {

    /**
     * Classe repository dos anexos
     */
    private AnexoRepository anexoRepository;

    /**
     * Construtor da classe
     */
    public AnexoService(AnexoRepository anexoRepository) {
        this.anexoRepository = anexoRepository;
    }

    /**
     * Função para buscar um anexo pelo ID informado
     */
    public Anexo findById(Long id) {
        return anexoRepository.findById(id).get();
    }

    /**
     * Função para excluir um anexo pelo ID
     */
    public void deleteById(Long id) {
        anexoRepository.deleteById(id);
    }

    /**
     * Função para buscar um anexo pelo nome informado
     */
    public Anexo findByNome(String nome) {
        return anexoRepository.findByNome(nome);
    }

    /**
     * Função para excluir um anexo pelo nome
     */
    public void deleteByNome(String nome) {
        anexoRepository.deleteByNome(nome);
    }

    /**
     * Função para salvar um anexo no banco de dados
     */
    public Anexo save(Anexo anexo) {
        return anexoRepository.save(anexo);
    }
}
