package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.BeneficioService;
import net.weg.wegssm.model.service.EscopoService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.EscopoUtil;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/escopo")
public class EscopoController {

    private EscopoService escopoService;
    private UsuarioService usuarioService;
    private BeneficioService beneficioService;

    /**
     * Método GET para listar todos os escopos
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Escopo>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findAll());
    }

    /**
     * Método GET para listar um escopo específico através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!escopoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(escopoService.findById(id).get());
    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<Escopo>> findPage(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//
//    }

    /**
     * Método GET para listar um escopo específico através do id do usuário
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> findByUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findByUsuario(usuario));
    }

    @PostMapping("/novo/{idUsuario}")
    public ResponseEntity<Escopo> saveNovo(@PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario).get();

        Escopo escopo = new Escopo();
        escopo.setUsuario(usuario);
        escopo.setUltimaModificacao(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

    /**
     * Método POST para criar um escopo, podendo adicionar anexos caso necessário
     *
     * @param files
     * @param escopoJSON
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("escopo") String escopoJSON) {
        EscopoUtil escopoUtil = new EscopoUtil();
        Escopo escopo = escopoUtil.convertJsonToModel(escopoJSON);

        if (!usuarioService.existsById(escopo.getUsuario().getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        escopo.setAnexos(files);

        return ResponseEntity.status(HttpStatus.CREATED).body(escopoService.save(escopo));
    }

    @PutMapping("/dados")
    public ResponseEntity<Escopo> atualizarDados(@RequestBody Escopo escopo) {
        if (!escopoService.existsById(escopo.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Escopo escopoAntigo = escopoService.findById(escopo.getId()).get();
        escopo.setUsuario(escopoAntigo.getUsuario());

        List<Anexo> listaAnexos = escopoService.findById(escopo.getId()).get().getAnexo();
        escopo.setAnexo(listaAnexos);
        escopo.setUltimaModificacao(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

    @PutMapping("/anexos/{escopo}")
    public ResponseEntity<Escopo> atualizarAnexos(@RequestParam("anexos") List<MultipartFile> files, @PathVariable(value = "escopo") Long idEscopo) {

        if (!escopoService.existsById(idEscopo)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Escopo escopo = escopoService.findById(idEscopo).get();
        escopo.setAnexos(files);
        escopo.setUltimaModificacao(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

    @PutMapping("/remover-anexos/{escopo}")
    public ResponseEntity<Object> removerAnexos(@PathVariable(value = "escopo") Long idEscopo) {
        if (!escopoService.existsById(idEscopo)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Escopo escopo = escopoService.findById(idEscopo).get();
        escopo.setAnexo(null);
        escopo.setUltimaModificacao(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(escopoService.save(escopo));
    }

    /**
     * Método PUT para atualizar um escopo no banco de dados, através de um id
     *
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

    /**
     * Método DELETE para deletar um escopo, colocando sua visibilidade como false
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!escopoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }

        Escopo escopo = escopoService.findById(id).get();
        escopoService.save(escopo);

        return ResponseEntity.status(HttpStatus.OK).body(escopo);
    }

    /**
     * Método DELETE para deletar um escopo do banco de dados
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!escopoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }
        escopoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("escopo deletado com sucesso.");
    }

}
