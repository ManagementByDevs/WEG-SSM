package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.*;

import java.util.List;

@Getter @Setter
public class DemandaDTO {

    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private String tamanho;
    private String buSolicitante;
    private String busBeneficiadas;
    private String secaoTI;
    private String motivoRecusa;
    private Status status;
    private Usuario solicitante;
    private Forum forum;
    private Departamento departamento;
    private List<Anexo> anexo;

}
