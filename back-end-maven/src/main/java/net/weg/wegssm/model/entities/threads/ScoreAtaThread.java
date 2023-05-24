package net.weg.wegssm.model.entities.threads;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.repository.AtaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Thread utilizada para atualizar o score de todas as atas em processo de aprovação uma vez por dia
 */
public class ScoreAtaThread extends Thread implements Runnable {

    /**
     * Variável utilizada para controlar a atualização do score das atas, limitado a uma vez por dia
     */
    private Integer diaAnterior;

    /**
     * Repository de atas para busca das atas
     */
    private AtaRepository ataRepository;

    /**
     * Construtor da classe com o repositório da ata
     *
     * @param ataRepository AtaRepository para a busca das atas
     */
    public ScoreAtaThread(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
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
     * Função para procurar todas as atas em processo de aprovação e atualizar seus scores, utilizando a função "calcularScore"
     */
    public void processar() {
        try {
            List<Ata> listaAtas = ataRepository.findByPublicadaDgNot(true);
            for (Ata ata : listaAtas) {
                ata.setScore(calcularScore(ata));
                ataRepository.save(ata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Função para calcular o score de uma ata através da soma dos scores das propostas presentes
     *
     * @param ata Ata a se calcular o score
     * @return Score final da ata
     */
    private Double calcularScore(Ata ata) {
        Double scoreFinal = 0.0;
        for (Proposta proposta : ata.getPropostas()) {
            scoreFinal += proposta.getScore();
        }

        return scoreFinal;
    }
}
