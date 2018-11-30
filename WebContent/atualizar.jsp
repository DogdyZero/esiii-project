<%@page import="br.com.library.domain.Usuario"%>
<%@page import="br.com.library.domain.Cliente"%>
<%@page import="br.com.library.domain.CartaoCredito"%>
<%@page import="br.com.library.domain.Endereco"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Sales - Atualização</title>
	<LINK rel="stylesheet" type="text/css" href="css/style.css"> 
	<link href="css/style-responsive.css" rel="stylesheet" media="screen and (max-width: 480px)" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
</head>
<body>
         <div class="cabecalho ">
		  
            <a href=#><img class="cabecalho-botoes carrinho" src="imagens/cart_icon-01.png"/></a>
			<a href="login.html"><img class="cabecalho-botoes login" src="imagens/login.png"/></a>
          </div>
          
        <div class="wrapper" >

			<div class="row">
				  <div class="navbar">
					  <a href="index.html">Home</a>
					  <div class="dropdown">
						<button class="dropbtn">Livros </button>
						<div class="dropdown-content">
						  <a href="#">Link 1</a>
						  <a href="#">Link 2</a>
						  <a href="#">Link 3</a>
						</div>
					  </div> 
					  <div class="dropdown">
						<button class="dropbtn">E-Book </button>
						<div class="dropdown-content">
						  <a href="#">Link 1</a>
						  <a href="#">Link 2</a>
						  <a href="#">Link 3</a>
						</div>
					  </div> 
					  <div class="dropdown">
						<button class="dropbtn">Sebo </button>
						<div class="dropdown-content">
						  <a href="#">Link 1</a>
						  <a href="#">Link 2</a>
						  <a href="#">Link 3</a>
						</div>
					  </div> 
					  <div class="dropdown">
						<button class="dropbtn">E-Book Móvel </button>
						<div class="dropdown-content">
						  <a href="#">Link 1</a>
						  <a href="#">Link 2</a>
						  <a href="#">Link 3</a>
						</div>
					  </div> 
					</div>
					
		  
			</div>
<% if(request.getAttribute("resultadoEndereco")!=null){ 
	Endereco endereco = (Endereco) request.getAttribute("resultadoEndereco");
	%>
<form method="POST" action="alterarDados">
			<div id="st_node_endereco" class="row ">
				<div class="w-50 content-b" id="nd_node_endereco"  >
				<h4>Endereço</h4>
					<div class=row>
						<input type="text" class="id_coluna" name="id" value="<%=endereco.getId() %>">
						<div class="w-10 ">
							<label>Tipo
								<input name="tipo" class="w-77" id="tipo_residencia" type="text" value="<%=endereco.getTipoDaResidencia().getTipo() %>" />
							</label>	
						</div>		
						<div class="w-75 ">
							<label>Logradouro
								<input name="logradouro" class="w-95" id="logradouro" type="text" value="<%=endereco.getLogradouro() %>"/>
							</label>
						</div>	
						<div class="w-20 ">
							<label>Número
								<input name="numero" class="w-83" id="numero_residencia" type="text"  value="<%=endereco.getNumeroResidencia() %>"/>
							</label>
						</div>	
					</div>	
					<div class=row>
						<label>CEP
							<input name="cep" id="cep" type="text" value="<%=endereco.getCep() %>"/>
						</label>
					</div>
					<div class=row>
						<div class="w-50 ">
							<label>Bairro
								<input name="bairro" class="w-95" id="bairro" type="text" value="<%=endereco.getBairro().getNomeBairro() %>"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Cidade
								<input name="cidade" class="w-95" id="cidade"  type="text" value="<%=endereco.getBairro().getCidade().getNomeCidade() %>"/>
							</label>
						</div>
					</div>	
					<div class=row>
						<div class="w-50 ">
							<label>Estado
								<input name="estado" class="w-95" id="estado"  type="text" value="<%=endereco.getBairro().getCidade().getEstado().getNomeEstado() %>"/>
							</label>
						</div>	
						<div class="w-50 ">
							<label>Pais
								<input name="pais" class="w-95" id="pais" type="text"value="<%=endereco.getBairro().getCidade().getEstado().getPais().getNomePais() %>"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" name="OPERACAO" type="submit" value="ALTERAR" />
							
					</div>
				</div>	
					
			</div>
		</form>
		<% 
		}
