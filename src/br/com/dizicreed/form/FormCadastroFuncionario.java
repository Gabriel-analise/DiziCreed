package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.dizicreed.dao.FuncionarioDao;
import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.util.Mascara;
import java.awt.Cursor;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class FormCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmailFuncionario;
	private JTextField txtNomeFuncionario;
	private JTextField txtCpfFuncionario;
	private JTextField txtDataNascFuncionario;
	private JTextField txtSenha;

	public static boolean isCpf(String CPF) {

		CPF = CPF.replaceAll("[^a-zA-z0-9]", "");

		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return false;

		return true;
	}

	public static boolean nomeValido(String palavra) {

		if (!palavra.trim().matches("[^a-zA-Z]*"))
			return true;

		return false;

	}

	public static boolean cpfRepetido(String cpf) {
		FuncionarioDao funcDao = new FuncionarioDao();
		List<Funcionario> funcionarios = funcDao.listar();

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCpfFuncionario().equals(cpf)) {
				return true;
			}

		}

		return false;

	}

	public static boolean datanascimentoValida(String dataNascimento) {

		LocalDate hoje = LocalDate.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataVerificada = LocalDate.parse(dataNascimento, dtf);

		if (dataVerificada.compareTo(hoje) < 0)
			return true;

		return false;
	}

	public static boolean maiorIdade(String dataNascimento) {

		int dia = Integer.parseInt(dataNascimento.substring(0, 2));
		int mes = Integer.parseInt(dataNascimento.substring(3, 5));
		int ano = Integer.parseInt(dataNascimento.substring(6, 10));

		Calendar hoje = Calendar.getInstance();

		int idade = hoje.get(Calendar.YEAR) - ano;

		// se ainda não chegou o aniversário, diminui 1 ano
		int mesAtual = hoje.get(Calendar.MONTH) + 1;
		if ((mesAtual == mes && hoje.get(Calendar.DAY_OF_MONTH) < dia) || mesAtual < mes) {
			idade--;
		}

		if (idade < 18)
			return false;

		return true;

	}

	private boolean validaDados() {

		if (txtNomeFuncionario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o nome do funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int dia = 0;
		int mes = 0;

		try {
			dia = Integer.parseInt(txtDataNascFuncionario.getText().substring(0, 2));
			mes = Integer.parseInt(txtDataNascFuncionario.getText().substring(3, 5));
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de nascimento do funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		if (!nomeValido(txtNomeFuncionario.getText())) {
			JOptionPane.showMessageDialog(null, "Atenção, digite um nome válido!", "Erro", JOptionPane.ERROR_MESSAGE);
			txtNomeFuncionario.setText("");
			return false;
		}

		if (txtEmailFuncionario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o e-mail do funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isCpf(txtCpfFuncionario.getText())) {
			JOptionPane.showMessageDialog(null, "Cpf inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
			txtCpfFuncionario.setText("");
			return false;
		}

		if (txtCpfFuncionario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Cpf do funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtDataNascFuncionario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de nascimento do funcionário!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cpfRepetido(txtCpfFuncionario.getText())) {
			JOptionPane.showMessageDialog(null, "Já existe um funcionário cadastrado com este Cpf cadastrado na base!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (dia > 31) {
			System.out.println(dia);
			JOptionPane.showMessageDialog(null, "Data inválida, o dia não pode ser maior que 31!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (mes > 12) {
			JOptionPane.showMessageDialog(null, "Data inválida, o mês não pode ser maior que 12!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!datanascimentoValida(txtDataNascFuncionario.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data inválida, a data de nascimento deve ser menor que a data de hoje!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!maiorIdade(txtDataNascFuncionario.getText())) {
			JOptionPane.showMessageDialog(null, "Data inválida, você deve ser maior de idade para acessar o sistema!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroFuncionario frame = new FormCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastroFuncionario() {
		setTitle("Cadastro de funcionários");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblCadastroDeFuncionrio = new JLabel("Cadastro de funcionário");
		lblCadastroDeFuncionrio.setBounds(12, 73, 431, 31);
		lblCadastroDeFuncionrio.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		contentPane.add(lblCadastroDeFuncionrio);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setBounds(12, 0, 431, 61);
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD | Font.ITALIC, 67));
		lblDizicreed.setBackground(Color.WHITE);
		contentPane.add(lblDizicreed);

		JLabel labelAvatar = new JLabel("");
		labelAvatar.setBounds(159, 116, 70, 79);
		labelAvatar.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/avatar6.png"));
		contentPane.add(labelAvatar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(89, 224, 70, 23);
		contentPane.add(lblNome);

		txtEmailFuncionario = new JTextField();
		txtEmailFuncionario.setBounds(159, 261, 181, 19);
		contentPane.add(txtEmailFuncionario);
		txtEmailFuncionario.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(89, 259, 70, 23);
		contentPane.add(lblEmail);

		txtNomeFuncionario = new JTextField();
		txtNomeFuncionario.setBounds(159, 226, 181, 19);
		txtNomeFuncionario.setColumns(10);
		contentPane.add(txtNomeFuncionario);

		JButton btnSair = new JButton("");
		btnSair.setBounds(675, 406, 117, 61);
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.setVisible(true);
				dispose();

			}
		});
		btnSair.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/logout3.png"));
		btnSair.setContentAreaFilled(false);
		btnSair.setBorder(null);
		btnSair.setBackground(Color.WHITE);
		contentPane.add(btnSair);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(89, 299, 70, 23);
		contentPane.add(lblCpf);

		txtCpfFuncionario = new JFormattedTextField(Mascara.getMascaraCpf());
		txtCpfFuncionario.setBounds(159, 301, 181, 19);
		txtCpfFuncionario.setColumns(10);
		contentPane.add(txtCpfFuncionario);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(12, 330, 147, 23);
		contentPane.add(lblDataDeNascimento);

		txtDataNascFuncionario = new JFormattedTextField(Mascara.getMascaraDataNascimento());
		txtDataNascFuncionario.setBounds(179, 332, 181, 19);
		txtDataNascFuncionario.setColumns(10);
		contentPane.add(txtDataNascFuncionario);

		JButton btnSalvarFuncionario = new JButton("Salvar");
		btnSalvarFuncionario.setBounds(42, 418, 117, 25);
		btnSalvarFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSalvarFuncionario);

		btnSalvarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FuncionarioDao funcionarioDao = new FuncionarioDao();
				if (validaDados()) {

					Funcionario funcionario = new Funcionario(txtNomeFuncionario.getText(),
							txtEmailFuncionario.getText(), txtCpfFuncionario.getText(),
							txtDataNascFuncionario.getText(), txtSenha.getText());

					funcionarioDao.inserir(funcionario);
					JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
					txtNomeFuncionario.setText("");
					txtEmailFuncionario.setText("");
					txtCpfFuncionario.setText("");
					txtDataNascFuncionario.setText("");
					txtSenha.setText("");
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(195, 418, 117, 25);
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnVoltar);

		JLabel lblImagem = new JLabel("");
		lblImagem.setBounds(410, -22, 382, 513);
		lblImagem.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/Church.png"));
		contentPane.add(lblImagem);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(72, 365, 70, 23);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(159, 368, 181, 19);
		contentPane.add(txtSenha);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormMenuPrincipal formMenuPrincipal = new FormMenuPrincipal();
				formMenuPrincipal.setVisible(true);
			}
		});

	}
}
