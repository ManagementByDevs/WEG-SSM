package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Usuario;

@Getter
@Setter
public class EscopoDTO {
    private Usuario usuario;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private Long porcentagem;
}
