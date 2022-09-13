package net.weg.wegssm.model.entities;

import java.util.ArrayList;

public class Escopo {
    private String usuarioEmailEscopo, tituloEscopo, problemaEscopo, propostaEscopo, frequenciaEscopo;
    private ArrayList<Anexo> anexosEscopo;

    public void excluirEscopo() {
    }

    public void editarEscopo() {}

    public void addAnexo(Anexo anexo) {
    }

    public Escopo(String usuarioEmailEscopo, String tituloEscopo, String problemaEscopo, String propostaEscopo, String frequenciaEscopo, ArrayList<Anexo> anexosEscopo) {
        this.usuarioEmailEscopo = usuarioEmailEscopo;
        this.tituloEscopo = tituloEscopo;
        this.problemaEscopo = problemaEscopo;
        this.propostaEscopo = propostaEscopo;
        this.frequenciaEscopo = frequenciaEscopo;
        this.anexosEscopo = anexosEscopo;
    }

    public Escopo() {
    }

    public String getUsuarioEmailEscopo() {
        return usuarioEmailEscopo;
    }

    public void setUsuarioEmailEscopo(String usuarioEmailEscopo) {
        this.usuarioEmailEscopo = usuarioEmailEscopo;
    }

    public String getTituloEscopo() {
        return tituloEscopo;
    }

    public void setTituloEscopo(String tituloEscopo) {
        this.tituloEscopo = tituloEscopo;
    }

    public String getProblemaEscopo() {
        return problemaEscopo;
    }

    public void setProblemaEscopo(String problemaEscopo) {
        this.problemaEscopo = problemaEscopo;
    }

    public String getPropostaEscopo() {
        return propostaEscopo;
    }

    public void setPropostaEscopo(String propostaEscopo) {
        this.propostaEscopo = propostaEscopo;
    }

    public String getFrequenciaEscopo() {
        return frequenciaEscopo;
    }

    public void setFrequenciaEscopo(String frequenciaEscopo) {
        this.frequenciaEscopo = frequenciaEscopo;
    }

    public ArrayList<Anexo> getAnexosEscopo() {
        return anexosEscopo;
    }

    public void setAnexosEscopo(ArrayList<Anexo> anexosEscopo) {
        this.anexosEscopo = anexosEscopo;
    }

    @Override
    public String toString() {
        return "Escopo{" +
                "usuarioEmailEscopo='" + usuarioEmailEscopo + '\'' +
                ", tituloEscopo='" + tituloEscopo + '\'' +
                ", problemaEscopo='" + problemaEscopo + '\'' +
                ", propostaEscopo='" + propostaEscopo + '\'' +
                ", frequenciaEscopo='" + frequenciaEscopo + '\'' +
                ", anexosEscopo=" + anexosEscopo +
                '}';
    }
}
