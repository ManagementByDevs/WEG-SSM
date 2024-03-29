package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;

/**
 * Classe DTO para a criação de uma notificação
 */
@Data
public class NotificacaoDTO {

    private String numeroSequencial;
    private Boolean visualizado;
    private Date data;
    private TipoNotificacao tipoNotificacao;
    private Usuario usuario;
    private Usuario remetente;

}
