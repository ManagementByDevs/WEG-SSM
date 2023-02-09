package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.CC;
import net.weg.wegssm.repository.CCsRepository;
import org.springframework.stereotype.Service;

@Service
public class CCsService {

    private CCsRepository ccsRepository;

    public CCsService(CCsRepository ccsRepository) {
        this.ccsRepository = ccsRepository;
    }

    public CC save(CC cc) {
        return ccsRepository.save(cc);
    }
}
