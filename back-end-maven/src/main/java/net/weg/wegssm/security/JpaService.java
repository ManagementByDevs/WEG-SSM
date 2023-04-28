package net.weg.wegssm.security;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioOptional = usuarioRepository.findByEmail(username);
        return new UserJpa(usuarioOptional);
    }

}
