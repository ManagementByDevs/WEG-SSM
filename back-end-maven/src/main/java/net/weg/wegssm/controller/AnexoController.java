package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.service.AnexoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

/**
 * Classe Controller para as funções dos anexos
 */
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/anexo")
public class AnexoController {

    /**
     * Service de anexos
     */
    private AnexoService anexoService;

    /**
     * Função para criar um anexo no banco de dados, recebendo um AnexoDTO no body
     */
    @PostMapping
    public ResponseEntity<Anexo> save(@RequestParam(value = "anexo") MultipartFile file) throws IOException {
        Anexo anexo = new Anexo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return ResponseEntity.status(HttpStatus.OK).body(anexoService.save(anexo));
    }

    /**
     * Função para excluir um anexo pelo seu ID, recebido pela variável "id"
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        Anexo anexo = anexoService.findById(id);
        anexoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(anexo);
    }

    /**
     * Função para excluir um anexo pelo seu nome, recebido pela variável "nome"
     */
    @Transactional
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Object> deleteByNome(@PathVariable(value = "nome") String nome) {
        try {
            Anexo anexo = anexoService.findByNome(nome);
            anexoService.deleteByNome(nome);
            return ResponseEntity.status(HttpStatus.OK).body(anexo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Sem anexo!");
        }
    }
}
