package com.jhondev.io.model;

import com.jhondev.io.model.enums.StatusLancamento;
import com.jhondev.io.model.enums.TipoLancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Representa uma conta no sistema
 */

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private double valor;
	
	@OneToMany(mappedBy = "conta", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CategoriaContas> categoriaContas = new ArrayList<CategoriaContas>();

	private TipoLancamento tipo;

	private StatusLancamento status;
	
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	

	public List<CategoriaContas> getCategoriaContas() {
		return categoriaContas;
	}

	public void setCategoriaContas(List<CategoriaContas> categoriaContas) {
		this.categoriaContas = categoriaContas;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public StatusLancamento getStatus() {
		return status;
	}

	public void setStatus(StatusLancamento status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Conta conta = (Conta) o;

		if (Double.compare(conta.valor, valor) != 0) return false;
		if (!id.equals(conta.id)) return false;
		if (!nome.equals(conta.nome)) return false;
		if (!categoriaContas.equals(conta.categoriaContas)) return false;
		if (tipo != conta.tipo) return false;
		return status == conta.status;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id.hashCode();
		result = 31 * result + nome.hashCode();
		temp = Double.doubleToLongBits(valor);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + categoriaContas.hashCode();
		result = 31 * result + tipo.hashCode();
		result = 31 * result + status.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Conta{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", valor=" + valor +
				", categoriaContas=" + categoriaContas +
				", tipo=" + tipo +
				", status=" + status +
				'}';
	}
}
