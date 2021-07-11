package org.generation.sb.saudedobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.Medicamento;
import org.generation.sb.saudedobem.model.util.MedicamentoTipo;
import org.generation.sb.saudedobem.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping
	public ResponseEntity<List<Medicamento>> getAll() {
		return medicamentoService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Medicamento> getById(@Valid @PathVariable Long id) {
		return medicamentoService.findById(id);
	}
		
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Medicamento>> getByNome(@Valid @PathVariable String nome) {
		return medicamentoService.findByNome(nome);
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Medicamento>> getByTipo(@Valid @PathVariable MedicamentoTipo tipo) {
		return medicamentoService.findByTipo(tipo);
	}
	
	@GetMapping("/preco-desc")
	public ResponseEntity<List<Medicamento>> getByPrecoDesc() {
		return medicamentoService.findByPrecoDesc();
	}
	
	@GetMapping("/preco-asc")
	public ResponseEntity<List<Medicamento>> getByPrecoAsc() {
		return medicamentoService.findByPrecoAsc();
	}
	
	@GetMapping("/promocao")
	public ResponseEntity<List<Medicamento>> getByPromocao() {
		return medicamentoService.findByPromocao();
	}
	
	@GetMapping("/destaque")
	public ResponseEntity<List<Medicamento>> getByDestaque() {
		return medicamentoService.findByDestaque();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Medicamento> postMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.saveMedicamento(medicamento);
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Medicamento> putMedicamento(@Valid @RequestBody Medicamento medicamento) {
		return medicamentoService.updateMedicamento(medicamento);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Medicamento> deleteMedicamento(@Valid @PathVariable Long id) {
		return medicamentoService.deleteMedicamento(id);
	}
	
}
