package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe repository para a mensagem
 */
@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    /**
     * Método para buscar as mensagens através de um chat
     *
     * @param chat
     * @return
     */
    List<Mensagem> findAllByIdChat(Chat chat);

    /**
     * Método para buscar as mensagens através de um chat e se foi visualizada
     *
     * @param chat
     * @param visto
     * @return
     */
    List<Mensagem> findAllByIdChatAndVisto(Chat chat, Boolean visto);

    /**
     * Método para buscar as mensagens através de um chat, se foi visualizada e se o usuário não é o que enviou a mensagem
     *
     * @param chat
     * @param visto
     * @param usuario
     * @return
     */
    List<Mensagem> findAllByIdChatAndVistoAndUsuarioNot(Chat chat, Boolean visto, Usuario usuario);

    /**
     * Método para buscar as mensagem através do id do chat, com paginação
     *
     * @param chat
     * @param pageable
     * @return
     */
    Page<Mensagem> findByIdChat(Chat chat, Pageable pageable);

}
