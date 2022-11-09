package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) { this.departamentoRepository = departamentoRepository; }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return departamentoRepository.existsById(id);
    }

    public boolean existsByNome(String nome) {
        return departamentoRepository.existsByNome(nome);
    }

    public <S extends Departamento> S save(S entity) { return departamentoRepository.save(entity); }

    public void deleteById(Long id) {
        departamentoRepository.deleteById(id);
    }

}
