package net.weg.wegssm.model.service;

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

@Service
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }

    public Page<Notificacao> findAll(Pageable pageable) {
        return notificacaoRepository.findAll(pageable);
    }

    public Optional<Notificacao> findById(Long id) {
        return notificacaoRepository.findById(id);
    }

    public List<Notificacao> findByTipoNotificacao(TipoNotificacao tipoNotificacao) {
        return notificacaoRepository.findByTipoNotificacao(tipoNotificacao);
    }

    public boolean existsById(Long id) {
        return notificacaoRepository.existsById(id);
    }

    public <S extends Notificacao> S save(S entity) {
        return notificacaoRepository.save(entity);
    }

    public void deleteById(Long id) {
        notificacaoRepository.deleteById(id);
    }

    public Page<Notificacao> findByUsuario(Usuario usuario, Pageable pageable) {
        return notificacaoRepository.findByUsuario(usuario, pageable);
    }

    public List<Notificacao> findByData(Data data) {
        return notificacaoRepository.findByData(data);
    }

    public Page<Notificacao> findByUsuarioAndVisualizado(Usuario usuario, Boolean visualizado, Pageable pageable) {
        return notificacaoRepository.findByUsuarioAndVisualizado(usuario, visualizado, pageable);
    }

}
