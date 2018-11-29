package br.com.library.dto;

import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.Tabela;

@Tabela ("usuario")
public class UsuarioDTO extends EntidadeDominio {
	@Coluna ("login_usuario")
	private String nomeUsuario;
	@Coluna ("senha_usuario")
	private String senha;
	@Coluna ("id_cliente")
	private int id;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Cliente cliente, int id) {
		
		this.nomeUsuario = cliente.getUsuario().getNomeUsuario();
		this.senha = cliente.getUsuario().getSenha();
		this.id = id;
	}
	
}
