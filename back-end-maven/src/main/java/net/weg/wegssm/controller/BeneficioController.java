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
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/beneficio")
public class BeneficioController {

    private BeneficioService beneficioService;

    /**
     * Método GET para buscar todos os benefícios
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Beneficio>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.findAll());
    }

    /**
     * Método GET para buscar um benefício específico através do id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!beneficioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum benefício com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(beneficioService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BeneficioDTO beneficioDTO) {
        if (beneficioService.existsByTipoBeneficio(beneficioDTO.getTipoBeneficio())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse tipo de benefício já está em uso.");
        }

        Beneficio beneficio = new Beneficio();
        BeanUtils.copyProperties(beneficioDTO, beneficio);

        return ResponseEntity.status(HttpStatus.OK).body(beneficioService.save(beneficio));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!beneficioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrao nenhum benefício com este id.");
        }

        beneficioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Benefício deletado com sucesso.");
    }

}
