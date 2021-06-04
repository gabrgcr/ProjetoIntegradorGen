package org.generation.sb.saudedobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping
	public ResponseEntity<List<Medicamento>> findAll() {
		return medicamentoService.findAll();
	}
	
	@GetMapping(path = "/buscar", params = "id")
	public ResponseEntity<Medicamento> findById(@Valid @RequestParam long id) {
		return medicamentoService.findById(id);
	}
		
	@GetMapping(path = "/buscar", params = "nome")
	public ResponseEntity<List<Medicamento>> findByNome(@Valid @RequestParam String nome) {
		return medicamentoService.findByNome(nome);
	}
	
	@PostMapping(path = "/cadastrar")
	public ResponseEntity<Medicamento> saveMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.saveMedicamento(medicamento);
	}
	
	@PutMapping(path = "/alterar")
	public ResponseEntity<Medicamento> updateMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.updateMedicamento(medicamento);
	}
	
	@DeleteMapping(path = "/deletar", params = "id")
	public ResponseEntity<Medicamento> deleteMedicamento(@Valid @RequestParam long id) {
		return medicamentoService.deleteMedicamento(id);
	}
	
}
