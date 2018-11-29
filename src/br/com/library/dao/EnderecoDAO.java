package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Bairro;
import br.com.library.domain.Cidade;
import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Estado;
import br.com.library.domain.Pais;
import br.com.library.domain.TipoDaResidencia;
import br.com.library.dto.EnderecoDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;

public class EnderecoDAO extends AbstractDAO{
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		List<EntidadeDominio> listaEndereco = new ArrayList<EntidadeDominio>();
		String sql = tipoConsulta(entidade);
		
		Endereco endereco = (Endereco) entidade;
		int contador =0;
		try {
			conexao = conectarBD();
			
			ResultSet resultado = preparar(endereco,sql);
			
			while(resultado.next()) {
				Endereco end = new Endereco();
				TipoDaResidencia tp = new TipoDaResidencia();
				Bairro bairro = new Bairro();
				Cidade cidade = new Cidade();
				Estado estado = new Estado();
				Pais pais = new Pais();
				end.setLogradouro(resultado.getString("logradouro"));
				end.setNumeroResidencia(resultado.getInt("numero"));
				end.setCep(resultado.getString("cep"));
				
				tp.setTipo(resultado.getString("tipo_logradouro"));
				end.setTipoDaResidencia(tp);
				
				pais.setNomePais(resultado.getString("pais"));
				estado.setNomeEstado(resultado.getString("estado"));
				estado.setPais(pais);
				
				cidade.setNomeCidade(resultado.getString("cidade"));
				cidade.setEstado(estado);
				
				bairro.setNomeBairro(resultado.getString("bairro"));
				bairro.setCidade(cidade);
				
				end.setBairro(bairro);
				
				end.setId(Integer.parseInt(resultado.getString("id_endereco")));
				listaEndereco.add(end);
				contador++;
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contador!=0) {
			return listaEndereco;
		}else {
			return null;
		}
		
	}
	private String tipoConsulta(EntidadeDominio entidade) {
		String consultas = entidade.getTipoConsulta();
		if (consultas.equals("id")) {
			return "SELECT * FROM endereco WHERE id_cliente=?";
		} else if(consultas.equals("id_endereco")) {
			return "SELECT * FROM endereco WHERE id_endereco=?";
		}
		
			return null;	
		
	}
	private ResultSet preparar(Endereco end, String sql) throws SQLException {
		String consultas = end.getTipoConsulta();
		PreparedStatement comando = conexao.prepareStatement(sql);
		if(consultas.equals("id") || consultas.equals("id_endereco")) {
			comando.setInt(1, end.getId());
		}
		return comando.executeQuery();
	}
	
	@Override
	public String salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		int idCliente = entidade.getId();
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		
		List<EnderecoDTO> listaEnderecosDTO = enderecoDTO.listaRetorno(cliente.getEndereco(),idCliente);
		
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringInsert(enderecoDTO);
		
		
		try {
			if(!conectarBD().isClosed() || conectarBD() == null) {
				conexao = conectarBD();
			}
			Class<?> classe = Class.forName(enderecoDTO.getClass().getName());
			Field[] f = classe.getDeclaredFields();
						
			for(EnderecoDTO enderecos :listaEnderecosDTO) {
				PreparedStatement comando = conexao.prepareStatement(sql);

				int i =1;
				for (Field atributos:f) {
					if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
						atributos.setAccessible(true);
						comando.setObject(i, atributos.get(enderecos));
						i++;
					}			
				}		
				
			comando.execute();
	
			}
			conexao.close();
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		return null;
	
	}


}
