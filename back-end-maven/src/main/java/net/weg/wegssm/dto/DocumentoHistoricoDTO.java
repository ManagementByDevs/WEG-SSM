package net.weg.wegssm.dto;

import lombok.Data;

@Data
public class DocumentoHistoricoDTO {

    private String nome;
    private String tipo;
    private byte[] dados;
}
