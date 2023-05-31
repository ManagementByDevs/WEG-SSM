package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.NotificacaoDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.ChatService;
import net.weg.wegssm.model.service.NotificacaoService;
import net.weg.wegssm.model.service.UsuarioService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Classe controller para as notificações
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/notificacoes")
public class NotificacaoController {

    /**
     * Service da notificação
     */
    private NotificacaoService notificacaoService;

    /**
     * Service do usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service do chat
     */
    private ChatService chatService;

    /**
     * Método GET para buscar todas as notificações
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Notificacao>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findAll());
    }

    /**
     * Método GET para buscar uma notificação específica através do id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!notificacaoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma notificação com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findById(id).get());
    }

    /**
     * Método GET para buscar as notificações de um usuário
     *
     * @param id
     * @return List de Notificação
     */
    @GetMapping("user/{id}")
    public ResponseEntity<Page<Notificacao>> findByUserId(@PathVariable(value = "id") Long id, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findByUsuario(usuarioService.findById(id).get(), pageable));
    }

    /**
     * Método GET para buscar notificação através de um id
     *
     * @param id
     * @param pageable
     * @return
     */
    @GetMapping("user/modal-notificacao/{id}")
    public ResponseEntity<Page<Notificacao>> findByUsuarioAndVisualizado(@PathVariable(value = "id") Long id, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (!usuarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findByUsuarioAndVisualizado(usuarioService.findById(id).get(), false, pageable));
    }

    /**
     * Método GET para buscar as notificações pela data
     *
     * @param data
     * @return
     */
    @GetMapping("/date/{data}")
    public ResponseEntity<Object> findByData(@PathVariable(value = "data") Data data) {
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findByData(data));
    }

    /**
     * Método GET para buscar as notificações de um determinado tipo de notificação (Aprovada, reprovada...)
     *
     * @param tipoNotificacao
     * @return
     */
    @GetMapping("/tipoNotificacao/{tipoNotificacao}")
    public ResponseEntity<List<Notificacao>> findByTipoNotificacao(@PathVariable(value = "tipoNotificacao") TipoNotificacao tipoNotificacao) {
        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.findByTipoNotificacao(tipoNotificacao));
    }

    /**
     * Método GET para ordenar as notificacoes a partir da DATA, da mais recente para a mais antiga
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataRecente")
    public ResponseEntity<Page<Notificacao>> findAllDataRecente(@PageableDefault(
            page = 0, size = 20, sort = "data", direction = Sort.Direction.DESC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(notificacaoService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as notificações a partir da DATA, da mais antiga para a mais recente
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarDataAntiga")
    public ResponseEntity<Page<Notificacao>> findAllDataAntiga(@PageableDefault(
            page = 0, size = 20, sort = "data", direction = Sort.Direction.ASC
    ) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.FOUND).body(notificacaoService.findAll(pageable));
    }

    /**
     * Método POST para criar uma notificação
     *
     * @param notificacaoDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);

        if (notificacao.getTipoNotificacao().equals(TipoNotificacao.MENSAGENS)) {
            Chat chat = chatService.findById(Long.parseLong(notificacao.getNumeroSequencial())).get();
            notificacao.setNumeroSequencial(String.valueOf(chat.getIdProposta().getCodigoPPM()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoService.save(notificacao));
    }

    @MessageMapping("/weg_ssm/notificacao/{userId}")
    @SendTo("/weg_ssm/{userId}/notificacao")
    public Notificacao salvarNotificacao(@DestinationVariable Long userId,
                                         @Payload NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        notificacao.setRemetente(usuarioService.findById(notificacao.getRemetente().getId()).get());

        if (notificacao.getTipoNotificacao().equals(TipoNotificacao.MENSAGENS)) {
            Chat chat = chatService.findById(Long.parseLong(notificacao.getNumeroSequencial())).get();
            notificacao.setNumeroSequencial(String.valueOf(chat.getIdProposta().getCodigoPPM()));
        }
        return notificacaoService.save(notificacao);
    }

    /**
     * Método PUT para atualizar a notificação
     *
     * @param notificacaoDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid NotificacaoDTO notificacaoDTO, @PathVariable(value = "id") Long id) {
        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        notificacao.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(notificacaoService.save(notificacao));
    }

    /**
     * Método DELETE para deletar uma notificação
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!notificacaoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma notificação com este id.");
        }

        notificacaoService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Notificação excluída com sucesso.");
    }

}
