package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.CC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCsRepository extends JpaRepository<CC, Long> {
}
