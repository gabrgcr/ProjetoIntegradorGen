package org.generation.sb.saudedobem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.UserLogin;
import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return usuarioService.findAll();
	}
	
	@GetMapping(path = "/buscar", params = "id")
	public ResponseEntity<Usuario> findById(@Valid @RequestParam long id) {
		return usuarioService.findById(id);
	}
		
	@GetMapping(path = "/buscar", params = "email")
	public ResponseEntity<Usuario> findByEmail(@Valid @RequestParam String email) {
		return usuarioService.findByEmail(email);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.login(user).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping(path = "/cadastrar")
	public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping(path = "/alterar")
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario);
	}
	
	@DeleteMapping(path = "/deletar", params = "id")
	public ResponseEntity<Usuario> deleteUsuario(@Valid @RequestParam long id) {
		return usuarioService.deleteUsuario(id);
	}
}
