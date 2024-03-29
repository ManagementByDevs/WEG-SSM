package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Historico;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Classe service para o historico
 */
@Service
@AllArgsConstructor
public class HistoricoService {

    /**
     * Repository do historico
     */
    private HistoricoRepository historicoRepository;

    /**
     * Função para buscar todos os históricos
     *
     * @return - Lista de históricos
     */
    public List<Historico> findAll() {
        return historicoRepository.findAll();
    }

    /**
     * Função para buscar um histórico através de um id
     *
     * @param id - Id do histórico
     * @return - Histórico encontrado
     */
    public Optional<Historico> findById(Long id) {
        return historicoRepository.findById(id);
    }

    /**
     * Função par buscar um histórico através do autor
     *
     * @param autor - Autor do histórico
     * @return - Histórico encontrado
     */
    public Optional<Historico> findByAutor(Usuario autor) {
        return historicoRepository.findByAutor(autor);
    }

    /**
     * Função para verificar se existe um histórico através do id
     *
     * @param id - Id do histórico
     * @return - Verificando se existe um histórico ou não
     */
    public Boolean existsById(Long id) {
        return historicoRepository.existsById(id);
    }

    /**
     * Função para verificar se existe um histórico através do autor
     *
     * @param autor - Autor do histórico
     * @return - Verificando se existe um histórico por autor ou não
     */
    public Boolean existsByAutor(Usuario autor) {
        return historicoRepository.existsByAutor(autor);
    }

    /**
     * Função para salvar um histórico
     *
     * @param entity - Entidade do histórico
     * @param <S> - Tipo de entidade
     * @return - Histórico salvo
     */
    public <S extends Historico> S save(S entity) {
        return historicoRepository.save(entity);
    }

    /**
     * Função para deletar um histórico através do id
     *
     * @param id - Id do histórico
     */
    public void deleteById(Long id) {
        historicoRepository.deleteById(id);
    }

}
