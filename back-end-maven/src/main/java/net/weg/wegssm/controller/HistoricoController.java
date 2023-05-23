package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Historico;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.DocumentoHistoricoService;
import net.weg.wegssm.model.service.HistoricoService;
import net.weg.wegssm.model.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Classe controller para os históricos
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/historico")
public class HistoricoController {

    /**
     * Service do histórico
     */
    private HistoricoService historicoService;

    /**
     * Service do usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service do documento histórico
     */
    private DocumentoHistoricoService documentoHistoricoService;

    /**
     * Service da demanda
     */
    private DemandaService demandaService;

    /**
     * Método GET para listar todos os históricos
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Historico>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(historicoService.findAll());
    }

    /**
     * Método GET para listar um historico específico através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!historicoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum historico com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(historicoService.findById(id).get());
    }

    /**
     * Método GET para listar um historico específico de um autor
     *
     * @param idAutor
     * @return
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> findByIdUser(@PathVariable(value = "idUsuario") Long idAutor) {
        if (!usuarioService.existsById(idAutor)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }
        Usuario autor = usuarioService.findById(idAutor).get();
        if (!historicoService.existsByAutor(autor)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum historico com este usuário.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(historicoService.findByAutor(autor).get());
    }

    /**
     * Método POST para criar um historico no banco de dados
     *
     * @param historicoDto ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping("/{idUsuario}")
    public ResponseEntity<Object> save(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody HistoricoDTO historicoDto) {
        Usuario usuario = usuarioService.findById(idUsuario).get();

        Historico historico = new Historico();
        BeanUtils.copyProperties(historicoDto, historico);
        historico.setDocumentoHistorico(historicoDto.getDocumentoHistorico());
        historico.setAutor(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(historicoService.save(historico));
    }

    /**
     * Método DELETE para deletar um historico, colocando sua visibilidade como false
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!historicoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum histórico com este id.");
        }

        Historico historico = historicoService.findById(id).get();
        historicoService.save(historico);

        return ResponseEntity.status(HttpStatus.OK).body(historico);
    }

    /**
     * Método DELETE para deletar um histórico do banco de dados
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!historicoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum histórico com este id.");
        }
        historicoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("histórico deletado com sucesso.");
    }

}
