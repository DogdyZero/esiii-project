<%@page import="br.com.library.domain.Cliente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Library Sales - Cadastro</title>

<script
  type="text/javascript"
  src="js/js.js">
</script>
	<LINK rel="stylesheet" type="text/css" href="css/style.css"> 
	<link href="css/style-responsive.css" rel="stylesheet" media="screen and (max-width: 480px)" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">
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
		<form method="POST" action="salvar-cliente">
			<div id="st_node_endereco" class="row ">
				<div class="w-50 content-b" id="nd_node_endereco"  >
				<h4>Endereço</h4>
					<div class=row>
						<div class="w-10 ">
							<label>Tipo
								<input class="w-77" id="tipo_residencia" type="text" />
							</label>	
						</div>		
						<div class="w-75 ">
							<label>Logradouro
								<input class="w-95" id="logradouro" type="text" placeholder="Digite o Endereco"/>
							</label>
						</div>	
						<div class="w-20 ">
							<label>Número
								<input class="w-83" id="numero_residencia" type="text"  placeholder="Número"/>
							</label>
						</div>	
					</div>	
					<div class=row>
						<label>CEP
							<input id="cep" type="text" placeholder="Digite o Cep"/>
						</label>
					</div>
					<div class=row>
						<div class="w-50 ">
							<label>Bairro
								<input class="w-95" id="bairro" type="text" placeholder="Digite o Bairro"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Cidade
								<input class="w-95" id="cidade"  type="text" placeholder="Digite a Cidade"/>
							</label>
						</div>
					</div>	
					<div class=row>
						<div class="w-50 ">
							<label>Estado
								<input class="w-95" id="estado"  type="text" placeholder="Digite o Estado"/>
							</label>
						</div>	
						<div class="w-50 ">
							<label>Pais
								<input class="w-95" id="pais" type="text" placeholder="Digite o Pais"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" type="button" value="Salvar Endereço" onclick="criarTabelaEndereco()"/>
							
					</div>
				</div>	
					
			</div>
		
				
			
					
			<table id="tbl_endereco">
				<thead>
		  			<tr>
						<th>Tipo</th>
						<th>Logradouro</th>
						<th>Número</th>
						<th>CEP</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>Estado</th>
						<th>Pais</th>
						
		  			</tr>
				</thead> 
				<tbody id="tbl_linhas_endereco">
				</tbody>
			</table>
			
			
			<div id="st_node_cartao" class="row  ">
				<div class="w-50 content-b" id="nd_node_cartao"  >
					<h4>Cartão de Crédito</h4>
					<div class=row>
						<div class="w-50 ">
							<label>Bandeira
								<input class="w-95" id="bandeira" type="text"  placeholder="Digite a Bandeira"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Nome no cartão
								<input class="w-95" id="nome_cartao" type="text" placeholder="Digite o nome no cartão"/>
							</label>
						</div>
						
					</div>
					<div class=row>
						<div class="w-50 ">
							<label>Numeração
								<input class="w-95" id="numero_cartao" type="text" placeholder="Digite o número do cartão"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Código Segurança
								<input class="w-95" id="codigo_seguranca" type="text" placeholder="Digite o código de segurança"/>
							</label>
						</div>
					</div>
					<div class="w-100">
						<input class="direita m-2" type="button" value="Salvar Cartão de Crédito" onclick="criarTabelaCartao()"/>
					</div>
				</div>

			</div>
		
			<table id="tbl_cartao">
				<thead>
		  			<tr>
						<th>Bandeira</th>
						<th>Nome Cartão</th>
						<th>Número Cartão</th>
						<th>Código Seguranca</th>
						
		  			</tr>
				</thead> 
				<tbody id="tbl_linhas_cartao">
				</tbody>
			</table>
			<div class="row  ">
				<div class="w-50 content-b">
				<h4>Dados Cadastrais</h4>
					<div class="row">
						<div class="w-100 ">
							<label>Nome
									<input  class="w-97 "  type="text" name="nome" placeholder="Digite o Nome"/>
							</label>
						</div>
					</div>
					<div class="row">
						<div class="w-50 ">
							<label>CPF
								<input class="w-95"  type="text" name="cpf" placeholder="Digite o CPF"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Data Nascimento
								<input  type="date" name="dt_nascimento" placeholder="Digite a sua data de Nascimento"/>
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
								<input class="w-95"  type="email" name="email" placeholder="Digite o Email"/>
							</label>
						</div>
						<div class="w-50 ">
							<label>Telefone
								<input  type="text" name="telefone" placeholder="Digite o Telefone"/>
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="row  ">
				<div class="w-50 content-b">
				<h4>Cadastro Usuário</h4>
					<div class="w-50 ">
						<label>Usuario
							<input class="w-95" type="text" name="usuario" placeholder="Digite o usuario"/>
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
				</div>
			</div>
			<div class="w-50">
				<input type="submit" name="OPERACAO" value="SALVAR"/>
			</div>
	</form>
	<br>
	<% if(request.getAttribute("lista")!=null){
		List<Cliente> listaClientes = (List<Cliente>)request.getAttribute("lista");%>
	 <table style="border:1px solid black;">
	 	<tr>
	 		<th>Nome</th>
	 	</tr>
	 	<%for(Cliente clientes:listaClientes){ %>
	 	<tr>
	 	
	 		<td><%=clientes.getNome() %></td>
	 	
	 <%} 
	 }%>
	 	</tr>
	 
	 </table>
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