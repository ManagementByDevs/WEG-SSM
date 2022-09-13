package net.weg.wegssm.model.entities;

import java.util.ArrayList;

public class Demanda {
    private Solicitante solicitanteDemanda;
    private String tituloDemanda, problemaDemdanda, propostaDemdanda, frequenciaDemdanda, tamanhoDemdanda, buSolicitanteDemdanda, secaoTiDemdanda, motivoRecusaDemdanda;
    private Integer statusDemanda;
    private ArrayList<String> buBeneficiadasDemdanda;
    private ArrayList<Beneficio> beneficiosDemdanda;
    private ArrayList<Anexo> anexosDemdanda;
    private Chat chatDemanda;

    public void addBeneficioDemanda(Beneficio beneficio) {};
    public void addAnexoDemanda(Anexo anexo) {};
    public void criarPropostaDeDemana() {};

    public Demanda(Solicitante solicitanteDemanda, String tituloDemanda, String problemaDemdanda, String propostaDemdanda, String frequenciaDemdanda, String tamanhoDemdanda, String buSolicitanteDemdanda, String secaoTiDemdanda, String motivoRecusaDemdanda, Integer statusDemanda, ArrayList<String> buBeneficiadasDemdanda, ArrayList<Beneficio> beneficiosDemdanda, ArrayList<Anexo> anexosDemdanda, Chat chatDemanda) {
        this.solicitanteDemanda = solicitanteDemanda;
        this.tituloDemanda = tituloDemanda;
        this.problemaDemdanda = problemaDemdanda;
        this.propostaDemdanda = propostaDemdanda;
        this.frequenciaDemdanda = frequenciaDemdanda;
        this.tamanhoDemdanda = tamanhoDemdanda;
        this.buSolicitanteDemdanda = buSolicitanteDemdanda;
        this.secaoTiDemdanda = secaoTiDemdanda;
        this.motivoRecusaDemdanda = motivoRecusaDemdanda;
        this.statusDemanda = statusDemanda;
        this.buBeneficiadasDemdanda = buBeneficiadasDemdanda;
        this.beneficiosDemdanda = beneficiosDemdanda;
        this.anexosDemdanda = anexosDemdanda;
        this.chatDemanda = chatDemanda;
    }

    public Demanda() {
    }

    public Solicitante getSolicitanteDemanda() {
        return solicitanteDemanda;
    }

    public void setSolicitanteDemanda(Solicitante solicitanteDemanda) {
        this.solicitanteDemanda = solicitanteDemanda;
    }

    public String getTituloDemanda() {
        return tituloDemanda;
    }

    public void setTituloDemanda(String tituloDemanda) {
        this.tituloDemanda = tituloDemanda;
    }

    public String getProblemaDemdanda() {
        return problemaDemdanda;
    }

    public void setProblemaDemdanda(String problemaDemdanda) {
        this.problemaDemdanda = problemaDemdanda;
    }

    public String getPropostaDemdanda() {
        return propostaDemdanda;
    }

    public void setPropostaDemdanda(String propostaDemdanda) {
        this.propostaDemdanda = propostaDemdanda;
    }

    public String getFrequenciaDemdanda() {
        return frequenciaDemdanda;
    }

    public void setFrequenciaDemdanda(String frequenciaDemdanda) {
        this.frequenciaDemdanda = frequenciaDemdanda;
    }

    public String getTamanhoDemdanda() {
        return tamanhoDemdanda;
    }

    public void setTamanhoDemdanda(String tamanhoDemdanda) {
        this.tamanhoDemdanda = tamanhoDemdanda;
    }

    public String getBuSolicitanteDemdanda() {
        return buSolicitanteDemdanda;
    }

    public void setBuSolicitanteDemdanda(String buSolicitanteDemdanda) {
        this.buSolicitanteDemdanda = buSolicitanteDemdanda;
    }

    public String getSecaoTiDemdanda() {
        return secaoTiDemdanda;
    }

    public void setSecaoTiDemdanda(String secaoTiDemdanda) {
        this.secaoTiDemdanda = secaoTiDemdanda;
    }

    public String getMotivoRecusaDemdanda() {
        return motivoRecusaDemdanda;
    }

    public void setMotivoRecusaDemdanda(String motivoRecusaDemdanda) {
        this.motivoRecusaDemdanda = motivoRecusaDemdanda;
    }

    public Integer getStatusDemanda() {
        return statusDemanda;
    }

    public void setStatusDemanda(Integer statusDemanda) {
        this.statusDemanda = statusDemanda;
    }

    public ArrayList<String> getBuBeneficiadasDemdanda() {
        return buBeneficiadasDemdanda;
    }

    public void setBuBeneficiadasDemdanda(ArrayList<String> buBeneficiadasDemdanda) {
        this.buBeneficiadasDemdanda = buBeneficiadasDemdanda;
    }

    public ArrayList<Beneficio> getBeneficiosDemdanda() {
        return beneficiosDemdanda;
    }

    public void setBeneficiosDemdanda(ArrayList<Beneficio> beneficiosDemdanda) {
        this.beneficiosDemdanda = beneficiosDemdanda;
    }

    public ArrayList<Anexo> getAnexosDemdanda() {
        return anexosDemdanda;
    }

    public void setAnexosDemdanda(ArrayList<Anexo> anexosDemdanda) {
        this.anexosDemdanda = anexosDemdanda;
    }

    public Chat getChatDemanda() {
        return chatDemanda;
    }

    public void setChatDemanda(Chat chatDemanda) {
        this.chatDemanda = chatDemanda;
    }

    @Override
    public String toString() {
        return "Demanda{" +
                "solicitanteDemanda=" + solicitanteDemanda +
                ", tituloDemanda='" + tituloDemanda + '\'' +
                ", problemaDemdanda='" + problemaDemdanda + '\'' +
                ", propostaDemdanda='" + propostaDemdanda + '\'' +
                ", frequenciaDemdanda='" + frequenciaDemdanda + '\'' +
                ", tamanhoDemdanda='" + tamanhoDemdanda + '\'' +
                ", buSolicitanteDemdanda='" + buSolicitanteDemdanda + '\'' +
                ", secaoTiDemdanda='" + secaoTiDemdanda + '\'' +
                ", motivoRecusaDemdanda='" + motivoRecusaDemdanda + '\'' +
                ", statusDemanda=" + statusDemanda +
                ", buBeneficiadasDemdanda=" + buBeneficiadasDemdanda +
                ", beneficiosDemdanda=" + beneficiosDemdanda +
                ", anexosDemdanda=" + anexosDemdanda +
                ", chatDemanda=" + chatDemanda +
                '}';
    }
}
