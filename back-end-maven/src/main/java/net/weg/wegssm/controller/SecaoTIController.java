package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.SecaoTI;
import net.weg.wegssm.model.service.SecaoTIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Classe controller para as seções de TI */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/secao_ti")
public class SecaoTIController {

    /** Classe service das seções de TI */
    private SecaoTIService secaoTIService;

    /**
     * Função para buscar todas as seções de TI
     */
    @GetMapping
    public ResponseEntity<List<SecaoTI>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(secaoTIService.findAll());
    }
}
