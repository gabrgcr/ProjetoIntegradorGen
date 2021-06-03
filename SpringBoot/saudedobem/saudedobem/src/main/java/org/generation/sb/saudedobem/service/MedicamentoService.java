package org.generation.sb.saudedobem.service;

import org.generation.sb.saudedobem.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService{

	@Autowired
	private MedicamentoRepository medicamentoRepository;
}
