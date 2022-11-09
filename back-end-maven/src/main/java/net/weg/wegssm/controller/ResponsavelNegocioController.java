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
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/responsavelNegocio")
public class ResponsavelNegocioController {

    private ResponsavelNegocioService responsavelNegocioService;

    /**
     * Método GET para listar todos os responsáveis de negócio
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ResponsavelNegocio>> findAll(){
        return ResponseEntity.status(200).body(responsavelNegocioService.findAll());
    }

    /**
     * Método GET para listar um reponsável de negócio específico através de um id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        if (!responsavelNegocioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum responsável negócio com este id.");
        }

        return ResponseEntity.status(200).body(responsavelNegocioService.findById(id));
    }

    /**
     * Método POST para criar um novo responsável de negócio
     * @param responsavelNegocioDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save (@RequestBody @Valid ResponsavelNegocioDTO responsavelNegocioDTO){
        ResponsavelNegocio responsavelNegocio = new ResponsavelNegocio();
        BeanUtils.copyProperties(responsavelNegocioDTO, responsavelNegocio);
        return ResponseEntity.status(HttpStatus.OK).body(responsavelNegocioService.save(responsavelNegocio));
    }

    /**
     * Método DELETE para excluir um responsável negócio do banco de dados
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id){
        if (!responsavelNegocioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum responsável negócio com este id.");
        }
        responsavelNegocioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Responsável negócio deletado com sucesso.");
    }

}
