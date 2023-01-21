package br.com.dizicreed.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.model.Recebimento;
import br.com.dizicreed.util.ArquivoTexto;

public class RecebimentoDao {

	private ArquivoTexto arquivo;
	// constante nome do arquivo
	private static final String NOME_ARQUIVO = "recebimentos.txt";

	public RecebimentoDao() {
		this.arquivo = new ArquivoTexto(NOME_ARQUIVO);
	}

	public void inserir(Recebimento recebimento) {
		this.arquivo.inserir(recebimento.toCsv());
	}

	public List<Recebimento> listar() {
		List<String> registros = this.arquivo.ler();
		List<Recebimento> recebimentos = new ArrayList<>();

		for (String registro : registros) {
			String[] reg = registro.split(";");

			Recebimento recebimento = new Recebimento(reg[0], reg[1], reg[2], reg[3],reg[4], Double.parseDouble(reg[5]));
			recebimentos.add(recebimento);
		}

		return recebimentos;
	}
	
	public void remover(Recebimento recebimento) {
		List<Recebimento> recebimentos = listar();
		for (Recebimento fc : recebimentos) {
			if (fc.getCpfRecebimento().equals(recebimento.getCpfRecebimento())) {
				recebimentos.remove(fc);
				break;
			}
		}

		arquivo.apagar();
		for (Recebimento v : recebimentos) {
			this.inserir(v);
		}
	}

}
