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
     * @param usuario  - Usuário solicitante
     * @param pageable - Componente de paginação
     * @return - Paginação de escopos da proposta de um usuário
     */
    public Page<EscopoProposta> findByUsuario(Usuario usuario, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitante(usuario, pageable);
    }

    /**
     * Função para buscar um escopo proposta pelo usuário e pelo título
     *
     * @param usuario  - Usuário solicitante
     * @param titulo   - Título do escopo da proposta
     * @param pageable - Componente de paginação
     * @return - Paginação de escopos da proposta de um usuário com um título específico
     */
    public Page<EscopoProposta> findByUsuarioAndTitulo(Usuario usuario, String titulo, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitanteAndTitulo(usuario, titulo, pageable);
    }

    /**
     * Função para salvar um escopo da proposta
     *
     * @param escopoProposta - Escopo da proposta a ser salvo
     * @return - Escopo da proposta salvo
     */
    public EscopoProposta save(EscopoProposta escopoProposta) {
        return escopoPropostaRepository.save(escopoProposta);
    }

    /**
     * Função para verificar se existe um escopo da proposta pelo id
     *
     * @param id - Id do escopo da proposta
     * @return - True se existir, false se não existir
     */
    public Boolean existsById(Long id) {
        return escopoPropostaRepository.existsById(id);
    }

    /**
     * Função para deletar um escopo da proposta pelo id
     *
     * @param id - Id do escopo da proposta
     */
    public void deleteById(Long id) {
        escopoPropostaRepository.deleteById(id);
    }

    /**
     * Função para buscar um escopo da proposta pela demanda
     *
     * @param demanda - Demanda do escopo da proposta
     * @return - Lista de escopos da proposta de uma demanda
     */
    public List<EscopoProposta> findByDemanda(Demanda demanda) {
        return escopoPropostaRepository.findByDemanda(demanda);
    }

    /**
     * Função para buscar um escopo da proposta pelo id
     *
     * @param idEscopo - Id do escopo da proposta
     * @return - Escopo da proposta encontrado
     */
    public Optional<EscopoProposta> findById(Long idEscopo) {
        return escopoPropostaRepository.findById(idEscopo);
    }

}
