package org.generation.sb.saudedobem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_medicamento")
	private Medicamento id_medicamento;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario id_usuario;
	
	@NotNull
	@Size(max = 255)
	private String endereco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Medicamento getId_medicamento() {
		return id_medicamento;
	}

	public void setId_medicamento(Medicamento id_medicamento) {
		this.id_medicamento = id_medicamento;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
