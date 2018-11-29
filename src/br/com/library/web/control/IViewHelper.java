package br.com.library.web.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.library.domain.EntidadeDominio;
import br.com.library.domain.Result;

public interface IViewHelper {
	EntidadeDominio getEntidade(HttpServletRequest request);
	void setView (Result resultado,HttpServletRequest request, HttpServletResponse response) throws IOException;
	
}
