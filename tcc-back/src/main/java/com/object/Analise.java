package com.object;

public class Analise {
	
	private int id;	
	private String nomeAnalise;
	private String nomeScript;
	private String fk_departamento;
	
	
	public String getFk_departamento() {
		return fk_departamento;
	}
	public void setFk_departamento(String fk_departamento) {
		this.fk_departamento = fk_departamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeAnalise() {
		return nomeAnalise;
	}
	public void setNomeAnalise(String nomeAnalise) {
		this.nomeAnalise = nomeAnalise;
	}
	public String getNomeScript() {
		return nomeScript;
	}
	public void setNomeScript(String nomeScript) {
		this.nomeScript = nomeScript;
	}
	
	
	

}
