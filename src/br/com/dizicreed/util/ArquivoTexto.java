package br.com.dizicreed.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArquivoTexto {

	private String nome;
	private File file;

	public ArquivoTexto(String nome) {
		this.nome = nome;
		criar();
	}

	private void criar() {
		this.file = new File(this.nome);
		try {
			if (!file.exists()) { // file.exists() == false
				file.createNewFile();
				System.out.println("O arquivo " + this.nome + " foi criado com sucesso!");
			} else {
				System.out.println("O arquivo " + this.nome + " já existe!");
			}
		} catch (IOException e) {
			System.out.println("Não foi possível criar o arquivo " + this.nome + "!");
		}
	}

	public void inserir(String texto) {
		try {
			FileWriter fileWriter = new FileWriter(this.file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(texto);
			System.out.println("O texto '" + texto + "' foi inserido no arquivo " + this.nome);
			printWriter.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao inserir os dados no arquivo " + this.nome);
		}
	}

	public List<String> ler() {
		List<String> lista = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(this.file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			System.out.println("Efetuando a leitura do arquivo " + this.nome + " ...");

			String linha = bufferedReader.readLine();
			while (linha != null) {
				lista.add(linha);
				linha = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possível localizar o arquivo " + this.nome);
		} catch (IOException e) {
			System.out.println("Não foi possível ler os dados do arquivo " + this.nome);
		}
		return lista;
	}

	public void apagar() {
		try {
			FileWriter fileWriter = new FileWriter(this.file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print("");
			System.out.println("O arquivo " + this.nome + " foi apagado");
			printWriter.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao apagar os dados do arquivo " + this.nome);
		}
	}
}
