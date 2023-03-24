package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AtaDTO {
    private Date dataReuniao;
    private String numeroSequencial;
    private Forum comissao;
    private Boolean visibilidade;
    private List<Proposta> propostas;
    private Usuario analistaResponsavel;
}
