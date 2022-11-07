package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {

//    List<Demanda> findByStatus(Status status);
//
//    List<Demanda> findByForum(Forum forum);
//
//    List<Demanda> findByDepartamento(String departamento);
//
//    List<Demanda> findBySolicitante(String solicitante);
//
//    List<Demanda> findByGerenteResponsavel(String gerenteResponsavel);

//    Optional<Demanda> findByTitulo(String titulo); //  verificar se é necessário preecnher o titulo completo ou ele já faz a busca por parte do titulo;

}

