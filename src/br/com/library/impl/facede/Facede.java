package br.com.library.impl.facede;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.library.dao.ClienteDAO;
import br.com.library.dao.UsuarioDAO;
import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.domain.Usuario;
import br.com.library.impl.persistence.dao.IDAO;
import br.com.library.impl.strategy.IStrategy;
import br.com.library.impl.strategy.ValidarCliente;


public class Facede implements IFacede{
	private Map<String,Map<String,List<IStrategy>>> rns;
	private Map<String,IDAO> daos;
	private Result resultado;
	public Facede(){
		rns = new HashMap<String,Map<String,List<IStrategy>>>();
		daos = new HashMap<String,IDAO>();
		
		/*
		 * Strategy
		 */
		ValidarCliente vlForm = new ValidarCliente();
		
		
		/* 
		 * criar e adicionar uma lista de Strategy para Salvar
		 */
		List<IStrategy> rnSalvarCliente = new ArrayList<IStrategy>();
		
		rnSalvarCliente.add(vlForm);
		
		Map<String,List<IStrategy>> rnsCliente = new HashMap<String,List<IStrategy>>();
		rnsCliente.put("SALVAR", rnSalvarCliente);
		
		
		
		
		List<IStrategy> rnConsultarUsuario = new ArrayList<IStrategy>();
				
		Map<String,List<IStrategy>> rnsUsuario = new HashMap<String,List<IStrategy>>();
		rnsUsuario.put("CONSULTAR",rnConsultarUsuario);
		
		/*
		 * adicionando ao mapa de regras de negócios
		 */
		rns.put(Cliente.class.getName(),rnsCliente);
		rns.put(Usuario.class.getName(),rnsUsuario);
		
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Usuario.class.getName(), new UsuarioDAO());
	}

	@Override
	public Result salvar(EntidadeDominio entidade) {
		resultado = new Result();
		String classe = entidade.getClass().getName();
		
		Map<String,List<IStrategy>> rn = rns.get(classe);
		
		String msg = null;
		
		List<IStrategy> regras = rn.get("SALVAR");
		if(regras!=null) {
			for (IStrategy strategies :regras) {
				msg= strategies.processar(entidade);
			}
		}

		if (msg !=null) {
			resultado.setMsg(msg);
		} else {
			daos.get(classe).salvar(entidade);

		}
		resultado.addEntidades(entidade);

		return resultado;
	}

	@Override
	public Result alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result consultar(EntidadeDominio entidade) {
		resultado = new Result();
		String classe = entidade.getClass().getName();
		
		Map<String,List<IStrategy>> rn = rns.get(classe);
		
		String msg = null;
		
		if(rn.get("CONSULTAR")!=null) {
			List<IStrategy> regras = rn.get("CONSULTAR");
				
			for (IStrategy strategies :regras) {
				msg= strategies.processar(entidade);
			}
		}	
		
		
		if (msg !=null) {
			resultado.setMsg(msg);
		} else {
			List<EntidadeDominio> lista= daos.get(classe).consultar(entidade);
			if (lista == null) {
				resultado.setMsg("Sem retorno");
			} else {
				resultado.addEntidades(lista.get(0));
			}

		}
		
		
		return resultado;
	}

	@Override
	public Result apagar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
