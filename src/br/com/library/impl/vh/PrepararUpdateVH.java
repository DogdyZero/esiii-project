package br.com.library.impl.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.Bairro;
import br.com.library.domain.Cidade;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Estado;
import br.com.library.domain.Pais;
import br.com.library.domain.Result;
import br.com.library.domain.TipoDaResidencia;
import br.com.library.dto.EnderecoDTO;
import br.com.library.web.control.IViewHelper;

public class PrepararUpdateVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("OPERACAO");
		int id = Integer.parseInt(request.getParameter("id"));
		if(operacao.equals("CONSULTAR")) {
			if(request.getParameter("tipo").equals("endereco")) {
				Endereco  endereco = new Endereco();
				endereco.setId(id);
				endereco.setTipoConsulta("id_endereco");
				return endereco;
			}
			
		}	
				
			
		if(operacao.equals("ALTERAR")) {
			Endereco endereco = new Endereco();
			Pais pais = new Pais();
			Estado estado = new Estado();
			Cidade cidade = new Cidade();
			Bairro bairro = new Bairro();
			TipoDaResidencia tipo = new TipoDaResidencia();
			
			pais.setNomePais(request.getParameter("pais"));
			
			estado.setNomeEstado(request.getParameter("estado"));
			estado.setPais(pais);
			
			cidade.setNomeCidade(request.getParameter("cidade"));
			cidade.setEstado(estado);
			
			bairro.setNomeBairro(request.getParameter("bairro"));
			bairro.setCidade(cidade);
			
			tipo.setTipo(request.getParameter("tipo"));
			
			endereco.setBairro(bairro);
			endereco.setTipoDaResidencia(tipo);
			
			endereco.setCep(request.getParameter("cep"));

			endereco.setNumeroResidencia(Integer.parseInt(request.getParameter("numero")));
			endereco.setLogradouro(request.getParameter("logradouro"));
			
			
			EnderecoDTO enderecoDTO = new EnderecoDTO(endereco,id);
			
			return enderecoDTO;
			
		}
		
		return null;
	}

	@Override
	public void setView(Result resultado, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("OPERACAO").equals("ALTERAR")) {
			response.sendRedirect(request.getContextPath() + "/sucesso.html");
			return;
		}
		if(resultado.getMsg()== null) {
			if(resultado.getEntidades().size()==1) {
				EntidadeDominio entidade =  resultado.getEntidades().get(0);
				request.setAttribute("resultado", entidade );
			}
			try {
				request.getRequestDispatcher("/atualizar.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}


		}
	}
}

