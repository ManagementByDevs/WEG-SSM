package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.ResponsavelNegocio;
import net.weg.wegssm.repository.ResponsavelNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelNegocioService {

    private ResponsavelNegocioRepository responsavelNegocioRepository;

    public ResponsavelNegocioService(ResponsavelNegocioRepository responsavelNegocioRepository) { this.responsavelNegocioRepository = responsavelNegocioRepository; }

    public List<ResponsavelNegocio> findAll(){ return responsavelNegocioRepository.findAll(); }

    public Optional<ResponsavelNegocio> findById(Long id){ return responsavelNegocioRepository.findById(id); }

    public Boolean existsById(Long id){ return responsavelNegocioRepository.existsById(id); }

    public <S extends ResponsavelNegocio> S save(S entity){ return responsavelNegocioRepository.save(entity); }

    public void deleteById(Long id){ responsavelNegocioRepository.deleteById(id); }

}
