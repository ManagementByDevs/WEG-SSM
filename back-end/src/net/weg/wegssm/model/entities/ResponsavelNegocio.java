package net.weg.wegssm.model.entities;

public class ResponsavelNegocio {
    String nomeResponsavel;
    String areaResponsavel;

    @Override
    public String toString() {
        return "ResponsavelNegocio{" +
                "nomeResponsavel='" + nomeResponsavel + '\'' +
                ", areaResponsavel='" + areaResponsavel + '\'' +
                '}';
    }

    public ResponsavelNegocio() {
    }

    public ResponsavelNegocio(String nomeResponsavel, String areaResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
        this.areaResponsavel = areaResponsavel;
    }

    private void excluirResponsavel() {

    }

    private void editarResponsavel() {

    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getAreaResponsavel() {
        return areaResponsavel;
    }

    public void setAreaResponsavel(String areaResponsavel) {
        this.areaResponsavel = areaResponsavel;
    }
}
