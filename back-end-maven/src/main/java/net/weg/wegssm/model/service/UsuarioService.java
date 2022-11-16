package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Departamento;
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

    public List<Usuario> findByDepartamento(Departamento departamento){ return usuarioRepository.findByDepartamento(departamento); }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByNomeStartsWith(String nome) {
        return usuarioRepository.findByNomeStartsWith(nome);
    }

    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    public Boolean existsByNomeContains(String titulo) {
        return usuarioRepository.existsByNomeContains(titulo);
    }

    public <S extends Usuario> S save(S entity) {
        return usuarioRepository.save(entity);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
