package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.UsuarioService;
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
@RequestMapping("/weg_ssm/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    /**
     * Método GET para listar todos os usuários
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    /**
     * Método GET para listar um usuário específico através de um id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        if(!usuarioService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioService.findById(id).get());
    }

    /**
     * Método GET para listar todos os usuários de um departamento específico
     * @param departamento
     * @return
     */
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Usuario>> findByDepartamento(@PathVariable(value = "departamento") Departamento departamento){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByDepartamento(departamento));
    }

    /**
     * Método POST para criar um usuário no banco de dados
     * @param usuarioDTO ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDTO usuarioDTO){
        if(usuarioService.existsByEmail(usuarioDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setVisibilidade(true);
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    /**
     * Método DELETE para deletar um usuário, colocando sua visibilidade como false
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        Usuario usuario = usuarioService.findById(id).get();
        usuario.setVisibilidade(false);
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    /**
     * Método PUT para atualizar um usuário no banco de dados, através de um id
     * @param id
     * @param usuarioDTO ( Novos dados do usuário = req.body )
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar um usuário com este id.");
        }

        if(usuarioService.existsByEmail(usuarioDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = usuarioOptional.get();
        BeanUtils.copyProperties(usuarioDTO, usuario, "id");
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

}
