package br.com.dizicreed.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.util.ArquivoTexto;

public class FuncionarioDao {

	private ArquivoTexto arquivo;
	// constante nome do arquivo
	private static final String NOME_ARQUIVO = "funcionarios.txt";

	public FuncionarioDao() {
		this.arquivo = new ArquivoTexto(NOME_ARQUIVO);
	}

	public void inserir(Funcionario funcionario) {
		this.arquivo.inserir(funcionario.toCsv());
	}

	public List<Funcionario> listar() {
		List<String> registros = this.arquivo.ler();
		List<Funcionario> funcionarios = new ArrayList<>();

		for (String registro : registros) {
			String[] reg = registro.split(";");

			Funcionario funcionario = new Funcionario(reg[0], reg[1], reg[2], reg[3], reg[4]);
			funcionarios.add(funcionario);
		}

		return funcionarios;
	}
/*
	public Funcionario pesquisarPelaPlaca(String placa) {
		List<Funcionario> veiculos = listar();
		for (Funcionario veiculo : veiculos) {
			if (placa.equals(veiculo.getPlaca())) {
				return veiculo;
			}
		}
		return null;
	}

	public Funcionario pesquisarPeloAno(short ano) {
		List<Funcionario> veiculos = listar();
		for (Funcionario veiculo : veiculos) {
			if (ano == veiculo.getAno()) {
				return veiculo;
			}
		}
		return null;
	}
*/
	public void remover(Funcionario funcionario) {
		List<Funcionario> funcionarios = listar();
		for (Funcionario fc : funcionarios) {
			if (fc.getCpfFuncionario().equals(funcionario.getCpfFuncionario())) {
				funcionarios.remove(fc);
				break;
			}
		}

		arquivo.apagar();
		for (Funcionario v : funcionarios) {
			this.inserir(v);
		}
	}
}
