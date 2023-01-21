package br.com.dizicreed.model;

public class Recebimento {

	private String cpfRecebimento;
	private String nomeRecebimento;
	private String emailRecebimento;
	private String dataCadastro;
	private String dataReferencia;
	private double valorRecebimento;

	public Recebimento(String cpfRecebimento, String nomeRecebimento, String emailRecebimento, String dataCadastro,
			String dataReferencia, double valorRecebimento) {
		this.cpfRecebimento = cpfRecebimento;
		this.nomeRecebimento = nomeRecebimento;
		this.emailRecebimento = emailRecebimento;
		this.dataCadastro = dataCadastro;
		this.dataReferencia = dataReferencia;
		this.valorRecebimento = valorRecebimento;

	}

	public String getCpfRecebimento() {
		return cpfRecebimento;
	}

	public void setCpfRecebimento(String cpfRecebimento) {
		this.cpfRecebimento = cpfRecebimento;
	}

	public String getNomeRecebimento() {
		return nomeRecebimento;
	}

	public void setNomeRecebimento(String nomeRecebimento) {
		this.nomeRecebimento = nomeRecebimento;
	}

	public String getEmailRecebimento() {
		return emailRecebimento;
	}

	public void setEmailRecebimento(String emailRecebimento) {
		this.emailRecebimento = emailRecebimento;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public double getValorRecebimento() {
		return valorRecebimento;
	}

	public void setValorRecebimento(double valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
	}

	public String toCsv() {
		return this.cpfRecebimento + ";" + this.nomeRecebimento + ";" + this.emailRecebimento + ";" + this.dataCadastro
				+ ";" + this.dataReferencia + ";" + this.valorRecebimento + ";";
	}

}
