package net.weg.wegssm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
