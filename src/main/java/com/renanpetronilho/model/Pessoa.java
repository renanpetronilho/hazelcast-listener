package com.renanpetronilho.model;

import java.io.Serializable;

/**
 * Created by renanpetronilho on 14/09/16.
 */
public class Pessoa implements Serializable {
	String cpf;
	String nome;

	public Pessoa(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public Pessoa() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Pessoa{" + "cpf='" + cpf + '\'' + ", nome='" + nome + '\'' + '}';
	}
}