%>
<% if(request.getAttribute("resultadoCartao")!=null){ 
	CartaoCredito cartao = (CartaoCredito) request.getAttribute("resultadoCartao");
	%>
	<form method="POST" action="alterarDados">
			<div id="st_node_cartao" class="row  ">
				<div class="w-50 content-b" id="nd_node_cartao"  >
					<h4>Cartão de Crédito</h4>
					<div class=row>
					<input type="text" class="id_coluna" name="id" value="<%=cartao.getId() %>"/>
					
						<div class="w-50 ">
							<label>Bandeira
								<input class="w-95" name="bandeira" type="text"  value="<%=cartao.getBandeiraCartao().getNomeBandeira() %>"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Nome no cartão
								<input class="w-95" name="nome_cartao" type="text" value="<%=cartao.getNomeNoCartao() %>"/>
							</label>
						</div>
						
					</div>
					<div class=row>
						<div class="w-50 ">
							<label>Numeração
								<input class="w-95" name="numero_cartao" type="text" value="<%=cartao.getNumeroCartao() %>"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Código Segurança
								<input class="w-95" name="codigo_seguranca" type="text" value="<%=cartao.getCodigoSegurancao() %>"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" type="submit" name="OPERACAO" value="ALTERAR" />
					</div>
				</div>

			</div>
			</form>
	<% 
		}
%>
<% if(request.getAttribute("resultadoCliente")!=null){ 
	Cliente cliente = (Cliente) request.getAttribute("resultadoCliente");
	%>
	<form method="POST" action="alterarDados">
			<div class="row  ">
				<div class="w-50 content-b">
				<h4>Dados Cadastrais</h4>
				<input type="text" class="id_coluna" name="id" value="<%=cliente.getId() %>"/>
					<div class="row">
						<div class="w-100 ">
							<label>Nome
									<input  class="w-97 "  type="text" name="nome" value="<%=cliente.getNome() %>"/>
							</label>
						</div>
					</div>
					<div class="row">
						<div class="w-50 ">
							<label>CPF
								<input class="w-95"  type="text" name="cpf" value="<%=cliente.getCpf() %>"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Data Nascimento
								<input  type="date" name="dt_nascimento" value="<%=cliente.getDataNascimento() %>"/>
							</label>
						</div>	
					</div>
					Gênero
					<div class="row">
					
						<label><input type="radio" value="feminino" name="genero">F</label>
						<label><input type="radio" value="masculino" name="genero">M</label>
					</div>
						
					<div class="row">
						<div class="w-50 ">
							<label>Email
								<input class="w-95"  type="email" name="email" value="<%=cliente.getEmail().getEnderecoEmail() %>"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Telefone
								<input  type="text" name="telefone" value="<%=cliente.getTelefone().getTelefone() %>"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" type="submit" name="OPERACAO" value="ALTERAR" />
					</div>
				</div>
			</div>
		</form>
				<% 
		}
%>
<% if(request.getAttribute("resultadoUsuario")!=null){ 
	Usuario usuario = (Usuario) request.getAttribute("resultadoUsuario");
	%>
	<form method="POST" action="alterarDados">
			<div class="row  ">
				<div class="w-50 content-b">
				<h4>Cadastro Usuário</h4>
									<input type="text" class="id_coluna" name="id" value="<%=usuario.getId() %>"/>
					<div class="w-50 ">
						<label>Usuario
							<input class="w-95" type="text" name="usuario" value="<%=usuario.getNomeUsuario() %>"/>
						</label>
					</div>
					<div class="row">
						<div class="w-50 ">
							<label>Senha
								<input class="w-95"  type="password" name="senha" placeholder="Digite a senha"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Repita a Senha
								<input class="w-95"  type="password" name="senha" placeholder="Repita a senha"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" type="submit" name="OPERACAO" value="ALTERAR" />
					</div>
				</div>
			</div>
		</form>
					<% 
		}
%>		
	 </div>
	         <div class="row">
			<div class="content w-100 rodape ">
				<div class="rodape">
					<p>Página Beta para implementação da matéria ESIII</p>
				</div>
			</div>
      </div>
</body>
</html>