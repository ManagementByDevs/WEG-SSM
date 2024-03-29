package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

/**
 * Classe DTO para a criação de uma proposta
 */
@Data
public class PropostaDTO {

    @NotNull
    private String titulo;
    @NotNull @FutureOrPresent
    private Date inicioExecucao;
    @NotNull @FutureOrPresent
    private Date fimExecucao;
    @NotNull @Positive
    private Double paybackValor;
    @NotNull
    private String paybackTipo;
    @NotNull @Positive
    private Long codigoPPM;
    private String linkJira;
    private Status status;
    private List<ResponsavelNegocio> responsavelNegocio;
    private Demanda demanda;
    @NotNull
    private String problema;
    @NotNull
    private String proposta;
    @NotNull
    private String frequencia;
    @NotNull
    private String tamanho;
    @NotNull
    private SecaoTI secaoTI;
    private Boolean publicada;
    @NotNull
    private Bu buSolicitante;
    private List<Bu> busBeneficiadas;
    private List<Beneficio> beneficios;
    @NotNull
    private Usuario solicitante;
    private Usuario gerente;
    private Usuario analista;
    @NotNull
    private Forum forum;
    @NotNull
    private Departamento departamento;
    private List<TabelaCusto> tabelaCustos;
    private List<Historico> historicoProposta;
    private String presenteEm;
    private List<Anexo> anexo;
    private String escopo;

}
