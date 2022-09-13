package net.weg.wegssm.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Pauta extends Reuniao {

    private String comissaoDaPauta;

    public void criarAtaDaPauta() {

    }

    @Override
    public String toString() {
        return "Pauta{" + super.toString() +
                "comissaoDaPauta='" + comissaoDaPauta + '\'' +
                '}';
    }

    public Pauta() {
        super();
    }

    public Pauta(String comissaoDaPauta, Date inicioDataReuniao, Date fimDataReuniao, ArrayList<Proposta> propostasDaReuniao) {
        super(inicioDataReuniao, fimDataReuniao, propostasDaReuniao);
        this.comissaoDaPauta = comissaoDaPauta;
    }

    public String getComissaoDaPauta() {
        return comissaoDaPauta;
    }

    public void setComissaoDaPauta(String comissaoDaPauta) {
        this.comissaoDaPauta = comissaoDaPauta;
    }
}
