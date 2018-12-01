package br.com.library.impl.strategy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.EnderecoDTO;


public class ValidarCep implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		String cep = null;
		
		if(nmClasse.equals(Cliente.class.getName())) {
			Cliente cliente = (Cliente) entidade;
			List<Endereco> listaEndereco = cliente.getEndereco();
			for (Endereco enderecos:listaEndereco){
				cep = enderecos.getCep();
	//			String cep = cliente.getEndereco().getCep().replaceAll("[^0-9]", "");
	//			Pattern pattern = Pattern.compile("\\d{5}-\\d{3}"); // com traço obrigatório(00000-000)

			}
		} else if (nmClasse.equals(Endereco.class.getName())||
				nmClasse.equals(EnderecoDTO.class.getName())) {
			if(nmClasse.equals(Endereco.class.getName())){
				Endereco endereco = (Endereco) entidade;
				cep = endereco.getCep();
			} else {
				EnderecoDTO endereco = (EnderecoDTO) entidade;
				cep = endereco.getCep();
			}
		}
		Pattern pattern = Pattern.compile("\\d{5}-?\\d{3}");// com traço opcional(00000-000)
		Matcher matcher = pattern.matcher(cep);

		if (!matcher.matches()) {
			return "Por favor, informe um cep válido";
		}
		
		
		
		return null;
	}

}
