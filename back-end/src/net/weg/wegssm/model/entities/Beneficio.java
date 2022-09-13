package net.weg.wegssm.model.entities;

public class Beneficio {
    private Integer tipoBeneficio;
    private Double valorMensalBeneficio;
    private String moedaBeneficio;
    private String memoriaCalculoBeneficio;
    private Demanda demandaBeneficio;

    public Beneficio() { }

    public Beneficio(Integer tipoBeneficio, Double valorMensalBeneficio, String moedaBeneficio, String memoriaCalculoBeneficio, Demanda demandaBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
        this.valorMensalBeneficio = valorMensalBeneficio;
        this.moedaBeneficio = moedaBeneficio;
        this.memoriaCalculoBeneficio = memoriaCalculoBeneficio;
        this.demandaBeneficio = demandaBeneficio;
    }

    public Integer getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(Integer tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public Double getValorMensalBeneficio() {
        return valorMensalBeneficio;
    }

    public void setValorMensalBeneficio(Double valorMensalBeneficio) {
        this.valorMensalBeneficio = valorMensalBeneficio;
    }

    public String getMoedaBeneficio() {
        return moedaBeneficio;
    }

    public void setMoedaBeneficio(String moedaBeneficio) {
        this.moedaBeneficio = moedaBeneficio;
    }

    public String getMemoriaCalculoBeneficio() {
        return memoriaCalculoBeneficio;
    }

    public void setMemoriaCalculoBeneficio(String memoriaCalculoBeneficio) {
        this.memoriaCalculoBeneficio = memoriaCalculoBeneficio;
    }

    public Demanda getDemandaBeneficio() {
        return demandaBeneficio;
    }

    public void setDemandaBeneficio(Demanda demandaBeneficio) {
        this.demandaBeneficio = demandaBeneficio;
    }

    @Override
    public String toString() {
        return "Beneficio{" +
                "tipoBeneficio=" + tipoBeneficio +
                ", valorMensalBeneficio=" + valorMensalBeneficio +
                ", moedaBeneficio='" + moedaBeneficio + '\'' +
                ", memoriaCalculoBeneficio='" + memoriaCalculoBeneficio + '\'' +
                ", demandaBeneficio=" + demandaBeneficio +
                '}';
    }
}
