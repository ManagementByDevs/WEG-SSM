package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;

@Getter @Setter
public class NotificacaoDTO {

    private Long id;
    private String titulo;
    private Boolean visualizado;
    private Date data;
    private TipoNotificacao tipoNotificacao;
    private Usuario usuario;

}
