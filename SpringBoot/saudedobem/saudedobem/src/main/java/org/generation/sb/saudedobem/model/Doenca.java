package org.generation.sb.saudedobem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_doenca")
public class Doenca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotBlank
	@Size(max = 100)
	private String nome;

	@NotNull
	@NotBlank
	@Size(max = 255)
	private String descricao;

	@NotNull
	@NotBlank
	@Column(columnDefinition = "TINYINT(1)")
	private String transmissivel;

	@ManyToMany(mappedBy = "doencas")
	@JsonIgnoreProperties({"vendidos", "doencas"})
	private List<Medicamento> medicamentos = new ArrayList<>();

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

	public String getTransmissivel() {
		return transmissivel;
	}

	public void setTransmissivel(String transmissivel) {
		this.transmissivel = transmissivel;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
}
