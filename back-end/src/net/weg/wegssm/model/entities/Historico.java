package net.weg.wegssm.model.entities;

import java.util.Date;

public class Historico {

    private Integer numeroVersaoHistorico;
    private Date dataVersaoHistorico;
    private Solicitante usuarioResponsavelHistorico;
    private Byte documentoHistorico;
    private String descricaoHistorico;
    private Demanda demandaHistorico;

    @java.lang.Override
    public java.lang.String toString() {
        return "Historico{" +
                "numeroVersaoHistorico=" + numeroVersaoHistorico +
                ", dataVersaoHistorico=" + dataVersaoHistorico +
                ", usuarioResponsavelHistorico=" + usuarioResponsavelHistorico +
                ", documentoHistorico=" + documentoHistorico +
                ", descricaoHistorico='" + descricaoHistorico + '\'' +
                ", demandaHistorico=" + demandaHistorico +
                '}';
    }

    public Historico(Integer numeroVersaoHistorico, Date dataVersaoHistorico, Solicitante usuarioResponsavelHistorico, Byte documentoHistorico, String descricaoHistorico, Demanda demandaHistorico) {
        this.numeroVersaoHistorico = numeroVersaoHistorico;
        this.dataVersaoHistorico = dataVersaoHistorico;
        this.usuarioResponsavelHistorico = usuarioResponsavelHistorico;
        this.documentoHistorico = documentoHistorico;
        this.descricaoHistorico = descricaoHistorico;
        this.demandaHistorico = demandaHistorico;
    }

    public Historico() {
    }

    public Integer getNumeroVersaoHistorico() {
        return numeroVersaoHistorico;
    }

    public void setNumeroVersaoHistorico(Integer numeroVersaoHistorico) {
        this.numeroVersaoHistorico = numeroVersaoHistorico;
    }

    public Date getDataVersaoHistorico() {
        return dataVersaoHistorico;
    }

    public void setDataVersaoHistorico(Date dataVersaoHistorico) {
        this.dataVersaoHistorico = dataVersaoHistorico;
    }

    public Solicitante getUsuarioResponsavelHistorico() {
        return usuarioResponsavelHistorico;
    }

    public void setUsuarioResponsavelHistorico(Solicitante usuarioResponsavelHistorico) {
        this.usuarioResponsavelHistorico = usuarioResponsavelHistorico;
    }

    public Byte getDocumentoHistorico() {
        return documentoHistorico;
    }

    public void setDocumentoHistorico(Byte documentoHistorico) {
        this.documentoHistorico = documentoHistorico;
    }

    public String getDescricaoHistorico() {
        return descricaoHistorico;
    }

    public void setDescricaoHistorico(String descricaoHistorico) {
        this.descricaoHistorico = descricaoHistorico;
    }

    public Demanda getDemandaHistorico() {
        return demandaHistorico;
    }

    public void setDemandaHistorico(Demanda demandaHistorico) {
        this.demandaHistorico = demandaHistorico;
    }
}
