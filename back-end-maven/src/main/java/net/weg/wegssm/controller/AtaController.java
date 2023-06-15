package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.AtaDTO;
import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.service.AtaService;
import net.weg.wegssm.model.service.PropostaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/ata")
public class AtaController {

    /**
     * Service da ata
     */
    private AtaService ataService;

    /**
     * Service da proposta
     */
    private PropostaService propostaService;

    /**
     * Método GET para listar todas as atas
     */
    @GetMapping
    public ResponseEntity<List<Ata>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ataService.findAll());
    }

    /**
     * Método GET de busca das atas, com filtro de título das propostas presentes e paginação / ordenação
     *
     * @param pageable - String de paginação e ordenação
     * @param titulo   - String com o título de uma proposta, para pesquisar as atas que contém tal proposta
     * @return - Página de atas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Ata>> findPage(@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                              @RequestParam(required = false) String titulo,
                                              @RequestParam(required = false) String numeroSequencial,
                                              @RequestParam(required = false, value = "publicadaDg") Boolean publicadaDg,
                                              @RequestParam(required = false, value = "publicada") Boolean publicada) {
        if (numeroSequencial != null && !numeroSequencial.isEmpty()) {
            if (publicadaDg != null) {
                if (publicada != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByNumeroSequencialAndPublicadaDgAndPublicada(numeroSequencial, publicadaDg, publicada, pageable));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByNumeroSequencialAndPublicadaDg(numeroSequencial, publicadaDg, pageable));
                }
            } else {
                if (publicada != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByNumeroSequencialAndPublicada(numeroSequencial, publicada, pageable));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByNumeroSequencial(numeroSequencial, pageable));
                }
            }
        } else if (titulo != null && !titulo.isEmpty()) {
            List<Proposta> propostas = propostaService.findByTituloContainingIgnoreCase(titulo);
            List<Ata> atas = new ArrayList<>();
            for (Proposta proposta : propostas) {
                Ata ata = ataService.findByPropostasContaining(proposta);

                if (publicadaDg != null) {
                    if (publicada != null) {
                        if (!atas.contains(ata) && ata != null && ata.getPublicadaDg().equals(publicadaDg) && ata.getPublicada().equals(publicada)) {
                            atas.add(ata);
                        }
                    } else {
                        if (!atas.contains(ata) && ata != null && ata.getPublicadaDg().equals(publicadaDg)) {
                            atas.add(ata);
                        }
                    }
                } else {
                    if(publicada != null) {
                        if (!atas.contains(ata) && ata != null && ata.getPublicada().equals(publicada)) {
                            atas.add(ata);
                        }
                    } else {
                        if (!atas.contains(ata) && ata != null) {
                            atas.add(ata);
                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<Ata>(atas, pageable, atas.size()));
        } else {
            if (publicadaDg != null) {
                if(publicada != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByPublicadaDgAndPublicada(publicadaDg, publicada, pageable));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByPublicadaDg(publicadaDg, pageable));
                }
            } else {
                if(publicada != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findByPublicada(publicada, pageable));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(ataService.findAll(pageable));
                }
            }
        }
    }

    /**
     * Método GET para listar uma ata específica através do id
     *
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
     * Método GET para ordenar as atas por DATA DE INICIO REUNIAO, da mais antiga para a mais recente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarInicioDataReuniaoAntiga")
    public ResponseEntity<Page<Ata>> findAllInicioDataReuniaoAntiga(
            @PageableDefault(size = 20, sort = "inicioDataReuniao", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(ataService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as atas por DATA DE INICIO REUNIAO, da mais recente para a mais antiga
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarInicioDataReuniaoRecente")
    public ResponseEntity<Page<Ata>> findAllInicioDataReuniaoRecente(
            @PageableDefault(size = 20, sort = "inicioDataReuniao", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(ataService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as atas por DATA DE FIM REUNIAO, da mais antiga para a mais recente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarFimDataReuniaoAntiga")
    public ResponseEntity<Page<Ata>> findAllFimDataReuniaoAntiga(
            @PageableDefault(size = 20, sort = "fimDataReuniao", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(ataService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as atas por DATA DE FIM REUNIAO, da mais recente para a mais antiga
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarFimDataReuniaoRecente")
    public ResponseEntity<Page<Ata>> findAllFimDataReuniaoRecente(
            @PageableDefault(size = 20, sort = "fimDataReuniao", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(ataService.findAll(pageable));
    }

    /**
     * Função para calcular o score de uma ata através da soma dos scores das propostas presentes
     *
     * @param ata Ata a se calcular o score
     * @return Score final da ata
     */
    private Double calcularScore(Ata ata) {
        Double scoreFinal = 0.0;
        for (Proposta proposta : ata.getPropostas()) {
            proposta = propostaService.findById(proposta.getId()).get();

            scoreFinal += proposta.getScore();
        }

        return scoreFinal;
    }

    /**
     * Método POST para cadastrar uma ata no banco de dados
     *
     * @param ataDto ( Objeto a ser cadastrado = req.body )
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AtaDTO ataDto) throws ParseException {

        if (ataService.existsByNumeroSequencial(ataDto.getNumeroSequencial())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O número sequencial já está em uso.");
        }

        Ata ata = new Ata();
        ata.setVisibilidade(true);
        BeanUtils.copyProperties(ataDto, ata);
        ata.setPublicadaDg(false);
        ata.setScore(calcularScore(ata));

        return ResponseEntity.status(HttpStatus.OK).body(ataService.save(ata));
    }

    /**
     * Método PUT para atualizar uma ata no banco de dados
     *
     * @param id
     * @param ataDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody AtaDTO ataDTO) {
        Optional<Ata> ataOptional = ataService.findById(id);

        if (ataOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar uma ata com este id.");
        }

        Ata ata = ataOptional.get();
        BeanUtils.copyProperties(ataDTO, ata, "id");
        ata.setScore(calcularScore(ata));

        return ResponseEntity.status(HttpStatus.OK).body(ataService.save(ata));
    }

    /**
     * Método DELETE para colocar sua visibilidade como false
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!ataService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma ata com este id.");
        }

        Ata ata = ataService.findById(id).get();
        ata.setVisibilidade(false);
        ataService.save(ata);

        return ResponseEntity.status(HttpStatus.OK).body(ata);
    }

    /**
     * Método DELETE para deletar uma ata
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!ataService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma ata com este id.");
        }
        ataService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ata deletada com sucesso.");
    }

}
