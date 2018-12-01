package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;


public class ValidarDataNascimento implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		/*Cliente cliente = (Cliente) entidade;
		String dataNascimento = cliente.getDataNascimento();
		
		Pattern pattern = Pattern.compile("(\\d{2}/){2}\\d{4}");
		Matcher matcher = pattern.matcher(dataNascimento);
		
		if(!matcher.matches())
			return "Data de nascimento inválido\n";	*/
		return null;
	}

}
