package br.com.library.impl.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.domain.Usuario;
import br.com.library.web.control.IViewHelper;

public class ConsultaVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String tipoConsulta = request.getParameter("tipoconsulta");
		if(tipoConsulta.equals("dados_cadastrais")) {
			Cliente cliente = new Cliente();
			cliente.setId(Integer.parseInt(request.getParameter("id")));
			return cliente;
		} else if (tipoConsulta.equals("cartao_credito")) {
			CartaoCredito cartao = new CartaoCredito();
			cartao.setId(Integer.parseInt(request.getParameter("id")));
			return cartao;
		}else if (tipoConsulta.equals("endereco")) {
			Endereco endereco = new Endereco();
			endereco.setId(Integer.parseInt(request.getParameter("id")));
			return endereco;
		} else if (tipoConsulta.equals("dados_usuario")) {
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(request.getParameter("id")));
			return usuario;
		}
		return null;
	}

	@Override
	public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if(resultado.getMsg()== null) {
			EntidadeDominio entidade =  resultado.getEntidades().get(0);
			request.setAttribute("lista", entidade );
			try {
				request.getRequestDispatcher("/consulta.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	
	}

}
