package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.MensagemDTO;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.model.service.ChatService;
import net.weg.wegssm.model.service.MensagemService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.MensagemUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe controller para as mensagens
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/weg_ssm/mensagem")
public class MensagemController {

    /**
     * Service da mensagem
     */
    private MensagemService mensagemService;

    /**
     * Service do chat
     */
    private ChatService chatService;

    /**
     * Service do usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service do utilitário de mensagens
     */
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Método MESSAGEMAPPING utilizado para receber uma mensagem
     *
     * @param id
     * @param mensagemDTO
     * @return
     */
    @MessageMapping("/weg_ssm/mensagem/{id}")
    @SendTo("/weg_ssm/mensagem/{id}/chat")
    public ResponseEntity<Object> receiveMessage(@DestinationVariable Long id, @Payload MensagemDTO mensagemDTO) {
        Mensagem mensagem = new Mensagem();
        BeanUtils.copyProperties(mensagemDTO, mensagem, "id");

        mensagem.setUsuario(usuarioService.findById(mensagemDTO.getUsuario().getId()).get());

        mensagem = mensagemService.save(mensagem);

        simpMessagingTemplate.convertAndSend("/weg_ssm/mensagem/all/user/" + mensagemDTO.getIdDestinatario(), mensagem);

        return ResponseEntity.ok().body(mensagem);
    }

    /**
     * Método MESSAGEMAPPING para o usuário poder entrar no chat
     *
     * @param idChat
     * @param usuarioID
     * @return
     */
    @MessageMapping("/weg_ssm/enter/chat/{idChat}")
    @SendTo("/weg_ssm/enter/chat/{idChat}")
    public ResponseEntity<Object> enteredChat(@DestinationVariable(value = "idChat") Long idChat, @Payload Long usuarioID) {
        List<Mensagem> mensagens = mensagemService.findAllByIdChatAndVisto(chatService.findById(idChat).get(), false);

        for (Mensagem mensagem : mensagens) {
            if (!mensagem.getVisto() && !mensagem.getUsuario().getId().equals(usuarioID)) {
                mensagem.setVisto(true);
                mensagemService.save(mensagem);
            }
        }

        simpMessagingTemplate.convertAndSend("/weg_ssm/mensagem/chat/" + idChat + "/visto", "visualizar-novas-mensagens/" + usuarioID);

        return ResponseEntity.ok().build();
    }

    /**
     * Método MESSAGEMAPPING para receber qualquer mensagem
     *
     * @param mensagem
     * @return
     */
    @MessageMapping("/weg_ssm/mensagem/all/user/{idUser}")
    @SendTo("/weg_ssm/mensagem/all/user/{idUser}")
    public ResponseEntity<Object> receiveAnyMessage(@Payload() Mensagem mensagem) {
        System.out.println("Chegou aqui");
        return ResponseEntity.ok().body(mensagem);
    }

    /**
     * Método MESSAGEMAPPING para enviar uma mensagem de um chat específico
     *
     * @param mensagemDTO
     * @param idChat
     * @return
     */
    @MessageMapping("/weg_ssm/mensagem/chat/{idChat}/visto")
    @SendTo("/weg_ssm/mensagem/chat/{idChat}/visto")
    public ResponseEntity<Object> verMensagem(
            @Payload MensagemDTO mensagemDTO,
            @DestinationVariable(value = "idChat") Long idChat
    ) {
        Optional<Chat> chat = chatService.findById(idChat);
        if (chat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum Chat com esse ID.");
        }

        Mensagem mensagem = new Mensagem();

        BeanUtils.copyProperties(mensagemDTO, mensagem);

        mensagem.setVisto(true);
        mensagem.setIdChat(chat.get());
        mensagemService.save(mensagem);

        return ResponseEntity.ok().build();
    }

    /**
     * Método PUT para editar uma mensagem
     *
     * @param idChat
     * @param idUser
     * @param pageable
     * @return
     */
    @PutMapping("/chat/{idChat}/user/{idUsuario}")
    public ResponseEntity<Object> updateMessages(
            @PathVariable(value = "idChat") Long idChat,
            @PathVariable(value = "idUsuario") Long idUser,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Optional<Chat> chat = chatService.findById(idChat);
        if (chat.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma mensagem deste id de chat.");
        }

        Optional<Usuario> usuario = usuarioService.findById(idUser);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este id.");
        }

        Page<Mensagem> mensagens = mensagemService.findByIdChat(chat.get(), pageable);
        for (Mensagem mensagem : mensagens.getContent()) {
            if (!Objects.equals(mensagem.getUsuario().getId(), idUser)) {
                mensagem.setVisto(true);
                mensagemService.save(mensagem);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(mensagens);
    }

    /**
     * Método GET para buscar todas as mensagens
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Mensagem>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findAll());
    }

    /**
     * Método GET para buscar uma mensagem através de um id específico
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!mensagemService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma mensagem com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(mensagemService.findById(id).get());
    }

    /**
     * Método GET para buscar várias mensagens através de um id de um chat
     *
     * @param id
     * @return
     */
    @GetMapping("/chat/{id}")
    public ResponseEntity<Object> findByIdChat(@PathVariable(value = "id") Long id) {
        Optional<Chat> chat = chatService.findById(id);
        if (!chat.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma mensagem deste id de chat.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findAllByIdChat(chat.get()));
    }

    /**
     * Método POST para criar uma mensagem, enviando também uma lista de arquivos caso necessário
     *
     * @param mensagemJSON
     * @param anexos
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam(value = "anexos", required = false) List<MultipartFile> anexos, @RequestParam("mensagem") String mensagemJSON) {
        MensagemUtil mensagemUtil = new MensagemUtil();
        Mensagem mensagem = mensagemUtil.convertJsonToModel(mensagemJSON);

//        mensagem.setAnexos(anexos);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemService.save(mensagem));
    }

    /**
     * Método DELETE para deletar uma mensagem
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!mensagemService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma mensagem com este id.");
        }

        mensagemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Mensagem deletada com sucesso.");
    }

}
