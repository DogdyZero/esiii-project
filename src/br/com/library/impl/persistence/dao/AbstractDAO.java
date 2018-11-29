package br.com.library.impl.persistence.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.library.domain.EntidadeDominio;

public abstract class AbstractDAO implements IDAO {
	protected GeradorStringSql objStringSql;
	protected Connection conexao;
	@Override
	public String salvar(EntidadeDominio entidade) {
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringInsert(entidade);
		
		try {
			//if(!conexao.isClosed() || conexao == null) {
				conexao = conectarBD();
			//}
			Class<?> classe = Class.forName(entidade.getClass().getName());
			Field[] f = classe.getDeclaredFields();
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			int i =1;
			for (Field atributos:f) {
				if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
					atributos.setAccessible(true);
					comando.setObject(i, atributos.get(entidade));
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
	
	@Override
	public String alterar(EntidadeDominio entidade) {
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringUpdate(entidade);
		
		try {
			Class<?> classe = Class.forName(entidade.getClass().getName());
			Field[] f = classe.getDeclaredFields();
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			int i =1;
			for (Field atributos:f) {
				if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
					atributos.setAccessible(true);
					comando.setObject(i, atributos.get(entidade));
					i++;
				}
				comando.setObject(i,entidade.getId());
			}		
			comando.execute();
		 
			conexao.close();
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		return null;	
	}
	@Override
	public String apagar(EntidadeDominio entidade) {
		objStringSql = new GeradorStringSql();
		String sql = objStringSql.stringDelete(entidade);
		try {
		
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setObject(1, entidade.getId());
			comando.execute();
		 
			conexao.close();
		} catch ( SQLException | IllegalArgumentException  e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public static Connection conectarBD() {
		
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/Supermercado";
			Connection conexao = DriverManager.getConnection(url, "postgres", "Senha123");	
			return conexao;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return null;
		
	}
}
