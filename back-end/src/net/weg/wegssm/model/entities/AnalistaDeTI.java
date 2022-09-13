package net.weg.wegssm.model.entities;

public class AnalistaDeTI extends Solicitante implements OperacoesGerenciais {
    //Getters and setters
    //Construtores (2)
    //ToString()
    //Atributos e métodos
    //Tipo de variável em formato de objeto

    public AnalistaDeTI() { }

    public AnalistaDeTI(String nomeUsuario, String senhaUsuario, String emailUsuario) {
        super(nomeUsuario, senhaUsuario, emailUsuario);
    }

    public void classificarDemanda() {}

    public void criarChat(Demanda demanda) {}
}
