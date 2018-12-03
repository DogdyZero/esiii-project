package br.com.library.impl.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.CartaoDTO;

public class ValidarNumeroCartao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		
		if (nmClasse.equals(Cliente.class.getName()))
			return verificarNumeroCliente(entidade);
		else 
			return verificarNumeroCartaoDTO(entidade);
		

	}

	private String verificarNumeroCartaoDTO(EntidadeDominio entidade) {
		CartaoDTO cartao = (CartaoDTO) entidade;
			String numeroCartao = cartao.getNumeroCartao();

			Pattern pattern = Pattern.compile("(\\d{4}\\.?){4}");
			Matcher matcher = pattern.matcher(numeroCartao);

			if (!matcher.matches())
				return "Número do cartão de crédito inválido";

		return null;
	}

	private String verificarNumeroCliente(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		List<CartaoCredito> listaCartoes = cliente.getCartaoCredito();
		for (CartaoCredito cartoes :listaCartoes){
			String numeroCartao = cartoes.getNumeroCartao();

			Pattern pattern = Pattern.compile("(\\d{4}\\.?){4}");
			Matcher matcher = pattern.matcher(numeroCartao);

			if (!matcher.matches())
				return "Número do cartão de crédito inválido";
		}


		return null;
	}

}
