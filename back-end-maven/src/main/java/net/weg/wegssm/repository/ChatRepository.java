package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para o chat
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    /**
     * Método para listar os chats através de um usuário
     *
     * @param usuario
     * @return
     */
    List<Chat> findByUsuariosChat(Usuario usuario);

    /**
     * Método para listar os chats através de uma proposta e um usuário
     *
     * @param proposta
     * @param usuario
     * @return
     */
    List<Chat> findByIdPropostaAndUsuariosChat(Proposta proposta, Usuario usuario);

    /**
     * Método para listar os chats através de uma demanda e um usuário
     * @param demanda
     * @param usuario
     * @return
     */
    List<Chat> findByIdDemandaAndUsuariosChat(Demanda demanda, Usuario usuario);

}
