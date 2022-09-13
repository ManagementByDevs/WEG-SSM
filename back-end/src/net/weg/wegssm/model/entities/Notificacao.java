package net.weg.wegssm.model.entities;

import java.util.Date;

public class Notificacao {

    private String tituloNotificacao, mensagemNotificacao;
    private Date dataNotificacao;
    private Integer tipoIconeNotificacao;
    private Solicitante usuarioNotificacao;
    private Demanda demandaNotificacao;

    @Override
    public String toString() {
        return "Notificacao{" +
                "tituloNotificacao='" + tituloNotificacao + '\'' +
                ", mensagemNotificacao='" + mensagemNotificacao + '\'' +
                ", dataNotificacao=" + dataNotificacao +
                ", tipoIconeNotificacao=" + tipoIconeNotificacao +
                ", usuarioNotificacao=" + usuarioNotificacao +
                ", demandaNotificacao=" + demandaNotificacao +
                '}';
    }

    public Notificacao() {
    }

    public Notificacao(String tituloNotificacao, String mensagemNotificacao, Date dataNotificacao, Integer tipoIconeNotificacao, Solicitante usuarioNotificacao, Demanda demandaNotificacao) {
        this.tituloNotificacao = tituloNotificacao;
        this.mensagemNotificacao = mensagemNotificacao;
        this.dataNotificacao = dataNotificacao;
        this.tipoIconeNotificacao = tipoIconeNotificacao;
        this.usuarioNotificacao = usuarioNotificacao;
        this.demandaNotificacao = demandaNotificacao;
    }

    public String getTituloNotificacao() {
        return tituloNotificacao;
    }

    public void setTituloNotificacao(String tituloNotificacao) {
        this.tituloNotificacao = tituloNotificacao;
    }

    public String getMensagemNotificacao() {
        return mensagemNotificacao;
    }

    public void setMensagemNotificacao(String mensagemNotificacao) {
        this.mensagemNotificacao = mensagemNotificacao;
    }

    public Date getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(Date dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Integer getTipoIconeNotificacao() {
        return tipoIconeNotificacao;
    }

    public void setTipoIconeNotificacao(Integer tipoIconeNotificacao) {
        this.tipoIconeNotificacao = tipoIconeNotificacao;
    }

    public Solicitante getUsuarioNotificacao() {
        return usuarioNotificacao;
    }

    public void setUsuarioNotificacao(Solicitante usuarioNotificacao) {
        this.usuarioNotificacao = usuarioNotificacao;
    }

    public Demanda getDemandaNotificacao() {
        return demandaNotificacao;
    }

    public void setDemandaNotificacao(Demanda demandaNotificacao) {
        this.demandaNotificacao = demandaNotificacao;
    }
}
