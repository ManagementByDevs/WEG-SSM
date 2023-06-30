package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para os departamentos
 */
@Service
public class DepartamentoService {

    /**
     * Classe repository dos departamentos
     */
    private DepartamentoRepository departamentoRepository;

    /**
     * Construtor da classe
     * @param departamentoRepository
     */
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    /**
     * Função para buscar todos os departamentos salvos
     * @return
     */
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

}
