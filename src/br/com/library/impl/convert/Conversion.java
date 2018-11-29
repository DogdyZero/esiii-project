package br.com.library.impl.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {
	public static Date conversorStringEmData(String valor) {
		 Date data = null;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd").parse(valor);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}

		return data;
	}
}
