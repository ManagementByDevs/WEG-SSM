package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.service.MensagemService;
import net.weg.wegssm.util.MensagemUtil;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/mensagem")
public class MensagemController {

    private MensagemService mensagemService;

    /**
     * Método GET para buscar todas as mensagens
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Mensagem>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findAll());
    }

    /**
     * Método GET para buscar uma mensagem através de um id específico
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
     * Método POST para criar uma mensagem, enviando também uma lista de arquivos caso necessário
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
