package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.ClienteDTO;


public class ValidarTelefone implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		String nmClasse = entidade.getClass().getName();
		if (nmClasse.equals(Cliente.class.getName()))
			return verificarTelefoneCliente(entidade);
		else 
			return verificarTelefoneClienteDTO(entidade);
	}

	private String verificarTelefoneCliente(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		String telefone = cliente.getTelefone().getTelefone().replaceAll("\\s", "");
		Pattern pattern = Pattern.compile("\\(?\\d{2}\\)?\\d{4}-?\\d{4}");
		Matcher matcher = pattern.matcher(telefone);

		if (!matcher.matches()) {
			return "Por favor, informe um número de telefone válido\n";
		}
		return null;
	}

	private String verificarTelefoneClienteDTO(EntidadeDominio entidade) {
		ClienteDTO cliente = (ClienteDTO) entidade;

		String telefone = cliente.getTelefone().replaceAll("\\s", "");
		Pattern pattern = Pattern.compile("\\(?\\d{2}\\)?\\d{4}-?\\d{4}");
		Matcher matcher = pattern.matcher(telefone);

		if (!matcher.matches()) {
			return "Por favor, informe um número de telefone válido\n";
		}
		return null;
	}
}
