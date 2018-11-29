package br.com.library.impl.strategy;

import br.com.library.domain.EntidadeDominio;

public interface IStrategy {
	String processar(EntidadeDominio entidade);
}
