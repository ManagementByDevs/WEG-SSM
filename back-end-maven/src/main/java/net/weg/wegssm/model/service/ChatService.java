package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe service para o chat
 */
@Service
@AllArgsConstructor
public class ChatService {

    /**
     * Classe repository das CCs
     */
    private ChatRepository chatRepository;

    /**
     * Função para buscar todos os chats
     *
     * @return
     */
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    /**
     * Função para buscar um chat pelo seu ID
     *
     * @param id
     * @return
     */
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

    /**
     * Função para verificar se um chat existe pelo seu ID
     *
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return chatRepository.existsById(id);
    }

//    public Boolean existsByUsuario(Usuario usuario) {
//        return chatRepository.existsByUsuario(usuario);
//    }

    /**
     * Função para salvar um chat
     *
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Chat> S save(S entity) {
        return chatRepository.save(entity);
    }

    /**
     * Função para deletar um chat pelo seu id
     *
     * @param id
     */
    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }

    /**
     * Função para buscar um chat pelos usuários que fazem parte
     *
     * @param usuario
     * @return
     */
    public List<Chat> findByUsuariosChat(Usuario usuario) {
        return chatRepository.findByUsuariosChat(usuario);
    }

    /**
     * Função para buscar um chat pela proposta e pelos usuários que fazem parte
     *
     * @param proposta
     * @param usuario
     * @return
     */
    public List<Chat> findByIdPropostaAndUsuariosChat(Proposta proposta, Usuario usuario) {
        return chatRepository.findByIdPropostaAndUsuariosChat(proposta, usuario);
    }

}
