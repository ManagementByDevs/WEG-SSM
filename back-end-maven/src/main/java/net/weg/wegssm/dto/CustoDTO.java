package net.weg.wegssm.dto;

import lombok.Data;

/**
 * Classe DTO para a criação de um custo
 */
@Data
public class CustoDTO {

    private String tipoDespesa;
    private String perfilDespesa;
    private Long periodoExecucao;
    private Double horas;
    private Double valorHora;

}
