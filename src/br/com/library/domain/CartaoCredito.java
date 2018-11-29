package br.com.library.domain;

public class CartaoCredito extends EntidadeDominio {
	private String nomeNoCartao;
	private int codigoSeguranca;
	private String numeroCartao;
	private BandeiraCartao bandeiraCartao;
	public String getNomeNoCartao() {
		return nomeNoCartao;
	}
	public void setNomeNoCartao(String nomeNoCartao) {
		this.nomeNoCartao = nomeNoCartao;
	}
	public int getCodigoSegurancao() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(int codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}
	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	
}