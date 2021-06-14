package org.generation.sb.saudedobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping
	public ResponseEntity<List<Medicamento>> getAll() {
		return medicamentoService.findAll();
	}
	
	@GetMapping(path = "/buscar/id/{id}")
	public ResponseEntity<Medicamento> getById(@Valid @PathVariable long id) {
		return medicamentoService.findById(id);
	}
		
	@GetMapping(path = "/buscar/nome/{nome}")
	public ResponseEntity<List<Medicamento>> getByNome(@Valid @PathVariable String nome) {
		return medicamentoService.findByNome(nome);
	}
	
	@PostMapping(path = "/cadastrar")
	public ResponseEntity<Medicamento> postMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.saveMedicamento(medicamento);
	}
	
	@PutMapping(path = "/alterar")
	public ResponseEntity<Medicamento> putMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.updateMedicamento(medicamento);
	}
	
	@DeleteMapping(path = "/deletar/id/{id}")
	public ResponseEntity<Medicamento> deleteMedicamento(@Valid @PathVariable long id) {
		return medicamentoService.deleteMedicamento(id);
	}
	
}
