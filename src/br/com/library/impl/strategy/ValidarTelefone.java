package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;


public class ValidarTelefone implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		Cliente cliente = (Cliente) entidade;

		String telefone = cliente.getTelefone().getTelefone().replaceAll("\\s", "");
		Pattern pattern = Pattern.compile("\\(?\\d{2}\\)?\\d{4}-?\\d{4}");
		Matcher matcher = pattern.matcher(telefone);

		if (!matcher.matches()) {
			return "Por favor, informe um número de telefone válido\n";
		}
		return null;
	}
}
