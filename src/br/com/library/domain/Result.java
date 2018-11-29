package br.com.library.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private String msg;
	private List<EntidadeDominio> entidades;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	public void addEntidades(EntidadeDominio entidade) {
		if (entidades == null) {
			entidades = new ArrayList<EntidadeDominio>();
		} 
			entidades.add(entidade);
		
			
	}
	
}
