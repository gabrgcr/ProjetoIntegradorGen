package org.generation.sb.saudedobem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.generation.sb.saudedobem.model.util.MedicamentoCategoria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_medicamento")
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotBlank
	@Size(max = 255)
	private String descricao;

	@NotNull
	private Double preco;

	@NotNull
	@Enumerated(EnumType.ORDINAL) 
	private MedicamentoCategoria categoria;
	
	@NotNull
	@ManyToMany
	@JoinTable(name = "tb_medicamento_doenca",
		joinColumns = @JoinColumn(name = "fk_medicamento"),
		inverseJoinColumns = @JoinColumn(name = "fk_doenca"))
	@JsonIgnoreProperties("medicamentos")
	private List<Doenca> doencas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public MedicamentoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(MedicamentoCategoria categoria) {
		this.categoria = categoria;
	}

	public List<Doenca> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

}
