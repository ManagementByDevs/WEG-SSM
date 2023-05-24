package net.weg.wegssm.configuration;

import net.weg.wegssm.model.entities.threads.ScoreAtaThread;
import net.weg.wegssm.model.entities.threads.ScoreDemandaThread;
import net.weg.wegssm.model.entities.threads.ScorePautaThread;
import net.weg.wegssm.model.entities.threads.ScorePropostaThread;
import net.weg.wegssm.repository.AtaRepository;
import net.weg.wegssm.repository.DemandaRepository;
import net.weg.wegssm.repository.PautaRepository;
import net.weg.wegssm.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * Classe de configuração e início da execução das Threads de atualização das scores
 */
@Configuration
public class ThreadConfig {

    /**
     * Repository de demandas
     */
    @Autowired
    private DemandaRepository demandaRepository;

    /**
     * Repository de propostas
     */
    @Autowired
    private PropostaRepository propostaRepository;

    /**
     * Repository de pautas
     */
    @Autowired
    private PautaRepository pautaRepository;

    /**
     * Repository de atas
     */
    @Autowired
    private AtaRepository ataRepository;

    /**
     * Bean utilizado para iniciar as threads na inicialização do sistema
     *
     * @return TaskExecutor usado como Bean
     */
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    /**
     * Função para executar as threads de scores na inicialização do sistema
     *
     * @param executor TaskExecutor recebido como Bean
     * @return CommandLineRunner criado para inicializar as threads
     */
    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("taskExecutor") TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(new ScoreDemandaThread(demandaRepository));
                executor.execute(new ScorePropostaThread(propostaRepository));
                executor.execute(new ScorePautaThread(pautaRepository));
                executor.execute(new ScoreAtaThread(ataRepository));
            }
        };
    }
}
