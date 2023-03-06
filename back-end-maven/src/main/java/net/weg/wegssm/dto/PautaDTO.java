package net.weg.wegssm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.weg.wegssm.model.entities.Proposta;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class PautaDTO {

    private Long numeroSequencial;
    private Date inicioDataReuniao;
    private Date fimDataReuniao;
    private String comissao;
    private List<Proposta> propostas;

}
