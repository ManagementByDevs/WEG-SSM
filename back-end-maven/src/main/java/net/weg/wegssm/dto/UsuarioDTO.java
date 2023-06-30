package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO para a criação de um usuário
 */
@Data
public class UsuarioDTO {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String senha;
    @NotNull
    private String email;
    private Boolean visibilidade;
    @NotNull
    private TipoUsuario tipoUsuario;
    private String preferencias;
    private Departamento departamento;

}
