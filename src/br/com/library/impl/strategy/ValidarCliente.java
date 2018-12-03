package br.com.library.impl.strategy;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.ClienteDTO;

public class ValidarCliente implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder sb = new StringBuilder();

		if(nmClasse.equals(Cliente.class.getName())) {
			Cliente cliente =(Cliente) entidade;	
			if(cliente.getNome().equals("")) {
				sb.append("Campo vazio ");
			}
			if(cliente.getNome().length()<5){
				sb.append("o nome digitado não é valido ");
			}
		} else {
			ClienteDTO cliente =(ClienteDTO) entidade;
			if(cliente.getNome().equals("")) {
				sb.append("Campo vazio ");
			}
			if(cliente.getNome().length()<5){
				sb.append("o nome digitado não é valido ");
			}
			
		}

		if (sb.length()!=0)
			return sb.toString();

		return null;
	}

}
