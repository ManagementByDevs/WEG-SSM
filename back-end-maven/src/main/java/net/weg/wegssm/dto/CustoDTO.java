package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustoDTO {

    private String tipoDespesa;
    private String perfilDespesa;
    private Long periodoExecucao;
    private Double horas;
    private Double valorHora;

}
