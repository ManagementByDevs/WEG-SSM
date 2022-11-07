package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.*;
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

    /**
     * Método GET para buscar todas as demandas
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    /**
     * Méotod GET para buscar uma demanda específica através do id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findById(id).get());
    }

    /**
     * Método GET para buscar as demandas com um determinado status
     *
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Demanda>> findByStatus(@PathVariable(value = "status") Status status) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByStatus(status));
    }

    /**
     * Método GET para buscar as demandas de um determinado forum
     *
     * @param forum
     * @return
     */
    @GetMapping("/forum/{forum}")
    public ResponseEntity<List<Demanda>> findByForum(@PathVariable(value = "forum") Forum forum) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByForum(forum));
    }

    /**
     * Método GET para buscar as demandas de um determinado usuário
     *
     * @param usuario
     * @return
     */
    @GetMapping("usuario/{usuario}")
    public ResponseEntity<List<Demanda>> findByUsuario(@PathVariable(value = "usuario") Usuario usuario) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByUsuario(usuario));
    }

    /**
     * Método GET para buscar uma demanda através de um título
     * @param titulo
     * @return
     */
    @GetMapping("titulo/{titulo}")
    public ResponseEntity<List<Demanda>> findByTitulo(@PathVariable(value = "titulo") String titulo) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByTitulo(titulo));
    }

    /**
     * Método GET para buscar todas as demandas de um determinado departamento
     * @param departamento
     * @return
     */
    @GetMapping("departamento/{departamento}")
    public ResponseEntity<List<Demanda>> findByDepartamento(@PathVariable(value = "departamento") Departamento departamento){
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByDepartamento(departamento));
    }

    /**
     * Método POST para criar uma demanda
     *
     * @param demandaDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DemandaDTO demandaDto) {
        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método PUT para editar uma demanda já existente
     *
     * @param id
     * @param demandaDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid DemandaDTO demandaDto) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        demanda.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método DELETE para deletar uma demanda, editando sua visibilidade para false
     *
     * @param id
     * @return
     */
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
