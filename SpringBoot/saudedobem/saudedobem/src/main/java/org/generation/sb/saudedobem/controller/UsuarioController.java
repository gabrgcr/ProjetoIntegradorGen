package org.generation.sb.saudedobem.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.sb.saudedobem.model.UserLogin;
import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return usuarioService.findAll();
	}
	
	@GetMapping(path = "/buscar/id/{id}")
	public ResponseEntity<Usuario> getById(@Valid @PathVariable long id) {
		return usuarioService.findById(id);
	}
		
	@GetMapping(path = "/buscar/email/{email}")
	public ResponseEntity<Usuario> getByEmail(@Valid @PathVariable String email) {
		return usuarioService.findByEmail(email);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> loginUsuario(@Valid @RequestBody UserLogin user) {
		return usuarioService.login(user);
	}
	
	@PostMapping(path = "/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping(path = "/alterar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario);
	}
	
	@DeleteMapping(path = "/deletar/id/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@Valid @PathVariable long id) {
		return usuarioService.deleteUsuario(id);
	}
}
