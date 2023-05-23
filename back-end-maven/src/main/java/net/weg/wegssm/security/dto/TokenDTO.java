package net.weg.wegssm.security.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO utilizada para a criação de um token de acesso
 */
@Data
public class TokenDTO {

    @NotNull
    private String tipo;

    @NotNull
    private String token;

}
