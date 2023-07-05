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

/**
 * Classe repository para a notificação
 */
@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    /**
     * Método para listar todas as notificações com determinado tipo de notificação
     *
     * @param tipoNotificacao - tipo de notificação
     * @return - lista de notificações
     */
    List<Notificacao> findByTipoNotificacao(TipoNotificacao tipoNotificacao);

    /**
     * Lista as notificações do usuário
     *
     * @param usuario - usuário
     * @return - lista de notificações
     */
    Page<Notificacao> findByUsuario(Usuario usuario, Pageable pageable);

    /**
     * Lista as notificações pela data escolhida
     *
     * @param data - data
     * @return - lista de notificações
     */
    List<Notificacao> findByData(Data data);

    /**
     * Método para buscar as notificações por usuário e se foi visualiada, com paginação
     *
     * @param usuario - usuário
     * @param visualizado - visualização
     * @param pageable - paginação
     * @return - lista de notificações
     */
    Page<Notificacao> findByUsuarioAndVisualizado(Usuario usuario, Boolean visualizado, Pageable pageable);

}
