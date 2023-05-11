package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.StatusChat;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;
import java.util.List;

@Data
public class MensagemDTO {

    private Date data;
    private Boolean visto;
    private String texto;
    private StatusChat status;
    private Usuario usuario;
    private Chat idChat;
    private Anexo anexo;

}
