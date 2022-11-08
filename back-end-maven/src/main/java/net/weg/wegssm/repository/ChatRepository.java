package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Chat;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUsuario(Usuario usuario);

    Boolean existsByUsuario(Usuario usuario);

    List<Chat> findBySolicitante(Usuario user);
}
