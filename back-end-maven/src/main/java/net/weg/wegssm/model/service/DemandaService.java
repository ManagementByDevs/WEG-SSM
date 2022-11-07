package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Status;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandaService {

    private DemandaRepository demandaRepository;

    public DemandaService(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    public List<Demanda> findAll() {
        return demandaRepository.findAll();
    }

    public Optional<Demanda> findById(Long id){
        return demandaRepository.findById(id);
    }

//    public List<Demanda> findByStatus(Status status){
//        return demandaRepository.findByStatus(status);
//    }
//
//    public List<Demanda> findBySolicitante(String solicitante){
//        return demandaRepository.findBySolicitante(solicitante);
//    }
//
//    public List<Demanda> findByGerenteResponsavel(String gerenteResponsavel){
//        return demandaRepository.findByGerenteResponsavel(gerenteResponsavel);
//    }
//
//    public List<Demanda> findByDepartamento(String departamento){
//        return demandaRepository.findByDepartamento(departamento);
//    }
//
//    public List<Demanda> findByForum(Forum forum){
//        return demandaRepository.findByForum(forum);
//    }

    public boolean existsById(Long id) {
        return demandaRepository.existsById(id);
    }

    public <S extends Demanda> S save(S entity) {
        return demandaRepository.save(entity);
    }

    // Salvar, editar, excluir e buscar por titulo
}
