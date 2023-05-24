package net.weg.wegssm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO para a criação de uma BU
 */
@Data
public class BuDTO {

    @NotNull
    private String nome;

}
