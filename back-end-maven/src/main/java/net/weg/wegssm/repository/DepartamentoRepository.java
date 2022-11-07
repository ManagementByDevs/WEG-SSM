package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    /**
     * Método que verifica se existe um departamento com o nome passado por parâmetro
     * @param nome
     * @return
     */
    Boolean existsByNome(String nome);

}
