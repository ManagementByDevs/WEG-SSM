package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Mensagem;
import net.weg.wegssm.repository.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    public MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public List<Mensagem> findAll(){
        return mensagemRepository.findAll();
    }

    public Optional<Mensagem> findById(Long id){
        return mensagemRepository.findById(id);
    }

    public Boolean existsById(Long id){
        return mensagemRepository.existsById(id);
    }

    public <S extends Mensagem> S save(S entity){
        return mensagemRepository.save(entity);
    }

    public void deleteById(Long id){
        mensagemRepository.deleteById(id);
    }

}
