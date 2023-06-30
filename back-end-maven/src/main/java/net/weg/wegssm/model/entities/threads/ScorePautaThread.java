package net.weg.wegssm.model.entities.threads;

import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.PautaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Thread utilizada para atualizar o score de todas as pautas uma vez por dia
 */
public class ScorePautaThread extends Thread implements Runnable {

    /**
     * Variável utilizada para controlar a atualização do score das pautas, limitado a uma vez por dia
     */
    private Integer diaAnterior;

    /**
     * Repository de pautas para busca das pautas
     */
    private PautaRepository pautaRepository;

    /**
     * Construtor da classe com o repositório da pauta
     *
     * @param pautaRepository PautaRepository para a busca das pautas
     */
    public ScorePautaThread(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
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
     * Função para procurar todas as pautas e atualizar seus scores, utilizando a função "calcularScore"
     */
    public void processar() {
        try {
            List<Pauta> listaPautas = pautaRepository.findAllScore();
            for (Pauta pauta : listaPautas) {
                pauta.setScore(calcularScore(pauta));
                pautaRepository.save(pauta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Função para calcular o score de uma pauta através da soma dos scores das propostas presentes
     *
     * @param pauta Pauta a se calcular o score
     * @return Score final da pauta
     */
    private Double calcularScore(Pauta pauta) {
        Double scoreFinal = 0.0;
        for (Proposta proposta : pauta.getPropostas()) {
            scoreFinal += proposta.getScore();
        }

        return scoreFinal;
    }

}
