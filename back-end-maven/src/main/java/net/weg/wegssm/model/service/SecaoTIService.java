package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.SecaoTI;
import net.weg.wegssm.repository.SecaoTIRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoTIService {

    public SecaoTIRepository secaoTIRepository;

    public SecaoTIService(SecaoTIRepository secaoTIRepository) {
        this.secaoTIRepository = secaoTIRepository;
    }

    public List<SecaoTI> findAll() {
        return secaoTIRepository.findAll();
    }
}
