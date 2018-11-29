package br.com.library.impl.vh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.Bairro;
import br.com.library.domain.BandeiraCartao;
import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cidade;
import br.com.library.domain.Cliente;
import br.com.library.domain.Email;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Estado;
import br.com.library.domain.Genero;
import br.com.library.domain.Pais;
import br.com.library.domain.Result;
import br.com.library.domain.Telefone;
import br.com.library.domain.TipoDaResidencia;
import br.com.library.domain.Usuario;
import br.com.library.impl.convert.Conversion;
import br.com.library.web.control.IViewHelper;

public class ClienteVH implements IViewHelper{
	List<Cliente> listaClientes;
	Cliente cliente;
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		//Cliente cliente = new Cliente();
		//cliente.setNome(request.getParameter("nome"));
		
		String[] pais = request.getParameterValues("pais");
		String[] estado = request.getParameterValues("estado");
		String[] cidade = request.getParameterValues("cidade");
		String[] bairro = request.getParameterValues("bairro");
		String[] tipo_residencia = request.getParameterValues("tipo_residencia");
		String[] logradouro = request.getParameterValues("logradouro");
		String[] nResidencia =  request.getParameterValues("numero_residencia");
		String[] cep = request.getParameterValues("cep");

		String[] bandeira = request.getParameterValues("bandeira");
		String[] nomeCartao = request.getParameterValues("nome_cartao");
		String[] nCartao = request.getParameterValues("numero_cartao");
		String[] codSeguranca = request.getParameterValues("codigo_seguranca");
		
		Usuario usuario = new Usuario();
		Email email = new Email();
		Telefone telefone = new Telefone();
		Genero genero = new Genero();
		
		usuario.setNomeUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		email.setEnderecoEmail(request.getParameter("email"));
		telefone.setTelefone(request.getParameter("telefone"));
		genero.setSexo(request.getParameter("genero"));

		
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		List<CartaoCredito> listaCartoes = new ArrayList<CartaoCredito>();

		for (int i = 0; i<logradouro.length;i++) {
			Pais p = new Pais();
			Estado e = new Estado();
			Cidade c = new Cidade();
			Bairro b = new Bairro();
			TipoDaResidencia tp = new TipoDaResidencia();
			Endereco end = new Endereco();
			
			p.setNomePais(pais[i]);
			e.setPais(p);
			e.setNomeEstado(estado[i]);
			c.setEstado(e);
			c.setNomeCidade(cidade[i]);
			b.setCidade(c);
			b.setNomeBairro(bairro[i]);
			tp.setTipo(tipo_residencia[i]);
			end.setBairro(b);
			end.setTipoDaResidencia(tp);
			end.setLogradouro(logradouro[i]);
			if(!nResidencia[i].equals(""))
				end.setNumeroResidencia(Integer.parseInt(nResidencia[i]));
			end.setCep(cep[i]);
			listaEndereco.add(end);
		}
		
		for (int i = 0; i<nomeCartao.length;i++) {
			BandeiraCartao b = new BandeiraCartao();
			CartaoCredito c = new CartaoCredito();
			
			b.setNomeBandeira(bandeira[i]);
			c.setBandeiraCartao(b);
			c.setNomeNoCartao(nomeCartao[i]);
			c.setNumeroCartao(nCartao[i]);
			if(!codSeguranca[i].equals(""))
				c.setCodigoSeguranca(Integer.parseInt(codSeguranca[i]));
			listaCartoes.add(c);
		}
		
		Cliente cliente = new Cliente();
		cliente.setEndereco(listaEndereco);
		cliente.setCartaoCredito(listaCartoes);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setUsuario(usuario);
		cliente.setGenero(genero);
		
		Date data =Conversion.conversorStringEmData(request.getParameter("dt_nascimento"));
		cliente.setDataNascimento(data);
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(request.getParameter("cpf"));
		
		return cliente;
	}

	@Override
	public void setView(Result resultado, HttpServletRequest request,  HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		if (listaClientes == null) {
			listaClientes = new ArrayList<Cliente>();
		}
		if(resultado.getMsg() == null) {
			
				List<EntidadeDominio> listaEntidades = resultado.getEntidades();
				/*for(EntidadeDominio entidades : listaEntidades) {
						listaClientes.add((Cliente) entidades);
					
				}*/
				
				//Cliente cliente = (Cliente) resultado.getEntidades().get(0);
				response.sendRedirect(request.getContextPath() + "/sucesso.html");

				/*request.setAttribute("lista",listaClientes);
				request.getRequestDispatcher("/cadastro.jsp").forward(request, response);*/
		
		} else {
			out.println("<h1>"+ resultado.getMsg() + "</h1>");

		}

		
	}

}
