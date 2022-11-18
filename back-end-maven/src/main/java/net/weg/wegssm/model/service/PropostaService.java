package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.PropostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public List<Proposta> findAll(){
        return propostaRepository.findAll();
    }

    public Page<Proposta> findAll(Pageable pageable) {
        return propostaRepository.findAll(pageable);
    }

    public Optional<Proposta> findById(Long id){
        return propostaRepository.findById(id);
    }

    public Optional<Proposta> findByPpm(Long ppm){ return propostaRepository.findByCodigoPPM(ppm); }

//    public List<Proposta> findByTitulo(String titulo){ return propostaRepository.findByTitulo(titulo); }

    public Boolean existsById(Long id){ return propostaRepository.existsById(id); }

    public Boolean existsByPpm(Long ppm){ return propostaRepository.existsByCodigoPPM(ppm); }

    public <S extends Proposta> S save(S entity){
        return propostaRepository.save(entity);
    }

    public void deleteById(Long id){
        propostaRepository.deleteById(id);
    }

}
