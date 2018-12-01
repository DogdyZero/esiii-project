package br.com.library.impl.strategy;

import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;


public class ValidarDadosCliente extends AbstractValidador {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Cliente cliente = (Cliente) entidade;
			
		if(cliente.getNome().trim().equals("")){
			sb.append("\nNome do cliente inválido!");
		}
		
		if(cliente.getCpf().trim().equals("")){
			sb.append("\nCPF do cliente inválido!");
		}
		
		
		ValidarEndereco vEnd = new ValidarEndereco();
		List<Endereco> listaEnderecos = cliente.getEndereco();
		for(Endereco enderecos :listaEnderecos){
			vEnd.processar(enderecos);
		}
		
		
		return verificaMsg();
	}

}
