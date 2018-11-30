package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Bairro;
import br.com.library.domain.BandeiraCartao;
import br.com.library.domain.CartaoCredito;
import br.com.library.domain.Cidade;
import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Estado;
import br.com.library.domain.Pais;
import br.com.library.domain.TipoDaResidencia;
import br.com.library.dto.CartaoDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;

public class CartaoCreditoDAO extends AbstractDAO {
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		List<EntidadeDominio> listaCartoes = new ArrayList<EntidadeDominio>();
		String sql = tipoConsulta(entidade);
		
		CartaoCredito cartao = (CartaoCredito) entidade;
		int contador =0;
		try {
			conexao = conectarBD();
			
			ResultSet resultado = preparar(cartao,sql);
			
			while(resultado.next()) {
				CartaoCredito cc = new CartaoCredito();
				BandeiraCartao bandeira = new BandeiraCartao();
				
				bandeira.setNomeBandeira(resultado.getString("bandeira"));
				cc.setBandeiraCartao(bandeira);
				
				cc.setNomeNoCartao(resultado.getString("nome_cartao"));
				cc.setNumeroCartao(resultado.getString("numero_cartao"));
				cc.setCodigoSeguranca(resultado.getInt("codigo_seguranca"));
				cc.setId(resultado.getInt("id_cartao_credito"));
				
				listaCartoes.add(cc);
				contador++;
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contador!=0) {
			return listaCartoes;
		}else {
			return null;
		}
		
	}
	private String tipoConsulta(EntidadeDominio entidade) {
		String consultas = entidade.getTipoConsulta();
		if (consultas.equals("id")) {
			return "SELECT * FROM cartao_credito WHERE id_cliente=?";
		} else if(consultas.equals("id_cartao")) {
			return "SELECT * FROM cartao_credito WHERE id_cartao_credito=?";
		}
			return null;	
		
	}
	private ResultSet preparar(CartaoCredito cc, String sql) throws SQLException {
		String consultas = cc.getTipoConsulta();
		PreparedStatement comando = conexao.prepareStatement(sql);
		if(consultas.equals("id")|| consultas.equals("id_cartao")) {
			comando.setInt(1, cc.getId());
		}
		return comando.executeQuery();
	}

	
	@Override
	public String salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		int idCliente = entidade.getId();
		CartaoDTO cartaoDTO = new CartaoDTO();
		
		
		List<CartaoDTO> listaCartoesDTO = cartaoDTO.listaRetorno(cliente.getCartaoCredito(),idCliente);
		
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringInsert(cartaoDTO);
		
		
		try {
			if(!conectarBD().isClosed() || conectarBD() == null) {
				conexao = conectarBD();
			}
			Class<?> classe = Class.forName(cartaoDTO.getClass().getName());
			Field[] f = classe.getDeclaredFields();
						
			for(CartaoDTO cartoes :listaCartoesDTO) {
				PreparedStatement comando = conexao.prepareStatement(sql);

				int i =1;
				for (Field atributos:f) {
					if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
						atributos.setAccessible(true);
						comando.setObject(i, atributos.get(cartoes));
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
