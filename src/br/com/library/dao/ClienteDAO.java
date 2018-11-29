package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.Email;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Genero;
import br.com.library.domain.Telefone;
import br.com.library.dto.ClienteDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;
import br.com.library.impl.persistence.dao.Tabela;

public class ClienteDAO extends AbstractDAO{

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> listaClientes = new ArrayList<EntidadeDominio>();
		String sql = "SELECT * FROM cliente where id_cliente = ?";

		int contador =0;
		try {
			conexao = conectarBD();
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, entidade.getId());
			

			ResultSet resultado = comando.executeQuery();
			
			if(resultado.next()) {
				Cliente cliente = new Cliente();
				Genero genero = new Genero();
				Telefone telefone = new Telefone();
				Email email = new Email();
				cliente.setNome(resultado.getString("nome"));
				cliente.setCpf(resultado.getString("cpf"));
				cliente.setDataNascimento(resultado.getDate("data_nascimento"));
				genero.setSexo(resultado.getString("genero"));
				telefone.setTelefone(resultado.getString("telefone"));
				email.setEnderecoEmail(resultado.getString("email"));
				
				cliente.setGenero(genero);
				cliente.setTelefone(telefone);
				cliente.setEmail(email);
				
				listaClientes.add(cliente);
				contador++;
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contador!=0) {
			return listaClientes;
		}else {
			return null;
		}
		
	}
	@Override
	public String salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringInsert(clienteDTO);
		

		EnderecoDAO enderecoDao = new EnderecoDAO();
		CartaoCreditoDAO cartaoDao = new CartaoCreditoDAO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		try {
			if(!conectarBD().isClosed() || conectarBD() == null) {
				conexao = conectarBD();
			}
			Class<?> classe = Class.forName(clienteDTO.getClass().getName());
			Field[] f = classe.getDeclaredFields();
			String nomeTabela = classe.getDeclaredAnnotation(Tabela.class).value();
			
			PreparedStatement comando = conexao.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			int i =1;
			for (Field atributos:f) {
				if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
					atributos.setAccessible(true);	
					comando.setObject(i, atributos.get(clienteDTO));
					i++;
				}			
			}		
				
			comando.executeUpdate();
			ResultSet rs = comando.getGeneratedKeys();
			if(rs.next()) {
				entidade.setId(rs.getInt("id_"+nomeTabela));
			}	
			enderecoDao.salvar(entidade);
			cartaoDao.salvar(entidade);
			usuarioDao.salvar(entidade);
			
			conexao.close();
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		return null;
		
		
	}

}
