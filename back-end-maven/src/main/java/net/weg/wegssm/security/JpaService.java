package net.weg.wegssm.security;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe usada para buscar um usuário no banco para sua autenticação
 */
@Service
@AllArgsConstructor
public class JpaService implements UserDetailsService {

    /**
     * Repository usado para a busca dos usuários no banco
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Função que busca o usuário no banco de dados caso ele exista, prosseguindo com a autenticação
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new UserJpa(usuarioOptional.get());
    }

}
