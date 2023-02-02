package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Notificacao> findByUsuario(Usuario usuario, Pageable pageable);

    /**
     * Lista as notificações pela data escolhida
     * @param data
     * @return
     */
    List<Notificacao> findByData(Data data);
    Page<Notificacao> findByUsuarioAndVisualizado(Usuario usuario, Boolean visualizado, Pageable pageable);
}
