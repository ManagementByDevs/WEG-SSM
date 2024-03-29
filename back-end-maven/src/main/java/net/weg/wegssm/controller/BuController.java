package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Bu;
import net.weg.wegssm.model.service.BuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe controller para as BUs
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/bu")
public class BuController {

    /**
     * Service usado para as buscas de BUs
     */
    private BuService buService;

    /**
     * Método GET para buscar todas as BUs salvas
     *
     * @return - Retorno da lista de bus
     */
    @GetMapping
    public ResponseEntity<List<Bu>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(buService.findAll());
    }

}
