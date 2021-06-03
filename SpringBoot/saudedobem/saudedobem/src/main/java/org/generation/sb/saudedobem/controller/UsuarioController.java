package org.generation.sb.saudedobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(Usuario usuario) {
		return usuarioService.findAll(usuario);
	}
	
	@GetMapping(path = "/search", params = "id")
	public ResponseEntity<Usuario> findById(@RequestParam long id) {
		return usuarioService.findById(id);
	}
	
	@GetMapping(path = "/search", params = "usuario")
	public ResponseEntity<Usuario> findByUsuario(@RequestParam String usuario) {
		return usuarioService.findByUsuario(usuario);
	}
	
	@GetMapping(path = "/search", params = "email")
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
		return usuarioService.findByEmail(email);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping(path = "/updt")
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario);
	}
	
	@DeleteMapping(path = "/del", params = "id")
	public ResponseEntity<Usuario> deleteUsuario(@RequestParam long id) {
		return usuarioService.deleteUsuario(id);
	}
}
