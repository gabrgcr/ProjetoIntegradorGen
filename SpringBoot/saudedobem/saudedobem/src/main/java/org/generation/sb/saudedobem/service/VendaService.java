package org.generation.sb.saudedobem.service;

import java.util.List;

import org.generation.sb.saudedobem.model.Venda;
import org.generation.sb.saudedobem.repository.vendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

	@Autowired
	private vendaRepository vendaRepository;
	
	/**
	 * Metodo para buscar todas as vendas
	 * @return ResponseEntity com o status HTTP da requisição e uma lista de todas as vendas
	 */
	public ResponseEntity<List<Venda>> findAll() {
		List<Venda> listaVenda = vendaRepository.findAll();
		if (listaVenda.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaVenda);
		}
	}
	
	/**
	 * Metodo para salvar uma venda na base de dados
	 * @return ResponseEntity com o status HTTP da requisição e a nova venda
	 */
	public ResponseEntity<Venda> saveVenda(Venda novaVenda) {
		return ResponseEntity.status(201).body(vendaRepository.save(novaVenda));
	}
	
}
