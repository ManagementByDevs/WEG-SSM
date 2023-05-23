package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.EscopoDTO;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.AnexoService;
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

/**
 * Classe controller do escopo de uma demanda
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/escopo")
public class EscopoController {

    /**
     * Service dos escopos
     */
    private EscopoService escopoService;

    /**
     * Service dos usuários
     */
    private UsuarioService usuarioService;

    /**
     * Service dos benefícios
     */
    private BeneficioService beneficioService;

    /**
     * Service dos anexo
     */
    private AnexoService anexoService;

    /**
     * Método GET para listar todos os escopos salvos no banco
     *
     * @return Lista com todos os escopos do sistema
     */
    @GetMapping
    public ResponseEntity<List<Escopo>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findAll());
    }

    /**
     * Método GET para listar um escopo específico através de um id
     *
     * @param id ID do escopo a procurar
     * @return Escopo com o ID recebido
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Escopo> escopoOptional = escopoService.findById(id);
        if (escopoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum escopo com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(escopoOptional.get());
    }

    /**
     * Método GET para procurar os escopos de um usuário pelo seu ID
     *
     * @param pageable  Objeto para paginação
     * @param idUsuario ID do usuário
     * @return Página com os escopos do usuário recebido
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Object> findByUsuario(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                @PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findByUsuario(usuarioOptional.get(), pageable));
    }

    /**
     * Método GET para buscar um escopo através do título e do id do usuário
     *
     * @param pageable
     * @param idUsuario
     * @param titulo
     * @return
     */
    @GetMapping("/titulo/{idUsuario}/{titulo}")
    public ResponseEntity<Page<Escopo>> findByUsuarioAndTitulo(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                               @PathVariable(value = "idUsuario") Long idUsuario,
                                                               @PathVariable(value = "titulo") String titulo) {
        Usuario usuario = usuarioService.findById(idUsuario).get();
        return ResponseEntity.status(HttpStatus.OK).body(escopoService.findByUsuarioAndTitulo(usuario, titulo, pageable));
    }

    /**
     * Método POST para salvar um novo escopo
     *
     * @param idUsuario
     * @return
     */
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

    /**
     * Método PUT para atualizar um escopo
     *
     * @param escopo
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Escopo escopo) {

        if (!escopoService.existsById(escopo.getId())) {
            return ResponseEntity.status(HttpStatus.OK).body("Escopo não encontrado!");
        }

        Escopo escopoAntigo = escopoService.findById(escopo.getId()).get();
        escopo.setUsuario(escopoAntigo.getUsuario());
        escopo.setUltimaModificacao(new Date());

        List<Beneficio> listaBeneficiosNova = new ArrayList<>();
        for (Beneficio beneficio : escopo.getBeneficios()) {
            if (beneficioService.existsById(beneficio.getId())) {
                listaBeneficiosNova.add(beneficio);
            }
        }
        escopo.setBeneficios(listaBeneficiosNova);

        List<Anexo> listaAnexosNova = new ArrayList<>();
        for (Anexo anexo : escopo.getAnexo()) {
            if (anexoService.existsById(anexo.getId())) {
                listaAnexosNova.add(anexo);
            }
        }
        escopo.setAnexo(listaAnexosNova);

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
