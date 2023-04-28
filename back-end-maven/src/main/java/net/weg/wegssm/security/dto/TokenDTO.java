package net.weg.wegssm.security.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TokenDTO {

    @NotNull
    private String tipo;

    @NotNull
    private String token;

}
