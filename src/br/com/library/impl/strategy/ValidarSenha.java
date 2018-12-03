package br.com.library.impl.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;

public class ValidarSenha implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		//[a-zA-Z]+.+{8,}
		/*Cliente cliente = (Cliente) entidade;
		Pattern pattern = Pattern.compile("(([A-Z]{1,}[a-z]{1,}[&%$#@]){1,}){8,23}");
//		Pattern pattern = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
		Matcher matcher = pattern.matcher(cliente.getUsuario().getSenha()); // mudar o nome para evitar redundância

		if (!matcher.matches())
			return "Senha inválida";*/
		return null;
	}

}
