package org.generation.sb.saudedobem.service;

import java.util.List;
import java.util.Optional;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService{

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	public ResponseEntity<List<Medicamento>> findAll(){
		List<Medicamento> listaTodos = medicamentoRepository.findAll();
		if (listaTodos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTodos);
		}
	}
	
	public ResponseEntity<Medicamento> findById(long id) {
		return medicamentoRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	public ResponseEntity<List<Medicamento>> findByNome(String nome) {
		List<Medicamento> listaPorNome = medicamentoRepository.findAllByNomeContaining(nome);
		if (listaPorNome.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorNome);
		}
	}
	
	public ResponseEntity<Medicamento> saveMedicamento(Medicamento novoMedicamento) {
		List<Medicamento> nomePertenceCategoria = medicamentoRepository
				.findByNomeAndCategoria(novoMedicamento.getNome(), novoMedicamento.getCategoria());
	
		if (nomePertenceCategoria.isEmpty()){
			return ResponseEntity.status(200).body(medicamentoRepository.save(novoMedicamento));
				
		} else {
			return ResponseEntity.status(409).build();
		}
	}
	
	public ResponseEntity<Medicamento> updateMedicamento(Medicamento alterMedicamento) {
		List<Medicamento> nomePertenceCategoriaPercenteId = medicamentoRepository
				.findByNomeAndCategoriaAndId(alterMedicamento.getNome(), alterMedicamento.getCategoria(), alterMedicamento.getId());
		
		List<Medicamento> nomePercenteCategoria = medicamentoRepository
				.findByNomeAndCategoria(alterMedicamento.getNome(), alterMedicamento.getCategoria());
		
		if (nomePertenceCategoriaPercenteId.isEmpty()) {
			// Caso o nome e a categoria nao condiz com o que esta registrado nesse medicamento
			if (nomePercenteCategoria.isEmpty()) {
				// Caso o nome não existe em uma categoria
				return ResponseEntity.status(200).body(medicamentoRepository.save(alterMedicamento));
			} else {
				// Caso o nome exista em uma categoria
				return ResponseEntity.status(409).build();
			}
		} else {
			// Caso o nome e a categoria condizem com o que esta registrado (entao podera alterar outros campos)
			return ResponseEntity.status(200).body(medicamentoRepository.save(alterMedicamento));	
		}
	}

	
	public ResponseEntity<Medicamento> deleteMedicamento(long id) {
		Optional<Medicamento> idExiste = medicamentoRepository.findById(id);
		
		if (idExiste.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			medicamentoRepository.deleteById(id);
		}
		return null;
	}
}
