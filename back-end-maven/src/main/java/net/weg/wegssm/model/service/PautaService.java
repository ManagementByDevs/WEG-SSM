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

    public Optional<Pauta> findByInicioDataReuniao(Date dataInicio) {
        return pautaRepository.findByInicioDataReuniao(dataInicio);
    }

    public Optional<Pauta> findByFimDataReuniao(Date dataFim) {
        return pautaRepository.findByFimDataReuniao(dataFim);
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

    public Boolean existsByFimDataReuniao(Date dataFim) {
        return pautaRepository.existsByFimDataReuniao(dataFim);
    }

    public Boolean existsByInicioDataReuniao(Date dataInicio) {
        return pautaRepository.existsByInicioDataReuniao(dataInicio);
    }

    public <S extends Pauta> S save(S entity) {
        return pautaRepository.save(entity);
    }

    public void deleteById(Long id) {
        pautaRepository.deleteById(id);
    }
}
