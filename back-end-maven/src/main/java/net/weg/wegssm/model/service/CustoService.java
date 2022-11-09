package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.CustoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustoService {

    private CustoRepository custoRepository;

    public CustoService(CustoRepository custoRepository) {
        this.custoRepository = custoRepository;
    }

    public <S extends Custo> S save(S entity) {
        return custoRepository.save(entity);
    }

    public List<Custo> findAll(){
        return custoRepository.findAll();
    }

    public Optional<Custo> findById(Long id){
        return custoRepository.findById(id);
    }

//    public List<Custo> findByProposta(Proposta proposta){
//        return custoRepository.findByProposta(proposta);
//    }

    public void deleteById(Long id){
        custoRepository.deleteById(id);
    }

}
