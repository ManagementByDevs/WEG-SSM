package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocumentoHistoricoDTO {

    private String nome;
    private String tipo;
    private byte[] dados;
}
