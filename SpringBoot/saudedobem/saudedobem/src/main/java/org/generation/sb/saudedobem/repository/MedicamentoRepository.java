package org.generation.sb.saudedobem.repository;

import java.util.List;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.model.util.MedicamentoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	public List<Medicamento> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Medicamento> findByNomeAndTipo(String nome, MedicamentoTipo tipo);
	
	public List<Medicamento> findByNomeAndTipoAndId(String nome, MedicamentoTipo tipo, Long id);
	
	public List<Medicamento> findAllByTipo(MedicamentoTipo tipo);
	
	public List<Medicamento> findByOrderByPrecoDesc();
	
	public List<Medicamento> findByOrderByPrecoAsc();
	
	public List<Medicamento> findByPromocaoTrue();
	
	public List<Medicamento> findByDestaqueTrue();
}
