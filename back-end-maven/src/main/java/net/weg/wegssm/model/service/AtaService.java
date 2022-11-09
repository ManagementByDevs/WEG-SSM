package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.repository.AtaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Optional<Ata> findByNumeroSequencial(String numeroSequencial) { return ataRepository.findByNumeroSequencial(numeroSequencial); }

//    public List<Ata> findByData(Date data) {
//        return ataRepository.findByData(data);
//    }

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

}
