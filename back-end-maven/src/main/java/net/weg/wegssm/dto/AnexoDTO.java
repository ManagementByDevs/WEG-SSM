package net.weg.wegssm.dto;

import lombok.Data;

/**
 * Classe DTO para a criação de um anexo
 */
@Data
public class AnexoDTO {

    private String nome;
    private String tipo;
    private byte[] dados;

}
