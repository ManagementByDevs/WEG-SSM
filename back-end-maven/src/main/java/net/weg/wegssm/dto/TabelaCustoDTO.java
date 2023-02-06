package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.model.entities.Custo;

import java.util.List;

@Getter @Setter
public class TabelaCustoDTO {

    private List<Custo> custos;
    private List<CC> ccs;
}
