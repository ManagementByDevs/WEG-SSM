package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.ChatDTO;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.ChatService;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/chat")
public class ChatController {

    private UsuarioService usuarioService;
    private DemandaService demandaService;
    private ChatService chatService;


    /**
     * Método POST para criar um chat no banco de dados
     *
     * @param chatDto ( Objeto a ser cadastrado = req.body )
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ChatDTO chatDto) {
        if (!demandaService.existsById(chatDto.getDemanda().getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        System.out.println("Criando chat: " + chatDto.getDemanda());

        Chat chat = new Chat();
//        chat.setConversa_encerrada(false);
//        chat.setIdDemanda(chatDto.getDemanda());
//        chat.setUsuariosChat(chatDto.getUsuarios());
        BeanUtils.copyProperties(chatDto, chat);

        return ResponseEntity.status(HttpStatus.OK).body(chatService.save(chat));
    }


    /**
     * Método GET para listar todos os chats
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Chat>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findAll());
    }

    /**
     * Método GET para listar um chat específico através de um id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum chat com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(chatService.findById(id).get());
    }

    @GetMapping("/remetente/{id}")
    public ResponseEntity<List<Chat>> findByRemetente(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(chatService.findByUsuariosChatContaining(usuario.get()));
    }

    /**
     * Método GET para listar um chat específico através do titulo (usuario da conversa) para admins
     *
     * @param titulo
     * @return
     */
//    @GetMapping("/tituloadmin/{titulo}")
//    public ResponseEntity<Object> findByTitleAdmin(@PathVariable(value = "titulo") String titulo) {
//        if (!usuarioService.existsByNomeContains(titulo)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este nome.");
//        }
//
//        List<Usuario> usuarios = usuarioService.findByNomeStartsWith(titulo);
//        List<Chat> chats = new ArrayList<>();
//
//        for (Usuario user : usuarios) {
//            List<Chat> chatAux = chatService.findByUsuario(user);
//            for (Chat chat : chatAux) {
//                chats.add(chat);
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(chats);
//    }

    /**
     * Método GET para listar um chat específico através do titulo (usuario da conversa) para solicitantes
     *
     * @param titulo
     * @return
     */
//    @GetMapping("/titulo/{titulo}")
//    public ResponseEntity<Object> findByTitle(@PathVariable(value = "titulo") String titulo) {
//        if (!usuarioService.existsByNomeContains(titulo)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este nome.");
//        }
//
//        List<Usuario> usuarios = usuarioService.findByNomeStartsWith(titulo);
//        List<Chat> chats = new ArrayList<>();
//
//        for (Usuario user : usuarios) {
//            List<Chat> chatAux = chatService.findBySolicitante(user);
//            for (Chat chat : chatAux) {
//                chats.add(chat);
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(chats);
//    }

    /**
     * Método GET para listar um chat específico através do usuário
     *
     * @param idUsuario
     * @return
     */
//    @GetMapping("/usuario/{idUsuario}")
//    public ResponseEntity<Object> findByUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
//        if (!usuarioService.existsById(idUsuario)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
//        }
//
////        Usuario usuario = usuarioService.findById(idUsuario).get();
////        if (!chatService.existsByUsuario(usuario)) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum chat com este usuário.");
////        }
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(chatService.findByUsuario(usuario));
//    }

    /**
     * Método DELETE para deletar um chat, colocando sua visibilidade como false
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum chat com este id.");
        }

        Chat chat = chatService.findById(id).get();
        chatService.save(chat);

        return ResponseEntity.status(HttpStatus.OK).body(chat);
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
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum histórico com este id.");
        }
        chatService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("chat deletado com sucesso.");
    }

}
