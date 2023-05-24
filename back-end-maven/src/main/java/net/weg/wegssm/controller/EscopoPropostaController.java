package net.weg.wegssm.controller;

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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe controller para os escopos de propostas
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/escopo-proposta")
public class EscopoPropostaController {

    /**
     * Service dos escopos de propostas
     */
    private EscopoPropostaService escopoPropostaService;

    /**
     * Service do usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service da demanda
     */
    private DemandaService demandaService;

    /**
     * Service do responsável negócio
     */
    private ResponsavelNegocioService responsavelNegocioService;

    /**
     * Service da tabela de custos
     */
    private TabelaCustoService tabelaCustoService;

    /**
     * Service do custo
     */
    private CustoService custoService;

    /**
     * Service do CC
     */
    private CCsService ccsService;

    /**
     * Service dos anexos
     */
    private AnexoService anexoService;

    /**
     * Service dos benefícios
     */
    private BeneficioService beneficioService;

    /**
     * Método GET para listar um escopo específico através do id do usuário
     *
     * @param pageable
     * @param idUsuario
     * @return
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<EscopoProposta>> findByUsuario(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                              @PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByUsuario(usuario, pageable));
    }

    /**
     * Método GET para listar um esocpo através do id do usuário e do título
     *
     * @param pageable
     * @param idUsuario
     * @param titulo
     * @return
     */
    @GetMapping("/titulo/{idUsuario}/{titulo}")
    public ResponseEntity<Page<EscopoProposta>> findByUsuarioAndTitulo(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                       @PathVariable(value = "idUsuario") Long idUsuario,
                                                                       @PathVariable(value = "titulo") String titulo) {
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByUsuarioAndTitulo(usuario, titulo, pageable));
    }

    /**
     * Método GET para listar um escopo através do id da demanda
     *
     * @param idDemanda
     * @return
     */
    @GetMapping("/demanda/{idDemanda}")
    public ResponseEntity<List<EscopoProposta>> findByDemanda(@PathVariable(value = "idDemanda") Long idDemanda) {
        Demanda demanda = demandaService.findById(idDemanda).get();

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.findByDemanda(demanda));
    }

    /**
     * Método POST para criar um escopo de proposta
     *
     * @param escopoPropostaJSON
     * @return
     */
    @PostMapping
    public ResponseEntity<EscopoProposta> save(@RequestParam("escopo-proposta") String escopoPropostaJSON) {

        EscopoPropostaUtil escopoPropostaUtil = new EscopoPropostaUtil();
        EscopoProposta escopoProposta = escopoPropostaUtil.convertJsonToModel(escopoPropostaJSON);
        escopoProposta.setUltimaModificacao(new Date());

        for (ResponsavelNegocio responsavelNegocio : escopoProposta.getResponsavelNegocio()) {
            responsavelNegocioService.save(responsavelNegocio);
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

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.save(escopoProposta));
    }

    /**
     * Método PUT para editar um escopo da proposta
     *
     * @param escopoPropostaJSON
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestParam(value = "escopo-proposta") String escopoPropostaJSON) {
        EscopoPropostaUtil escopoPropostaUtil = new EscopoPropostaUtil();

        EscopoProposta escopoProposta = escopoPropostaUtil.convertJsonToModelDirect(escopoPropostaJSON);
        escopoProposta.setUltimaModificacao(new Date());

        if (!escopoPropostaService.existsById(escopoProposta.getId())) {
            return ResponseEntity.status(HttpStatus.OK).body("Escopo não encontrado!");
        }

        List<ResponsavelNegocio> listaResponsaveis = new ArrayList<>();
        for (ResponsavelNegocio responsavelNegocio : escopoProposta.getResponsavelNegocio()) {
            if (responsavelNegocioService.existsById(responsavelNegocio.getId())) {
                listaResponsaveis.add(responsavelNegocioService.save(responsavelNegocio));
            }
        }
        escopoProposta.setResponsavelNegocio(listaResponsaveis);

        List<Beneficio> listaBeneficiosNova = new ArrayList<>();
        for (Beneficio beneficio : escopoProposta.getBeneficios()) {
            if (beneficioService.existsById(beneficio.getId())) {
                listaBeneficiosNova.add(beneficio);
            }
        }
        escopoProposta.setBeneficios(listaBeneficiosNova);

        ArrayList<TabelaCusto> tabelaCustos = new ArrayList<>();
        for (TabelaCusto tabelaCusto : escopoProposta.getTabelaCustos()) {
            if (tabelaCusto.getId() != null && tabelaCustoService.existsById(tabelaCusto.getId())) {

                ArrayList<Custo> listaCustos = new ArrayList<>();
                for (Custo custo : tabelaCusto.getCustos()) {
                    if (custo.getId() != null || custoService.existsById(custo.getId())) {
                        listaCustos.add(custoService.save(custo));
                    }
                }
                tabelaCusto.setCustos(listaCustos);

                ArrayList<CC> listaCCs = new ArrayList<>();
                for (CC cc : tabelaCusto.getCcs()) {
                    if (cc.getId() != null && ccsService.existsById(cc.getId())) {
                        listaCCs.add(ccsService.save(cc));
                    }
                }
                tabelaCusto.setCcs(listaCCs);

                tabelaCustos.add(tabelaCustoService.save(tabelaCusto));
            }
        }
        escopoProposta.setTabelaCustos(tabelaCustos);

        return ResponseEntity.status(HttpStatus.OK).body(escopoPropostaService.save(escopoProposta));
    }

    /**
     * Método DELETE para deletar um escopo da proposta
     *
     * @param id
     * @return
     */
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
