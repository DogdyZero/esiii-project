package br.com.library.impl.facede;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;

public interface IFacede {
	Result salvar(EntidadeDominio entidade);
	Result alterar(EntidadeDominio entidade);
	Result consultar(EntidadeDominio entidade);
	Result apagar(EntidadeDominio entidade);
}
