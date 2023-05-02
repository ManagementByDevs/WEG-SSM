package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.MensagemDTO;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.service.ChatService;
import net.weg.wegssm.model.service.MensagemService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.MensagemUtil;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/weg_ssm/mensagem")
public class MensagemController {

    private MensagemService mensagemService;
    private ChatService chatService;
    private UsuarioService usuarioService;

//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/weg_ssm/mensagem/{id}")
    @SendTo("/weg_ssm/mensagem/{id}/chat")
    public ResponseEntity<Object> receiveMessage(@DestinationVariable Long id, @Payload MensagemDTO mensagemDTO) {
        System.out.println("Mensagem: " + mensagemDTO);
        Mensagem mensagem = new Mensagem();

        BeanUtils.copyProperties(mensagemDTO, mensagem, "id");


        mensagem.setUsuario(usuarioService.findById(mensagemDTO.getUsuario().getId()).get());
//
//        Chat chat = chatService.findById(mensagemDTO.getChat().getId()).get();
//
//        simpMessagingTemplate.convertAndSendToUser(chat.getIdDemanda().getId().toString(), mensagem.getIdChat().getId().toString(), mensagem);
//
//        try {
//            mensagemService.save(mensagem);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("A mensagem não foi salva!");
//        }
//
//        return ResponseEntity.ok().body("Mensagem enviada!");

        return ResponseEntity.ok().body(mensagemService.save(mensagem));
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

        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findByIdChat(chat.get()));
    }

    /**
     * Método POST para criar uma mensagem, enviando também uma lista de arquivos caso necessário
     *
     * @param mensagemJSON
     * @param anexos
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("anexos") List<MultipartFile> anexos, @RequestParam("mensagem") String mensagemJSON) {
        MensagemUtil mensagemUtil = new MensagemUtil();
        Mensagem mensagem = mensagemUtil.convertJsonToModel(mensagemJSON);

        mensagem.setAnexos(anexos);

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
