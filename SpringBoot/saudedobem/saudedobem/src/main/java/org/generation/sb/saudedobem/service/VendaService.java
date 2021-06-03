package org.generation.sb.saudedobem.service;

import org.generation.sb.saudedobem.repository.vendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

	@Autowired
	private vendaRepository vendaRepository;
}
