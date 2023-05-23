package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.model.entities.Custo;

import java.util.List;

/**
 * Classe DTO para a criação de uma tabela de custo
 */
@Data
public class TabelaCustoDTO {

    private List<Custo> custos;
    private List<CC> ccs;

}
