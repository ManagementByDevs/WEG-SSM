package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.PropostaDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.service.PropostaService;
import net.weg.wegssm.util.EscopoUtil;
import net.weg.wegssm.util.PropostaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/proposta")
public class PropostaController {

    private PropostaService propostaService;

    /**
     * Método GET para listar todas as propostas
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Proposta>> findAll() {
        return ResponseEntity.status(200).body(propostaService.findAll());
    }

    /**
     * Método GET para listar uma proposta específica através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        return ResponseEntity.status(200).body(propostaService.findById(id));
    }

    /**
     * Método GET para listar uma proposta específica através de um código PPM
     *
     * @param ppm
     * @return
     */
    @GetMapping("/ppm/{ppm}")
    public ResponseEntity<Object> findByPpm(@PathVariable(value = "ppm") Long ppm) {
        if (!propostaService.existsByPpm(ppm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este ppm.");
        }

        return ResponseEntity.status(200).body(propostaService.findByPpm(ppm));
    }

    /**
     * Método GET para buscar propostas por um título
     *
     * @param titulo
     * @return
     */
//    @GetMapping("/titulo/{titulo}")
//    public ResponseEntity<List<Proposta>> findByTitulo(@PathVariable(value = "titulo") String titulo) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(propostaService.findByTitulo(titulo));
//    }

    /**
     * Método GET para ordenar as propostas por número ppm de forma crescente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarPPMCrescente")
    public ResponseEntity<Page<Proposta>> findAllPPMCrescente(@PageableDefault(
            page = 0, size = 20, sort = "codigoPPM", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(propostaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as propostas por número ppm de forma decrescente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarPPMDecrescente")
    public ResponseEntity<Page<Proposta>> findAllPPMDecrescente(@PageableDefault(
            page = 0, size = 20, sort = "codigoPPM", direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(propostaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as propostas pelo título de forma crescente ( A-Z )
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarTituloAZ")
    public ResponseEntity<Page<Proposta>> findAllTituloCrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(propostaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as propostas pelo título de forma decrescente ( Z-A )
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarTituloZA")
    public ResponseEntity<Page<Proposta>> findAllTituloDecrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(propostaService.findAll(pageable));
    }

    /**
     * Método POST para criar uma proposta no banco de dados
     *
     * @param propostaJSON
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("proposta") String propostaJSON) {
        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJsonToModel(propostaJSON);

        proposta.setAnexos(files);
        proposta.setVisibilidade(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.save(proposta));
    }

    /**
     * Método DELETE para modificar a visibilidade de uma proposta
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        Proposta proposta = propostaService.findById(id).get();
        proposta.setVisibilidade(false);
        propostaService.save(proposta);
        return ResponseEntity.status(HttpStatus.OK).body(proposta);
    }

    /**
     * Método DELETE para remover uma proposta do banco de dados
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        propostaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Proposta deletada com sucesso.");
    }

}
