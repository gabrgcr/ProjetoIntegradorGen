package org.generation.sb.saudedobem.service;

import java.util.List;
import java.util.Optional;

import org.generation.sb.saudedobem.model.Doenca;
import org.generation.sb.saudedobem.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoencaService {

	@Autowired
	private DoencaRepository doencaRepository;
	
	/**
	 * Metodo para buscar todas as doenças
	 * @return ResponseEntity com o status HTTP da requisição e uma lista com as doenças
	 */
	public ResponseEntity<List<Doenca>> findAll() {
		List<Doenca> listaDoenca = doencaRepository.findAll();
		if (listaDoenca.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDoenca);
		}
	}
	
	/**
	 * Metodo para buscar ua doença correspondente com o ID
	 * @return ResponseEntity com o status HTTP da requisição e uma doença
	 */
	public ResponseEntity<Doenca> findById(Long id) {
		return doencaRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar ua doença correspondente com o nome
	 * @return ResponseEntity com o status HTTP da requisição e uma doença
	 */
	public ResponseEntity<Doenca> findByNome(String nome) {
		return doencaRepository.findByNome(nome).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para salvar uma doença na base de dados
	 * @param novaDoenca
	 * @return ResponseEntity com o status HTTP da requisição e a nova doença cadastrada ou alterada
	 */
	public ResponseEntity<Doenca> saveDoenca(Doenca novaDoenca) {
		if (novaDoenca.getNome().isBlank()) {
			return ResponseEntity.status(400).build();
		} else {
			Optional<Doenca> doencaExiste = doencaRepository.findByNome(novaDoenca.getNome());
			if (doencaExiste.isEmpty()) {
				return ResponseEntity.status(201).body(doencaRepository.save(novaDoenca));
			} else {
				return ResponseEntity.status(406).build();
			}
		}
	}
	
	
	/**
	 * Metodo para atualizar uma doença na base de dados
	 * @param alterDoenca
	 * @return ResponseEntity com o status HTTP da requisição e a nova doença alterada
	 */
	public ResponseEntity<Doenca> updateDoenca(Doenca alterDoenca) {
		if (alterDoenca.getNome().isBlank()) {
			return ResponseEntity.status(400).build();
		} else {
			
		List<Doenca> nomePertenceId = doencaRepository.findByNomeAndId(alterDoenca.getNome(), alterDoenca.getId());
		Optional<Doenca> doencaExiste = doencaRepository.findByNome(alterDoenca.getNome());
		
		if (nomePertenceId.isEmpty()) {
			if (doencaExiste.isEmpty()) {
				return ResponseEntity.status(200).body(doencaRepository.save(alterDoenca));
			} else {
				return ResponseEntity.status(409).build();
			}
		} else {
			return ResponseEntity.status(200).body(doencaRepository.save(alterDoenca));
		}
		}
	}
	/**
	 * Metodo para deletar uma doença correspondente ao ID
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição
	 */
	public ResponseEntity<Doenca> deleteDoenca(Long id){
		if(doencaRepository.existsById(id)) {
			doencaRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
