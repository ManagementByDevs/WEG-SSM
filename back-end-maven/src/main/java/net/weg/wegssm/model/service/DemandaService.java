package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandaService {

    private DemandaRepository demandaRepository;

    public DemandaService(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    public List<Demanda> findAll() {
        return demandaRepository.findAll();
    }

    public Page<Demanda> findAll(Pageable pageable) {
        return demandaRepository.findAll(pageable);
    }

    public Optional<Demanda> findById(Long id) {
        return demandaRepository.findById(id);
    }

    public List<Demanda> findByStatus(Status status) {
        return demandaRepository.findByStatus(status);
    }

    public List<Demanda> findByUsuario(Usuario usuario) {
        return demandaRepository.findByUsuario(usuario);
    }

    public List<Demanda> findByTitulo(String titulo) {
        return demandaRepository.findByTitulo(titulo);
    }

    public List<Demanda> findByForum(Forum forum) {
        return demandaRepository.findByForum(forum);
    }

    public List<Demanda> findByDepartamento(Departamento departamento) { return demandaRepository.findByDepartamento(departamento); }

    public boolean existsById(Long id) {
        return demandaRepository.existsById(id);
    }

    public <S extends Demanda> S save(S entity) {
        return demandaRepository.save(entity);
    }

    public void deleteById(Long id) {
        demandaRepository.deleteById(id);
    }

}
