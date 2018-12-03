package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.ClienteDTO;


public class ValidarEmail implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		
		if (nmClasse.equals(Cliente.class.getName()))
			return verificarEmailCliente(entidade);
		else 
			return verificarEmailClienteDTO(entidade);
		

	}
	private String verificarEmailCliente(EntidadeDominio entidade) {
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
	private String verificarEmailClienteDTO(EntidadeDominio entidade) {
		ClienteDTO cliente= (ClienteDTO) entidade;
		String email = cliente.getEmail();
		
//		Pattern pattern = Pattern.compile("\\w+");
		Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w{2,3}|\\w+@\\w+\\.\\w{2,3}\\.\\w{2,2}");
		Matcher matcher = pattern.matcher(email); // arrumar para nome do getNomeEmail

		if (!matcher.matches()) {
			return "Por favor, informe um e-mail válido/n";
		}	
		return null;
	}


	

}
