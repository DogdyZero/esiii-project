package br.com.library.impl.command;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;

public class ApagarCommand extends AbstractIcommand{

	@Override
	public Result executar(EntidadeDominio entidade) {
		return fachada.apagar(entidade);
	}

}
