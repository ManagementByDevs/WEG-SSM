package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.TabelaCusto;
import net.weg.wegssm.repository.TabelaCustoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaCustoService {

    private TabelaCustoRepository tabelaCustoRepository;

    public TabelaCustoService(TabelaCustoRepository tabelaCustoRepository) {
        this.tabelaCustoRepository = tabelaCustoRepository;
    }

    public List<TabelaCusto> findAll() {
        return tabelaCustoRepository.findAll();
    }

    public TabelaCusto save(TabelaCusto tabelaCusto) {
        return tabelaCustoRepository.save(tabelaCusto);
    }

    public Boolean existsById(Long id) {
        return tabelaCustoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        tabelaCustoRepository.deleteById(id);
    }
}
