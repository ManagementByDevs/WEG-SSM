package net.weg.wegssm.model.entities;

import java.util.ArrayList;

public class Custo {
    private String tipoCusto, perfilCusto, periodoExecucaoCusto;
    private Double horasCusto, valorHoraCusto, totalCusto;
    private ArrayList<String> ccsCusto;

    public void excluirCusto() {
    }

    ;

    public void editarCusto() {
    }

    ;

    public Custo(String tipoCusto, String perfilCusto, String periodoExecucaoCusto, Double horasCusto, Double valorHoraCusto, Double totalCusto, ArrayList<String> ccsCusto) {
        this.tipoCusto = tipoCusto;
        this.perfilCusto = perfilCusto;
        this.periodoExecucaoCusto = periodoExecucaoCusto;
        this.horasCusto = horasCusto;
        this.valorHoraCusto = valorHoraCusto;
        this.totalCusto = totalCusto;
        this.ccsCusto = ccsCusto;
    }

    public Custo() {
    }

    public String getTipoCusto() {
        return tipoCusto;
    }

    public void setTipoCusto(String tipoCusto) {
        this.tipoCusto = tipoCusto;
    }

    public String getPerfilCusto() {
        return perfilCusto;
    }

    public void setPerfilCusto(String perfilCusto) {
        this.perfilCusto = perfilCusto;
    }

    public String getPeriodoExecucaoCusto() {
        return periodoExecucaoCusto;
    }

    public void setPeriodoExecucaoCusto(String periodoExecucaoCusto) {
        this.periodoExecucaoCusto = periodoExecucaoCusto;
    }

    public Double getHorasCusto() {
        return horasCusto;
    }

    public void setHorasCusto(Double horasCusto) {
        this.horasCusto = horasCusto;
    }

    public Double getValorHoraCusto() {
        return valorHoraCusto;
    }

    public void setValorHoraCusto(Double valorHoraCusto) {
        this.valorHoraCusto = valorHoraCusto;
    }

    public Double getTotalCusto() {
        return totalCusto;
    }

    public void setTotalCusto(Double totalCusto) {
        this.totalCusto = totalCusto;
    }

    public ArrayList<String> getCcsCusto() {
        return ccsCusto;
    }

    public void setCcsCusto(ArrayList<String> ccsCusto) {
        this.ccsCusto = ccsCusto;
    }

    @Override
    public String toString() {
        return "Custo{" +
                "tipoCusto='" + tipoCusto + '\'' +
                ", perfilCusto='" + perfilCusto + '\'' +
                ", periodoExecucaoCusto='" + periodoExecucaoCusto + '\'' +
                ", horasCusto=" + horasCusto +
                ", valorHoraCusto=" + valorHoraCusto +
                ", totalCusto=" + totalCusto +
                ", ccsCusto=" + ccsCusto +
                '}';
    }
}
