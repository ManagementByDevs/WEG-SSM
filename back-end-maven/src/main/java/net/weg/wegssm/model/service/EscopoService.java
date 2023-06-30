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
     * @return
     */
    public List<Escopo> findAll() {
        return escopoRepository.findAll();
    }

    /**
     * Função para buscar um escopo através de um id
     * @param id
     * @return
     */
    public Optional<Escopo> findById(Long id) {
        return escopoRepository.findById(id);
    }

    /**
     * Função para buscar um escopo através de um título
     * @param titulo
     * @return
     */
    public Optional<Escopo> findByTitle(String titulo) {
        return escopoRepository.findByTitulo(titulo);
    }

    /**
     * Função para buscar um escopo pelo usuário
     * @param usuario
     * @return
     */
    public List<Object> findByUsuario(Usuario usuario) {
        return escopoRepository.findByUsuario(usuario);
    }

    /**
     * Função para buscar um escopo pelo usuário com paginação
     * @param usuario
     * @param pageable
     * @return
     */
    public Page<Escopo> findByUsuario(Usuario usuario, Pageable pageable) {
        return escopoRepository.findByUsuario(usuario, pageable);
    }

    /**
     * Função para verificar se existe um escopo através do id
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return escopoRepository.existsById(id);
    }

    /**
     * Função para verificar se existe um escopo através do título
     * @param titulo
     * @return
     */
    public Boolean existsByTitle(String titulo) {
        return escopoRepository.existsByTitulo(titulo);
    }

    /**
     * Função para verificar se existe um escopo através do usuário
     * @param usuario
     * @return
     */
    public boolean existsByUsuario(Usuario usuario) {
        return escopoRepository.existsByUsuario(usuario);
    }

    /**
     * Função para salvar um escopo
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Escopo> S save(S entity) {
        return escopoRepository.save(entity);
    }

    /**
     * Função para deletar um escopo
     * @param id
     */
    public void deleteById(Long id) {
        escopoRepository.deleteById(id);
    }

    /**
     * Função para buscar um escopo através do usuário e do título com paginação
     * @param usuario
     * @param titulo
     * @param pageable
     * @return
     */
    public Page<Escopo> findByUsuarioAndTitulo(Usuario usuario, String titulo, Pageable pageable) {
        return escopoRepository.findByUsuarioAndTituloContaining(usuario, titulo, pageable);
    }

}
