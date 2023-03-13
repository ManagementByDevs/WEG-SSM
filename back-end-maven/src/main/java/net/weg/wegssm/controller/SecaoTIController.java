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

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/secao_ti")
public class SecaoTIController {

    private SecaoTIService secaoTIService;

    /**
     * Método GET para buscar todas as seções de TI
     */
    @GetMapping
    public ResponseEntity<List<SecaoTI>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(secaoTIService.findAll());
    }
}
