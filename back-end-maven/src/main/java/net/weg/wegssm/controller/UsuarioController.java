package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Classe controller para o usuário
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/usuario")
public class UsuarioController {

    /**
     * Classe service dos usuários
     */
    private UsuarioService usuarioService;

    /**
     * Função para buscar um usuário pelo seu email, usado na conversão do cookie de autenticação
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        if(usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
    }

    /**
     * Função para buscar um usuário pelo seu ID, recebido por uma variável
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id).get());
    }

    /**
     * Função para buscar uma lista de usuários pelo nome e tipo de usuário, necessária para filtragem
     */
    @GetMapping("/filtragem/{nome}/{tipo_usuario}")
    public ResponseEntity<List<Usuario>> findByNomeAndTipoUsuario(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                  @PathVariable(value = "nome") String nome,
                                                                  @PathVariable(value = "tipo_usuario") TipoUsuario tipo_usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByNomeAndTipoUsuario(nome, tipo_usuario, pageable));
    }

    /**
     * Função para criar um novo usuário, recebendo o objeto pelo body
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setVisibilidade(true);
        BeanUtils.copyProperties(usuarioDTO, usuario);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    /**
     * Função para atualizar um usuário, recebendo o objeto atualizado no body e o ID como parâmetro
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar um usuário com este id.");
        }

        Usuario usuario = usuarioOptional.get();
        BeanUtils.copyProperties(usuarioDTO, usuario, "id", "senha");

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

}
