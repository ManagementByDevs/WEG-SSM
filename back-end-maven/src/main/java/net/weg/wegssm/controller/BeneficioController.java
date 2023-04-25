package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.service.BeneficioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Classe controller para os benefícios
 */
@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/beneficio")
public class BeneficioController {

    /**
     * Service dos benefícios
     */
    private BeneficioService beneficioService;

    /**
     * Função para salvar um benefício, recebendo o objeto no body
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BeneficioDTO beneficioDTO) {

        Beneficio beneficio = new Beneficio();
        BeanUtils.copyProperties(beneficioDTO, beneficio);

        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.save(beneficio));
    }

    /**
     * Função para atualizar um benefício já existente, recebendo ele atualizado no body
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Beneficio beneficio) {
        if (!beneficioService.existsById(beneficio.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Benefício não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.save(beneficio));
    }

    /**
     * Função para excluir um benefício, recebendo seu ID como variável
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!beneficioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum benefício com este id.");
        }

        Beneficio beneficio = beneficioService.findById(id).get();
        beneficioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(beneficio);
    }

}
