package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.Ata;
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

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        if(!usuarioService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDTO usuarioDTO){
        if(usuarioService.existsByEmail(usuarioDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O email já está em uso.");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }


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

}
