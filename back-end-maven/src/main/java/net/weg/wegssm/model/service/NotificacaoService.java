package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Notificacao;
import net.weg.wegssm.model.entities.TipoNotificacao;
import net.weg.wegssm.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public <S extends Notificacao> S save(S entity) {
        return notificacaoRepository.save(entity);
    }

    public List<Notificacao> findAll(){
        return notificacaoRepository.findAll();
    }

    public Optional<Notificacao> findById(Long id){
        return notificacaoRepository.findById(id);
    }

    public List<Notificacao> findByTipoNotificacao(TipoNotificacao tipoNotificacao){
        return notificacaoRepository.findByTipoNotificacao(tipoNotificacao);
    }

//    public List<Notificacao> findByData(Date data){
//        return notificacaoRepository.findByData(data);
//    }

    public void deleteById(Long id) {
        notificacaoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return notificacaoRepository.existsById(id);
    }

}
