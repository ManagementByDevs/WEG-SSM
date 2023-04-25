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
@Controller
@RequestMapping("/weg_ssm/cc")
public class CCsController {

    /**
     * Classe service das CCs
     */
    private CCsService ccsService;

    /**
     * Função para salvar uma CC nova no banco, recebendo o objeto no body
     */
    @PostMapping
    public ResponseEntity<CC> save(@RequestBody @Valid CcDTO ccDTO) {
        CC cc = new CC();
        BeanUtils.copyProperties(ccDTO, cc);
        return ResponseEntity.status(HttpStatus.OK).body(ccsService.save(cc));
    }

    /**
     * Função para excluir uma CC pelo seu ID, recebido como variável
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
