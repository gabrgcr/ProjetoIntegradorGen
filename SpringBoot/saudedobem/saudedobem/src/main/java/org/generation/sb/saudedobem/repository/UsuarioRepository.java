package org.generation.sb.saudedobem.repository;

import java.util.List;
import java.util.Optional;

import org.generation.sb.saudedobem.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	
	public List<Usuario> findByEmailAndId(String email, long id);
	
	
}
