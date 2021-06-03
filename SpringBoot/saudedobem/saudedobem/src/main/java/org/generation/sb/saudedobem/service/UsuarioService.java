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
	
	/**
	 * Metodo para buscar todos o usuarios
	 * @return ResponseEntity com o status HTTP da requisição e uma lista de todos os usuarios
	 */
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> listaUsuario = usuarioRepository.findAll();
		if (listaUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuario);
		}
	}
	
	/**
	 * Metodo para buscar um usuario correspondente ao ID
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição e o usuario buscado
	 */
	public ResponseEntity<Usuario> findById(long id) {
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar um usuario correspondente ao email
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição e o usuario buscado
	 */
	public ResponseEntity<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para salvar um usuario caso ele não existe no banco de dados
	 * @param usuario
	 * @return ResponseEntity com o status HTTP da requisição com o usuario novo
	 */
	public ResponseEntity<Usuario> saveUsuario(Usuario novoUsuario) {
		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(novoUsuario.getEmail());
		String tipoNulo = novoUsuario.getTipo();
		if (tipoNulo.isEmpty()) {
			novoUsuario.setTipo("Cliente");
		}
		
		if (emailExistente.isEmpty()) {
			return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
		} else {
			return ResponseEntity.status(406).build();
		}
	}
	
	/**
	 * Metodo para alterar o usuario, inspecionando se o email pertence a ele mesmo, caso não pertença e o novo email não existe, é feita a alteração
	 * dando a ele um nome email
	 * @param usuario
	 * @return Um ResponseEntity com o status HTTP com o alteração do email ou de outro campo
	 */
	public ResponseEntity<Usuario> updateUsuario(Usuario alterUsuario) {
		Optional<Usuario> emailExiste = usuarioRepository.findByEmail(alterUsuario.getEmail());
		List<Usuario> emailPertenceId = usuarioRepository.findByEmailAndId(alterUsuario.getEmail(), alterUsuario.getId());
		String tipoNulo = alterUsuario.getTipo();
		if (tipoNulo.isEmpty()) {
			alterUsuario.setTipo("Cliente");
		}
		
		if (emailPertenceId.isEmpty()){
			// Caso o email nao pertence ao usuario atual
			if(emailExiste.isEmpty()) {
				// Caso o email nao exista
				return ResponseEntity.status(200).body(usuarioRepository.save(alterUsuario));
			} else {
				//Caso o email exista em outro usuario
				return ResponseEntity.status(409).build();
			}
		} else {
			// Altera qualquer atributo mantendo o email
			return ResponseEntity.status(200).body(usuarioRepository.save(alterUsuario));
		}
	}
	
	/**
	 * Metodo para deletar um usuario caso ele exista no banco de dados
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição
	 */
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
