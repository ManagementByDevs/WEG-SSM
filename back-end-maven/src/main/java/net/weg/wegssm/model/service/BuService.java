package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Bu;
import net.weg.wegssm.repository.BuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuService {

    private BuRepository buRepository;

    public BuService(BuRepository buRepository) {
        this.buRepository = buRepository;
    }

    public List<Bu> findAll() {
        return buRepository.findAll();
    }

    public Optional<Bu> findById(Long id) {
        return buRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return buRepository.existsById(id);
    }

    public <S extends Bu> S save(S entity) {
        return buRepository.save(entity);
    }

    public void deleteById(Long id) {
        buRepository.deleteById(id);
    }

}
