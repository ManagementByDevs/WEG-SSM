package net.weg.wegssm.model.service;

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
public class UsuarioService {

    /**
     * Classe repository do usuário
     */
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor da classe
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Função para buscar um usuário pelo seu ID
     */
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Função booleana de verificação caso exista um usuário com o email recebido
     */
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    /**
     * Função booleana de verificação caso exista um usuário com o ID recebido
     */
    public Boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    /**
     * Função para salvar um usuário no banco
     */
    public <S extends Usuario> S save(S entity) {
        return usuarioRepository.save(entity);
    }

    /**
     * Função para excluir um usuário pelo seu ID
     */
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Função para buscar uma lisa de usuários pelo seu nome e tipo de usuário
     */
    public List<Usuario> findByNomeAndTipoUsuario(String nome, TipoUsuario tipo_usuario, Pageable pageable) {
        return usuarioRepository.findByNomeContainingAndTipoUsuario(nome, tipo_usuario, pageable);
    }

    /**
     * Função para buscar um usuário pelo seu departamento e tipo de usuário
     */
    public Usuario findByDepartamentoAndTipoUsuario(Departamento departamento, TipoUsuario tipoUsuario) {
        return usuarioRepository.findByDepartamentoAndTipoUsuario(departamento, tipoUsuario);
    }

    /**
     * Função para buscar um usuário pelo seu email
     */
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
