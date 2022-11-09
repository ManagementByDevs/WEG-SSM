package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Proposta;

@Getter @Setter
public class CustoDTO {

    private Long id;
    private String tipo;
    private String perfil;
    private Long periodoExecucao;
    private Double horas;
    private Double valorHora;
    private String css;
//    private Proposta proposta;

}
