package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.BuDTO;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.Bu;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.service.BuService;
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
@RequestMapping("/weg_ssm/bu")
public class BuController {

    private BuService buService;

    /**
     * Método GET para buscar todas as bus
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Bu>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(buService.findAll());
    }

    /**
     * Método GET para buscar uma BU específica através do id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!buService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma bu com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(buService.findById(id).get());
    }

    /**
     * Método POST para salvar uma BU
     *
     * @param buDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BuDTO buDTO) {
        Bu bu = new Bu();
        BeanUtils.copyProperties(buDTO, bu);

        return ResponseEntity.status(HttpStatus.OK).body(buService.save(bu));
    }

    /**
     * Método DELETE para deletar uma BU
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!buService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrao nenhuma bu com este id.");
        }

        buService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("BU deletada com sucesso.");
    }

    /**
     * Método PUT para atualizar uma BU
     *
     * @param id
     * @param buDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid BuDTO buDTO) {
        if (!buService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma bu com este id.");
        }

        Bu bu = new Bu();
        BeanUtils.copyProperties(buDTO, bu);
        bu.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(buService.save(bu));
    }

}
