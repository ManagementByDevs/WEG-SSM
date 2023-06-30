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
public class CustoController {

    /**
     * Classe service dos custos
     */
    private CustoService custoService;

    /**
     * Método POST para salvar um custo
     *
     * @param custoDTO - Objeto do custo a ser salvo
     * @return - Retorno do objeto salvo
     */
    @PostMapping
    public ResponseEntity<Custo> save(@RequestBody @Valid CustoDTO custoDTO) {
        Custo custo = new Custo();
        BeanUtils.copyProperties(custoDTO, custo);
        return ResponseEntity.status(HttpStatus.OK).body(custoService.save(custo));
    }

    /**
     * Método DELETE para deletar um custo pelo id
     *
     * @param id - ID do custo a ser deletado
     * @return - Retorno da mensagem de sucesso ou erro
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
