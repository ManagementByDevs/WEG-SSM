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

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/cc")
@CrossOrigin(origins = "http://localhost:3000")
public class CCsController {

    private CCsService ccsService;

    @PostMapping
    public ResponseEntity<CC> save(@RequestBody @Valid CcDTO ccDTO) {
        CC cc = new CC();
        BeanUtils.copyProperties(ccDTO, cc);
        return ResponseEntity.status(HttpStatus.OK).body(ccsService.save(cc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!ccsService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum CC com este id.");
        }

        ccsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("CC deletado com sucesso.");
    }
}
