package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public <S extends Usuario> S save(S entity) {
        return usuarioRepository.save(entity);
    }

    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
