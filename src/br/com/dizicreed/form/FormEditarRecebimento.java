package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
import br.com.dizicreed.dao.RecebimentoDao;
import br.com.dizicreed.model.Dizimista;
import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.model.Recebimento;
import br.com.dizicreed.util.Mascara;

public class FormEditarRecebimento extends JFrame {

	private Recebimento recebimento;
	private RecebimentoDao recebimentoDao;

	private JPanel contentPane;
	private JTextField txtNomeDizimista;
	private JTextField txtDataCadastro;
	private JTextField txtDataReferencia;
	private JTextField txtValorDizimo;
	private JTextField txtCpfDizimista;
	private JTextField txtEmailDizimista;

	public String getNomePeloCpf(String cpf) {

		DizimistaDao dizDao = new DizimistaDao();
		List<Dizimista> dizimistas = dizDao.listar();

		for (Dizimista dizimista : dizimistas) {
			if (dizimista.getCpfDizimista().equals(cpf)) {
				return dizimista.getNomeDizimista();
			}
		}

		return null;

	}

	public String getEmailPeloCpf(String cpf) {

		DizimistaDao dizDao = new DizimistaDao();
		List<Dizimista> dizimistas = dizDao.listar();

		for (Dizimista dizimista : dizimistas) {
			if (dizimista.getCpfDizimista().equals(cpf)) {
				return dizimista.getEmailDizimista();
			}
		}

		return null;

	}

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

	public static boolean dataValida(String dataNascimento) {

		LocalDate hoje = LocalDate.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dataVerificada = LocalDate.parse(dataNascimento, dtf);

		if (dataVerificada.compareTo(hoje) <= 0)
			return true;

		return false;
	}

	public static boolean valorNegativo(String valor) {
		double valor2;

		valor2 = Double.parseDouble(valor);

		if (valor2 < 1)
			return true;

		return false;
	}

