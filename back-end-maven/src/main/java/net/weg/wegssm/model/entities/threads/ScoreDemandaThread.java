package net.weg.wegssm.model.entities.threads;

import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.DemandaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Thread utilizada para atualizar o score de todas as demandas em processo de aprovação uma vez por dia
 */
public class ScoreDemandaThread extends Thread implements Runnable {

    /**
     * Variável utilizada para controlar a atualização do score das demandas, limitado a uma vez por dia
     */
    private Integer diaAnterior;

    /**
     * Repository de demandas para busca das demandas
     */
    private DemandaRepository demandaRepository;

    /**
     * Construtor da classe com o repositório da demanda
     *
     * @param demandaRepository DemandaRepository para a busca das demandas
     */
    public ScoreDemandaThread(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    /**
     * Função principal da Thread, que irá se repetir infinitamente e entrará na função "processar" uma vez por dia
     */
    @Override
    public void run() {
        diaAnterior = LocalDate.now().getDayOfMonth();
        while (true) {
            try {
                if (LocalDate.now().getDayOfMonth() != diaAnterior) {
                    diaAnterior = LocalDate.now().getDayOfMonth();
                    this.processar();
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Função para procurar todas as demandas em processo de aprovação e atualizar seus scores, utilizando a função "calcularScore"
     */
    public void processar() {
        try {
            List<Demanda> listaDemandas = demandaRepository.findByStatusNotAndStatusNot(Status.DONE, Status.CANCELLED);
            for (Demanda demanda : listaDemandas) {
                demanda.setScore(calcularScore(demanda));
                demandaRepository.save(demanda);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Função para calcular e retornar o score de uma demanda
     *
     * @param demanda Demanda usada para o cálculo de seu Score
     * @return Score calculado da demanda recebida
     */
    private Double calcularScore(Demanda demanda) {
        Double valorBeneficiosReais = 0.0;
        Double valorBeneficiosPotenciais = 0.0;

        for (Beneficio beneficio : demanda.getBeneficios()) {

            if (beneficio.getTipoBeneficio().equals(TipoBeneficio.REAL)) {
                valorBeneficiosReais += beneficio.getValor_mensal();
            }

            if (beneficio.getTipoBeneficio().equals(TipoBeneficio.POTENCIAL)) {
                valorBeneficiosPotenciais += beneficio.getValor_mensal();
            }
        }

        Integer valorTamanhoDemanda;
        if (demanda.getTamanho() == null) {
            valorTamanhoDemanda = 1000000000;
        } else if (demanda.getTamanho().equals("Muito Pequeno")) {
            valorTamanhoDemanda = 40;
        } else if (demanda.getTamanho().equals("Pequeno")) {
            valorTamanhoDemanda = 300;
        } else if (demanda.getTamanho().equals("Médio")) {
            valorTamanhoDemanda = 1000;
        } else if (demanda.getTamanho().equals("Grande")) {
            valorTamanhoDemanda = 3000;
        } else {
            valorTamanhoDemanda = 5000;
        }

        Long agingMilisegundos = Math.abs(new Date().getTime() - demanda.getData().getTime());
        Long agingFinal = TimeUnit.DAYS.convert(agingMilisegundos, TimeUnit.MILLISECONDS);

        Usuario solicitante = demanda.getSolicitante();
        Integer valorPrioridade;
        if (solicitante.getTipoUsuario().equals(TipoUsuario.SOLICITANTE)) {
            valorPrioridade = 1;
        } else if (solicitante.getTipoUsuario().equals(TipoUsuario.ANALISTA)) {
            valorPrioridade = 2;
        } else if (solicitante.getTipoUsuario().equals(TipoUsuario.GERENTE)) {
            valorPrioridade = 4;
        } else {
            valorPrioridade = 16;
        }

        return (((2 * valorBeneficiosReais) + (1 * valorBeneficiosPotenciais) + agingFinal) / valorTamanhoDemanda) * valorPrioridade;
    }
}
