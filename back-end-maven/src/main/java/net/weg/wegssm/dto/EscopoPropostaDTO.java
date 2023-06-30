package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe DTO para a criação de um escopo da proposta
 */
@Data
public class EscopoPropostaDTO {

    private Date inicioExecucao;
    private Date fimExecucao;
    private Double paybackValor;
    private String paybackTipo;
    private Integer codigoPPM;
    private String linkJira;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private String tamanho;
    private Date ultimaModificacao;
    private String escopo;
    private SecaoTI secaoTI;
    private List<Beneficio> beneficios = new ArrayList<>();
    private Bu buSolicitante;
    private List<Bu> busBeneficiadas;
    @NotNull
    private Usuario solicitante;
    @NotNull
    private Usuario gerente;
    private Usuario analista;
    private Forum forum;
    private Departamento departamento;
    private List<TabelaCusto> tabelaCustos;
    private List<ResponsavelNegocio> responsavelNegocio;
    @NotNull
    private Demanda demanda;
    private List<Anexo> anexo;
    private String presenteEm;

}
