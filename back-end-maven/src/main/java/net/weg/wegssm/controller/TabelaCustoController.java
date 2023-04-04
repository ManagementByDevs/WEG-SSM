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

/**
 * Classe controller para as tabelas de custos
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/tabela-custo")
@CrossOrigin(origins = "http://localhost:3000")
public class TabelaCustoController {

    /**
     * Classe service das tabelas de custos
     */
    private TabelaCustoService tabelaCustoService;

    /**
     * Classe service dos custos
     */
    private CustoService custoService;

    /**
     * Classe service dos CCs
     */
    private CCsService ccsService;

    /**
     * Função para criar uma nova tabela de custos, recebendo o objeto como body
     */
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

    /**
     * Função para atualizar uma tabela de custos, recebendo o objeto no body
     */
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

    /**
     * Função para excluir uma tabela de custos pelo ID, recebido como variável
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        if (!tabelaCustoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma tabela de custos com este id.");
        }
        tabelaCustoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Tabela de custos deletada com sucesso.");
    }
}
