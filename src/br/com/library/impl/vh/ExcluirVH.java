package br.com.library.impl.vh;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.domain.Usuario;
import br.com.library.web.control.IViewHelper;

public class ExcluirVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("OPERACAO");
		int id = Integer.parseInt(request.getParameter("id"));
		if(operacao.equals("APAGAR")) {
			if(request.getParameter("tipo").equals("endereco")) {
				Endereco  endereco = new Endereco();
				endereco.setId(id);
				return endereco;
			}else if(request.getParameter("tipo").equals("cliente")) {
				Cliente  cliente = new Cliente();
				cliente.setId(id);
				return cliente;
			} else if (request.getParameter("tipo").equals("cartao")) {
				
				CartaoCredito  cartao = new CartaoCredito();
				cartao.setId(id);
				return cartao;
			} else if(request.getParameter("tipo").equals("usuario")) {
				
				Usuario  usuario = new Usuario();
				usuario.setId(id);
				return usuario;
			}
			
		}	
		return null;
	}

	@Override
	public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/sucesso.html");
	}

}
