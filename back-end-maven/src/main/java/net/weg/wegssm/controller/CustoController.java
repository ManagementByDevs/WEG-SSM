package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.CustoDTO;
import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.model.service.CustoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Classe controller para os custos
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/custo")
@CrossOrigin(origins = "http://localhost:3000")
public class CustoController {

    /**
     * Classe service dos custos
     */
    private CustoService custoService;

    /**
     * Função para salvar um custo pelo seu objeto recebido no body
     */
    @PostMapping
    public ResponseEntity<Custo> save(@RequestBody @Valid CustoDTO custoDTO) {
        Custo custo = new Custo();
        BeanUtils.copyProperties(custoDTO, custo);
        return ResponseEntity.status(HttpStatus.OK).body(custoService.save(custo));
    }


    /**
     * Função para excluir un custo pelo seu ID, recebido como variável
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!custoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum custo com este id.");
        }

        custoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Custo deletado com sucesso.");
    }

}
