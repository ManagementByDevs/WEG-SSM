package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

@Data
public class ChatDTO {

    private Proposta proposta;
    private List<Usuario> usuarios;

}
