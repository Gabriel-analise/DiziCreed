package br.com.dizicreed.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.dizicreed.model.Dizimista;
import br.com.dizicreed.util.ArquivoTexto;

public class DizimistaDao {

	private ArquivoTexto arquivo;
	// constante nome do arquivo
	private static final String NOME_ARQUIVO = "dizimistas.txt";

	public DizimistaDao() {
		this.arquivo = new ArquivoTexto(NOME_ARQUIVO);
	}

	public void inserir(Dizimista dizimista) {
		this.arquivo.inserir(dizimista.toCsv());
	}

	public List<Dizimista> listar() {
		List<String> registros = this.arquivo.ler();
		List<Dizimista> dizimistas = new ArrayList<>();

		for (String registro : registros) {
			String[] reg = registro.split(";");

			Dizimista dizimista = new Dizimista(reg[0], reg[1], reg[2], reg[3], reg[4]);
			dizimistas.add(dizimista);
		}

		return dizimistas;
	}
	
	public void remover(Dizimista dizimista) {
		List<Dizimista> dizimistas = listar();
		for (Dizimista fc : dizimistas) {
			if (fc.getCpfDizimista().equals(dizimista.getCpfDizimista())) {
				dizimistas.remove(fc);
				break;
			}
		}

		arquivo.apagar();
		for (Dizimista v : dizimistas) {
			this.inserir(v);
		}
	}
	

}
