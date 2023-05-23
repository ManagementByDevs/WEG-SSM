package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;
import java.util.List;

/**
 * Classe DTO para a criação de uma pauta
 */
@Data
public class PautaDTO {

    private String numeroSequencial;
    private Date dataReuniao;
    private Forum comissao;
    private Usuario analistaResponsavel;
    private List<Proposta> propostas;

}
