package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.PautaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public Page<Pauta> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable);
    }

    public Optional<Pauta> findById(Long id) {
        return pautaRepository.findById(id);
    }

    public Optional<Pauta> findByNumeroSequencial(Long numeroSequencial) { return pautaRepository.findByNumeroSequencial(numeroSequencial); }

    public Boolean existsById(Long id) {
        return pautaRepository.existsById(id);
    }

    public Boolean existsByNumeroSequencial(Long numeroSequencial) { return pautaRepository.existsByNumeroSequencial(numeroSequencial); }

    public <S extends Pauta> S save(S entity) {
        return pautaRepository.save(entity);
    }

    public void deleteById(Long id) {
        pautaRepository.deleteById(id);
    }

    public Pauta findByPropostasContaining(Proposta proposta) {
        return pautaRepository.findByPropostasContaining(proposta);
    }
}
