package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class MensagemDTO {

    private Long id;
    private Date data;
    private Boolean visto;
    private String texto;
    private List<Anexo> anexo;
    private Usuario usuario;
    private Chat chat;

}
