function criarTabelaEndereco(){
	var lista = limparFormEndereco();
	var linha = document.createElement('tr');
	
	var txtTipo = document.getElementById('tipo_residencia').value;
	var txtLog = document.getElementById('logradouro').value;
	var txtNum = document.getElementById('numero_residencia').value;
	var txtCep = document.getElementById('cep').value;
	var txtBairro = document.getElementById('bairro').value;
	var txtCidade = document.getElementById('cidade').value;
	var txtEstado = document.getElementById('estado').value;
	var txtPais = document.getElementById('pais').value;

	var cpTipo = document.createElement('td');
	var cpLog = document.createElement('td');
	var cpNum = document.createElement('td');
	var cpCep = document.createElement('td');
	var cpBairro = document.createElement('td');
	var cpCidade = document.createElement('td');
	var cpEstado = document.createElement('td');
	var cpPais = document.createElement('td');

	
	var ctnTipo= document.createTextNode(cpTipo);
	var ctnLog= document.createTextNode(txtLog);
	var ctnNum= document.createTextNode(txtNum);
	var ctnCep= document.createTextNode(txtCep);
	var ctnBairro= document.createTextNode(txtBairro);
	var ctnCidade= document.createTextNode(txtCidade);
	var ctnEstado= document.createTextNode(txtEstado);
	var ctnPais= document.createTextNode(txtPais);

	var inputTipo = document.createElement('input');
	var inputLog = document.createElement('input');
	var inputNum = document.createElement('input');
	var inputCep = document.createElement('input');
	var inputBairro = document.createElement('input');
	var inputCidade = document.createElement('input');
	var inputEstado = document.createElement('input');
	var inputPais = document.createElement('input');
		
	inputTipo.setAttribute('value', txtTipo);
	inputLog.setAttribute('value', txtLog);
	inputNum.setAttribute('value', txtNum);
	inputCep.setAttribute('value', txtCep);
	inputBairro.setAttribute('value', txtBairro);
	inputCidade.setAttribute('value', txtCidade);
	inputEstado.setAttribute('value', txtEstado);
	inputPais.setAttribute('value', txtPais);

	inputTipo.setAttribute('name', 'tipo_residencia');
	inputLog.setAttribute('name', 'logradouro');
	inputNum.setAttribute('name', 'numero_residencia');
	inputCep.setAttribute('name', 'cep');
	inputBairro.setAttribute('name', 'bairro');
	inputCidade.setAttribute('name', 'cidade');
	inputEstado.setAttribute('name', 'estado');
	inputPais.setAttribute('name', 'pais');
		
	cpTipo.appendChild(inputTipo);
	cpLog.appendChild(inputLog);
	cpNum.appendChild(inputNum);
	cpCep.appendChild(inputCep);
	cpBairro.appendChild(inputBairro);
	cpCidade.appendChild(inputCidade);
	cpEstado.appendChild(inputEstado);
	cpPais.appendChild(inputPais);

	linha.appendChild(cpTipo);
	linha.appendChild(cpLog);
	linha.appendChild(cpNum);
	linha.appendChild(cpCep);
	linha.appendChild(cpBairro);
	linha.appendChild(cpCidade);
	linha.appendChild(cpEstado);
	linha.appendChild(cpPais);

	
	document.getElementById('tbl_linhas_endereco').appendChild(linha);
	document.getElementById('tbl_endereco').style.display="none";
	
	for (var i = 0; i<lista.length;i++){
		document.getElementById(lista[i].id).value='';	
	}
}
function limparFormEndereco(){
	var lista =[{
		id:'pais'
	},{
		id:'estado'
	},{
		id:'cidade'
	},{
		id:'bairro'
	},{
		id:'tipo_residencia'
	},{
		id:'logradouro'
	},{
		id:'numero_residencia'
	},{
		id:'cep'
	}];
	
	return lista;
}
function criarTabelaCartao(){
	var lista = limparFormCartao();
	var linha = document.createElement('tr');
	
	var txtBand = document.getElementById('bandeira').value;
	var txtNomeCartao = document.getElementById('nome_cartao').value;
	var txtNumCartao = document.getElementById('numero_cartao').value;
	var txtCodSeg = document.getElementById('codigo_seguranca').value;


	var cpBand = document.createElement('td');
	var cpNomeCartao = document.createElement('td');
	var cpNumCartao = document.createElement('td');
	var cpCodSeg = document.createElement('td');

	var ctnBand= document.createTextNode(txtBand);
	var ctnNomeCartao= document.createTextNode(txtNomeCartao);
	var ctnNumCartao= document.createTextNode(txtNumCartao);
	var ctnCodSeg= document.createTextNode(txtCodSeg);
	
	var inputBand = document.createElement('input');
	var inputNomeCartao = document.createElement('input');
	var inputNumCartao = document.createElement('input');
	var inputCodSeg = document.createElement('input');

	inputBand.setAttribute('value', txtBand);
	inputNomeCartao.setAttribute('value', txtNomeCartao);
	inputNumCartao.setAttribute('value', txtNumCartao);
	inputCodSeg.setAttribute('value', txtCodSeg);

	inputBand.setAttribute('name', 'bandeira');
	inputNomeCartao.setAttribute('name', 'nome_cartao');
	inputNumCartao.setAttribute('name', 'numero_cartao');
	inputCodSeg.setAttribute('name', 'codigo_seguranca');
	
	cpBand.appendChild(inputBand);
	cpNomeCartao.appendChild(inputNomeCartao);
	cpNumCartao.appendChild(inputNumCartao);
	cpCodSeg.appendChild(inputCodSeg);

	linha.appendChild(cpBand);
	linha.appendChild(cpNomeCartao);
	linha.appendChild(cpNumCartao);
	linha.appendChild(cpCodSeg);
	
	document.getElementById('tbl_linhas_cartao').appendChild(linha);
	document.getElementById('tbl_cartao').style.display="none";
	
	
	for (var i = 0; i<lista.length;i++){
		document.getElementById(lista[i].id).value='';	
	}
}
function limparFormCartao(){
	var lista =[{
		id:'bandeira'
	},{
		id:'nome_cartao'
	},{
		id:'numero_cartao'
	},{
		id:'codigo_seguranca'
	}];
	
	return lista;
}