package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.service.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe controller para os fóruns
 */
@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/forum")
public class ForumController {

    /**
     * Classe service dos fóruns
     */
    private ForumService forumService;

    /**
     * Função para buscar todos os fóruns salvos
     */
    @GetMapping
    public ResponseEntity<List<Forum>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(forumService.findAll());
    }

}
