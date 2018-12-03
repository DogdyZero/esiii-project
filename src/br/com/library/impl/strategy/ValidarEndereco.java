package br.com.library.impl.strategy;

import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.EnderecoDTO;


public class ValidarEndereco extends AbstractValidador {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		
		if (nmClasse.equals(Endereco.class.getName()) || 
				nmClasse.equals(EnderecoDTO.class.getName())) {
			if(nmClasse.equals(Endereco.class.getName())) {
				Endereco endereco = (Endereco) entidade;
				if(endereco.getBairro().getCidade().getNomeCidade().trim().equals("")){
					sb.append("\nOs campos de endere�o n�o foram informados!");
				}
				
				if(endereco.getLogradouro().trim().equals("")){
					sb.append("\nLogradouro inv�lido!");
				}
				
				if(endereco.getCep().trim().equals("")){
					sb.append("\nCEP inv�lido!");
				}	

			} else {
				EnderecoDTO endereco = (EnderecoDTO) entidade;
				if(endereco.getBairro().trim().equals("")){
					sb.append("\nOs campos de endere�o n�o foram informados!");
				}
				
				if(endereco.getLogradouro().trim().equals("")){
					sb.append("\nLogradouro inv�lido!");
				}
				
				if(endereco.getCep().trim().equals("")){
					sb.append("\nCEP inv�lido!");
				}	
			}
			
	
		} else if (nmClasse.equals(Cliente.class.getName())) {
			Cliente cliente = (Cliente) entidade;
			List<Endereco> listaEndereco = cliente.getEndereco();
			for (Endereco enderecos:listaEndereco){
				if(enderecos.getBairro().getCidade().getNomeCidade().trim().equals("")){
					sb.append("\nOs campos de endere�o n�o foram informados!");
				}
				
				if(enderecos.getLogradouro().trim().equals("")){
					sb.append("\nLogradouro inv�lido!");
				}
				
				if(enderecos.getCep().trim().equals("")){
					sb.append("\nCEP inv�lido!");
				}	
				
			}
		}
		
		return verificaMsg();
	}

}
