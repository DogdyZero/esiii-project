package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;


public class ValidarEmail implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente= (Cliente) entidade;
		String email = cliente.getEmail().getEnderecoEmail();
		
//		Pattern pattern = Pattern.compile("\\w+");
		Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w{2,3}|\\w+@\\w+\\.\\w{2,3}\\.\\w{2,2}");
		Matcher matcher = pattern.matcher(email); // arrumar para nome do getNomeEmail

		if (!matcher.matches()) {
			return "Por favor, informe um e-mail válido/n";
		}	
		return null;
	}

}
