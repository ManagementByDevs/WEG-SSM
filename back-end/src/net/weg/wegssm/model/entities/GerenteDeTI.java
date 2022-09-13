package net.weg.wegssm.model.entities;

public class GerenteDeTI extends AnalistaDeTI implements OperacoesGerenciais {
    public void classificarDemanda() {}

    public GerenteDeTI(String nomeUsuario, String senhaUsuario, String emailUsuario) {
        super(nomeUsuario, senhaUsuario, emailUsuario);
    };

    public GerenteDeTI() {
    }
}
