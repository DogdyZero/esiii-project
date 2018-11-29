<%@page import="br.com.library.domain.Endereco"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(request.getAttribute("resultado")!=null){ 
	Endereco endereco = (Endereco) request.getAttribute("resultado");
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
</body>
</html>