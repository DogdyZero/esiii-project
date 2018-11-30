package br.com.library.impl.vh;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.dto.CartaoDTO;
import br.com.library.dto.ClienteDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.dto.UsuarioDTO;
import br.com.library.web.control.IViewHelper;

public class ExcluirVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("OPERACAO");
		int id = Integer.parseInt(request.getParameter("id"));
		if(operacao.equals("APAGAR")) {
			if(request.getParameter("tipo").equals("endereco")) {
				EnderecoDTO  endereco = new EnderecoDTO();
				endereco.setId(id);
				return endereco;
			}else if(request.getParameter("tipo").equals("cliente")) {
				ClienteDTO  cliente = new ClienteDTO();
				cliente.setId(id);
				return cliente;
			} else if (request.getParameter("tipo").equals("cartao")) {
				
				CartaoDTO  cartao = new CartaoDTO();
				cartao.setId(id);
				return cartao;
			} else if(request.getParameter("tipo").equals("usuario")) {
				
				UsuarioDTO  usuario = new UsuarioDTO();
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
