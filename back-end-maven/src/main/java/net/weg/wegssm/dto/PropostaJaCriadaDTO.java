package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe DTO para a criação de uma proposta ja criada
 */
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
    private String parecerInformacaoDG;
    private Boolean visibilidade;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private String tamanho;
    private SecaoTI secaoTI;
    private String escopo;
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
    private String presenteEm;
    private Double score;
}
