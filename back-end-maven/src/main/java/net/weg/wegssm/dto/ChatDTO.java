package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Usuario;

@Getter @Setter
public class ChatDTO {

    private Usuario solicitante;
    private Usuario usuario;

}
