package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método que verifica se existe um usuário com o email passado por parâmetro
     *
     * @param email
     * @return
     */
    Boolean existsByEmail(String email);

    /**
     * Método para listar todos os usuários pertencentes a um departamento
     *
     * @param departamento
     * @return
     */
    List<Usuario> findByDepartamento(Departamento departamento);

    /**
     * Método para listar todos os usuários que começam com o nome passado por parâmetro
     *
     * @param nome
     * @return
     */
    List<Usuario> findByNomeStartsWith(String nome);

    Boolean existsByNomeContains(String titulo);

    Optional<Usuario> findByEmail(String email);

    Usuario findByEmailAndSenha(String email, String senha);

    Boolean existsByEmailAndSenha(String email, String senha);
}
