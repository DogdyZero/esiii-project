package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.ClienteDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;
import br.com.library.impl.persistence.dao.Tabela;

public class EnderecoDAO extends AbstractDAO{

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

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
