package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Forum;
import net.weg.wegssm.model.entities.Status;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    /**
     * Método para listar as demandas pelo status
     * @param status
     * @return
     */
    List<Demanda> findByStatus(Status status);

    /**
     * Método para listar as demandas a partir de um forum ( id )
     * @param forum
     * @return
     */
    List<Demanda> findByForum(Forum forum);

    /**
     * Método para listar as demandas a partir de um usuário ( id )
     * @param usuario
     * @return
     */
    List<Demanda> findByUsuario(Usuario usuario);

//    List<Demanda> findByDepartamento(String departamento); -- Não possui este atibuto no BD

    /**
     * Método para listar todas as demandas a partir de um título
     * @param titulo
     * @return
     */
    List<Demanda> findByTitulo(String titulo);

}

