package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Historico;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {
    private HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public List<Historico> findAll() {
        return historicoRepository.findAll();
    }

    public Optional<Historico> findById(Long id) {
        return historicoRepository.findById(id);
    }

    public Optional<Historico> findByAutor(Usuario autor) {
        return historicoRepository.findByAutor(autor);
    }

    public Boolean existsById(Long id) {
        return historicoRepository.existsById(id);
    }

    public Boolean existsByAutor(Usuario autor) {
        return historicoRepository.existsByAutor(autor);
    }

    public <S extends Historico> S save(S entity) {
        return historicoRepository.save(entity);
    }

    public void deleteById(Long id) {
        historicoRepository.deleteById(id);
    }
}
