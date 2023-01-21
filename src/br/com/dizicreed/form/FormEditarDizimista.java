package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.Cursor;
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

import br.com.dizicreed.dao.DizimistaDao;
import br.com.dizicreed.dao.FuncionarioDao;
import br.com.dizicreed.model.Dizimista;
import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.util.Mascara;

@SuppressWarnings("serial")
public class FormEditarDizimista extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmailDizimista;
	private JTextField txtNomeDizimista;
	private JTextField txtCpfdizimista;
	private JTextField txtDataNascDizimista;

	Scanner teclado = new Scanner(System.in);

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

		int dia = 0;
		int mes = 0;

		try {
			dia = Integer.parseInt(txtDataNascDizimista.getText().substring(0, 2));
			mes = Integer.parseInt(txtDataNascDizimista.getText().substring(3, 5));
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de nascimento do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		if (txtNomeDizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o nome do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!nomeValido(txtNomeDizimista.getText())) {
			JOptionPane.showMessageDialog(null, "Atenção, digite um nome válido!", "Erro", JOptionPane.ERROR_MESSAGE);
			txtNomeDizimista.setText("");
			return false;
		}

		if (txtEmailDizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o e-mail do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isCpf(txtCpfdizimista.getText())) {
			JOptionPane.showMessageDialog(null, "Cpf inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
			txtCpfdizimista.setText("");
			return false;
		}

		if (txtCpfdizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Cpf do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtDataNascDizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de nascimento do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
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

		if (!datanascimentoValida(txtDataNascDizimista.getText())) {
			JOptionPane.showMessageDialog(null,
					"Data inválida, a data de nascimento deve ser menor que a data de hoje!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!maiorIdade(txtDataNascDizimista.getText())) {
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
					FormEditarDizimista frame = new FormEditarDizimista();
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
	public FormEditarDizimista() {
		setTitle("Edição de Dizimistas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblCadastroDeFuncionrio = new JLabel("Edição de dizimista");
		lblCadastroDeFuncionrio.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		lblCadastroDeFuncionrio.setBounds(12, 73, 431, 31);
		contentPane.add(lblCadastroDeFuncionrio);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD | Font.ITALIC, 67));
		lblDizicreed.setBackground(Color.WHITE);
		lblDizicreed.setBounds(12, 0, 431, 61);
		contentPane.add(lblDizicreed);

		JLabel labelAvatar = new JLabel("");
		labelAvatar.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/avatar6.png"));
		labelAvatar.setBounds(159, 116, 70, 79);
		contentPane.add(labelAvatar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(73, 247, 70, 23);
		contentPane.add(lblNome);

		txtEmailDizimista = new JTextField();
		txtEmailDizimista.setBounds(159, 284, 181, 19);
		contentPane.add(txtEmailDizimista);
		txtEmailDizimista.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(73, 282, 70, 23);
		contentPane.add(lblEmail);

		txtNomeDizimista = new JTextField();
		txtNomeDizimista.setColumns(10);
		txtNomeDizimista.setBounds(159, 253, 181, 19);
		contentPane.add(txtNomeDizimista);

		JButton btnSair = new JButton("");
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
		btnSair.setBounds(675, 406, 117, 61);
		contentPane.add(btnSair);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(89, 212, 70, 23);
		contentPane.add(lblCpf);

		txtCpfdizimista = new JFormattedTextField(Mascara.getMascaraCpf());
		txtCpfdizimista.setColumns(10);
		txtCpfdizimista.setBounds(159, 220, 181, 19);
		contentPane.add(txtCpfdizimista);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(12, 317, 147, 23);
		contentPane.add(lblDataDeNascimento);

		txtDataNascDizimista = new JFormattedTextField(Mascara.getMascaraDataNascimento());
		txtDataNascDizimista.setColumns(10);
		txtDataNascDizimista.setBounds(169, 315, 181, 19);
		contentPane.add(txtDataNascDizimista);

		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/Church.png"));
		lblImagem.setBounds(410, -22, 382, 513);
		contentPane.add(lblImagem);

		JButton btnSalvarDizimista = new JButton("Salvar");
		btnSalvarDizimista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvarDizimista.setBounds(12, 418, 117, 25);
		contentPane.add(btnSalvarDizimista);

		btnSalvarDizimista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DizimistaDao dizdao = new DizimistaDao();
				List<Dizimista> dizimistas = dizdao.listar();

				for (Dizimista dizimista : dizimistas) {
					if (dizimista.getCpfDizimista().equals(txtCpfdizimista.getText())) {
						dizdao.remover(dizimista);

						dizimista.setNomeDizimista(txtNomeDizimista.getText());
						dizimista.setEmailDizimista(txtEmailDizimista.getText());
						dizimista.setDataNascimentoDizimista(txtDataNascDizimista.getText());
						dizimista.setTipoDizimistaDizimista(dizimista.getTipoDizimistaDizimista());

						if (validaDados()) {
							dizdao.inserir(dizimista);
							txtCpfdizimista.setText("");
							txtNomeDizimista.setText("");
							txtEmailDizimista.setText("");
							txtDataNascDizimista.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Dizimista inválido, tente novamente!", "Erro",
									JOptionPane.ERROR_MESSAGE);
							txtCpfdizimista.setText("");
							txtNomeDizimista.setText("");
							txtEmailDizimista.setText("");
							txtDataNascDizimista.setText("");
						}

					}
				}

			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.setBounds(142, 418, 117, 25);
		contentPane.add(btnVoltar);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBounds(104, 352, 188, 25);
		contentPane.add(btnPesquisar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(271, 418, 117, 25);
		contentPane.add(btnExcluir);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DizimistaDao dizdao = new DizimistaDao();
				List<Dizimista> dizimistas = dizdao.listar();

				for (Dizimista dizimista : dizimistas) {
					if (dizimista.getCpfDizimista().equals(txtCpfdizimista.getText())) {
						dizdao.remover(dizimista);
						JOptionPane.showMessageDialog(null, "Dizimista removido com sucesso!", "Aviso",
								JOptionPane.DEFAULT_OPTION);
						txtCpfdizimista.setText("");
						txtNomeDizimista.setText("");
						txtEmailDizimista.setText("");
						txtDataNascDizimista.setText("");
					}
				}
			}
		});
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DizimistaDao dizdao = new DizimistaDao();
				List<Dizimista> dizimistas = dizdao.listar();

				boolean encontrouCpf = false;
				for (Dizimista dizimista : dizimistas) {
					if (dizimista.getCpfDizimista().equals(txtCpfdizimista.getText())) {
						encontrouCpf = true;
						txtNomeDizimista.setText(dizimista.getNomeDizimista());
						txtEmailDizimista.setText(dizimista.getEmailDizimista());
						txtDataNascDizimista.setText(dizimista.getDataNascimentoDizimista());

					}
				}
				if (encontrouCpf == false) {
					JOptionPane.showMessageDialog(null, "Dizimista não encontrado, tente novamente!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCpfdizimista.setText("");

				}

			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTelaPrincipal formTelaPrincipal = new FormTelaPrincipal();
				formTelaPrincipal.setVisible(true);
				dispose();
			}
		});

	}
}
