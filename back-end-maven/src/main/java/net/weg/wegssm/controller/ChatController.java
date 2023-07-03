package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.ChatDTO;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.*;
import net.weg.wegssm.model.service.PropostaService;
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

/**
 * Classe controller para o chat
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/chat")
public class ChatController {

    /**
     * Service do usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service da proposta
     */
    private PropostaService propostaService;

    /**
     * Service da demanda
     */
    private DemandaService demandaService;

    /**
     * Service do chat
     */
    private ChatService chatService;

    /**
     * Service da mensagem
     */
    private MensagemService mensagemService;

    /**
     * Método MessageMapping para receber qualquer mensagem enviada para o endpoint /weg_ssm/chats
     *
     * @param mensagem - Payload da mensagem enviada
     * @return - Retorno da mensagem enviada
     */
    @MessageMapping("/weg_ssm/chats")
    @SendTo("/weg_ssm/chats")
    public ResponseEntity<Object> receiveAnyMessage(@Payload Mensagem mensagem) {
        return ResponseEntity.ok().body(mensagem);
    }

    /**
     * Método POST para criar um chat no banco de dados
     *
     * @param chatDto ( Objeto a ser cadastrado = req.body )
     * @return - Retorno do chat criado
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ChatDTO chatDto) {
        if (chatDto.getIdProposta() != null) {
            if (!propostaService.existsById(chatDto.getIdProposta().getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");

            }
        } else {
            if (!demandaService.existsById((chatDto.getIdDemanda().getId()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
            }
        }

        Chat chat = new Chat();
        BeanUtils.copyProperties(chatDto, chat);
        chat.setConversaEncerrada(false);

        return ResponseEntity.status(HttpStatus.OK).body(chatService.save(chat));
    }


    /**
     * Método GET para listar todos os chats
     *
     * @return - Retorno da lista de chats
     */
    @GetMapping
    public ResponseEntity<List<Chat>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findAll());
    }

    /**
     * Método GET para listar um chat específico através de um id
     *
     * @param id - ID utilizado para a busca
     * @return - Retorno do objeto ou mensagem de erro
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum chat com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(chatService.findById(id).get());
    }

    /**
     * Método GET para buscar um chat através do id do usuário e da proposta
     *
     * @param idUser     - ID do usuário relacionado ao chat
     * @param idProposta - ID da proposta relacionada ao chat
     * @return - Retorno do chat ou mensagem de erro
     */
    @GetMapping("/user/{idUser}/proposta/{idProposta}")
    public ResponseEntity<List<Chat>> findByPropostaAndUser(@PathVariable(value = "idUser") Long idUser, @PathVariable(value = "idProposta") Long idProposta) {
        Optional<Usuario> usuario = usuarioService.findById(idUser);
        Optional<Proposta> proposta = propostaService.findById(idProposta);

        if (usuario.isEmpty() || proposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(chatService.findByIdPropostaAndUsuariosChat(proposta.get(), usuario.get()));
    }

    /**
     * Método GET para buscar um chat através do id do usuário e da demanda
     *
     * @param idUser    - ID do usuário relacionado ao chat
     * @param idDemanda - ID da demanda relacionada ao chat
     * @return - Retorno do chat ou mensagem de erro
     */
    @GetMapping("/user/{idUser}/demanda/{idDemanda}")
    public ResponseEntity<List<Chat>> findByDemandaAndUser(@PathVariable(value = "idUser") Long idUser, @PathVariable(value = "idDemanda") Long idDemanda) {
        Optional<Usuario> usuario = usuarioService.findById(idUser);
        Optional<Demanda> demanda = demandaService.findById(idDemanda);

        if (usuario.isEmpty() || demanda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(chatService.findByIdDemandaAndUsuariosChat(demanda.get(), usuario.get()));
    }

    /**
     * Método GET para buscar uma lista de chats através do id do remetente
     *
     * @param id - ID utilizado para a busca
     * @return - Retorno do chat ou mensagem de erro
     */
    @GetMapping("/remetente/{id}")
    public ResponseEntity<List<Chat>> findByRemetente(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Chat> chats = chatService.findByUsuariosChat(usuario.get());

        for (Chat chat : chats) {
            List<Mensagem> mensagensNaoLidas = mensagemService.findAllByIdChatAndVistoAndUsuarioNot(chat, false, usuario.get());

            chat.setMsgNaoLidas((long) mensagensNaoLidas.size());
        }

        return ResponseEntity.status(HttpStatus.OK).body(chats);
    }

    /**
     * Método PUT para editar um chat através de seu id
     *
     * @param id      - ID utilizado para a edição do chat
     * @param chatDto - Objeto a ser editado = req.body
     * @return - Retorno do chat editado ou mensagem de erro
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ChatDTO chatDto) {
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum chat com este id.");
        }

        Chat chat = new Chat();
        BeanUtils.copyProperties(chatDto, chat);
        chat.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(chatService.save(chat));
    }

    /**
     * Método DELETE para deletar um chat, colocando sua visibilidade como false
     *
     * @param id - ID utilizado para deletar um chat (visibilidade = false)
     * @return - Retorno do chat deletado ou mensagem de erro
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
     * @param id - ID utilizado para deletar o chat
     * @return - Retorno da mensagem de sucesso ou erro
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!chatService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum histórico com este id.");
        }

        chatService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Chat deletado com sucesso.");
    }

}
