package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.TabelaCustoDTO;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.model.entities.TabelaCusto;
import net.weg.wegssm.model.service.CCsService;
import net.weg.wegssm.model.service.CustoService;
import net.weg.wegssm.model.service.TabelaCustoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/tabela-custo")
@CrossOrigin(origins = "http://localhost:3000")
public class TabelaCustoController {

    private TabelaCustoService tabelaCustoService;
    private CustoService custoService;
    private CCsService ccsService;

    @GetMapping
    public ResponseEntity<List<TabelaCusto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tabelaCustoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaCusto> findById(@RequestParam(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tabelaCustoService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<TabelaCusto> save(@RequestBody @Valid TabelaCustoDTO tabelaCustoDTO) {
        for (Custo custo : tabelaCustoDTO.getCustos()) {
            custoService.save(custo);
        }

        for (CC cc : tabelaCustoDTO.getCcs()) {
            ccsService.save(cc);
        }

        TabelaCusto tabelaCusto = new TabelaCusto();
        BeanUtils.copyProperties(tabelaCustoDTO, tabelaCusto);
        return ResponseEntity.status(HttpStatus.OK).body(tabelaCustoService.save(tabelaCusto));
    }

    @PutMapping
    public ResponseEntity<TabelaCusto> update(@RequestBody TabelaCusto tabelaCusto) {
        for (Custo custo : tabelaCusto.getCustos()) {
            custoService.save(custo);
        }

        for (CC cc : tabelaCusto.getCcs()) {
            ccsService.save(cc);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tabelaCustoService.save(tabelaCusto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!tabelaCustoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma tabela de custos com este id.");
        }
        tabelaCustoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Tabela de custos deletada com sucesso.");
    }
}
