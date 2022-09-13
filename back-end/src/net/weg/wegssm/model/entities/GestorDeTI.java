package net.weg.wegssm.model.entities;

public class GestorDeTI extends AnalistaDeTI implements OperacoesGerenciais {
    public void classificarDemanda() {}

    public GestorDeTI(String nomeUsuario, String senhaUsuario, String emailUsuario) {
        super(nomeUsuario, senhaUsuario, emailUsuario);
    };

    public GestorDeTI() {
    }
}
