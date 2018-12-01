package br.com.library.impl.strategy;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;


public class ValidarCpf implements IStrategy {
	
	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente)entidade;
		
		int soma = 0, resultado, i, peso, numero;
		char digitoVerificador10, digitoVerificador11;

		String cpf = cliente.getCpf().replaceAll("[^0-9]", "");

		// verifica se o cpf possui 11 caracteres
		if (cpf.length() != 11) {
			return "Por favor, informe um número de cpf válido";
		} else {
			// verifica se o 10 dígito é válido
			for (i = 0, peso = 10; i < 9; i++, peso--) {
				numero = (int) cpf.charAt(i) - 48;
				soma += (numero * peso);
			}

			resultado = 11 - (soma % 11);

			if (resultado == 10 || resultado == 11)
				digitoVerificador10 = 0;
			else
				digitoVerificador10 = (char) (resultado + 48);

			// verifica se o 11 caracter é válido
			soma = 0;
			for (i = 0, peso = 11; i < 10; i++, peso--) {
				numero = (int) cpf.charAt(i) - 48;
				soma += numero * peso;
			}

			resultado = 11 - (soma % 11);

			if (resultado == 10 || resultado == 11)
				digitoVerificador11 = 0;
			else
				digitoVerificador11 = (char) (resultado + 48);

			// verifica se os dígitos (10 e 11) calculados batem com os informados pelo usuário
			if ((digitoVerificador10 == cpf.charAt(9)) && (digitoVerificador11 == cpf.charAt(10)))
				return null;
			else
				return "Por favor, informe um cpf válido\n";
		}
	}
}
