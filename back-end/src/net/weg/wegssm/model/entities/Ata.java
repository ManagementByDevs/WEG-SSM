package net.weg.wegssm.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Ata extends Reuniao {
    private String numeroSequencialAta;

    public Ata(){ }

    public String getNumeroSequencialAta() {
        return numeroSequencialAta;
    }

    public Ata(String numeroSequencialAta) {
        this.numeroSequencialAta = numeroSequencialAta;
    }

    public Ata(Date inicioDataReuniao, Date fimDataReuniao, ArrayList<Proposta> propostasDaReuniao, String numeroSequencialAta) {
        super(inicioDataReuniao, fimDataReuniao, propostasDaReuniao);
        this.numeroSequencialAta = numeroSequencialAta;
    }

    public void publicarAta(){ }

    @Override
    public String toString() {
        return "Ata{" +
                "numeroSequencialAta='" + numeroSequencialAta + '\'' +
                '}';
    }
}
