package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;


public class ValidarNome implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		String nome = cliente.getNome();
		
		Pattern pattern = Pattern.compile("(\\w+\\s\\w+){1,8}\\D");
		Matcher matcher = pattern.matcher(nome);
		
		if(!matcher.matches())
			return "Nome inválido\n";	
		return null;
	}

}
