package org.generation.sb.saudedobem.repository;

import java.util.List;

import org.generation.sb.saudedobem.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	public List<Medicamento> findAllByNomeContaining(String nome);
	
	public List<Medicamento> findByNomeAndCategoriaAndId(String nome, String categoria, long id);
	
	public List<Medicamento> findByNomeAndCategoria(String nome, String categoria);
}
