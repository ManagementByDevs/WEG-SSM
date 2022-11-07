package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Status;
import net.weg.wegssm.model.entities.Usuario;


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
    private Usuario usuario;
    private Forum forum;
    private Departamento departamento;

}