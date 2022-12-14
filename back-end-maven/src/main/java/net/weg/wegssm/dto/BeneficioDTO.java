package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.TipoBeneficio;

@Getter @Setter
public class BeneficioDTO {

    private Double valor_mensal;
    private String moeda;
    private String memoriaCalculo;
    private Demanda demanda;

}
