package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;
import java.util.List;

/**
 * Classe DTO para a criação de uma ata
 */
@Data
public class AtaDTO {
    private Date dataReuniao;
    private String numeroSequencial;
    private Forum comissao;
    private Boolean visibilidade;
    private List<Proposta> propostas;
    private Usuario analistaResponsavel;
    private Boolean publicadaDg;
    private Boolean publicada;
    private String numeroSequencialDG;
}
