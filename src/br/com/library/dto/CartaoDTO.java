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
	
}
