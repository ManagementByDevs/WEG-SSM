package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.MensagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe service para as mensagens
 */
@Service
@AllArgsConstructor
public class MensagemService {

    /**
     * Repository das mensagens
     */
    public MensagemRepository mensagemRepository;

    /**
     * Função para buscar todas as mensagens
     * @return
     */
    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    /**
     * Função para buscar uma mensagem através de um id
     * @param id
     * @return
     */
    public Optional<Mensagem> findById(Long id) {
        return mensagemRepository.findById(id);
    }

    /**
     * Função para verificar se existe uma mensagem através do id
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return mensagemRepository.existsById(id);
    }

    /**
     * Função para salvar uma mensagem
     * @param entity
     * @param <S>
     * @return
     */
    public <S extends Mensagem> S save(S entity) {
        return mensagemRepository.save(entity);
    }

    /**
     * Função para deletar uma mensagem através de um id
     * @param id
     */
    public void deleteById(Long id) {
        mensagemRepository.deleteById(id);
    }

    /**
     * Função para buscar mensagem através do id com paginação
     * @param chat
     * @param pageable
     * @return
     */
    public Page<Mensagem> findByIdChat(Chat chat, Pageable pageable) {
        return mensagemRepository.findByIdChat(chat, pageable);
    }

    /**
     * Função para encontrar todas as mensagens através do id do chat
     * @param chat
     * @return
     */
    public List<Mensagem> findAllByIdChat(Chat chat) {
        return mensagemRepository.findAllByIdChat(chat);
    }

    /**
     * Função para encontrar todas as mensagens através do id do chat e se foi visto
     * @param chat
     * @param visto
     * @return
     */
    public List<Mensagem> findAllByIdChatAndVisto(Chat chat, Boolean visto) {
        return mensagemRepository.findAllByIdChatAndVisto(chat, visto);
    }

    /**
     * Função para encontrar todas as mensagens através do id do chat e se foi visto e se o usuário não é o usuário atual
     * @param chat
     * @param visto
     * @param usuario
     * @return
     */
    public List<Mensagem> findAllByIdChatAndVistoAndUsuarioNot(Chat chat, Boolean visto, Usuario usuario) {
        return mensagemRepository.findAllByIdChatAndVistoAndUsuarioNot(chat, visto, usuario);
    }

}
