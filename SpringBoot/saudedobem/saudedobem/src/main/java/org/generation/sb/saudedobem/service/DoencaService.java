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
	
	public ResponseEntity<List<Doenca>> findAll() {
		List<Doenca> listaTodos = doencaRepository.findAll();
		if (listaTodos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTodos);
		}
	}
	
	public ResponseEntity<Doenca> saveOrUpdateDoenca(Doenca novaDoenca) {
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
	
	public ResponseEntity<Doenca> deleteDoenca(Long id){
		if(doencaRepository.findById(id).isPresent()) {
			doencaRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
