package net.weg.wegssm.model.entities;

public class Solicitante {
    private String nomeUsuario, senhaUsuario, emailUsuario;

    @Override
    public String toString() {
        return "Solicitante{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                '}';
    }

    public Solicitante() {
    }

    public Solicitante(String nomeUsuario, String senhaUsuario, String emailUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.emailUsuario = emailUsuario;
    }

    public boolean login(String emailUsuario, String senhaUsuario) {
        return true;
    }

    public void criarDemanda() {}

    public void editarDemanda() {};

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
