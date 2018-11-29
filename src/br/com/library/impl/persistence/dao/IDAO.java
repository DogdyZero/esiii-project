package br.com.library.impl.persistence.dao;

import java.util.List;

import br.com.library.domain.EntidadeDominio;

public interface IDAO {

	String salvar(EntidadeDominio entidade);

	String alterar(EntidadeDominio entidade);

	String apagar(EntidadeDominio entidade);
	
	List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
