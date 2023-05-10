package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.TipoBeneficio;

@Data
public class BeneficioDTO {

    private TipoBeneficio tipoBeneficio;
    private Double valor_mensal;
    private String moeda;

}
