package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.AtaDto;
import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.service.AtaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/ata")
public class AtaController {

    private AtaService ataService;

    /**
     * Método GET para listar todas as atas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Ata>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ataService.findAll());
    }

    /**
     * Método GET para listar uma ata específica através do id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!ataService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma ata com este código.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ataService.findById(id).get());
    }

    /**
     * Método GET para listar uma ata através de seu número sequencial
     * @param numeroSequencial
     * @return
     */
    @GetMapping("numeroSequencial/{numeroSequencial}")
    public ResponseEntity<Object> findByNumeroSequencial(@PathVariable(value = "numeroSequencial") String numeroSequencial) {
        Optional<Ata> ataOptional = ataService.findByNumeroSequencial(numeroSequencial);
        if (ataOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma ata com este número sequencial.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ataOptional.get());
    }

    /**
     * Método POST para cadastrar uma ata no banco de dados
     * @param ataDto ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AtaDto ataDto) {

        if (ataService.existsByNumeroSequencial(ataDto.getNumeroSequencial())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O número sequencial já está em uso.");
        }

        Ata ata = new Ata();
        ata.setVisibilidade(true);
        BeanUtils.copyProperties(ataDto, ata);
        return ResponseEntity.status(HttpStatus.OK).body(ataService.save(ata));
    }

    /**
     * Método DELETE para deletar uma ata, colocando sua visibilidade como false
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!ataService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma ata com este id.");
        }

        Ata ata = ataService.findById(id).get();
        ata.setVisibilidade(false);
        ataService.save(ata);
        return ResponseEntity.status(HttpStatus.OK).body(ata);
    }

    // Arrumar a parte de buscar por data da ata

    //    @GetMapping("data/{data}")
    //    public ResponseEntity<Object> findByData(@PathVariable(value = "data") Date data) {
    //        Optional<Ata> ataOptional = ataService.findByData(data);
    //        if (ataOptional.isEmpty()) {
    //            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma ata com esta data.");
    //        }
    //        return ResponseEntity.status(HttpStatus.OK).body(ataOptional.get());
    //    }

}
