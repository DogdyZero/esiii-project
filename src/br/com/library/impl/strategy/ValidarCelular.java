package br.com.library.impl.strategy;

import br.com.library.domain.EntidadeDominio;


public class ValidarCelular implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
/*		celular = celular.replaceAll("[^0-9]", "");
		Pattern pattern = Pattern.compile("\\d{11}");
		Matcher matcher = pattern.matcher(celular);

		if (!matcher.matches()) {
			return "Por favor, informe um número de celular válido";
		}
		return null;
	}*/
	return null;
	}

}
