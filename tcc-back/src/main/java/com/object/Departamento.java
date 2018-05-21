package com.object;



public class Departamento {

	private int id;	
	private String nome;	
	private int fk_empresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFk_empresa() {
		return fk_empresa;
	}

	public void setFk_empresa(int fk_empresa) {
		this.fk_empresa = fk_empresa;
	}


}
