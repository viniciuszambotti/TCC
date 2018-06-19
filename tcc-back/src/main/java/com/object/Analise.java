package com.object;

public class Analise {
	
	private int id;	
	private String nomeAnalise;
	private String nomeScript;
	private String fk_empresa;
	
	
	public String getFk_empresa() {
		return fk_empresa;
	}
	public void setFk_empresa(String fk_empresa) {
		this.fk_empresa = fk_empresa;
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
