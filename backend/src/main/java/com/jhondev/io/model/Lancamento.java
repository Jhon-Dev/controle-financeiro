package com.jhondev.io.model;

import com.jhondev.io.model.enums.StatusLancamento;
import com.jhondev.io.model.enums.TipoLancamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa uma conta no sistema
 */

@Entity
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private double valor;

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

		Lancamento that = (Lancamento) o;

		if (Double.compare(that.valor, valor) != 0) return false;
		if (!id.equals(that.id)) return false;
		if (!nome.equals(that.nome)) return false;
		if (tipo != that.tipo) return false;
		return status == that.status;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id.hashCode();
		result = 31 * result + nome.hashCode();
		temp = Double.doubleToLongBits(valor);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + tipo.hashCode();
		result = 31 * result + status.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Lancamento{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", valor=" + valor +
				", tipo=" + tipo +
				", status=" + status +
				'}';
	}
}
