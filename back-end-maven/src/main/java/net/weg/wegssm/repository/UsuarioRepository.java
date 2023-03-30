package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para os usuários
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Função de verificação caso exista um usuário com o email recebido
     */
    Boolean existsByEmail(String email);

    /**
     * Função para buscar um usuário pelo seu email
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Função para buscar um usuário pelo seu email e senha
     */
    Usuario findByEmailAndSenha(String email, String senha);

    /**
     * Função para buscar uma lista de usuários com o nome (ou parte) e tipo usuário recebidos
     */
    List<Usuario> findByNomeContainingAndTipoUsuario(String nome, TipoUsuario tipo_usuario, Pageable pageable);

    /**
     * Função para buscar um usuário pelo seu departamento e tipo de usuário
     */
    Usuario findByDepartamentoAndTipoUsuario(Departamento departamento, TipoUsuario tipoUsuario);
}
