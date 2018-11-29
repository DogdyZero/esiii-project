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
        	
        	</select>
        	  <% if(request.getAttribute("idCliente")!=null){
        		  Usuario usuario = (Usuario) request.getAttribute("idCliente");
        	   %>
        	<input type="text" value="<%=usuario.getId()%>" name="id"/>
        	<input type="submit" name="OPERACAO" value="CONSULTAR"/>
        </form>
        	</div>
        </div>
     <% 
       	
        }
        	  if(request.getAttribute("lista")!=null){
        		  Cliente cliente = (Cliente) request.getAttribute("lista");
        		  %>
        		  
        		  <%=cliente.getNome() %>
        		  <%
        	  }
       
        	
        	%>
 
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