package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.CcDTO;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.model.service.CCsService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Classe controller para as CCs
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/cc")
public class CCsController {

    /**
     * Classe service das CCs
     */
    private CCsService ccsService;

    /**
     * Método POST para salvar uma CC, recebendo o objeto no body
     *
     * @param ccDTO - Objeto a ser cadastrado
     * @return - Retorno do objeto cadastrado
     */
    @PostMapping
    public ResponseEntity<CC> save(@RequestBody @Valid CcDTO ccDTO) {
        CC cc = new CC();
        BeanUtils.copyProperties(ccDTO, cc);
        return ResponseEntity.status(HttpStatus.OK).body(ccsService.save(cc));
    }

    /**
     * Método DELETE para excluir uma CC pelo seu ID
     *
     * @param id - ID utilizado para um CC a ser deletado
     * @return - Retorno de sucesso ou erro
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!ccsService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum CC com este id.");
        }

        ccsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("CC deletado com sucesso.");
    }

}