package org.generation.sb.saudedobem.controller;

import java.util.List;

import org.generation.sb.saudedobem.model.Doenca;
import org.generation.sb.saudedobem.service.DoencaService;
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
@RequestMapping("/doencas")
public class DoencaController {

	@Autowired
	private DoencaService doencaService;
	
	@GetMapping
	public ResponseEntity<List<Doenca>> getAll() {
		return doencaService.findAll();
	}

	@PostMapping
	public ResponseEntity<Doenca> postDoenca(@RequestBody Doenca doenca){
		return doencaService.saveOrUpdateDoenca(doenca);
	}
	
	@PutMapping
	public ResponseEntity<Doenca> putDoenca(@RequestBody Doenca doenca) {
		return doencaService.saveOrUpdateDoenca(doenca);
	}
	
	@DeleteMapping(params = "id")
	public ResponseEntity<Doenca> deleteDoenca(@RequestParam Long id){
		return doencaService.deleteDoenca(id);
	}
	
}
