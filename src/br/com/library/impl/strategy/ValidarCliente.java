package br.com.library.impl.strategy;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;

public class ValidarCliente implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente =(Cliente) entidade;
		StringBuilder sb = new StringBuilder();
		if(cliente.getNome().equals("")) {
			sb.append("Campo vazio ");
		}
		if(cliente.getNome().length()<5){
			sb.append("o nome digitado não é valido ");
		}
		if (sb.length()!=0)
			return sb.toString();
		return null;
	}

}
