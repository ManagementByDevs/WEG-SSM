package net.weg.wegssm.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Proposta extends Demanda {
    String escopoProposta;
    Date inicioExecucaoProposta;
    Date fimExecucaoPRoposta;
    double paybackValorProposta;
    String paybackTipoProposta;
    Integer codigoPpmProposta;
    String linkJiraProposta;
    boolean propostaPublicada;
    boolean propostaNaoPublicada;
    Integer statusProposta;
    String parecerComissaoProposta;
    String informacaoParecerProposta;
    String parecerDGProposta;


    ArrayList<Custo> custosProposta = new ArrayList<>();
    ArrayList<ResponsavelNegocio> responsaveisProposta = new ArrayList<>();

    @Override
    public String toString() {
        return "Proposta{" +
                "escopoProposta='" + escopoProposta + '\'' +
                ", inicioExecucaoProposta=" + inicioExecucaoProposta +
                ", fimExecucaoPRoposta=" + fimExecucaoPRoposta +
                ", paybackValorProposta=" + paybackValorProposta +
                ", paybackTipoProposta='" + paybackTipoProposta + '\'' +
                ", codigoPpmProposta=" + codigoPpmProposta +
                ", linkJiraProposta='" + linkJiraProposta + '\'' +
                ", propostaPublicada=" + propostaPublicada +
                ", propostaNaoPublicada=" + propostaNaoPublicada +
                ", statusProposta=" + statusProposta +
                ", parecerComissaoProposta='" + parecerComissaoProposta + '\'' +
                ", informacaoParecerProposta='" + informacaoParecerProposta + '\'' +
                ", parecerDGProposta='" + parecerDGProposta + '\'' +
                ", custosProposta=" + custosProposta +
                ", responsaveisProposta=" + responsaveisProposta +
                '}';
    }

    public Proposta() {
    }

    public Proposta(Solicitante solicitanteDemanda, String tituloDemanda, String problemaDemdanda, String propostaDemdanda, String frequenciaDemdanda, String tamanhoDemdanda, String buSolicitanteDemdanda, String secaoTiDemdanda, String motivoRecusaDemdanda, Integer statusDemanda, ArrayList<String> buBeneficiadasDemdanda, ArrayList<Beneficio> beneficiosDemdanda, ArrayList<Anexo> anexosDemdanda, Chat chatDemanda, String escopoProposta, Date inicioExecucaoProposta, Date fimExecucaoPRoposta, double paybackValorProposta, String paybackTipoProposta, Integer codigoPpmProposta, String linkJiraProposta, boolean propostaPublicada, boolean propostaNaoPublicada, Integer statusProposta, String parecerComissaoProposta, String informacaoParecerProposta, String parecerDGProposta, ArrayList<Custo> custosProposta, ArrayList<ResponsavelNegocio> responsaveisProposta) {
        super(solicitanteDemanda, tituloDemanda, problemaDemdanda, propostaDemdanda, frequenciaDemdanda, tamanhoDemdanda, buSolicitanteDemdanda, secaoTiDemdanda, motivoRecusaDemdanda, statusDemanda, buBeneficiadasDemdanda, beneficiosDemdanda, anexosDemdanda, chatDemanda);
        this.escopoProposta = escopoProposta;
        this.inicioExecucaoProposta = inicioExecucaoProposta;
        this.fimExecucaoPRoposta = fimExecucaoPRoposta;
        this.paybackValorProposta = paybackValorProposta;
        this.paybackTipoProposta = paybackTipoProposta;
        this.codigoPpmProposta = codigoPpmProposta;
        this.linkJiraProposta = linkJiraProposta;
        this.propostaPublicada = propostaPublicada;
        this.propostaNaoPublicada = propostaNaoPublicada;
        this.statusProposta = statusProposta;
        this.parecerComissaoProposta = parecerComissaoProposta;
        this.informacaoParecerProposta = informacaoParecerProposta;
        this.parecerDGProposta = parecerDGProposta;
        this.custosProposta = custosProposta;
        this.responsaveisProposta = responsaveisProposta;
    }

    public Proposta(String escopoProposta, Date inicioExecucaoProposta, Date fimExecucaoPRoposta, double paybackValorProposta, String paybackTipoProposta, Integer codigoPpmProposta, String linkJiraProposta, boolean propostaPublicada, boolean propostaNaoPublicada, Integer statusProposta, String parecerComissaoProposta, String informacaoParecerProposta, String parecerDGProposta, ArrayList<Custo> custosProposta, ArrayList<ResponsavelNegocio> responsaveisProposta) {
        this.escopoProposta = escopoProposta;
        this.inicioExecucaoProposta = inicioExecucaoProposta;
        this.fimExecucaoPRoposta = fimExecucaoPRoposta;
        this.paybackValorProposta = paybackValorProposta;
        this.paybackTipoProposta = paybackTipoProposta;
        this.codigoPpmProposta = codigoPpmProposta;
        this.linkJiraProposta = linkJiraProposta;
        this.propostaPublicada = propostaPublicada;
        this.propostaNaoPublicada = propostaNaoPublicada;
        this.statusProposta = statusProposta;
        this.parecerComissaoProposta = parecerComissaoProposta;
        this.informacaoParecerProposta = informacaoParecerProposta;
        this.parecerDGProposta = parecerDGProposta;
        this.custosProposta = custosProposta;
        this.responsaveisProposta = responsaveisProposta;
    }

    private void addCustoProposta(Custo custo) {

    }

    private void addResponsavelProposta(ResponsavelNegocio responsavel) {

    }

    public String getEscopoProposta() {
        return escopoProposta;
    }

    public void setEscopoProposta(String escopoProposta) {
        this.escopoProposta = escopoProposta;
    }

    public Date getInicioExecucaoProposta() {
        return inicioExecucaoProposta;
    }

    public void setInicioExecucaoProposta(Date inicioExecucaoProposta) {
        this.inicioExecucaoProposta = inicioExecucaoProposta;
    }

    public Date getFimExecucaoPRoposta() {
        return fimExecucaoPRoposta;
    }

    public void setFimExecucaoPRoposta(Date fimExecucaoPRoposta) {
        this.fimExecucaoPRoposta = fimExecucaoPRoposta;
    }

    public double getPaybackValorProposta() {
        return paybackValorProposta;
    }

    public void setPaybackValorProposta(double paybackValorProposta) {
        this.paybackValorProposta = paybackValorProposta;
    }

    public String getPaybackTipoProposta() {
        return paybackTipoProposta;
    }

    public void setPaybackTipoProposta(String paybackTipoProposta) {
        this.paybackTipoProposta = paybackTipoProposta;
    }

    public int getCodigoPpmProposta() {
        return codigoPpmProposta;
    }

    public void setCodigoPpmProposta(int codigoPpmProposta) {
        this.codigoPpmProposta = codigoPpmProposta;
    }

    public String getLinkJiraProposta() {
        return linkJiraProposta;
    }

    public void setLinkJiraProposta(String linkJiraProposta) {
        this.linkJiraProposta = linkJiraProposta;
    }

    public boolean isPropostaPublicada() {
        return propostaPublicada;
    }

    public void setPropostaPublicada(boolean propostaPublicada) {
        this.propostaPublicada = propostaPublicada;
    }

    public boolean isPropostaNaoPublicada() {
        return propostaNaoPublicada;
    }

    public void setPropostaNaoPublicada(boolean propostaNaoPublicada) {
        this.propostaNaoPublicada = propostaNaoPublicada;
    }

    public int getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(int statusProposta) {
        this.statusProposta = statusProposta;
    }

    public String getParecerComissaoProposta() {
        return parecerComissaoProposta;
    }

    public void setParecerComissaoProposta(String parecerComissaoProposta) {
        this.parecerComissaoProposta = parecerComissaoProposta;
    }

    public String getInformacaoParecerProposta() {
        return informacaoParecerProposta;
    }

    public void setInformacaoParecerProposta(String informacaoParecerProposta) {
        this.informacaoParecerProposta = informacaoParecerProposta;
    }

    public String getParecerDGProposta() {
        return parecerDGProposta;
    }

    public void setParecerDGProposta(String parecerDGProposta) {
        this.parecerDGProposta = parecerDGProposta;
    }

    public ArrayList<Custo> getCustosProposta() {
        return custosProposta;
    }

    public void setCustosProposta(ArrayList<Custo> custosProposta) {
        this.custosProposta = custosProposta;
    }

    public ArrayList<ResponsavelNegocio> getResponsaveisProposta() {
        return responsaveisProposta;
    }

    public void setResponsaveisProposta(ArrayList<ResponsavelNegocio> responsaveisProposta) {
        this.responsaveisProposta = responsaveisProposta;
    }
}
