package org.generation.sb.saudedobem.service;

import org.generation.sb.saudedobem.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoencaService {

	@Autowired
	private DoencaRepository doencaRepository;
}
