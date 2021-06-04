package org.generation.sb.saudedobem.service;

import java.util.List;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService{

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	/**
	 * Metodo para buscar todos os medicamentos cadastrados
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findAll(){
		List<Medicamento> listaMedicamento = medicamentoRepository.findAll();
		if (listaMedicamento.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaMedicamento);
		}
	}
	/**
	 * Metodo para buscar um medicamento correspondente com o ID
	 * @return ResponseEntity com o status HTTP da requisição e um medicamento
	 */
	public ResponseEntity<Medicamento> findById(long id) {
		return medicamentoRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar os medicamentos correspondentes ao nome
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByNome(String nome) {
		List<Medicamento> listaPorNome = medicamentoRepository.findAllByNomeContaining(nome);
		if (listaPorNome.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorNome);
		}
	}
	
	/**
	 * Metodo para salvar medicamentos na base de dados
	 * @return ResponseEntity com o status HTTP da requisição e o novo medicamento
	 */
	public ResponseEntity<Medicamento> saveMedicamento(Medicamento novoMedicamento) {
		String categoriaValida = novoMedicamento.getCategoria();

		boolean valido = true;
		
		if (categoriaValida.equals("Referência")
				|| categoriaValida.equals("Genérico")
				|| categoriaValida.equals("Similar")) {
			valido = true;
		} else {
			valido = false;
		}
		
		if (valido == false) {
			return ResponseEntity.status(400).build();
		}
		
		List<Medicamento> nomePertenceCategoria = medicamentoRepository
				.findByNomeAndCategoria(novoMedicamento.getNome(), novoMedicamento.getCategoria());
	
		if (nomePertenceCategoria.isEmpty()){
			return ResponseEntity.status(200).body(medicamentoRepository.save(novoMedicamento));
				
		} else {
			return ResponseEntity.status(409).build();
		}
	}
	
	/**
	 * Metodo para alterar medicamentos na base de dados
	 * @return ResponseEntity com o status HTTP da requisição e o medicamento alterado
	 */
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

	/**
	 * Metodo para deletar um medicamento correspondente ao ID
	 * @return ResponseEntity com o status HTTP da requisição
	 */
	public ResponseEntity<Medicamento> deleteMedicamento(long id) {	
		if (medicamentoRepository.existsById(id)) {
			medicamentoRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
