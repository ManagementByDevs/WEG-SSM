package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnexoDTO {

    private Long id;
    private String nome;
    private String tipo;
    private byte[] dados;

}
