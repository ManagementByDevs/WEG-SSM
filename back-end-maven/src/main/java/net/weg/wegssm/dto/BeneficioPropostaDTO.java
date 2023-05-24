package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.TipoBeneficio;

/**
 * Classe DTO para a criação de um benefício da proposta
 */
@Data
public class BeneficioPropostaDTO {

    private TipoBeneficio tipoBeneficio;
    private Double valor_mensal;
    private String moeda;
    private String memoriaCalculo;

}
