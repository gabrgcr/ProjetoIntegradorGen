package org.generation.sb.saudedobem.repository;

import org.generation.sb.saudedobem.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vendaRepository extends JpaRepository<Venda, Long> {

}
