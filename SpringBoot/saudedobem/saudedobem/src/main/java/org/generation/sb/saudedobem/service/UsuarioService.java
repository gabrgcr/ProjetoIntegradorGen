package org.generation.sb.saudedobem.service;

import java.util.List;
import java.util.Optional;

import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<List<Usuario>> findAll(Usuario usuario) {
		List<Usuario> listaUsuario = usuarioRepository.findAll();
		if (listaUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuario);
		}
	}
	
	public ResponseEntity<Usuario> findById(long id) {
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	public ResponseEntity<Usuario> findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	public ResponseEntity<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	public ResponseEntity<Usuario> saveUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		String tipoNulo = usuario.getTipo();
		if (tipoNulo.isEmpty()) {
			usuario.setTipo("Cliente");
		}
		
		if (usuarioExistente.isEmpty() && emailExistente.isEmpty()) {
			return ResponseEntity.status(201).body(usuarioRepository.save(usuario));
		} else {
			return ResponseEntity.status(406).build();
		}
	}
	
	public ResponseEntity<Usuario> updateUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		String tipoNulo = usuario.getTipo();
		if (tipoNulo.isEmpty()) {
			usuario.setTipo("Cliente");
		}
		
		if (usuarioExistente.isEmpty() && emailExistente.isEmpty()) {
			return ResponseEntity.status(201).body(usuarioRepository.save(usuario));
		} else {
			return ResponseEntity.status(406).build();
		}
	}
	
	public ResponseEntity<Usuario> deleteUsuario(long id) {
		Optional<Usuario> idExiste = usuarioRepository.findById(id);
		
		if (idExiste.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			usuarioRepository.deleteById(id);
		}
		return null;
	}
}
