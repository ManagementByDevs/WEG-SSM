package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.repository.AnexoRepository;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnexoService {

    private AnexoRepository anexoRepository;

    public AnexoService(AnexoRepository anexoRepository) {
        this.anexoRepository = anexoRepository;
    }

    public Anexo findById(Long id) {
        return anexoRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        anexoRepository.deleteById(id);
    }

    public Anexo findByNome(String nome) {
        return anexoRepository.findByNome(nome);
    }

    public void deleteByNome(String nome) {
        anexoRepository.deleteByNome(nome);
    }
}
