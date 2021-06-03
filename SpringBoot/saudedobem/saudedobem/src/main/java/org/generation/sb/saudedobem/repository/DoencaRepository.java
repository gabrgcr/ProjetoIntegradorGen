package org.generation.sb.saudedobem.repository;

import org.generation.sb.saudedobem.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long> {

}
