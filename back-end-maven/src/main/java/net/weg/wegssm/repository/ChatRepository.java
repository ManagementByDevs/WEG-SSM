package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByUsuariosChat(Usuario usuario);

    List<Chat> findByIdPropostaAndUsuariosChat(Proposta proposta, Usuario usuario);

//    Boolean existsByUsuario(Usuario usuario);

//    List<Chat> findBySolicitante(Usuario user);

}
