package org.generation.sb.saudedobem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_medicamento")
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	private String nome;

	@NotNull
	@Size(max = 255)
	private String descricao;

	@NotNull
	private double preco;

	@NotNull
	@Column(columnDefinition = "ENUM('Referência','Genérico','Similar')")
	private String categoria;

	@OneToMany(mappedBy = "id_medicamento")
	private List<Venda> vendidos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "tb_medicamento_doenca",
		joinColumns = @JoinColumn(name = "fk_medicamento"),
		inverseJoinColumns = @JoinColumn(name = "fk_doenca"))
	private List<Doenca> medicamentoDoenca;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Venda> getVendidos() {
		return vendidos;
	}

	public void setVendidos(List<Venda> vendidos) {
		this.vendidos = vendidos;
	}

	public List<Doenca> getMedicamentoDoenca() {
		return medicamentoDoenca;
	}

	public void setMedicamentoDoenca(List<Doenca> medicamentoDoenca) {
		this.medicamentoDoenca = medicamentoDoenca;
	}

}
