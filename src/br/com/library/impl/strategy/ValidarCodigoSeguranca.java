package br.com.library.impl.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.CartaoDTO;


public class ValidarCodigoSeguranca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		
		if (nmClasse.equals(Cliente.class.getName()))
			return verificarCodigoSegurancaCliente(entidade);
		else 
			return verificarCodigoSegurancaCartaoDTO(entidade);
		
		
	}

	private String verificarCodigoSegurancaCartaoDTO(EntidadeDominio entidade) {
		CartaoDTO cartao = (CartaoDTO)entidade;

			String codigoSeguranca = String.valueOf(cartao.getCodigoCartao());
			
			Pattern pattern = Pattern.compile("\\d{4}|\\d{3}");
			Matcher matcher = pattern.matcher(codigoSeguranca);

			if (!matcher.matches())
				return "Código de segurança inválido\n";
			
		
		return null;
	}

	private String verificarCodigoSegurancaCliente(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		List<CartaoCredito> listaCartoes = cliente.getCartaoCredito();
		for (CartaoCredito cartoes :listaCartoes){
			String codigoSeguranca = String.valueOf(cartoes.getCodigoSegurancao());
			
			Pattern pattern = Pattern.compile("\\d{4}|\\d{3}");
			Matcher matcher = pattern.matcher(codigoSeguranca);

			if (!matcher.matches())
				return "Código de segurança inválido\n";
			
		}

		return null;
	}
}
