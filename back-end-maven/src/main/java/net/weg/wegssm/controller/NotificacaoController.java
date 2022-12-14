package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.NotificacaoDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.service.NotificacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/notificacoes")
public class NotificacaoController {

    private NotificacaoService notificacaoService;

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

        return ResponseEntity.status(HttpStatus.FOUND).body(notificacaoService.findById(id).get());
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
    public ResponseEntity<Object> save(@RequestBody NotificacaoDTO notificacaoDTO) {
        Notificacao notificcao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificcao);

        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoService.save(notificcao));
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
