package br.com.library.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Usuario;
import br.com.library.dto.UsuarioDTO;
import br.com.library.impl.persistence.dao.AbstractDAO;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.GeradorStringSql;

public class UsuarioDAO extends AbstractDAO {
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		List<EntidadeDominio> listaUsuarios = new ArrayList<EntidadeDominio>();
		String sql = tipoConsulta(entidade);
		
		Usuario user = (Usuario) entidade;
		int contador =0;
		try {
			conexao = conectarBD();
			
			ResultSet resultado = preparar(user,sql);
			
			while(resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setNomeUsuario(resultado.getString("login_usuario"));
				usuario.setSenha(resultado.getString("senha_usuario"));
				usuario.setId(Integer.parseInt(resultado.getString("id_cliente")));
				listaUsuarios.add(usuario);
				contador++;
			}
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contador!=0) {
			return listaUsuarios;
		}else {
			return null;
		}
		
	}
	private String tipoConsulta(EntidadeDominio entidade) {
		String consultas = entidade.getTipoConsulta();
		if(consultas.equals("login")) {
			return "SELECT * FROM usuario WHERE login_usuario =? and senha_usuario=?";
		} else if (consultas.equals("id")) {
			return "SELECT * FROM usuario WHERE id_cliente=?";
		} 
			return null;	
		
	}
	private ResultSet preparar(Usuario user, String sql) throws SQLException {
		String consultas = user.getTipoConsulta();
		PreparedStatement comando = conexao.prepareStatement(sql);
		if(consultas.equals("login")) {
			comando.setString(1, user.getNomeUsuario());
			comando.setString(2, user.getSenha());
			
			
		} else if(consultas.equals("id")) {
			comando.setInt(1, user.getId());
		}
		return comando.executeQuery();
	}
	
	@Override
	public String salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		int idCliente = entidade.getId();
		UsuarioDTO usuarioDTO = new UsuarioDTO(cliente,idCliente);

		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringInsert(usuarioDTO);
		
		
		try {
			if(!conectarBD().isClosed() || conectarBD() == null) {
				conexao = conectarBD();
			}
			Class<?> classe = Class.forName(usuarioDTO.getClass().getName());
			Field[] f = classe.getDeclaredFields();
						
			
			PreparedStatement comando = conexao.prepareStatement(sql);

			int i =1;
			for (Field atributos:f) {
				if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
					atributos.setAccessible(true);
					comando.setObject(i, atributos.get(usuarioDTO));
					i++;
				}	
			}
							
			comando.execute();
	
			
			conexao.close();
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		return null;
			
	}



}
