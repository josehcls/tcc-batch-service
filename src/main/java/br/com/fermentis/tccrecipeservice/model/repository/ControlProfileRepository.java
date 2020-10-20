package br.com.fermentis.tccrecipeservice.model.repository;

import br.com.fermentis.tccrecipeservice.model.entity.ControlProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlProfileRepository extends JpaRepository<ControlProfile, Long> {
}

