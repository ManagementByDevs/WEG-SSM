package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Demanda;

@Data
public class BeneficioDTO {

    private Double valor_mensal;
    private String moeda;
    private String memoriaCalculo;
    private Demanda demanda;

}
