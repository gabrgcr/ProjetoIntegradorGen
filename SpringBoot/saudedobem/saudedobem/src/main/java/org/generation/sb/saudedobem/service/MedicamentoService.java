package org.generation.sb.saudedobem.service;

import java.util.List;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.model.util.MedicamentoTipo;
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
	public ResponseEntity<Medicamento> findById(Long id) {
		return medicamentoRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar os medicamentos correspondentes ao nome
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByNome(String nome) {
		List<Medicamento> listaPorNome = medicamentoRepository.findAllByNomeContainingIgnoreCase(nome);
		if (listaPorNome.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorNome);
		}
	}
	
	/**
	 * Metodo para buscar os medicamentos correspondentes ao tipo
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByTipo(MedicamentoTipo tipo) {
		List<Medicamento> listaPorTipo = medicamentoRepository.findAllByTipo(tipo);
		if (listaPorTipo.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorTipo);
		}
	}
	
	/**
	 * Metodo para buscar os medicamentos com maior preço
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByPrecoDesc() {
		List<Medicamento> listaPorPrecoDesc = medicamentoRepository.findByOrderByPrecoDesc();
		if (listaPorPrecoDesc.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorPrecoDesc);
		}
	}
	
	/**
	 * Metodo para buscar os medicamentos com menor preço
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByPrecoAsc() {
		List<Medicamento> listaPorPrecoAsc = medicamentoRepository.findByOrderByPrecoAsc();
		if (listaPorPrecoAsc.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorPrecoAsc);
		}
	}
	
	/**
	 * Metodo para buscar os medicamentos que estão em promoção
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByPromocao() {
		List<Medicamento> listaPorPromocao = medicamentoRepository.findByPromocaoTrue();
		if (listaPorPromocao.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorPromocao);
		}
	}
	
	/**
	 * Metodo para buscar os medicamentos que estão em destaque
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com os medicamentos
	 */
	public ResponseEntity<List<Medicamento>> findByDestaque() {
		List<Medicamento> listaPorDestaque = medicamentoRepository.findByDestaqueTrue();
		if (listaPorDestaque.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPorDestaque);
		}
	}
	
	/**
	 * Metodo para salvar medicamentos na base de dados
	 * @return ResponseEntity com o status HTTP da requisição e o novo medicamento
	 */
	public ResponseEntity<Medicamento> saveMedicamento(Medicamento novoMedicamento) {
		String tipoValido = novoMedicamento.getTipo().toString();

		boolean valido = true;
		
		if (tipoValido.equals("Referência")
				|| tipoValido.equals("Genérico")
				|| tipoValido.equals("Similar")) {
			valido = true;
		} else {
			valido = false;
		}
		
		if (valido == false) {
			return ResponseEntity.status(400).build();
		}
		
		List<Medicamento> nomePertenceTipo = medicamentoRepository
				.findByNomeAndTipo(novoMedicamento.getNome(), novoMedicamento.getTipo());
		if (nomePertenceTipo.isEmpty()){
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
		List<Medicamento> nomePertenceTipoPercenteId = medicamentoRepository
				.findByNomeAndTipoAndId(alterMedicamento.getNome(), alterMedicamento.getTipo(), alterMedicamento.getId());
		
		List<Medicamento> nomePercenteTipo = medicamentoRepository
				.findByNomeAndTipo(alterMedicamento.getNome(), alterMedicamento.getTipo());
		
		if (nomePertenceTipoPercenteId.isEmpty()) {
			if (nomePercenteTipo.isEmpty()) {
				return ResponseEntity.status(200).body(medicamentoRepository.save(alterMedicamento));
			} else {
				return ResponseEntity.status(409).build();
			}
		} else {
			return ResponseEntity.status(200).body(medicamentoRepository.save(alterMedicamento));	
		}
	}

	/**
	 * Metodo para deletar um medicamento correspondente ao ID
	 * @return ResponseEntity com o status HTTP da requisição
	 */
	public ResponseEntity<Medicamento> deleteMedicamento(Long id) {	
		if (medicamentoRepository.existsById(id)) {
			medicamentoRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
