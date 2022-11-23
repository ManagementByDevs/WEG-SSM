package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.AtaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AtaService {

    private AtaRepository ataRepository;

    public AtaService(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
    }

    public List<Ata> findAll() {
        return ataRepository.findAll();
    }

    public Page<Ata> findAll(Pageable pageable) {
        return ataRepository.findAll(pageable);
    }

    public Optional<Ata> findByNumeroSequencial(String numeroSequencial) { return ataRepository.findByNumeroSequencial(numeroSequencial); }

    public Optional<Ata> findById(Long id) {
        return ataRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return ataRepository.existsById(id);
    }

    public Boolean existsByNumeroSequencial(String numeroSequencial) { return ataRepository.existsByNumeroSequencial(numeroSequencial); }

    public <S extends Ata> S save(S entity) {
        return ataRepository.save(entity);
    }

    public void deleteById(Long id) {
        ataRepository.deleteById(id);
    }

    public Ata findByPropostasContaining(Proposta proposta) {
        return ataRepository.findByPropostasContaining(proposta);
    }
}
