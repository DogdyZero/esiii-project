package br.com.library.domain;

import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.Tabela;
@Tabela("endereco")

public class Endereco extends EntidadeDominio {
	@Coluna("logradouro")
	private String logradouro;
	@Coluna("numero")
	private int numeroResidencia;
	@Coluna("cep")
	private String cep;
	@Coluna("bairro")
	private Bairro bairro;
	@Coluna("tipo_logradouro")
	private TipoDaResidencia tipoDaResidencia;
	
	
	public TipoDaResidencia getTipoDaResidencia() {
		return tipoDaResidencia;
	}
	public void setTipoDaResidencia(TipoDaResidencia tipoDaResidencia) {
		this.tipoDaResidencia = tipoDaResidencia;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumeroResidencia() {
		return numeroResidencia;
	}
	public void setNumeroResidencia(int numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
}
