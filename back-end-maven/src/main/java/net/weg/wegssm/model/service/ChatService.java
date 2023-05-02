package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    public Optional<Chat> findById(Long id) {
        return chatRepository.findById(id);
    }

//    public List<Chat> findByUsuario(Usuario usuario) {
//        return chatRepository.findByUsuario(usuario);
//    }
//
//    public List<Chat> findBySolicitante(Usuario user) {
//        return chatRepository.findBySolicitante(user);
//    }

    public Boolean existsById(Long id) {
        return chatRepository.existsById(id);
    }

//    public Boolean existsByUsuario(Usuario usuario) {
//        return chatRepository.existsByUsuario(usuario);
//    }

    public <S extends Chat> S save(S entity) {
        return chatRepository.save(entity);
    }

    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }

    public List<Chat> findByUsuariosChatContaining(Usuario usuario) {
        return chatRepository.findByUsuariosChatContaining(usuario);
    }
}
