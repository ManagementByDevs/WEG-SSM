package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.service.AnexoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/anexo")
public class AnexoController {

    private AnexoService anexoService;

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        Anexo anexo = anexoService.findById(id);
        anexoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(anexo);
    }

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
