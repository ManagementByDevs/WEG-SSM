package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.repository.EscopoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscopoService {
    private EscopoRepository escopoRepository;

    public EscopoService(EscopoRepository escopoRepository) {
        this.escopoRepository = escopoRepository;
    }

    public List<Escopo> findAll() {
        return escopoRepository.findAll();
    }

    public Optional<Escopo> findById(Long id) {
        return escopoRepository.findById(id);
    }

    public Optional<Escopo> findByTitle(String titulo) {
        return escopoRepository.findByTitulo(titulo);
    }

    public Optional<Escopo> findByPercentagem(Long porcentagem) {
        return escopoRepository.findByPorcentagem(porcentagem);
    }

    public Boolean existsById(Long id) {
        return escopoRepository.existsById(id);
    }

    public Boolean existsByTitle(String titulo) {
        return escopoRepository.existsByTitulo(titulo);
    }

    public Boolean existsByPercentagem(Long porcentagem) {
        return escopoRepository.existsByPorcentagem(porcentagem);
    }

    public <S extends Escopo> S save(S entity) {
        return escopoRepository.save(entity);
    }

    public void deleteById(Long id) {
        escopoRepository.deleteById(id);
    }
}
