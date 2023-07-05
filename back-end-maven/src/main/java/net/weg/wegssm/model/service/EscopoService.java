package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.EscopoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe service para o escopo service
 */
@Service
@AllArgsConstructor
public class EscopoService {

    /**
     * Repository do escopo
     */
    private EscopoRepository escopoRepository;

    /**
     * Função para buscar todos os escopos
     *
     * @return - Lista de escopos
     */
    public List<Escopo> findAll() {
        return escopoRepository.findAll();
    }

    /**
     * Função para buscar um escopo através de um id
     *
     * @param id - Id do escopo
     * @return - Escopo encontrado
     */
    public Optional<Escopo> findById(Long id) {
        return escopoRepository.findById(id);
    }

    /**
     * Função para buscar um escopo pelo usuário com paginação
     *
     * @param usuario  - Usuário solicitante
     * @param pageable - Componente de paginação
     * @return - Paginação de escopos de um usuário
     */
    public Page<Escopo> findByUsuario(Usuario usuario, Pageable pageable) {
        return escopoRepository.findByUsuario(usuario, pageable);
    }

    /**
     * Função para verificar se existe um escopo através do id
     *
     * @param id - Id do escopo
     * @return - Booleano se existe ou não
     */
    public Boolean existsById(Long id) {
        return escopoRepository.existsById(id);
    }

    /**
     * Função para salvar um escopo
     *
     * @param entity - Escopo a ser salvo
     * @param <S>    - Tipo do escopo
     * @return - Escopo salvo
     */
    public <S extends Escopo> S save(S entity) {
        return escopoRepository.save(entity);
    }

    /**
     * Função para deletar um escopo
     *
     * @param id - Id do escopo
     */
    public void deleteById(Long id) {
        escopoRepository.deleteById(id);
    }

    /**
     * Função para buscar um escopo através do usuário e do título com paginação
     *
     * @param usuario  - Usuário solicitante
     * @param titulo   - Título do escopo
     * @param pageable - Componente de paginação
     * @return - Paginação de escopos de um usuário com um título específico
     */
    public Page<Escopo> findByUsuarioAndTitulo(Usuario usuario, String titulo, Pageable pageable) {
        return escopoRepository.findByUsuarioAndTituloContaining(usuario, titulo, pageable);
    }

}
