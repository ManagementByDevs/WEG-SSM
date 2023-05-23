package net.weg.wegssm.security.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO recebida na autenticação do usuário com os dados necessários
 */
@Data
public class UserDTO {

    @NotNull
    private String email;

    @NotNull
    private String senha;

}
