package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.model.entities.Custo;

import java.util.List;

@Data
public class TabelaCustoDTO {

    private List<Custo> custos;

    private List<CC> ccs;
}
