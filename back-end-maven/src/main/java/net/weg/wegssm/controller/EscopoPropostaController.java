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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/escopo-proposta")
public class EscopoPropostaController {

    private EscopoPropostaService escopoPropostaService;
    private UsuarioService usuarioService;

    private ResponsavelNegocioService responsavelNegocioService;
    private TabelaCustoService tabelaCustoService;
    private CustoService custoService;
    private CCsService ccsService;

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

    @PostMapping
    public ResponseEntity<EscopoProposta> save(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("escopo-proposta") String escopoPropostaJSON) {
        EscopoPropostaUtil escopoPropostaUtil = new EscopoPropostaUtil();
        EscopoProposta escopoProposta = escopoPropostaUtil.convertJsonToModel(escopoPropostaJSON);

        escopoProposta.setAnexos(files);
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
}
