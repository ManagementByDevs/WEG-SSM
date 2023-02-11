package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.TipoUsuario;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String senha;
    private String email;
    private Boolean visibilidade;
    private TipoUsuario tipoUsuario;
    private String preferencias;
    private Departamento departamento;

}
