package net.weg.wegssm.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.*;
import net.weg.wegssm.util.EscopoPropostaUtil;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Classe controller para os escopos de propostas */
@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/escopo-proposta")
public class EscopoPropostaController {

    private EscopoPropostaService escopoPropostaService;
    private UsuarioService usuarioService;
    private DemandaService demandaService;

    private ResponsavelNegocioService responsavelNegocioService;
    private TabelaCustoService tabelaCustoService;
    private CustoService custoService;
    private CCsService ccsService;
    private AnexoService anexoService;
    private BeneficioService beneficioService;

    /**
     * Método GET para listar um escopo específico através do id do usuário
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<EscopoProposta>> findByUsuario(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                              @PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByUsuario(usuario, pageable));
    }

    @GetMapping("/titulo/{idUsuario}/{titulo}")
    public ResponseEntity<Page<EscopoProposta>> findByUsuarioAndTitulo(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                               @PathVariable(value = "idUsuario") Long idUsuario,
                                                               @PathVariable(value = "titulo") String titulo) {
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByUsuarioAndTitulo(usuario, titulo, pageable));
    }

    @GetMapping("/demanda/{idDemanda}")
    public ResponseEntity<List<EscopoProposta>> findByDemanda(@PathVariable(value = "idDemanda") Long idDemanda) {
        Demanda demanda = demandaService.findById(idDemanda).get();

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByDemanda(demanda));
    }

    @PostMapping
    public ResponseEntity<EscopoProposta> save(@RequestParam("escopo-proposta") String escopoPropostaJSON) {
        EscopoPropostaUtil escopoPropostaUtil = new EscopoPropostaUtil();
        EscopoProposta escopoProposta = escopoPropostaUtil.convertJsonToModel(escopoPropostaJSON);
        escopoProposta.setUltimaModificacao(new Date());

        for (ResponsavelNegocio responsavelNegocio : escopoProposta.getResponsavelNegocio()) {
            responsavelNegocioService.save(responsavelNegocio);
        }

        for (Beneficio beneficio : escopoProposta.getBeneficios()) {
            beneficioService.save(beneficio);
        }

        for (TabelaCusto tabelaCusto : escopoProposta.getTabelaCustos()) {
            for (Custo custo : tabelaCusto.getCustos()) {
                custoService.save(custo);
            }
            for (CC cc : tabelaCusto.getCcs()) {
                ccsService.save(cc);
            }
            tabelaCustoService.save(tabelaCusto);
        }

        List<Anexo> listaAnexos = new ArrayList<>();
        if(escopoProposta.getAnexo() != null) {
            for (Anexo anexo : escopoProposta.getAnexo()) {
                listaAnexos.add(anexoService.save(anexo));
            }
        }
        escopoProposta.setAnexo(listaAnexos);

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.save(escopoProposta));
    }

    @PutMapping
    public ResponseEntity<EscopoProposta> update(@RequestParam(value = "escopo-proposta") String escopoPropostaJSON,
                                                 @RequestParam(value = "idsAnexos", required = false) List<String> listaIdsAnexos) {
        EscopoPropostaUtil escopoPropostaUtil = new EscopoPropostaUtil();

        EscopoProposta escopoProposta = escopoPropostaUtil.convertJsonToModel(escopoPropostaJSON);
        escopoProposta.setUltimaModificacao(new Date());

        for (ResponsavelNegocio responsavelNegocio : escopoProposta.getResponsavelNegocio()) {
            responsavelNegocioService.save(responsavelNegocio);
        }

        ArrayList<Beneficio> listaNovaBeneficios = new ArrayList<>();
        for (Beneficio beneficio : escopoProposta.getBeneficios()) {
            if(beneficioService.existsById(beneficio.getId())) {
                listaNovaBeneficios.add(beneficioService.save(beneficio));
            }
        }
        escopoProposta.setBeneficios(listaNovaBeneficios);

        for (TabelaCusto tabelaCusto : escopoProposta.getTabelaCustos()) {
            for (Custo custo : tabelaCusto.getCustos()) {
                custoService.save(custo);
            }
            for (CC cc : tabelaCusto.getCcs()) {
                ccsService.save(cc);
            }
            tabelaCustoService.save(tabelaCusto);
        }

        ArrayList<Anexo> listaAnexos = new ArrayList<>();
        if(listaIdsAnexos != null) {
            for (String id : listaIdsAnexos) {
                listaAnexos.add(anexoService.findById(Long.parseLong(id)));
            }
        }
        escopoProposta.setAnexo(listaAnexos);

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.save(escopoProposta));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!escopoPropostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }
        escopoPropostaService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Escopo da proposta excluído!");
    }
}
