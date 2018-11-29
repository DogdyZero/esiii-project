package br.com.library.impl.command;

import br.com.library.impl.facede.Facede;

public abstract class AbstractIcommand implements ICommand{
	protected Facede fachada = new Facede();
}
