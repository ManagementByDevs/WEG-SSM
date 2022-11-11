package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.*;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class PropostaDTO {

    private Long id;
    private String escopo;
    private String titulo;
    private Date inicioExecucao;
    private Date fimExecucao;
    private Double paybackValor;
    private String paybackTipo;
    private Long codigoPPM;
    private String linkJira;
    private Boolean publicada;
    private Boolean naoPublicada;
    private Status status;
    private ParecerGerencia parecerComissao;
    private String parecerInformacao;
    private ParecerGerencia parecerDG;
    private List<Custo> custo;
    private List<ResponsavelNegocio> responsavelNegocio;
    private Demanda demanda;
    private Pauta pauta;
    private Ata ata;
    private List<Anexo> anexo;

}
