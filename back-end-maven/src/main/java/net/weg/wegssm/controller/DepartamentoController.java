package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Classe controller para os departamentos
 */
@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/departamento")
public class DepartamentoController {

    /**
     * Classe service dos departamentos
     */
    private DepartamentoService departamentoService;

    /**
     * Função para procurar todos os departamentos salvos
     */
    @GetMapping
    public ResponseEntity<List<Departamento>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
    }

}
