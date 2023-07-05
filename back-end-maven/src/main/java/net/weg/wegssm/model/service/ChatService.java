package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Demanda;
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
     * @return - Retorno da lista de chats
     */
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    /**
     * Função para buscar um chat pelo seu ID
     *
     * @param id - ID do chat
     * @return - Retorno do chat com o ID
     */
    public Optional<Chat> findById(Long id) {
        return chatRepository.findById(id);
    }

    /**
     * Função para verificar se um chat existe pelo seu ID
     *
     * @param id - ID do chat
     * @return - Retorno se o chat existe ou não
     */
    public Boolean existsById(Long id) {
        return chatRepository.existsById(id);
    }

    /**
     * Função para salvar um chat
     *
     * @param entity - Chat a ser salvo
     * @param <S>    - Tipo do chat
     * @return - Retorno do chat salvo
     */
    public <S extends Chat> S save(S entity) {
        return chatRepository.save(entity);
    }

    /**
     * Função para deletar um chat pelo seu id
     *
     * @param id - ID do chat a ser deletado
     */
    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }

    /**
     * Função para buscar um chat pelos usuários que fazem parte
     *
     * @param usuario - Usuário que faz parte do chat
     * @return - Retorno do chat com o usuário
     */
    public List<Chat> findByUsuariosChat(Usuario usuario) {
        return chatRepository.findByUsuariosChat(usuario);
    }

    /**
     * Função para buscar um chat pela proposta e pelos usuários que fazem parte
     *
     * @param proposta - Proposta que faz parte do chat
     * @param usuario  - Usuário que faz parte do chat
     * @return - Retorno do chat com a proposta e o usuário
     */
    public List<Chat> findByIdPropostaAndUsuariosChat(Proposta proposta, Usuario usuario) {
        return chatRepository.findByIdPropostaAndUsuariosChat(proposta, usuario);
    }

    /**
     * Função para buscar um chat pela demanda e pelos usuários que fazem parte
     *
     * @param demanda - Demanda que faz parte do chat
     * @param usuario - Usuário que faz parte do chat
     * @return - Retorno do chat com a demanda e o usuário
     */
    public List<Chat> findByIdDemandaAndUsuariosChat(Demanda demanda, Usuario usuario) {
        return chatRepository.findByIdDemandaAndUsuariosChat(demanda, usuario);
    }

}
