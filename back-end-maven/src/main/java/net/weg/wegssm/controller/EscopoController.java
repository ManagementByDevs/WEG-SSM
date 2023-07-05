package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Escopo;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.AnexoService;
import net.weg.wegssm.model.service.BeneficioService;
import net.weg.wegssm.model.service.EscopoService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.UsuarioUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
     * Método GET para listar e filtras os escopos com paginação
     *
     * @param pageable    - Objeto que contém os dados da paginação
     * @param usuarioJson - Usuário que está logado
     * @param titulo      - Título do escopo
     * @return - Retorno da paginação filtrada
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Escopo>> findPage(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                 @RequestParam(value = "usuario") String usuarioJson,
                                                 @RequestParam(value = "titulo", required = false) String titulo) {
        UsuarioUtil usuarioUtil = new UsuarioUtil();
        Usuario usuario = usuarioUtil.convertJsonToModel(usuarioJson);

        if (titulo != null && !titulo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(escopoService.findByUsuarioAndTitulo(usuario, titulo, pageable));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(escopoService.findByUsuario(usuario, pageable));
        }
    }

    /**
     * Método GET para procurar os escopos de um usuário pelo seu ID
     *
     * @param pageable  - Objeto para paginação
     * @param idUsuario - ID do usuário
     * @return - Página com os escopos do usuário recebido
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
     * @param pageable  - Objeto que contém os dados da paginação
     * @param idUsuario - ID do usuário que está logado
     * @param titulo    - Título do escopo
     * @return - Retorno da paginação filtrada
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
     * @param idUsuario - ID do usuário que está logado
     * @return -  Retorno do escopo salvo
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
     * Método PUT para atualizar um escopo
     *
     * @param escopo - Objeto de escopo a ser editado
     * @return - Retorno do escopo atualizado
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
     * Método DELETE para deletar um escopo do banco de dados
     *
     * @param id - ID utilizado para deletar o escopo
     * @return - Mensagme de sucesso ou erro
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