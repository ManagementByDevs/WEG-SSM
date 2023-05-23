package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.NotificacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Classe service para as notificações
 */
@Service
@AllArgsConstructor
public class NotificacaoService {

    /**
     * Repository das notificações
     */
    private NotificacaoRepository notificacaoRepository;

    /**
     * Função para buscar todas as notificações
     *
     * @return
     */
    public List<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }

    /**
     * Função para buscar todas as notificações com paginação
     *
     * @param pageable
     * @return
     */
    public Page<Notificacao> findAll(Pageable pageable) {
        return notificacaoRepository.findAll(pageable);
    }

    /**
     * Função para buscar uma notificação através de um id
     *
     * @param id
     * @return
     */
    public Optional<Notificacao> findById(Long id) {
        return notificacaoRepository.findById(id);
    }

    /**
     * Função para buscar notificação através do tipo
     *
     * @param tipoNotificacao
     * @return
     */
    public List<Notificacao> findByTipoNotificacao(TipoNotificacao tipoNotificacao) {
        return notificacaoRepository.findByTipoNotificacao(tipoNotificacao);
    }

    /**
     * Função para verificar se existe uma notificação através do id
     *
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return notificacaoRepository.existsById(id);
    }

    /**
     * Função para salvar uma notificação
     *
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Notificacao> S save(S entity) {
        return notificacaoRepository.save(entity);
    }

    /**
     * Função para deletar uma notificação através do id
     *
     * @param id
     */
    public void deleteById(Long id) {
        notificacaoRepository.deleteById(id);
    }

    /**
     * Função para buscar notificações através de um usuário
     *
     * @param usuario
     * @param pageable
     * @return
     */
    public Page<Notificacao> findByUsuario(Usuario usuario, Pageable pageable) {
        return notificacaoRepository.findByUsuario(usuario, pageable);
    }

    /**
     * Função para buscar notificações através da data
     *
     * @param data
     * @return
     */
    public List<Notificacao> findByData(Data data) {
        return notificacaoRepository.findByData(data);
    }

    /**
     * Função para buscar notificação através do usuário e da visualização
     *
     * @param usuario
     * @param visualizado
     * @param pageable
     * @return
     */
    public Page<Notificacao> findByUsuarioAndVisualizado(Usuario usuario, Boolean visualizado, Pageable pageable) {
        return notificacaoRepository.findByUsuarioAndVisualizado(usuario, visualizado, pageable);
    }

}
