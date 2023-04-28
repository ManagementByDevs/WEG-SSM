package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

@Data
public class ChatDTO {

    private Demanda demanda;
    private List<Usuario> usuarios;

}
