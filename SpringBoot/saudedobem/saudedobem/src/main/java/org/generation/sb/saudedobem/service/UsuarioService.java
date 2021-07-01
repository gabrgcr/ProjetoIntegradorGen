package org.generation.sb.saudedobem.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.sb.saudedobem.model.UserLogin;
import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.model.util.TipoUsuario;
import org.generation.sb.saudedobem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public ResponseEntity<Usuario> findById(Long id) {
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
	 * Metodo para efetuar um login de usuario dando a ele um token para acessar o sistema
	 * @param user
	 * @return ResponseEntity com o status HTTP da requisição e o usuario logado, informando o seu token
	 */
	public ResponseEntity<UserLogin> login(UserLogin user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario= usuarioRepository.findByEmail(user.getEmail());
		
		if (usuario.isPresent() && encoder.matches(user.getSenha(), usuario.get().getSenha())) {
				String auth = user.getEmail() + ":" + user.getSenha();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic "+ new String (encoderAuth);
				
				user.setToken(authHeader);
				user.setNome(usuario.get().getNome());
				user.setApelido(usuario.get().getApelido());
				user.setTipo(usuario.get().getTipo().toString());
				
				return ResponseEntity.status(202).body(user);
			}
		return ResponseEntity.status(401).build();
	}


	/**
	 * Metodo para salvar um usuario caso ele não existe no banco de dados
	 * @param usuario
	 * @return ResponseEntity com o status HTTP da requisição com o usuario novo
	 */
	public ResponseEntity<Usuario> saveUsuario(Usuario novoUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(novoUsuario.getEmail());
		String tipoNulo = novoUsuario.getTipo().toString();
		if (tipoNulo.isEmpty()) {
			novoUsuario.setTipo(TipoUsuario.Cliente);
		}
		
		if (emailExistente.isEmpty()) {
			novoUsuario.setSenha(encoder.encode(novoUsuario.getSenha()));
			return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
		} else {
			return ResponseEntity.status(406).build();
		}
	}
	
	/**
	 * Metodo para alterar o usuario, inspecionando se o email pertence a ele mesmo, caso não pertença e o novo email não existe, é feita a alteração
	 * dando a ele um nome email
	 * @param usuario
	 * @return ResponseEntity com o status HTTP com o alteração do email ou de outro campo
	 */
	public ResponseEntity<Usuario> updateUsuario(Usuario alterUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> emailExiste = usuarioRepository.findByEmail(alterUsuario.getEmail());
		List<Usuario> emailPertenceId = usuarioRepository.findByEmailAndId(alterUsuario.getEmail(), alterUsuario.getId());
		String tipoNulo = alterUsuario.getTipo().toString();
		if (tipoNulo.isEmpty()) {
			alterUsuario.setTipo(TipoUsuario.Cliente);
		}
		
		if (emailPertenceId.isEmpty()){
			if(emailExiste.isEmpty()) {
				alterUsuario.setSenha(encoder.encode(alterUsuario.getSenha()));
				return ResponseEntity.status(200).body(usuarioRepository.save(alterUsuario));
			} else {
				return ResponseEntity.status(409).build();
			}
		} else {
			alterUsuario.setSenha(encoder.encode(alterUsuario.getSenha()));
			return ResponseEntity.status(200).body(usuarioRepository.save(alterUsuario));
		}
	}
	
	/**
	 * Metodo para deletar um usuario caso ele exista no banco de dados
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição
	 */
	public ResponseEntity<Usuario> deleteUsuario(Long id) {	
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
