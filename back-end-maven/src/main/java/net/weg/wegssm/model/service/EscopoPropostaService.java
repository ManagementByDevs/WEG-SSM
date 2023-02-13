package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.EscopoProposta;
import net.weg.wegssm.model.entities.Usuario;
import net.weg.wegssm.repository.EscopoPropostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EscopoPropostaService {

    private EscopoPropostaRepository escopoPropostaRepository;

    public Page<EscopoProposta> findByUsuario(Usuario usuario, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitante(usuario, pageable);
    }

    public Page<EscopoProposta> findByUsuarioAndTitulo(Usuario usuario, String titulo, Pageable pageable) {
        return escopoPropostaRepository.findBySolicitanteAndTitulo(usuario, titulo, pageable);
    }

    public EscopoProposta save(EscopoProposta escopoProposta) {
        return escopoPropostaRepository.save(escopoProposta);
    }

    public Boolean existsById(Long id) {
        return escopoPropostaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        escopoPropostaRepository.deleteById(id);
    }
}
