package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.service.EscopoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/escopo")
public class EscopoController {
    private EscopoService escopoService;

    /**
     * Método GET para listar todos os escopos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Escopo>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findAll());
    }

    /**
     * Método GET para listar um escopo específico através de um id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        if(!escopoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(escopoService.findById(id).get());
    }

    /**
     * Método GET para listar um escopo específico através do titulo
     * @param titulo
     * @return
     */
    @GetMapping("/{titulo}")
    public ResponseEntity<Object> findByTitle(@PathVariable(value = "titulo") String titulo){
        if(!escopoService.existsByTitle(titulo)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este título.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(escopoService.findByTitle(titulo).get());
    }

    /**
     * Método GET para listar um escopo específico através da porcentagem feita
     * @param porcentagem
     * @return
     */
    @GetMapping("/{porcentagem}")
    public ResponseEntity<Object> findByPercentagem(@PathVariable(value = "porcentagem") Long porcentagem){
        if(!escopoService.existsByPercentagem(porcentagem)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com esta porcentagem.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(escopoService.findByPercentagem(porcentagem).get());
    }

    /**
     * Método POST para criar um escopo no banco de dados
     * @param escopoDto ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EscopoDTO escopoDto){
        Escopo escopo = new Escopo();
        BeanUtils.copyProperties(escopoDto, escopo);
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

    /**
     * Método DELETE para deletar um escopo, colocando sua visibilidade como false
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!escopoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }

        Escopo escopo = escopoService.findById(id).get();
        escopo.setVisibilidade(false);
        escopoService.save(escopo);
        return ResponseEntity.status(HttpStatus.OK).body(escopo);
    }

    /**
     * Método PUT para atualizar um escopo no banco de dados, através de um id
     * @param id
     * @param escopoDTO ( Novos dados do escopo = req.body )
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid EscopoDTO escopoDTO) {
        Optional<Escopo> escopoOptional = escopoService.findById(id);

        if (escopoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar um escopo com este id.");
        }

        Escopo escopo = escopoOptional.get();
        BeanUtils.copyProperties(escopoDTO, escopo, "id");
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

}