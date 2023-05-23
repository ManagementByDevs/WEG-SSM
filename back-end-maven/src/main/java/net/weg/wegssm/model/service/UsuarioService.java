package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe service para os usuários
 */
@Service
@AllArgsConstructor
public class UsuarioService {

    /**
     * Classe repository do usuário
     */
    private UsuarioRepository usuarioRepository;

    /**
     * Função para buscar um usuário pelo seu ID
     *
     * @param id
     * @return
     */
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Função booleana de verificação caso exista um usuário com o email recebido
     *
     * @param email
     * @return
     */
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    /**
     * Função booleana de verificação caso exista um usuário com o ID recebido
     *
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    /**
     * Função para salvar um usuário no banco
     *
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Usuario> S save(S entity) {
        return usuarioRepository.save(entity);
    }

    /**
     * Função para excluir um usuário pelo seu ID
     *
     * @param id
     */
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Função para buscar uma lisa de usuários pelo seu nome e tipo de usuário
     *
     * @param nome
     * @param tipo_usuario
     * @param pageable
     * @return
     */
    public List<Usuario> findByNomeAndTipoUsuario(String nome, TipoUsuario tipo_usuario, Pageable pageable) {
        return usuarioRepository.findByNomeContainingAndTipoUsuario(nome, tipo_usuario, pageable);
    }

    /**
     * Função para buscar um usuário pelo seu departamento e tipo de usuário
     *
     * @param departamento
     * @param tipoUsuario
     * @return
     */
    public Usuario findByDepartamentoAndTipoUsuario(Departamento departamento, TipoUsuario tipoUsuario) {
        return usuarioRepository.findByDepartamentoAndTipoUsuario(departamento, tipoUsuario);
    }

    /**
     * Função para buscar um usuário pelo seu email
     *
     * @param email
     * @return
     */
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
