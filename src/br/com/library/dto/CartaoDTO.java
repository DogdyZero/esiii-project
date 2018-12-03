package br.com.library.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.CartaoCredito;
import br.com.library.domain.EntidadeDominio;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.Tabela;

@Tabela("cartao_credito")
public class CartaoDTO extends EntidadeDominio {
	@Coluna ("nome_cartao")
	private String nomeCartao;
	@Coluna ("numero_cartao")
	private String numeroCartao;
	@Coluna ("bandeira")
	private String bandeiraCartao;
	@Coluna ("codigo_seguranca")
	private int codigoCartao;
	@Coluna ("id_cliente")
	private int idCliente;
	@Coluna ("id_cartao_credito")
	private int idCartao;
	
	public CartaoDTO() {
		
	}
	public CartaoDTO(CartaoCredito cartao, int id) {
		this.nomeCartao = cartao.getNomeNoCartao();
		this.numeroCartao = cartao.getNumeroCartao();
		this.bandeiraCartao = cartao.getBandeiraCartao().getNomeBandeira();
		this.codigoCartao = cartao.getCodigoSegurancao();
		this.idCartao = id;
	}
	
	public List<CartaoDTO> listaRetorno (List<CartaoCredito> listaCartoes, int id){
		List<CartaoDTO> listaRetorno = new ArrayList<CartaoDTO>();
		for (CartaoCredito cartoes : listaCartoes) {
			CartaoDTO cartao = new CartaoDTO();
			cartao.nomeCartao = cartoes.getNomeNoCartao();
			cartao.numeroCartao = cartoes.getNumeroCartao();
			cartao.bandeiraCartao = cartoes.getBandeiraCartao().getNomeBandeira();
			cartao.codigoCartao = cartoes.getCodigoSegurancao();
			cartao.idCliente = id;
			listaRetorno.add(cartao);
		}
		
		return listaRetorno;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getBandeiraCartao() {
		return bandeiraCartao;
	}
	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	public int getCodigoCartao() {
		return codigoCartao;
	}
	public void setCodigoCartao(int codigoCartao) {
		this.codigoCartao = codigoCartao;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdCartao() {
		return idCartao;
	}
	public void setIdCartao(int idCartao) {
		this.idCartao = idCartao;
	}
	
	
	
}
