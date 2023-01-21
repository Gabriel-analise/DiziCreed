package br.com.dizicreed.model;

public class Dizimista {
	private String nomeDizimista;
	private String emailDizimista;
	private String cpfDizimista;
	private String dataNascimentoDizimista;
	private String tipoDizimista;

	public Dizimista(String nomeDizimista, String emailDizimista, String cpfDimizista, String dataNascDizimista,
			String tipoDizimista) {
		this.nomeDizimista = nomeDizimista;
		this.emailDizimista = emailDizimista;
		this.cpfDizimista = cpfDimizista;
		this.dataNascimentoDizimista = dataNascDizimista;
		this.tipoDizimista = tipoDizimista;

	}

	public String getNomeDizimista() {
		return nomeDizimista;
	}

	public void setNomeDizimista(String nomeDizimista) {
		this.nomeDizimista = nomeDizimista;
	}

	public String getEmailDizimista() {
		return emailDizimista;
	}

	public void setEmailDizimista(String emailDizimista) {
		this.emailDizimista = emailDizimista;
	}

	public String getCpfDizimista() {
		return cpfDizimista;
	}

	public void setCpfDizimista(String cpfDizimista) {
		this.cpfDizimista = cpfDizimista;
	}

	public String getDataNascimentoDizimista() {
		return dataNascimentoDizimista;
	}

	public void setDataNascimentoDizimista(String dataNascimentoDizimista) {
		this.dataNascimentoDizimista = dataNascimentoDizimista;
	}

	public String getTipoDizimistaDizimista() {
		return tipoDizimista;
	}

	public void setTipoDizimistaDizimista(String tipoDizimistaDizimista) {
		this.tipoDizimista = tipoDizimistaDizimista;
	}

	public String toCsv() {
		return this.nomeDizimista + ";" + this.emailDizimista + ";" + this.cpfDizimista + ";"
				+ this.dataNascimentoDizimista + ";" + this.tipoDizimista + ";";
	}
}
