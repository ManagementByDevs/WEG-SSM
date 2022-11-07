package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.AtaDto;
import net.weg.wegssm.dto.DemandaDto;
import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Status;
import net.weg.wegssm.model.service.DemandaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    private DemandaService demandaService;

    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findById(id).get());
    }

//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<Demanda>> findByStatus(@PathVariable(value = "status") Status status) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByStatus(status));
//    }
//
//    @GetMapping("/forum/{forum}")
//    public ResponseEntity<List<Demanda>> findByForum(@PathVariable(value = "forum") Forum forum) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByForum(forum));
//    }
//
//    @GetMapping("solicitante/{solicitante}")
//    public ResponseEntity<List<Demanda>> findBySolicitante (@PathVariable(value = "solicitante") String solicitante) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findBySolicitante(solicitante));
//    }
//
//    @GetMapping("gerenteResponsavel/{gerenteResponsavel}")
//    public ResponseEntity<List<Demanda>> findByGerenteResponsavel (@PathVariable(value = "gerenteResponsavel") String gerenteResponsavel) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByGerenteResponsavel(gerenteResponsavel));
//    }
//
//    @GetMapping("departamento/{departamento}")
//    public ResponseEntity<List<Demanda>> findByDepartamento (@PathVariable(value = "departamento") String departamento) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByDepartamento(departamento));
//    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DemandaDto demandaDto) {
        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid DemandaDto demandaDto) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        demanda.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        Demanda demanda = demandaService.findById(id).get();
        demanda.setVisibilidade(false);
        demandaService.save(demanda);
        return ResponseEntity.status(HttpStatus.OK).body(demanda);
    }
}
