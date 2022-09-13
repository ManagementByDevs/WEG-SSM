package net.weg.wegssm.model.entities;

public class AnalistaDeTI extends Solicitante implements OperacoesGerenciais {
    public AnalistaDeTI() { }

    public AnalistaDeTI(String nomeUsuario, String senhaUsuario, String emailUsuario) {
        super(nomeUsuario, senhaUsuario, emailUsuario);
    }

    public void classificarDemanda() {}

    public void criarChat(Demanda demanda) {}
}
