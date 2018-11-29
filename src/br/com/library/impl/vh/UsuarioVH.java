package br.com.library.impl.vh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.domain.Usuario;
import br.com.library.web.control.IViewHelper;

public class UsuarioVH implements IViewHelper {
	List<Cliente> listaClientes;
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		
		usuario.setNomeUsuario(request.getParameter("txtLogin"));
		usuario.setSenha(request.getParameter("txtSenha"));
		
		usuario.setTipoConsulta("login");
		return usuario;
	}

	@Override
	public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		if (listaClientes == null) {
			listaClientes = new ArrayList<Cliente>();
		}
		if(resultado.getMsg() == null) {
			EntidadeDominio entidade =  resultado.getEntidades().get(0);
			request.setAttribute("idCliente", entidade );
			try {
				request.getRequestDispatcher("/consulta.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			
			//response.sendRedirect(request.getContextPath() + "/consulta.jsp");
			//out.println("<h1>seja bem vindo</h1>");
		} else {
			out.println("<h1>"+ resultado.getMsg() + "</h1>");

		}
	}

}
