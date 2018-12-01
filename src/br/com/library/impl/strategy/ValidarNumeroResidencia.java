package br.com.library.impl.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;

public class ValidarNumeroResidencia implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
		List<Endereco> listaEndereco = cliente.getEndereco();
		for (Endereco enderecos:listaEndereco){
			String num = String.valueOf(enderecos.getNumeroResidencia());
			String numeroResidencia = num.replaceAll("\\.", "");
			
			Pattern pattern = Pattern.compile("\\d{1,6}");
			Matcher matcher = pattern.matcher(numeroResidencia);
			
			if(!matcher.matches())
				return "Número da residência inválido\n";	

		}

		return null;
	}

}
