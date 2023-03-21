package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.ResponsavelNegocioDTO;
import net.weg.wegssm.model.entities.ResponsavelNegocio;
import net.weg.wegssm.model.service.ResponsavelNegocioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Classe controller para os responsáveis de negócio
 */
@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/responsavel_negocio")
@CrossOrigin(origins = "http://localhost:3000")
public class ResponsavelNegocioController {

    /**
     * Classe service dos responsáveis de negócio
     */
    private ResponsavelNegocioService responsavelNegocioService;

    /**
     * Função para criar um novo responsável de negócio
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ResponsavelNegocioDTO responsavelNegocioDTO) {
        ResponsavelNegocio responsavelNegocio = new ResponsavelNegocio();
        BeanUtils.copyProperties(responsavelNegocioDTO, responsavelNegocio);

        return ResponseEntity.status(HttpStatus.OK).body(responsavelNegocioService.save(responsavelNegocio));
    }

    /**
     * Função para atualizar um responsável de negócio
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ResponsavelNegocio responsavelNegocio) {
        return ResponseEntity.status(HttpStatus.OK).body(responsavelNegocioService.save(responsavelNegocio));
    }

    /**
     * Função para excluir um responsável de negócio pelo seu ID
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!responsavelNegocioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum responsável negócio com este id.");
        }
        responsavelNegocioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Responsável negócio deletado com sucesso.");
    }

}
