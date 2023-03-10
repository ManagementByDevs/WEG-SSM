package net.weg.wegssm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class PautaDTO {

    private String numeroSequencial;
    private Date dataReuniao;
    private String comissao;
    private Usuario analistaResponsavel;
    private List<Proposta> propostas;

}
