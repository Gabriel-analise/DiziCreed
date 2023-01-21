package br.com.dizicreed.model;

import javax.swing.JTextField;

@SuppressWarnings("unused")
public class Funcionario {

	// ATRIBUTOS
	private String nomeFuncionario;
	private String emailFuncionario;
	private String cpfFuncionario;
	private String dataNascFuncionario;
	private String senha;

	public Funcionario(String nomeFuncionario, String emailFuncionario, String cpfFuncionario,
			String dataNascFuncionario, String senha) {
		this.nomeFuncionario = nomeFuncionario;
		this.emailFuncionario = emailFuncionario;
		this.cpfFuncionario = cpfFuncionario;
		this.dataNascFuncionario = dataNascFuncionario;
		this.senha = senha;

	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getEmailFuncionario() {
		return emailFuncionario;
	}

	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}

	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}

	public String getDataNascFuncionrio() {
		return dataNascFuncionario;
	}

	public void setDataNascFuncionrio(String dataNascFuncionrio) {
		this.dataNascFuncionario = dataNascFuncionrio;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toCsv() {
		return this.nomeFuncionario + ";" + this.emailFuncionario + ";" + this.cpfFuncionario + ";"
				+ this.dataNascFuncionario + ";" + this.senha + ";";
	}

}
