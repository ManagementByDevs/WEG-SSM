package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscopoDTO {
    private String usuarioEmail;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private Long porcentagem;
}
