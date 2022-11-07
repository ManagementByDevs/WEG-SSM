package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.dto.PautaDTO;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.service.PautaService;
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
@RequestMapping("/weg_ssm/pauta")
public class PautaController {
    private PautaService pautaService;

    /**
     * Método GET para listar todas as pautas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Pauta>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll());
    }

    /**
     * Método GET para listar uma pauta específica através de um id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        if(!pautaService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com este id.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findById(id).get());
    }

    /**
     * Método GET para listar uma pauta específica através da data de início da reunião
     * @param dataInicio
     * @return
     */
    @GetMapping("/datainicio/{dataInicio}")
    public ResponseEntity<Object> findByDataInicio(@PathVariable(value = "dataInicio") Date dataInicio){
        if(!pautaService.existsByInicioDataReuniao(dataInicio)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com esta data de início.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findByInicioDataReuniao(dataInicio).get());
    }

    /**
     * Método GET para listar uma pauta específica através da data de fim da reunião
     * @param dataFim
     * @return
     */
    @GetMapping("/datafim/{dataFim}")
    public ResponseEntity<Object> findByDataFim(@PathVariable(value = "dataFim") Date dataFim){
        if(!pautaService.existsByFimDataReuniao(dataFim)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com esta data de fim.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findByFimDataReuniao(dataFim).get());
    }

    /**
     * Método GET para listar uma pauta específica através do número sequencial
     * @param numeroSequencial
     * @return
     */
    @GetMapping("/numerosequencial/{numeroSequencial}")
    public ResponseEntity<Object> findByNumeroSequencial(@PathVariable(value = "numeroSequencial") Long numeroSequencial){
        if(!pautaService.existsByNumeroSequencial(numeroSequencial)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com este numero sequencial.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findByNumeroSequencial(numeroSequencial).get());
    }

    /**
     * Método POST para criar uma pauta no banco de dados
     * @param pautaDto ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PautaDTO pautaDto){
        Pauta pauta = new Pauta();
        pauta.setVisibilidade(true);
        BeanUtils.copyProperties(pautaDto, pauta);
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta));
    }

    /**
     * Método DELETE para deletar uma pauta do banco de dados
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!pautaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma pauta com este id.");
        }
        pautaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pauta deletada com sucesso.");
    }

    /**
     * Método DELETE para deletar uma pauta, colocando sua visibilidade como false
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!pautaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma pauta com este id.");
        }

        Pauta pauta = pautaService.findById(id).get();
        pauta.setVisibilidade(false);
        pautaService.save(pauta);
        return ResponseEntity.status(HttpStatus.OK).body(pauta);
    }

    /**
     * Método PUT para atualizar uma pauta no banco de dados, através de um id
     * @param id
     * @param pautaDto ( Novos dados da pauta = req.body )
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid PautaDTO pautaDto) {
        Optional<Pauta> pautaOptinal = pautaService.findById(id);

        if (pautaOptinal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar uma pauta com este id.");
        }

        Pauta pauta = pautaOptinal.get();
        BeanUtils.copyProperties(pautaDto, pauta, "id");
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta));
    }
}
