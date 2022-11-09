package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    /**
     * Método para listar as demandas pelo status
     *
     * @param status
     * @return
     */
    List<Demanda> findByStatus(Status status);

    /**
     * Método para listar as demandas a partir de um forum ( id )
     *
     * @param forum
     * @return
     */
    List<Demanda> findByForum(Forum forum);

    /**
     * Método para listar as demandas a partir de um usuário ( id )
     *
     * @param usuario
     * @return
     */
    List<Demanda> findByUsuario(Usuario usuario);

    /**
     * Método para listar as demandas a partir de um departamento ( id )
     *
     * @param departamento
     * @return
     */
    List<Demanda> findByDepartamento(Departamento departamento);

    /**
     * Método para listar todas as demandas a partir de um título
     *
     * @param titulo
     * @return
     */
    List<Demanda> findByTitulo(String titulo);

}

