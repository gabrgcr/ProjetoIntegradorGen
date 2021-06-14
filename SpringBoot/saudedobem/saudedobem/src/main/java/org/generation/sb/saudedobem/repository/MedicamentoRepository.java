package org.generation.sb.saudedobem.repository;

import java.util.List;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.model.util.MedicamentoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	public List<Medicamento> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Medicamento> findByNomeAndCategoria(String nome, MedicamentoCategoria categoria);
	
	public List<Medicamento> findByNomeAndCategoriaAndId(String nome, MedicamentoCategoria categoria, long id);
}
