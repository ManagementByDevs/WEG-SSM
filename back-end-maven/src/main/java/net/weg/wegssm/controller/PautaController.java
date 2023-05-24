package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.PautaDTO;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.service.PautaService;
import net.weg.wegssm.model.service.PropostaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller para a classe pauta
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/pauta")
public class PautaController {

    /**
     * Service da pauta
     */
    private PautaService pautaService;

    /**
     * Service da proposta
     */
    private PropostaService propostaService;

    /**
     * Método GET para listar todas as pautas
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Pauta>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll());
    }

    /**
     * Método principal de busca das pautas, com filtro de título das propostas presentes e paginação / ordenação
     *
     * @param pageable - String de paginação e ordenação
     * @param titulo   - String com o título de uma proposta, para pesquisar as pautas que contém tal proposta
     * @return - Página de pautas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Pauta>> findPage(@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                @RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isEmpty()) {
            List<Proposta> propostas = propostaService.findByTitulo(titulo);
            List<Pauta> pautas = new ArrayList<>();
            for (Proposta proposta : propostas) {
                Pauta pauta = pautaService.findByPropostasContaining(proposta);
                if (!pautas.contains(pauta)) {
                    pautas.add(pauta);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body((Page<Pauta>) pautas);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pautaService.findAll(pageable));
        }
    }

    /**
     * Método GET para listar uma pauta específica através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!pautaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pautaService.findById(id).get());
    }

    /**
     * Método GET para listar uma pauta específica através do número sequencial
     *
     * @param numeroSequencial
     * @return
     */
    @GetMapping("/numerosequencial/{numeroSequencial}")
    public ResponseEntity<Object> findByNumeroSequencial(@PathVariable(value = "numeroSequencial") Long numeroSequencial) {
        if (!pautaService.existsByNumeroSequencial(numeroSequencial)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pauta com este numero sequencial.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findByNumeroSequencial(numeroSequencial).get());
    }

    /**
     * Método GET para ordenar as pautas a partir da DATA DE INÍCIO, da mais recente para a mais antiga
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataInicioRecente")
    public ResponseEntity<Page<Pauta>> findAllDataInicioRecente(@PageableDefault(
            page = 0, size = 20, sort = "inicioDataReuniao", direction = Sort.Direction.DESC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as pautas a partir da DATA DE INÍCIO, da mais antiga para a mais recente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataInicioAntiga")
    public ResponseEntity<Page<Pauta>> findAllDataInicioAntiga(@PageableDefault(
            page = 0, size = 20, sort = "inicioDataReuniao", direction = Sort.Direction.ASC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as pautas a partir da DATA DE FIM, da mais recente para a mais antiga
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataFimRecente")
    public ResponseEntity<Page<Pauta>> findAllDataFimRecente(@PageableDefault(
            page = 0, size = 20, sort = "fimDataReuniao", direction = Sort.Direction.DESC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as pautas a partir da DATA DE FIM, da mais antiga para a mais recente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataFimAntiga")
    public ResponseEntity<Page<Pauta>> findAllDataFimAntiga(@PageableDefault(
            page = 0, size = 20, sort = "fimDataReuniao", direction = Sort.Direction.ASC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(pautaService.findAll(pageable));
    }

    /**
     * Função para calcular o score de uma pauta através da soma dos scores das propostas presentes
     * @param pauta Pauta a se calcular o score
     * @return Score final da pauta
     */
    private Double calcularScore(Pauta pauta) {
        Double scoreFinal = 0.0;
        for (Proposta proposta : pauta.getPropostas()) {
            proposta = propostaService.findById(proposta.getId()).get();

            scoreFinal += proposta.getScore();
        }

        return scoreFinal;
    }

    /**
     * Método POST para criar uma pauta no banco de dados
     *
     * @param pautaDto ( Objeto a ser cadastrado = req.body )
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PautaDTO pautaDto) {
        Pauta pauta = new Pauta();
        BeanUtils.copyProperties(pautaDto, pauta);
        pauta.setScore(calcularScore(pauta));

        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta));
    }

    /**
     * Método PUT para atualizar uma pauta no banco de dados, através de um id
     *
     * @param pautaDto ( Novos dados da pauta = req.body )
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody PautaDTO pautaDto) {
        Optional<Pauta> pautaOptinal = pautaService.findById(id);

        if (pautaOptinal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar uma pauta com este id.");
        }

        Pauta pauta = pautaOptinal.get();
        BeanUtils.copyProperties(pautaDto, pauta, "id");
        pauta.setScore(calcularScore(pauta));

        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta));
    }

    /**
     * Método DELETE para deletar uma pauta do banco de dados
     *
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
     *
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
        pautaService.save(pauta);

        return ResponseEntity.status(HttpStatus.OK).body(pauta);
    }

}
