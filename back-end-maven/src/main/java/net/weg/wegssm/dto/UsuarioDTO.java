package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String senha;
    private String email;

}
