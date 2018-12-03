package br.com.library.impl.facede;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.library.dao.CartaoCreditoDAO;
import br.com.library.dao.ClienteDAO;
import br.com.library.dao.EnderecoDAO;
import br.com.library.dao.UsuarioDAO;
import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.domain.Usuario;
import br.com.library.dto.CartaoDTO;
import br.com.library.dto.ClienteDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.dto.UsuarioDTO;
import br.com.library.impl.persistence.dao.IDAO;
import br.com.library.impl.strategy.IStrategy;
import br.com.library.impl.strategy.ValidarCep;
import br.com.library.impl.strategy.ValidarCliente;
import br.com.library.impl.strategy.ValidarCodigoSeguranca;
import br.com.library.impl.strategy.ValidarCpf;
import br.com.library.impl.strategy.ValidarDadosCliente;
import br.com.library.impl.strategy.ValidarDataNascimento;
import br.com.library.impl.strategy.ValidarEmail;
import br.com.library.impl.strategy.ValidarEndereco;
import br.com.library.impl.strategy.ValidarNome;
import br.com.library.impl.strategy.ValidarNumeroCartao;
import br.com.library.impl.strategy.ValidarNumeroResidencia;
import br.com.library.impl.strategy.ValidarSenha;
import br.com.library.impl.strategy.ValidarTelefone;


public class Facede implements IFacede{
	private Map<String,Map<String,List<IStrategy>>> rns;
	private Map<String,IDAO> daos;
	private Result resultado;
	StringBuilder sb;
	public Facede(){
		rns = new HashMap<String,Map<String,List<IStrategy>>>();
		daos = new HashMap<String,IDAO>();
		sb = new StringBuilder();
		
		/*
		 * Strategies
		 */
		ValidarCliente vlForm = new ValidarCliente();
		ValidarDadosCliente vCliente = new ValidarDadosCliente();
		ValidarCpf vCpf = new ValidarCpf();
		ValidarEmail vEmail = new ValidarEmail();
		ValidarSenha vSenha = new ValidarSenha();
		ValidarTelefone vTelefone = new ValidarTelefone();
		ValidarEndereco vEndereco = new ValidarEndereco();
		ValidarCep vCep = new ValidarCep();
		ValidarCodigoSeguranca vCodigoSeguranca = new ValidarCodigoSeguranca();
		ValidarNumeroCartao vNumeroCartao = new ValidarNumeroCartao();
		ValidarDataNascimento vDataNas = new ValidarDataNascimento();
		ValidarNumeroResidencia vNumRes = new ValidarNumeroResidencia();
		ValidarNome vNome = new ValidarNome();
		
		
		/* 
		 * criar e adicionar uma lista de Strategy para Salvar
		 */
		List<IStrategy> rnSalvarCliente = new ArrayList<IStrategy>();
		
		rnSalvarCliente.add(vlForm);
		rnSalvarCliente.add(vCliente);
		rnSalvarCliente.add(vCpf);
		rnSalvarCliente.add(vEmail);
		rnSalvarCliente.add(vSenha);
		rnSalvarCliente.add(vTelefone);
		rnSalvarCliente.add(vEndereco);
		rnSalvarCliente.add(vCep);
		rnSalvarCliente.add(vNumeroCartao);
		rnSalvarCliente.add(vCodigoSeguranca);
		rnSalvarCliente.add(vDataNas);
		rnSalvarCliente.add(vNumRes);
		rnSalvarCliente.add(vNome);
		
		Map<String,List<IStrategy>> rnsCliente = new HashMap<String,List<IStrategy>>();
		rnsCliente.put("SALVAR", rnSalvarCliente);
		
		
		/*
		 * Regras de negócios para Consulta
		 */
		
		List<IStrategy> rnConsultarUsuario = new ArrayList<IStrategy>();
				
		Map<String,List<IStrategy>> rnsUsuario = new HashMap<String,List<IStrategy>>();
		rnsUsuario.put("CONSULTAR",rnConsultarUsuario);
		
		
		List<IStrategy> rnConsultarEndereco = new ArrayList<IStrategy>();
		Map<String,List<IStrategy>> rnsEndereco = new HashMap<String,List<IStrategy>>();
		rnsEndereco.put("CONSULTAR",rnConsultarEndereco);
		
		List<IStrategy> rnConsultarCartao = new ArrayList<IStrategy>();
		Map<String,List<IStrategy>> rnsCartao = new HashMap<String,List<IStrategy>>();
		rnsCartao.put("CONSULTAR",rnConsultarCartao);
	
		List<IStrategy> rnAlterarCliente = new ArrayList<IStrategy>();
		rnAlterarCliente.add(vlForm);
		rnAlterarCliente.add(vCpf);
		rnAlterarCliente.add(vTelefone);
		rnAlterarCliente.add(vDataNas);
		rnAlterarCliente.add(vNome);
		rnAlterarCliente.add(vEmail);
		rnsCliente.put("ALTERAR",rnAlterarCliente);
		
		List<IStrategy> rnAlterarEndereco = new ArrayList<IStrategy>();
		rnAlterarEndereco.add(vEndereco);
		rnAlterarEndereco.add(vCep);
		rnsEndereco.put("ALTERAR",rnAlterarEndereco);
		
		List<IStrategy> rnAlterarCartao = new ArrayList<IStrategy>();
		rnAlterarCartao.add(vNumeroCartao);
		rnAlterarCartao.add(vCodigoSeguranca);
		rnsCartao.put("ALTERAR",rnAlterarCartao);
		
		List<IStrategy> rnAlterarUsuario = new ArrayList<IStrategy>();
		rnsUsuario.put("ALTERAR",rnAlterarUsuario);
				
		rns.put(Cliente.class.getName(),rnsCliente);
		rns.put(ClienteDTO.class.getName(), rnsCliente);
		rns.put(UsuarioDTO.class.getName(), rnsUsuario);
		rns.put(Usuario.class.getName(),rnsUsuario);
		rns.put(Endereco.class.getName(),rnsEndereco);
		rns.put(EnderecoDTO.class.getName(),rnsEndereco);
		rns.put(CartaoCredito.class.getName(), rnsCartao);
		rns.put(CartaoDTO.class.getName(),rnsCartao);
		
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(ClienteDTO.class.getName(), new ClienteDAO());
		daos.put(Usuario.class.getName(), new UsuarioDAO());
		daos.put(UsuarioDTO.class.getName(), new UsuarioDAO());
		daos.put(Endereco.class.getName(), new EnderecoDAO());
		daos.put(EnderecoDTO.class.getName(), new EnderecoDAO());
		daos.put(CartaoCredito.class.getName(), new CartaoCreditoDAO());
		daos.put(CartaoDTO.class.getName(), new CartaoCreditoDAO());
		
	}

