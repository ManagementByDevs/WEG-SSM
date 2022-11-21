package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.ForumDTO;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.ForumService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/forum")
public class ForumController {

    private ForumService forumService;

    /**
     * Método GET para buscar todos os fóruns
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Forum>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(forumService.findAll());
    }

    /**
     * Método GET para buscar um fórum através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!forumService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum fórum com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(forumService.findById(id).get());
    }

    /**
     * Método POST para criar um fórum no banco de dados
     *
     * @param forumDTO ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ForumDTO forumDTO) {
        if (forumService.existsByNome(forumDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome já está em uso.");
        }

        Forum forum = new Forum();
        forum.setVisibilidade(true);
        BeanUtils.copyProperties(forumDTO, forum);

        return ResponseEntity.status(HttpStatus.OK).body(forumService.save(forum));
    }

    /**
     * Método PUT para atualizar um fórum, através de um id
     *
     * @param id
     * @param forumDTO ( Novos dados do fórum = req.body )
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ForumDTO forumDTO) {
        Optional<Forum> forumOptional = forumService.findById(id);

        if (forumOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar um fórum com este id.");
        }

        if (forumService.existsByNome(forumDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome já está em uso.");
        }

        Forum forum = forumOptional.get();
        BeanUtils.copyProperties(forumDTO, forum, "id");

        return ResponseEntity.status(HttpStatus.OK).body(forumService.save(forum));
    }

    /**
     * Método DELETE para editar um fórum, colocando sua visibilidade como false
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!forumService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum fórum com este id.");
        }

        Forum forum = forumService.findById(id).get();
        forum.setVisibilidade(false);
        forumService.save(forum);

        return ResponseEntity.status(HttpStatus.OK).body(forum);
    }

    /**
     * Método DELETE para deletar um forum
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!forumService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum fórum com este id.");
        }

        forumService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Fórum deletado com sucesso.");
    }

}
