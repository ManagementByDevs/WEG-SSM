package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter @Setter
public class PautaDTO {

    private Long numeroSequencial;
    private Date inicioDataReuniao;
    private Date fimDataReuniao;
    private String comissao;
}
