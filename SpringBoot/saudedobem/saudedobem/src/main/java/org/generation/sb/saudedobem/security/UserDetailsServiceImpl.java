package org.generation.sb.saudedobem.security;

import java.util.Optional;

import org.generation.sb.saudedobem.model.Usuario;
import org.generation.sb.saudedobem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> user= userRepository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException(email + "Not found"));
		return user.map(UserDetailsImpl:: new).get(); 
	}
}