package net.weg.wegssm.model.entities;

import java.util.Date;

public class Mensagem {

    private Solicitante usuarioMensagem;
    private Date dataMensagem;
    private Boolean vistoMensagem = false;
    private String textoMensagem;
    private Byte anexoMensagem;

    @Override
    public String toString() {
        return "Mensagem{" +
                "usuarioMensagem=" + usuarioMensagem +
                ", dataMensagem=" + dataMensagem +
                ", vistoMensagem=" + vistoMensagem +
                ", textoMensagem='" + textoMensagem + '\'' +
                ", anexoMensagem=" + anexoMensagem +
                '}';
    }

    public Mensagem(Solicitante usuarioMensagem, Date dataMensagem, String textoMensagem, Byte anexoMensagem) {
        this.usuarioMensagem = usuarioMensagem;
        this.dataMensagem = dataMensagem;
        this.textoMensagem = textoMensagem;
        this.anexoMensagem = anexoMensagem;
    }

    public Mensagem() {
    }

    public Solicitante getUsuarioMensagem() {
        return usuarioMensagem;
    }

    public void setUsuarioMensagem(Solicitante usuarioMensagem) {
        this.usuarioMensagem = usuarioMensagem;
    }

    public Date getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public Boolean getVistoMensagem() {
        return vistoMensagem;
    }

    public void setVistoMensagem(Boolean vistoMensagem) {
        this.vistoMensagem = vistoMensagem;
    }

    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public Byte getAnexoMensagem() {
        return anexoMensagem;
    }

    public void setAnexoMensagem(Byte anexoMensagem) {
        this.anexoMensagem = anexoMensagem;
    }
}
