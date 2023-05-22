package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.EscopoProposta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.EscopoPropostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe service para o escopo da proposta
 */
@Service
@AllArgsConstructor
public class EscopoPropostaService {

    /**
     * Classe repository do escopo da proposta
     */
    private EscopoPropostaRepository escopoPropostaRepository;


    /**
     * Função para buscar um escopo da proposta pelo usuário
     *
     * @param usuario
     * @param pageable
     * @return
     */
    public Page<EscopoProposta> findByUsuario(Usuario usuario, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitante(usuario, pageable);
    }

    /**
     * Função para buscar um escopo proposta pelo usuário e pelo título
     *
     * @param usuario
     * @param titulo
     * @param pageable
     * @return
     */
    public Page<EscopoProposta> findByUsuarioAndTitulo(Usuario usuario, String titulo, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitanteAndTitulo(usuario, titulo, pageable);
    }

    /**
     * Função para salvar um escopo da proposta
     *
     * @param escopoProposta
     * @return
     */
    public EscopoProposta save(EscopoProposta escopoProposta) {
        return escopoPropostaRepository.save(escopoProposta);
    }

    /**
     * Função para verificar se existe um escopo da proposta pelo id
     *
     * @param id
     * @return
     */
    public Boolean existsById(Long id) {
        return escopoPropostaRepository.existsById(id);
    }

    /**
     * Função para deletar um escopo da proposta pelo id
     *
     * @param id
     */
    public void deleteById(Long id) {
        escopoPropostaRepository.deleteById(id);
    }

    /**
     * Função para buscar um escopo da proposta pela demanda
     *
     * @param demanda
     * @return
     */
    public List<EscopoProposta> findByDemanda(Demanda demanda) {
        return escopoPropostaRepository.findByDemanda(demanda);
    }

    /**
     * Função para buscar um escopo da proposta pelo id
     *
     * @param idEscopo
     * @return
     */
    public Optional<EscopoProposta> findById(Long idEscopo) {
        return escopoPropostaRepository.findById(idEscopo);
    }

}
