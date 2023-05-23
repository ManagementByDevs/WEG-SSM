package net.weg.wegssm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO para a criação de um fórum
 */
@Data
public class ForumDTO {

    @NotNull
    private String nome;

}
