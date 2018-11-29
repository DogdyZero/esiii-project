package br.com.library.web.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;
import br.com.library.impl.command.AlterarCommand;
import br.com.library.impl.command.ApagarCommand;
import br.com.library.impl.command.ConsultarCommand;
import br.com.library.impl.command.ICommand;
import br.com.library.impl.command.SalvarCommand;
import br.com.library.impl.vh.ConsultaVH;
import br.com.library.impl.vh.PrepararUpdateVH;
import br.com.library.impl.vh.ClienteVH;
import br.com.library.impl.vh.UsuarioVH;

public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map <String, ICommand> commands;
	private Map <String,IViewHelper> vhs;
	
	public Control() {
		commands = new HashMap<String,ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("APAGAR", new ApagarCommand());
		
		vhs = new HashMap<String,IViewHelper>();
		
		vhs.put("/library/salvar-cliente", new ClienteVH());
		vhs.put("/library/servicoConsultarLogin", new UsuarioVH());
		vhs.put("/library/consultarDados", new ConsultaVH());
		vhs.put("/library/alterarDados", new PrepararUpdateVH());
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("OPERACAO");
		String uri = request.getRequestURI();
		
		IViewHelper vh = vhs.get(uri);
		
		EntidadeDominio entidade = vh.getEntidade(request);
		Result resultado = commands.get(operacao).executar(entidade);
		vh.setView(resultado,request, response);
		
	}

}
