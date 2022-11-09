package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    /**
     * Método para listar todas as notificações com determinado tipo de notificação
     *
     * @param tipoNotificacao
     * @return
     */
    List<Notificacao> findByTipoNotificacao(TipoNotificacao tipoNotificacao);

    /**
     * Método para listar todas as notificações a partir de uma data
     * @param data
     * @return
     */
//    List<Notificacao> findByData(Date data);

}
