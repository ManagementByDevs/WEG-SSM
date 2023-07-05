package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para os departamentos
 */
@Service
@AllArgsConstructor
public class DepartamentoService {

    /**
     * Repository do departamento
     */
    private DepartamentoRepository departamentoRepository;

    /**
     * Função para buscar todos os departamentos salvos
     *
     * @return - Retorno da lista de departamentos
     */
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

}