	@Override
	public Result salvar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Result();
		String classe = entidade.getClass().getName();
		
		Map<String,List<IStrategy>> rn = rns.get(classe);
		
		List<IStrategy> regras = rn.get("SALVAR");
		if(regras!=null) {
			for (IStrategy strategies :regras) {
				String msg= strategies.processar(entidade);
				if (msg!=null) {
					sb.append(msg + "\n");
				}
			}
		}

		if (sb.length() !=0) {
			resultado.setMsg(sb.toString());
		} else {
			daos.get(classe).salvar(entidade);

		}
		resultado.addEntidades(entidade);

		return resultado;
	}

	@Override
	public Result alterar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Result();
		String classe = entidade.getClass().getName();
		
		Map<String,List<IStrategy>> rn = rns.get(classe);
		
				
		if(rn.get("ALTERAR")!=null) {
			List<IStrategy> regras = rn.get("ALTERAR");
				
			for (IStrategy strategies :regras) {
				String msg= strategies.processar(entidade);
				if (msg!=null) {
					sb.append(msg);
				}
			}
		}	
		
		
		if (sb.length() !=0) {
			resultado.setMsg(sb.toString());
		} else {
			daos.get(classe).alterar(entidade);

		}
		resultado.addEntidades(entidade);

		return resultado;
	}

	@Override
	public Result consultar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Result();
		String classe = entidade.getClass().getName();
		
		Map<String,List<IStrategy>> rn = rns.get(classe);
		
		
		if(rn.get("CONSULTAR")!=null) {
			List<IStrategy> regras = rn.get("CONSULTAR");
				
			for (IStrategy strategies :regras) {
				String msg= strategies.processar(entidade);
				if (msg!=null) {
					sb.append(msg);
				}
			}
		}	
		
		
		if (sb.length() !=0) {
			resultado.setMsg(sb.toString());
		} else {
			List<EntidadeDominio> lista= daos.get(classe).consultar(entidade);
			if (lista == null) {
				resultado.setMsg("Sem retorno");
			} else {
				for (int i = 0 ; i<lista.size();i++) {
					resultado.addEntidades(lista.get(i));
				}
				
			}

		}
		
		return resultado;
	}

	@Override
	public Result apagar(EntidadeDominio entidade) {
		resultado = new Result();
		String classe = entidade.getClass().getName();

		if (sb !=null) {
			resultado.setMsg(sb.toString());
		} else {
			daos.get(classe).apagar(entidade);

		}
		resultado.addEntidades(entidade);
		return resultado;
	}
	
}
