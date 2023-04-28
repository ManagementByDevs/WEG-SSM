package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DemandaDTO {

    @NotNull
    private String titulo;

    @NotNull
    private String problema;

    @NotNull
    private String proposta;

    @NotNull
    private String frequencia;

    private String tamanho;
    private String secaoTI;
    private String motivoRecusa;

    @NotNull
    private Status status;

    private Usuario solicitante;
    private Forum forum;
    private Departamento departamento;
    private List<Beneficio> beneficios;

}
