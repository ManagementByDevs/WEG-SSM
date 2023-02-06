package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.CustoDTO;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.TabelaCustoDTO;
import net.weg.wegssm.model.entities.Custo;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.CustoService;
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
@RequestMapping("/weg_ssm/custo")
public class CustoController {

    private CustoService custoService;

    /**
     * Método GET para buscar todos os custos
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Custo>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(custoService.findAll());
    }

    /**
     * Método GET para buscar todos os custos através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!custoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum custo com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(custoService.findById(id).get());
    }

    /**
     * Método DELETE para excluir um custo através de um id
     *
     * @param id
     * @return
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
