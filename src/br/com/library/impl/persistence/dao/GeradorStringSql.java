package br.com.library.impl.persistence.dao;

import java.lang.reflect.Field;

import br.com.library.domain.EntidadeDominio;

public class GeradorStringSql {
	public String stringInsert(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		try {
			Class<?> classe = Class.forName(nomeClasse);
			String nomeTabela =classe.getDeclaredAnnotation(Tabela.class).value();
					
			Field[] f = classe.getDeclaredFields();
			
			StringBuilder sbParte1 = new StringBuilder();
			StringBuilder sbParte2 = new StringBuilder();
			sbParte1.append("INSERT INTO "+ nomeTabela + "(");
			String idTabela = "id_"+nomeTabela;
			for (Field atributos :f) {
				if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
					if(!atributos.getDeclaredAnnotation(Coluna.class).value().equals(idTabela)) {
						String nomeColuna = atributos.getDeclaredAnnotation(Coluna.class).value();
						sbParte1.append(nomeColuna+",") ;	
						sbParte2.append("?,");
					}

				}
				
			}
			sbParte1.delete(sbParte1.toString().length()-1, sbParte1.toString().length());
			sbParte1.append(") VALUES (");
			sbParte2 = sbParte2.delete(sbParte2.toString().length()-1, sbParte2.toString().length());
			sbParte1.append(sbParte2 +")");
			
			
			return sbParte1.toString();
		} catch (ClassNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
				
		}
		return null;
	}
	
	
	public String stringUpdate(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
				
				Class<?> classe;
				try {
					classe = Class.forName(nomeClasse);
					String nomeTabela =classe.getDeclaredAnnotation(Tabela.class).value();
		
					Field[] f = classe.getDeclaredFields();
					
					StringBuilder sbParte1 = new StringBuilder();
					sbParte1.append("UPDATE "+ nomeTabela + " SET ");
					
					for (Field atributos :f) {
						if(atributos.getDeclaredAnnotation(Coluna.class)!=null ) {
							String nomeColuna = atributos.getDeclaredAnnotation(Coluna.class).value();
							if(!nomeColuna.contains("id_")) {
								sbParte1.append(nomeColuna+"=?,") ;	
							}
								
						}
					}
					sbParte1 = sbParte1.delete(sbParte1.toString().length()-1, sbParte1.toString().length());
					
					sbParte1.append(" WHERE id_" + nomeTabela +"=?");
					return sbParte1.toString();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return null;
		
	}
	public String stringDelete(EntidadeDominio entidade ) {
		
		String nomeClasse = entidade.getClass().getName();
		try {
			Class<?> classe = Class.forName(nomeClasse);
			String nomeTabela =classe.getDeclaredAnnotation(Tabela.class).value();
		
			StringBuilder sbParte1 = new StringBuilder();
			sbParte1.append("DELETE FROM "+ nomeTabela + " WHERE id_" + nomeTabela +"=?");

			return sbParte1.toString();
		} catch (ClassNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
				
		}
		return null;
	}
	
}
