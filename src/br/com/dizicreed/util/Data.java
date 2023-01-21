package br.com.dizicreed.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	public static Date formataData(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = null;
		try {
			dataFormatada = formatter.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataFormatada;
		
	}

}
