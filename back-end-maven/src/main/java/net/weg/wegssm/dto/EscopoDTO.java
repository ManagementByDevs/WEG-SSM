package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Usuario;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Classe DTO para a criação de um escopo
 */
@Data
public class EscopoDTO {

    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private Long porcentagem;
    @NotNull
    private Usuario usuario;
    private List<Anexo> anexo;

}
