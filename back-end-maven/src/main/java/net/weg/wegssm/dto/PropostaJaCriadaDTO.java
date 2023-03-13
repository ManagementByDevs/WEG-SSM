package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PropostaJaCriadaDTO {
    private Long id;
    private Date inicioExecucao;
    private Date fimExecucao;
    private Double paybackValor;
    private String paybackTipo;
    private Long codigoPPM;
    private String linkJira;
    private Boolean publicada;
    private Status status;
    private Date data;
    private ParecerGerencia parecerComissao;
    private String parecerInformacao;
    private ParecerGerencia parecerDG;
    private Boolean visibilidade;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private String tamanho;
    private String secaoTI;
    private byte[] escopo;
    private List<Beneficio> beneficios = new ArrayList<>();
    private Bu buSolicitante;
    private List<Bu> busBeneficiadas;
    private Usuario solicitante;
    private Usuario gerente;
    private Usuario analista;
    private Forum forum;
    private Departamento departamento;
    private List<TabelaCusto> tabelaCustos;
    private List<ResponsavelNegocio> responsavelNegocio;
    private Demanda demanda;
    private List<Anexo> anexo;
    private List<Historico> historicoProposta;
}