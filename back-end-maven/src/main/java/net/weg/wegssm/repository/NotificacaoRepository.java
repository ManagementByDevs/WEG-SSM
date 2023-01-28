package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
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
     * Lista as notificações do usuário
     * @param usuario
     * @return lista de notificações
     */
    List<Notificacao> findByUsuario(Usuario usuario);

    /**
     * Lista as notificações pela data escolhida
     * @param data
     * @return
     */
    List<Notificacao> findByData(Data data);

}
