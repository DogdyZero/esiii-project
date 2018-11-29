package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.dto.CartaoDTO;
import br.com.library.dto.EnderecoDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;

public class CartaoCreditoDAO extends AbstractDAO {
	
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
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
