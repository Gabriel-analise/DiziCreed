package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.dizicreed.dao.DizimistaDao;
import br.com.dizicreed.model.Dizimista;
import br.com.dizicreed.util.Mascara;
import java.awt.Cursor;

public class FormCadastroDizimista extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeDizimista;
	private JTextField txtEmaildizimista;
	private JTextField txtCpfDizimista;
	private JTextField txtDataNascDizimista;
	private String txtTipoDizimista;

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
		DizimistaDao dizDao = new DizimistaDao();
		List<Dizimista> dizimistas = dizDao.listar();

		for (Dizimista dizimista : dizimistas) {
			if (dizimista.getCpfDizimista().equals(cpf)) {
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

		if (txtEmaildizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o e-mail do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isCpf(txtCpfDizimista.getText())) {
			JOptionPane.showMessageDialog(null, "Cpf inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
			txtCpfDizimista.setText("");
			return false;
		}

		if (txtCpfDizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Cpf do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtDataNascDizimista.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de nascimento do dizimista!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cpfRepetido(txtCpfDizimista.getText())) {
			JOptionPane.showMessageDialog(null, "Já existe um dizimista cadastrado com este Cpf cadastrado na base!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			dia = Integer.parseInt(txtDataNascDizimista.getText().substring(0, 2));
			mes = Integer.parseInt(txtDataNascDizimista.getText().substring(3, 5));
		} catch (Exception e) {
			System.out.println(e);
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

		if (txtTipoDizimista == "" || txtTipoDizimista == null) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione o tipo de dizimista a ser cadastrado!", "Erro",
					JOptionPane.ERROR_MESSAGE);
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
					FormCadastroDizimista frame = new FormCadastroDizimista();
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
	public FormCadastroDizimista() {
		setTitle("Cadastro de dizimista");
		setBounds(new Rectangle(0, 0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(405, -28, 389, 514);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(12, 171, 45, 15);
		panel.add(lblNewLabel);

		txtNomeDizimista = new JTextField();
		txtNomeDizimista.setColumns(10);
		txtNomeDizimista.setBounds(75, 169, 181, 19);
		panel.add(txtNomeDizimista);

		txtEmaildizimista = new JTextField();
		txtEmaildizimista.setColumns(10);
		txtEmaildizimista.setBounds(75, 200, 181, 19);
		panel.add(txtEmaildizimista);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(12, 202, 62, 15);
		panel.add(lblEmail);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(12, 240, 62, 15);
		panel.add(lblCpf);

		txtCpfDizimista = new JFormattedTextField(Mascara.getMascaraCpf());
		txtCpfDizimista.setColumns(10);
		txtCpfDizimista.setBounds(75, 238, 181, 19);
		panel.add(txtCpfDizimista);

		JLabel lblDataDeNascimeto = new JLabel("Data de nascimeto:");
		lblDataDeNascimeto.setBounds(12, 274, 138, 15);
		panel.add(lblDataDeNascimeto);

		txtDataNascDizimista = new JFormattedTextField(Mascara.getMascaraDataNascimento());
		txtDataNascDizimista.setColumns(10);
		txtDataNascDizimista.setBounds(168, 272, 181, 19);
		panel.add(txtDataNascDizimista);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD | Font.ITALIC, 67));
		lblDizicreed.setBackground(Color.WHITE);
		lblDizicreed.setBounds(6, 29, 364, 61);
		panel.add(lblDizicreed);

		JLabel lblCadastroDeDizimista = new JLabel("Cadastro de dizimista");
		lblCadastroDeDizimista.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		lblCadastroDeDizimista.setBounds(6, 67, 382, 61);
		panel.add(lblCadastroDeDizimista);

		JRadioButton rdbtnExtra = new JRadioButton("Extra");
		rdbtnExtra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnExtra.setBounds(209, 336, 76, 25);
		panel.add(rdbtnExtra);

		JRadioButton rdbtnDizimista = new JRadioButton("Dizimista");
		rdbtnDizimista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnDizimista.setBounds(56, 336, 117, 25);
		panel.add(rdbtnDizimista);

		rdbtnExtra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTipoDizimista = "Extra";
				rdbtnDizimista.setSelected(false);
				;
			}
		});

		rdbtnDizimista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTipoDizimista = "Dizimista";
				rdbtnExtra.setSelected(false);
				;
			}
		});

		JLabel lblEscolhaTipo = new JLabel("Escolha o tipo do dizimista:");
		lblEscolhaTipo.setBounds(12, 313, 234, 15);
		panel.add(lblEscolhaTipo);

		JButton btnSalvarDizimista = new JButton("Salvar");
		btnSalvarDizimista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvarDizimista.setBounds(56, 388, 117, 25);
		panel.add(btnSalvarDizimista);

		btnSalvarDizimista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DizimistaDao dizimistaDao = new DizimistaDao();
				if (validaDados()) {

					Dizimista dizimista = new Dizimista(txtNomeDizimista.getText(), txtEmaildizimista.getText(),
							txtCpfDizimista.getText(), txtDataNascDizimista.getText(), txtTipoDizimista);

					dizimistaDao.inserir(dizimista);
					JOptionPane.showMessageDialog(null, "Dizimista cadastrado com sucesso!");
					txtNomeDizimista.setText("");
					txtEmaildizimista.setText("");
					txtCpfDizimista.setText("");
					txtDataNascDizimista.setText("");
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.setBounds(200, 388, 117, 25);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormTelaPrincipal formTelaPrincipal = new FormTelaPrincipal();
				formTelaPrincipal.setVisible(true);
				dispose();
			}
		});
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/icons8-catholic-99.png"));
		lblAvatar.setBounds(66, 396, 110, 106);
		panel.add(lblAvatar);

		JButton btnSair = new JButton("");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setContentAreaFilled(false);
		btnSair.setBorder(null);
		btnSair.setBackground(Color.WHITE);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.setVisible(true);
				dispose();

			}
		});
		btnSair.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/logout3.png"));
		btnSair.setBounds(253, 441, 117, 61);
		panel.add(btnSair);

		JLabel lblImage = new JLabel("");
		lblImage.setBounds(-214, -56, 604, 542);
		contentPane.add(lblImage);
		lblImage.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/mainchurch5.jpg"));
		setLocationRelativeTo(null);
	}
}