	public boolean validaDados() {

		int dia = 0;
		int mes = 0;

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

		if (txtDataCadastro.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de cadastro do dízimo!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			dia = Integer.parseInt(txtDataReferencia.getText().substring(0, 2));
			mes = Integer.parseInt(txtDataReferencia.getText().substring(3, 5));
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Por favor, informe a data de referência do dízimo!", "Erro",
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

		if (!dataValida(txtDataCadastro.getText())) {
			JOptionPane.showMessageDialog(null, "Por favor, informe uma data válida de cadastro!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!dataValida(txtDataReferencia.getText())) {
			JOptionPane.showMessageDialog(null, "A data de referência não pode ser futura!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtValorDizimo.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Por favor, digite o valor a ser doado!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (valorNegativo(txtValorDizimo.getText())) {
			JOptionPane.showMessageDialog(null, "O valor do dízimo não pode ser negativo!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void exibeDadosDoRecebimento() {
		if (this.recebimento != null) {
			txtCpfDizimista.setText(recebimento.getCpfRecebimento());
			txtNomeDizimista.setText(recebimento.getNomeRecebimento());
			txtEmailDizimista.setText(recebimento.getEmailRecebimento());
			txtDataCadastro.setText(recebimento.getDataCadastro());
			txtDataReferencia.setText(recebimento.getDataReferencia());
			txtValorDizimo.setText("" + recebimento.getValorRecebimento());
		}
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
		exibeDadosDoRecebimento();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormEditarRecebimento frame = new FormEditarRecebimento();
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
	public FormEditarRecebimento() {
		setTitle("DiziCreed");
		setResizable(false);
		setBounds(new Rectangle(0, -16, 733, 492));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 498);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, -16, 733, 492));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -16, 733, 492);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNomeDizimista = new JLabel("Nome dizimista:");
		lblNomeDizimista.setBounds(99, 199, 147, 23);
		panel.add(lblNomeDizimista);

		txtNomeDizimista = new JTextField();
		txtNomeDizimista.setColumns(10);
		txtNomeDizimista.setBounds(244, 201, 181, 19);
		panel.add(txtNomeDizimista);

		JLabel lblDataDeCadastro = new JLabel("Data de cadastro:");
		lblDataDeCadastro.setBounds(69, 267, 190, 23);
		panel.add(lblDataDeCadastro);

		txtDataCadastro = new JFormattedTextField(Mascara.getMascaraDataNascimento());
		txtDataCadastro.setColumns(10);
		txtDataCadastro.setBounds(244, 269, 181, 19);
		panel.add(txtDataCadastro);

		Date x = new Date();
		SimpleDateFormat formatarData;
		formatarData = new SimpleDateFormat("dd/MM/yyyy");

		txtDataCadastro.setText(formatarData.format(x));

		JLabel lblDataReferencia = new JLabel("Data de referência:");
		lblDataReferencia.setBounds(79, 302, 190, 23);
		panel.add(lblDataReferencia);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/churchidondemais.png"));
		label_1.setBounds(492, 185, 121, 123);
		panel.add(label_1);

		txtDataReferencia = new JFormattedTextField(Mascara.getMascaraDataNascimento());
		txtDataReferencia.setColumns(10);
		txtDataReferencia.setBounds(244, 302, 181, 19);
		panel.add(txtDataReferencia);

		JLabel lblValorDoDzimo = new JLabel("Valor do dízimo:");
		lblValorDoDzimo.setBounds(99, 337, 121, 23);
		panel.add(lblValorDoDzimo);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(444, 405, 117, 25);
		panel.add(btnExcluir);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RecebimentoDao recDao = new RecebimentoDao();
				List<Recebimento> recebimentos = recDao.listar();

				for (Recebimento recebimento : recebimentos) {
					if (recebimento.getCpfRecebimento().equals(txtCpfDizimista.getText())) {
						recDao.remover(recebimento);
						JOptionPane.showMessageDialog(null, "Recebimento removido com sucesso!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						txtCpfDizimista.setText("");
						txtNomeDizimista.setText("");
						txtEmailDizimista.setText("");
						txtDataCadastro.setText("");
						txtDataReferencia.setText("");
						txtValorDizimo.setText("");

					}
				}
			}
		});
		txtValorDizimo = new JTextField();
		txtValorDizimo.setColumns(10);
		txtValorDizimo.setBounds(244, 337, 181, 19);
		panel.add(txtValorDizimo);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setBounds(189, 12, 359, 78);
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD | Font.ITALIC, 67));
		lblDizicreed.setBackground(Color.WHITE);
		panel.add(lblDizicreed);

		JLabel lblCpfDizimista = new JLabel("Cpf dizimista:");
		lblCpfDizimista.setBounds(99, 167, 147, 23);
		panel.add(lblCpfDizimista);

		txtCpfDizimista = new JFormattedTextField(Mascara.getMascaraCpf());
		txtCpfDizimista.setEditable(false);
		txtCpfDizimista.setColumns(10);
		txtCpfDizimista.setBounds(244, 169, 181, 19);
		panel.add(txtCpfDizimista);

		JLabel lblRecebimentoDeDzimo = new JLabel("Recebimento de dízimo");
		lblRecebimentoDeDzimo.setForeground(Color.BLACK);
		lblRecebimentoDeDzimo.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		lblRecebimentoDeDzimo.setBounds(166, 61, 406, 61);
		panel.add(lblRecebimentoDeDzimo);

		JButton btnContribuir = new JButton("Atualizar");
		btnContribuir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContribuir.setBounds(131, 405, 117, 25);
		panel.add(btnContribuir);

		btnContribuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RecebimentoDao recDao = new RecebimentoDao();
				List<Recebimento> recebimentos = recDao.listar();

				for (Recebimento recebimento : recebimentos) {
					if (recebimento.getCpfRecebimento().equals(txtCpfDizimista.getText())) {
						recDao.remover(recebimento);
						recebimento.setCpfRecebimento(txtCpfDizimista.getText());
						recebimento.setNomeRecebimento(txtNomeDizimista.getText());
						recebimento.setEmailRecebimento(txtEmailDizimista.getText());
						recebimento.setDataCadastro(txtDataCadastro.getText());
						recebimento.setDataReferencia(txtDataReferencia.getText());
						recebimento.setValorRecebimento(Double.parseDouble(txtValorDizimo.getText()));
						if (validaDados()) {
							recDao.inserir(recebimento);
							JOptionPane.showMessageDialog(null, "Recebimento atalizado com sucesso!", "Atualização",
									JOptionPane.INFORMATION_MESSAGE);
							txtCpfDizimista.setText("");
							txtNomeDizimista.setText("");
							txtEmailDizimista.setText("");
							txtDataCadastro.setText("");
							txtDataReferencia.setText("");
							txtValorDizimo.setText("");

						} else {
							txtCpfDizimista.setText("");
							txtNomeDizimista.setText("");
							txtEmailDizimista.setText("");
							txtDataCadastro.setText("");
							txtDataReferencia.setText("");
							txtValorDizimo.setText("");
						}

					}
				}

			}
		});

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/nacle.png"));
		label.setBounds(176, 83, 70, 86);
		panel.add(label);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBounds(288, 405, 117, 25);
		panel.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTelaPrincipal formTelaPrincipal = new FormTelaPrincipal();
				formTelaPrincipal.setVisible(true);
				dispose();

			}
		});

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
		btnSair.setBounds(604, 405, 117, 61);
		panel.add(btnSair);

		JLabel lblEmailDizimista = new JLabel("E-mail dizimista:");
		lblEmailDizimista.setBounds(99, 234, 147, 23);
		panel.add(lblEmailDizimista);

		txtEmailDizimista = new JTextField();
		txtEmailDizimista.setColumns(10);
		txtEmailDizimista.setBounds(244, 234, 181, 19);
		panel.add(txtEmailDizimista);

		JLabel lblImage2 = new JLabel("");
		lblImage2.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/color.jpeg"));
		lblImage2.setBounds(-17, -111, 776, 591);
		panel.add(lblImage2);
		setLocationRelativeTo(null);
	}
}
