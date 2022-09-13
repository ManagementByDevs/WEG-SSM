package net.weg.wegssm.model.entities;

import java.util.ArrayList;

public class Chat {
    private Solicitante solicitanteChat1;
    private Solicitante solicitanteChat2;
    private boolean usuarioBloqueadoChat;
    private ArrayList<Mensagem> mensagensChat = new ArrayList<Mensagem>();
    private Demanda demandaChat;

    public Chat(){}

    public Chat(Solicitante solicitanteChat1, Solicitante solicitanteChat2, boolean usuarioBloqueadoChat, ArrayList<Mensagem> mensagensChat, Demanda demandaChat) {
        this.solicitanteChat1 = solicitanteChat1;
        this.solicitanteChat2 = solicitanteChat2;
        this.usuarioBloqueadoChat = usuarioBloqueadoChat;
        this.mensagensChat = mensagensChat;
        this.demandaChat = demandaChat;
    }

    public Solicitante getSolicitanteChat1() {
        return solicitanteChat1;
    }

    public void setSolicitanteChat1(Solicitante solicitanteChat1) {
        this.solicitanteChat1 = solicitanteChat1;
    }

    public Solicitante getSolicitanteChat2() {
        return solicitanteChat2;
    }

    public void setSolicitanteChat2(Solicitante solicitanteChat2) {
        this.solicitanteChat2 = solicitanteChat2;
    }

    public boolean isUsuarioBloqueadoChat() {
        return usuarioBloqueadoChat;
    }

    public void setUsuarioBloqueadoChat(boolean usuarioBloqueadoChat) {
        this.usuarioBloqueadoChat = usuarioBloqueadoChat;
    }

    public ArrayList<Mensagem> getMensagensChat() {
        return mensagensChat;
    }

    public void setMensagensChat(ArrayList<Mensagem> mensagensChat) {
        this.mensagensChat = mensagensChat;
    }

    public Demanda getDemandaChat() {
        return demandaChat;
    }

    public void setDemandaChat(Demanda demandaChat) {
        this.demandaChat = demandaChat;
    }

    public void bloquearChat(Solicitante solicitante){}

    public void denunciarSolicitanteChat(Solicitante solicitante){}

    public void enviarMensagemChat(Solicitante solicitante){}

    @Override
    public String toString() {
        return "Chat{" +
                "solicitanteChat1=" + solicitanteChat1 +
                ", solicitanteChat2=" + solicitanteChat2 +
                ", usuarioBloqueadoChat=" + usuarioBloqueadoChat +
                ", mensagensChat=" + mensagensChat +
                ", demandaChat=" + demandaChat +
                '}';
    }
}
