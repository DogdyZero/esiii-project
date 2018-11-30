<%@page import="br.com.library.domain.CartaoCredito"%>
<%@page import="java.util.List"%>
<%@page import="br.com.library.domain.Endereco"%>
<%@page import="br.com.library.domain.Usuario"%>
<%@page import="br.com.library.domain.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html >
  <head>
  <title>Library Sales - Index</title>
  
	<meta charset="UTF-8"/>
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
					  <a href="#home">Home</a>
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
        </div>
        <div class="row">
        	<div class="content w-100">
        <form action="consultarDados" method="POST">
        	<select name="tipoconsulta" >
        		<option  value="dados_cadastrais">Dados Cadastrais</option>
        		<option value="cartao_credito">Cartões de Crédito</option>
        		<option value="endereco">Endereços</option>
        		<option value="dados_usuario">Dados de Acesso</option>
        	
        	</select><br>
        	  <% if(request.getAttribute("idCliente")!=null){
        		  Usuario usuario = (Usuario) request.getAttribute("idCliente");
        	   %>
        	   	<label>id cliente
        			<input type="text" value="<%=usuario.getId()%>" name="id"/>
        		</label>
        		<%} 
        	else { 
        		%>
        	<label>id cliente
        		<input type="text" name="id"/>
        	</label>
        	<%  } %>
        	<input type="submit" name="OPERACAO" value="CONSULTAR"/>
        </form>
        <br>
		<table>
     <% 
       	
        
        	  if(request.getAttribute("resultado")!=null || request.getAttribute("listagem")!=null){
        		  String requisicao =  request.getParameter("tipoconsulta");
        		  if(requisicao.equals("dados_cadastrais")){  
        		  Cliente cliente = (Cliente) request.getAttribute("resultado");
        		  %>
        		  <tr>
        		  	<th class="w-5">Nome</th>
        		  	<th class="w-5">CPF</th>
        		  	<th class="w-5">Nascimento</th>
        		  	<th class="w-5">Genero</th>
        		  	<th class="w-5">Telefone</th>
        		  	<th class="w-5">E-mail</th>
        		  	<th class="w-5"></th>
					<th class="w-5"></th>
        		  </tr>
        		  <tr>
        		  <td><%=cliente.getNome() %></td>
        		  <td><%=cliente.getCpf() %></td>
        		  <td><%=cliente.getDataNascimento() %></td>
        		  <td><%=cliente.getGenero().getSexo() %></td>
        		  <td><%=cliente.getTelefone().getTelefone() %></td>
        		  <td><%=cliente.getEmail().getEnderecoEmail() %></td>
        		  <td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=cliente&id=<%=cliente.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
        		  <td><a href="excluirDados?OPERACAO=APAGAR&tipo=cliente&id=<%=cliente.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>   
        		      		  
        		  </tr>
        		  <%
        		  } else if (requisicao.equals("endereco")) { %>
        		     <tr>
        		  		<th class="w-5">Tipo</th>
						<th class="w-20">Logradouro</th>
						<th class="w-5">Número</th>
						<th class="w-5">CEP</th>
						<th class="w-10">Bairro</th>
						<th class="w-10">Cidade</th>
						<th class="w-5">Estado</th>
						<th class="w-5">Pais</th>
						<th class="w-5"></th>
						<th class="w-5"></th>
        		 	</tr>
        		  <%
					  if(request.getAttribute("listagem") !=null) {
        			  List<Endereco> listaEnderecos = (List<Endereco>) request.getAttribute("listagem");%>

        		  	<% for(Endereco end :listaEnderecos){%>
        		  	
        		  	<tr>
        		  	
	        		  	<td><input class="w-97" value="<%=end.getTipoDaResidencia().getTipo()%>"></td>
	        			<td><input class="w-97" value="<%=end.getLogradouro() %>"></td>
	        			<td><input class="w-97" value="<%=end.getNumeroResidencia()%>"></td>
	        			<td><input class="w-97" value="<%=end.getCep() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getNomeBairro() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getNomeCidade() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getEstado().getNomeEstado() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getEstado().getPais().getNomePais() %>"></td>
	        			<td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=endereco&id=<%=end.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
        		  		<td><a href="excluirDados?OPERACAO=APAGAR&tipo=endereco&id=<%=end.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>   
	        			</tr>
        			
        		  <%
        	 			}
					  }
        		  	 if (request.getAttribute("resultado") !=null){
        			  Endereco end = (Endereco) request.getAttribute("resultado");%>

        			<tr>
	        		  	<td><input class="w-97" value="<%=end.getTipoDaResidencia().getTipo()%>"></td>
	        			<td><input class="w-97" value="<%=end.getLogradouro() %>"></td>
	        			<td><input class="w-97" value="<%=end.getNumeroResidencia()%>"></td>
	        			<td><input class="w-97" value="<%=end.getCep() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getNomeBairro() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getNomeCidade() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getEstado().getNomeEstado() %>"></td>
	        			<td><input class="w-97" value="<%=end.getBairro().getCidade().getEstado().getPais().getNomePais() %>"></td>
	        			<td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=endereco&id=<%=end.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
        		  		<td><a href="excluirDados?OPERACAO=APAGAR&tipo=endereco&id=<%=end.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>   
        			  
        			<%  }
        		  } else if (requisicao.equals("cartao_credito")) { %>
     		     <tr>
     		  			<th>Bandeira</th>
						<th>Nome no Cartão</th>
						<th>Numeração</th>
						<th>Código</th>
     		 	</tr>
     		  <%
					  if(request.getAttribute("listagem") !=null) {
     			  List<CartaoCredito> listaCartoes = (List<CartaoCredito>) request.getAttribute("listagem");%>

     		  	<% for(CartaoCredito cart :listaCartoes){%>
     		  	<tr>
	        		  	<td><%=cart.getBandeiraCartao().getNomeBandeira()%></td>
	        		  	<td><%=cart.getNomeNoCartao()%></td>
	        		  	<td><%=cart.getNumeroCartao()%></td>
	        		  	<td><%=cart.getCodigoSegurancao()%></td>
		        		<td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=cartao&id=<%=cart.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
						<td><a href="excluirDados?OPERACAO=APAGAR&tipo=cartao&id=<%=cart.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>           			</tr>
     			
     		  <%
     	 			}
					  }
     		  	 if (request.getAttribute("resultado") !=null){
     		  		CartaoCredito cart = (CartaoCredito) request.getAttribute("resultado");%>

     		  	<tr>
	        		  	<td><%=cart.getBandeiraCartao().getNomeBandeira()%></td>
	        		  	<td><%=cart.getNomeNoCartao()%></td>
	        		  	<td><%=cart.getNumeroCartao()%></td>
	        		  	<td><%=cart.getCodigoSegurancao()%></td>
		        		<td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=cartao&id=<%=cart.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
						<td><a href="excluirDados?OPERACAO=APAGAR&tipo=cartao&id=<%=cart.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>           			</tr>
     			  
     			<%  }
        		  	 
        		  } else if ((requisicao.equals("dados_usuario"))){
        			  Usuario usuario = (Usuario) request.getAttribute("resultado");%>
        			<tr>
        		  	<th>Login</th>
        		  	<th>Senha</th>
        		  </tr>
        		  <tr>
        			<td><%=usuario.getNomeUsuario() %></td>
        			<td><%=usuario.getSenha() %></td>
		        		<td><a href="alterarDados?OPERACAO=CONSULTAR&tipo=usuario&id=<%=usuario.getId() %>"><img class="w-30px" src="imagens/pen-512.png"/></a></td>   
						<td><a href="excluirDados?OPERACAO=APAGAR&tipo=usuario&id=<%=usuario.getId() %>"><img class="w-30px" src="imagens/Eraser-icon.png"/></a></td>           			</tr>
        			</tr>
        		  <%
        		  }
        	  } 
       	
        	
        	%>
        </table>
          	</div>
        </div>
 
        <div class="row">
			<div class="content w-100 rodape ">
				<div class="rodape">
					<p>Página Beta para implementação da matéria ESIII</p>
				</div>
			</div>
      </div>
    </div>

   
        
  </body>
</html>