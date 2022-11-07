package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Optional<Pauta> findById(Long id) {
        return pautaRepository.findById(id);
    }

    public Optional<Pauta> findByDataInicio(Date dataInicio) {
        return pautaRepository.findByDataInicio(dataInicio);
    }

    public Optional<Pauta> findByDataFim(Date dataFim) {
        return pautaRepository.findByDataFim(dataFim);
    }

    public Optional<Pauta> findByNumeroSequencial(Long numeroSequencial) {
        return pautaRepository.findByNumeroSequencial(numeroSequencial);
    }

    public Boolean existsById(Long id) {
        return pautaRepository.existsById(id);
    }

    public Boolean existsByNumeroSequencial(Long numeroSequencial) {
        return pautaRepository.existsByNumeroSequencial(numeroSequencial);
    }

    public Boolean existsByDataFim(Date dataFim) {
        return pautaRepository.existsByDataFim(dataFim);
    }

    public Boolean existsByDataInicio(Date dataInicio) {
        return pautaRepository.existsByDataInicio(dataInicio);
    }

    public <S extends Pauta> S save(S entity) {
        return pautaRepository.save(entity);
    }

    public void deleteById(Long id) {
        pautaRepository.deleteById(id);
    }
}
