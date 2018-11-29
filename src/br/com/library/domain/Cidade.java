package br.com.library.domain;

import br.com.library.impl.persistence.dao.Coluna;

public class Cidade extends EntidadeDominio {
	@Coluna("cidade")
	private String nomeCidade;
	@Coluna("estado")
	private Estado estado;
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
