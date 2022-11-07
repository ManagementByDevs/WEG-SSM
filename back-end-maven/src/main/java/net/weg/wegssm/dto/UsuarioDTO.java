package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Departamento;

@Getter @Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String senha;
    private String email;
    private Departamento departamento;

}
