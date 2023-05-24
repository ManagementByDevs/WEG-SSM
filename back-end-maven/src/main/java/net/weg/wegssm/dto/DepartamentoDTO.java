package net.weg.wegssm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO para a criação de um departamento
 */
@Data
public class DepartamentoDTO {

    @NotNull
    private String nome;

}
