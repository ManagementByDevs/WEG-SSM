package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DepartamentoDTO;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.service.DepartamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/departamento")
public class DepartamentoController {

    private DepartamentoService departamentoService;

    /**
     * Método para listar todos os departamentos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Departamento>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
    }

    /**
     * Método para buscar um departamento através de um id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!departamentoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhum departamento com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(departamentoService.findById(id).get());
    }

    /**
     * Método POST para criar um departamento
     * @param departamentoDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DepartamentoDTO departamentoDTO) {
        if (departamentoService.existsByNome(departamentoDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome do departamento já está em uso.");
        }

        Departamento departamento = new Departamento();
        departamento.setVisibilidade(true);
        BeanUtils.copyProperties(departamentoDTO, departamento);
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.save(departamento));
    }

    /**
     * Método PUT para atualizar um departamento através de um id
     * @param id
     * @param departamentoDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid DepartamentoDTO departamentoDTO) {
        Optional<Departamento> departamentoOptional = departamentoService.findById(id);

        if (departamentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar nenhum departamento com este id.");
        }

        if (departamentoService.existsByNome(departamentoDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome do departamento já está em uso.");
        }

        Departamento departamento = departamentoOptional.get();
        BeanUtils.copyProperties(departamentoDTO, departamento, "id");
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.save(departamento));
    }

    /**
     * Método DELETe para deletar um departamento, colocando sua visibilidade como false.
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!departamentoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum departamento com este id.");
        }

        Departamento departamento = departamentoService.findById(id).get();
        departamento.setVisibilidade(false);
        departamentoService.save(departamento);
        return ResponseEntity.status(HttpStatus.OK).body(departamento);
    }

}
