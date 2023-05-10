package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.service.AnexoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

/**
 * Classe Controller para as funções dos anexos
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/anexo")
public class AnexoController {

    /**
     * Service de anexos
     */
    private AnexoService anexoService;

    /**
     * Função para criar um anexo no banco de dados, recebendo um AnexoDTO no body
     *
     * @param arquivo Arquivo para salvamento
     * @return ResponseEntity com o anexo recém-salvo
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam(value = "anexo") MultipartFile arquivo) {
        try {
            Anexo anexo = new Anexo(null, arquivo.getOriginalFilename(), arquivo.getContentType(), arquivo.getBytes());
            return ResponseEntity.status(HttpStatus.OK).body(anexoService.save(anexo));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Arquivo Inválido!");
        }
    }

    /**
     * Função para excluir um anexo pelo seu ID, recebido pela variável "id"
     *
     * @param id ID do anexo a ser excluído
     * @return ResponseEntity com string confirmando a deleção
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!anexoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anexo não encontrado!");
        }

        anexoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Anexo deletado com sucesso!");
    }
}
