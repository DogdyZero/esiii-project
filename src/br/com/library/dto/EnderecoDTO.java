package br.com.library.dto;


import java.util.ArrayList;
import java.util.List;

import br.com.library.domain.Endereco;
import br.com.library.domain.EntidadeDominio;
import br.com.library.impl.persistence.dao.Coluna;
import br.com.library.impl.persistence.dao.Tabela;

@Tabela ("endereco")
public class EnderecoDTO extends EntidadeDominio{
	@Coluna("logradouro")
	private String logradouro;
	@Coluna("numero")
	private int numeroResidencia;
	@Coluna("cep")
	private String cep;
	@Coluna("bairro")
	private String bairro;
	@Coluna("tipo_logradouro")
	private String tipoDaResidencia;
	@Coluna("cidade")
	private String cidade;
	@Coluna("estado")
	private String estado;
	@Coluna("pais")
	private String pais;
	@Coluna("id_cliente")
	private int id;
	@Coluna("id_endereco")
	private int idEndereco;
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Endereco endereco, int id) {
		this.logradouro = endereco.getLogradouro();
		this.numeroResidencia = endereco.getNumeroResidencia();
		this.cep = endereco.getCep();
		this.bairro = endereco.getBairro().getNomeBairro();
		this.tipoDaResidencia = endereco.getTipoDaResidencia().getTipo();
		this.cidade = endereco.getBairro().getCidade().getNomeCidade();
		this.estado = endereco.getBairro().getCidade().getEstado().getNomeEstado();
		this.pais = endereco.getBairro().getCidade().getEstado().getPais().getNomePais();
		this.idEndereco = id;
	}


	public List<EnderecoDTO> listaRetorno (List<Endereco> listaEndereco, int id) {
		List<EnderecoDTO> listaRetorno = new ArrayList<EnderecoDTO>();		
		for (Endereco enderecos: listaEndereco) {
			EnderecoDTO end = new EnderecoDTO();
			end.logradouro = enderecos.getLogradouro();
			end.numeroResidencia = enderecos.getNumeroResidencia();
			end.cep = enderecos.getCep();
			end.bairro = enderecos.getBairro().getNomeBairro();
			end.tipoDaResidencia = enderecos.getTipoDaResidencia().getTipo();
			end.cidade = enderecos.getBairro().getCidade().getNomeCidade();
			end.estado = enderecos.getBairro().getCidade().getEstado().getNomeEstado();
			end.pais = enderecos.getBairro().getCidade().getEstado().getPais().getNomePais();
			end.id = id;
			listaRetorno.add(end);
		}
		return listaRetorno;	
	}
	
}
