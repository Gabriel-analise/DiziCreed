package br.com.dizicreed.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public abstract class Mascara {

	/* * * * * * * * * * * * * * * * * * * * * * * 
	 * 
	 * ? - Qualquer letra
	 * U - Letras mai�sculas
	 * L - Letras min�sculas
	 * A - Letras e n�meros
	 * # - N�mero
	 * * - Qualquer caracter
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * */


	private static final String MASCARA_CEP = "##.###-###";
	private static final String MASCARA_DATA = "##/##/####";
	private static final String MASCARA_CPF = "###.###.###-##";
	private static final String MASCARA_CNPJ = "##.###.###/####-##";
	private static final String MASCARA_TELEFONE = "(##)####-####";
	private static final String MASCARA_CELULAR = "(##)#####-####";
	private static final String DATA_NASCIMENTO = "##/##/####";
		

	
	public static MaskFormatter getMascaraCpf() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_CPF);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do CPF");
		}
		return maskFormmater;
	}
	
	public static MaskFormatter getMascaraCnpj() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_CNPJ);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do CNPJ");
		}
		return maskFormmater;
	}
	
	public static MaskFormatter getMascaraTelefone() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_TELEFONE);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do telefone");
		}
		return maskFormmater;
	}
	
	public static MaskFormatter getMascaraCelular() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_CELULAR);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do celular");
		}
		return maskFormmater;
	}
	
	public static MaskFormatter getMascaraData() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_DATA);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara de data");
		}
		return maskFormmater;
	}
	
	
	public static MaskFormatter getMascaraCep() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_CEP);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do cep");
		}
		return maskFormmater;
	}
	
	
	public static MaskFormatter getMascaraDataNascimento() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(DATA_NASCIMENTO);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do cep");
		}
		return maskFormmater;
	}
	
	
	
	
	
	
	
}