package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.MensagemDTO;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.service.ChatService;
import net.weg.wegssm.model.service.MensagemService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.MensagemUtil;
import org.springframework.beans.BeanUtils;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/weg_ssm/mensagem")
public class MensagemController {

    private MensagemService mensagemService;
    private ChatService chatService;
    private UsuarioService usuarioService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/weg_ssm/mensagem/{id}")
    @SendTo("/weg_ssm/mensagem/{id}/chat")
    public ResponseEntity<Object> receiveMessage(@DestinationVariable Long id, @Payload MensagemDTO mensagemDTO) {
        System.out.println("Mensagem: " + mensagemDTO);
        Mensagem mensagem = new Mensagem();
//        Proposta proposta = new Proposta();

        BeanUtils.copyProperties(mensagemDTO, mensagem, "id");

        mensagem.setUsuario(usuarioService.findById(mensagemDTO.getUsuario().getId()).get());
//        mensagem.setIdChat(chatService.findById(mensagemDTO.getIdChat().getId()).get());
//        proposta.setId(mensagem.getIdChat().getIdProposta().getId());
//        mensagem.getIdChat().setIdProposta(proposta);

        mensagem = mensagemService.save(mensagem);

        simpMessagingTemplate.convertAndSend("/weg_ssm/mensagem/all", mensagem);

        return ResponseEntity.ok().body(mensagem);
    }

    @MessageMapping("/weg_ssm/mensagem/all")
    @SendTo("/weg_ssm/mensagem/all")
    public ResponseEntity<Object> receiveAnyMessage(@Payload Mensagem mensagem) {
        System.out.println("Nova Mensagem: " + mensagem);

        return ResponseEntity.ok().body(mensagem);
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
