package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

@Getter @Setter
public class ChatDTO {

    private Demanda demanda;
    private List<Usuario> usuarios;

}
