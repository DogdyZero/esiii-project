package br.com.library.impl.command;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;

public interface ICommand {
	Result executar(EntidadeDominio entidade);
}
