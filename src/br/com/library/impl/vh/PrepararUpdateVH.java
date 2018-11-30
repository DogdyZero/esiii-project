package br.com.library.impl.vh;

import java.io.IOException;
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
import br.com.library.dto.CartaoDTO;
import br.com.library.dto.ClienteDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.dto.UsuarioDTO;
import br.com.library.impl.convert.Conversion;
import br.com.library.web.control.IViewHelper;
	
public class PrepararUpdateVH implements IViewHelper {
	private String tipo;
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("OPERACAO");
		int id = Integer.parseInt(request.getParameter("id"));
		if(operacao.equals("CONSULTAR")) {
			if(request.getParameter("tipo").equals("endereco")) {
				tipo = "endereco";
				Endereco  endereco = new Endereco();
				endereco.setId(id);
				endereco.setTipoConsulta("id_endereco");
				return endereco;
			}else if(request.getParameter("tipo").equals("cliente")) {
				tipo = "cliente";
				Cliente  cliente = new Cliente();
				cliente.setId(id);
				cliente.setTipoConsulta("id_cliente");
				return cliente;
			} else if (request.getParameter("tipo").equals("cartao")) {
				tipo = "cartao";
				CartaoCredito  cartao = new CartaoCredito();
				cartao.setId(id);
				cartao.setTipoConsulta("id_cartao");
				return cartao;
			} else if(request.getParameter("tipo").equals("usuario")) {
				tipo = "usuario";
				Usuario  usuario = new Usuario();
				usuario.setId(id);
				usuario.setTipoConsulta("id_usuario");
				return usuario;
			}
			
		}	
				
			
		if(operacao.equals("ALTERAR")) {
			if(tipo.equals("cliente")) {
				Email email = new Email();
				Telefone telefone = new Telefone();
				Genero genero = new Genero();
				Cliente cliente = new Cliente();
				
				email.setEnderecoEmail(request.getParameter("email"));
				telefone.setTelefone(request.getParameter("telefone"));
				genero.setSexo(request.getParameter("genero"));
				
				cliente.setEmail(email);
				cliente.setTelefone(telefone);
				cliente.setGenero(genero);
				
				Date data =Conversion.conversorStringEmData(request.getParameter("dt_nascimento"));
				cliente.setDataNascimento(data);
				cliente.setNome(request.getParameter("nome"));
				cliente.setCpf(request.getParameter("cpf"));
				ClienteDTO clienteDto = new ClienteDTO(cliente,id);
				return clienteDto;
				
			} else if (tipo.equals("cartao")) {
				CartaoCredito cartao = new CartaoCredito();
				BandeiraCartao bandeira = new BandeiraCartao();
				bandeira.setNomeBandeira(request.getParameter("bandeira"));
				cartao.setBandeiraCartao(bandeira);
				cartao.setCodigoSeguranca(Integer.parseInt(request.getParameter("codigo_seguranca")));
				cartao.setNomeNoCartao(request.getParameter("nome_cartao"));
				cartao.setNumeroCartao(request.getParameter("numero_cartao"));
				
				CartaoDTO cartaoDTO = new CartaoDTO(cartao,id);
				return cartaoDTO;
			
			} else if (tipo.equals("usuario")) {
				Usuario usuario = new Usuario();
				usuario.setNomeUsuario(request.getParameter("usuario"));
				usuario.setSenha(request.getParameter("senha"));
				
				UsuarioDTO usuarioDto = new UsuarioDTO(usuario, id);
				return usuarioDto;
			}
					
			
			else if (tipo.equals("endereco")) {
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
				if(tipo.equals("endereco")) {
					EntidadeDominio entidade =  resultado.getEntidades().get(0);
					request.setAttribute("resultadoEndereco", entidade );
				} else if(tipo.equals("cliente")) {
					EntidadeDominio entidade =  resultado.getEntidades().get(0);
					request.setAttribute("resultadoCliente", entidade );
				} else if (tipo.equals("usuario")) {
					EntidadeDominio entidade =  resultado.getEntidades().get(0);
					request.setAttribute("resultadoUsuario", entidade );
				} else if (tipo.equals("cartao")) {
					EntidadeDominio entidade =  resultado.getEntidades().get(0);
					request.setAttribute("resultadoCartao", entidade );
				}
				try {
					request.getRequestDispatcher("/atualizar.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
			


		}
	}
}

