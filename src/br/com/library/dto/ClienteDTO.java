package br.com.library.dto;


import br.com.library.domain.Cliente;
import br.com.library.domain.EntidadeDominio;
import br.com.library.impl.persistence.dao.*;

@Tabela("cliente")
public class ClienteDTO extends EntidadeDominio{
	@Coluna("nome")
	private String nome;
	@Coluna("cpf")
	private String cpf;
	@Coluna ("data_nascimento")
	private java.sql.Date dataNascimento;
	@Coluna ("genero")
	private String genero;
	@Coluna ("email")
	private String email;
	@Coluna ("telefone")
	private String telefone;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente cliente) {
		java.sql.Date dataSql = new java.sql.Date(cliente.getDataNascimento().getTime());
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.dataNascimento = dataSql;
		this.genero = cliente.getGenero().getSexo();
		this.email = cliente.getEmail().getEnderecoEmail();
		this.telefone = cliente.getTelefone().getTelefone();
		
	}
	public ClienteDTO(Cliente cliente, int id) {
		java.sql.Date dataSql = new java.sql.Date(cliente.getDataNascimento().getTime());
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.dataNascimento = dataSql;
		this.genero = cliente.getGenero().getSexo();
		this.email = cliente.getEmail().getEnderecoEmail();
		this.telefone = cliente.getTelefone().getTelefone();
		this.id = id;
		
	}
}
