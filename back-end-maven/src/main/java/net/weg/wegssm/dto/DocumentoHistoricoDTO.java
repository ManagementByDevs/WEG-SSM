package net.weg.wegssm.dto;

import lombok.Data;

/**
 * Classe DTO para a criação de um documento histórico
 */
@Data
public class DocumentoHistoricoDTO {

    private String nome;
    private String tipo;
    private byte[] dados;

}
