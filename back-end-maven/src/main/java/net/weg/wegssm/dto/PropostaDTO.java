package net.weg.wegssm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PropostaDTO {

    private Long id;
    private String titulo;
    private Date inicioExecucao;
    private Date fimExecucao;
    private Double paybackValor;
    private String paybackTipo;
    private Long codigoPPM;
    private String linkJira;
    private Status status;
    private List<ResponsavelNegocio> responsavelNegocio;
    private Demanda demanda;

    private String problema;
    private String proposta;
    private String frequencia;
    private String tamanho;
    private SecaoTI secaoTI;
    private Boolean publicada;
    private Bu buSolicitante;
    private List<Bu> busBeneficiadas;
    private List<Beneficio> beneficios;
    private Usuario solicitante;
    private Usuario gerente;
    private Usuario analista;
    private Forum forum;
    private Departamento departamento;
    private List<TabelaCusto> tabelaCustos;
    private List<Historico> historicoProposta;
}
