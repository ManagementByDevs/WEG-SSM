package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.BeneficioDTO;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.service.BeneficioService;
import net.weg.wegssm.util.BeneficioUtil;
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
@RestController
@RequestMapping("/weg_ssm/beneficio")
public class BeneficioController {

    /**
     * Service dos benefícios
     */
    private BeneficioService beneficioService;

    /**
     * Método POST para salvar um benefício, recebendo o objeto no body
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> createNew() {
        Beneficio beneficio = new Beneficio();
        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.save(beneficio));
    }

    /**
     * Método PUT para atualizar um benefício já existente, recebendo ele atualizado no body
     *
     * @param id
     * @param beneficioJson
     * @param memoriaCalculo
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestParam(value = "beneficio") String beneficioJson,
                                         @RequestParam(value = "memoriaCalculo") byte[] memoriaCalculo) {

        BeneficioUtil beneficioUtil = new BeneficioUtil();
        Beneficio beneficio = beneficioUtil.convertJsonToModel(beneficioJson);
        beneficio.setId(id);
        beneficio.setMemoriaCalculo(memoriaCalculo);

        if (!beneficioService.existsById(beneficio.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Benefício não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.save(beneficio));
    }

    /**
     * Método DELETE para excluir um benefício, recebendo seu ID como variável
     *
     * @param id
     * @return
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
