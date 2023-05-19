package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findAllByIdChat(Chat chat);

    List<Mensagem> findAllByIdChatAndVisto(Chat chat, Boolean visto);

    List<Mensagem> findAllByIdChatAndVistoAndUsuarioNot(Chat chat, Boolean visto, Usuario usuario);

    Page<Mensagem> findByIdChat(Chat chat, Pageable pageable);
}
