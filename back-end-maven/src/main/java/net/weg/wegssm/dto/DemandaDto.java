package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Status;

@Getter @Setter
public class DemandaDto {

    private String usuarioEmail;
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
    
}
