package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.TipoBeneficio;
import net.weg.wegssm.repository.BeneficioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioService {

    private BeneficioRepository beneficioRepository;

    public BeneficioService(BeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    public List<Beneficio> findAll(){
        return beneficioRepository.findAll();
    }

    public Optional<Beneficio> findById(Long id){
        return beneficioRepository.findById(id);
    }

    public boolean existsByTipoBeneficio(TipoBeneficio tipoBeneficio){ return beneficioRepository.existsByTipoBeneficio(tipoBeneficio); }

    public boolean existsById(Long id){
        return beneficioRepository.existsById(id);
    }

    public <S extends Beneficio> S save(S entity){
        return beneficioRepository.save(entity);
    }

    public void deleteById(Long id){
        beneficioRepository.deleteById(id);
    }
}
