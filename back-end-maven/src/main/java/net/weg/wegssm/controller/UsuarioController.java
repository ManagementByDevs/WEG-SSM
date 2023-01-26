package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    /**
     * Método GET para listar todos os usuários
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    /**
     * Método GET para procurar um usuário por email e senha, necessário para o login
     */
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity<Usuario> findByEmailAndSenha(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByEmailAndSenha(email, senha));
    }

    /**
     * Método GET para listar um usuário específico através de um id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id).get());
    }

    /**
     * Método GET para listar todos os usuários de um departamento específico
     */
    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Usuario>> findByDepartamento(@PathVariable(value = "departamento") Departamento departamento) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByDepartamento(departamento));
    }

    @GetMapping("/tipo_usuario/{tipo_usuario}")
    public ResponseEntity<List<Usuario>> findByTipoUsuario(@PathVariable(value = "tipo_usuario") TipoUsuario tipo_usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByTipoUsuario(tipo_usuario));
    }

    @GetMapping("/filtragem/{nome}/{tipo_usuario}")
    public ResponseEntity<List<Usuario>> findByNomeAndTipoUsuario(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                  @PathVariable(value = "nome") String nome,
                                                                  @PathVariable(value = "tipo_usuario") TipoUsuario tipo_usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByNomeAndTipoUsuario(nome, tipo_usuario, pageable));
    }

    /**
     * Método POST para criar um usuário no banco de dados
     *
     * @param usuarioDTO ( Objeto a ser cadastrado = req.body )
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setVisibilidade(true);
        BeanUtils.copyProperties(usuarioDTO, usuario);
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    /**
     * Método PUT para atualizar um usuário no banco de dados, através de um id
     *
     * @param usuarioDTO ( Novos dados do usuário = req.body )
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar um usuário com este id.");
        }

        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = usuarioOptional.get();
        BeanUtils.copyProperties(usuarioDTO, usuario, "id");

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    /**
     * Método DELETE para deletar um usuário, colocando sua visibilidade como false
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        Usuario usuario = usuarioService.findById(id).get();
        usuario.setVisibilidade(false);
        usuarioService.save(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        usuarioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }

}
