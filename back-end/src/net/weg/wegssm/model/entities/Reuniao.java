package net.weg.wegssm.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Reuniao {
    Date inicioDataReuniao;
    Date fimDataReuniao;

    ArrayList<Proposta> propostasDaReuniao = new ArrayList<>();

    @Override
    public String toString() {
        return "Reuniao{" +
                "inicioDataReuniao=" + inicioDataReuniao +
                ", fimDataReuniao=" + fimDataReuniao +
                ", propostasDaReuniao=" + propostasDaReuniao +
                '}';
    }

    public Reuniao() {
    }

    public Reuniao(Date inicioDataReuniao, Date fimDataReuniao, ArrayList<Proposta> propostasDaReuniao) {
        this.inicioDataReuniao = inicioDataReuniao;
        this.fimDataReuniao = fimDataReuniao;
        this.propostasDaReuniao = propostasDaReuniao;
    }

    private void addProposta(Proposta proposta) {

    }

    private void excluirProposta(Proposta proposta) {

    }

    private void avaliarProposta(Proposta proposta) {

    }

    public Date getInicioDataReuniao() {
        return inicioDataReuniao;
    }

    public void setInicioDataReuniao(Date inicioDataReuniao) {
        this.inicioDataReuniao = inicioDataReuniao;
    }

    public Date getFimDataReuniao() {
        return fimDataReuniao;
    }

    public void setFimDataReuniao(Date fimDataReuniao) {
        this.fimDataReuniao = fimDataReuniao;
    }

    public ArrayList<Proposta> getPropostasDaReuniao() {
        return propostasDaReuniao;
    }

    public void setPropostasDaReuniao(ArrayList<Proposta> propostasDaReuniao) {
        this.propostasDaReuniao = propostasDaReuniao;
    }
}
