package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

/**
 * Classe DTO para a criação de um chat
 */
@Data
public class ChatDTO {

    private Boolean conversaEncerrada;
    private Proposta idProposta;
    private Demanda idDemanda;
    private List<Usuario> usuariosChat;

}
