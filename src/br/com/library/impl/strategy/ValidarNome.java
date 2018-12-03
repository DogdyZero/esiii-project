package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.ClienteDTO;


public class ValidarNome implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		
		if (nmClasse.equals(Cliente.class.getName()))
			return verificarNomeCliente(entidade);
		else 
			return verificarNomeClienteDTO(entidade);
	}

	private String verificarNomeClienteDTO(EntidadeDominio entidade) {
		ClienteDTO cliente = (ClienteDTO) entidade;
		String nome = cliente.getNome();
		
		Pattern pattern = Pattern.compile("(\\w+\\s\\w+){1,8}\\D");
		Matcher matcher = pattern.matcher(nome);
		
		if(!matcher.matches())
			return "Nome inválido\n";	
		return null;
	}

	private String verificarNomeCliente(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		String nome = cliente.getNome();
		
		Pattern pattern = Pattern.compile("(\\w+\\s\\w+){1,8}\\D");
		Matcher matcher = pattern.matcher(nome);
		
		if(!matcher.matches())
			return "Nome inválido\n";	
		return null;
	}

}
