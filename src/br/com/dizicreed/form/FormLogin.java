package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.MatteBorder;

import br.com.dizicreed.dao.FuncionarioDao;
import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.util.Mascara;
import java.awt.Cursor;
import javax.swing.JPasswordField;

@SuppressWarnings("unused")
public class FormLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;

	private static String leituraTeclado() {
		String texto = "";
		Scanner teclado = new Scanner(System.in);
		try {
			if (teclado.hasNextLine()) {
				texto = teclado.nextLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		teclado = null;
		return texto;
	}

	Scanner teclado = new Scanner(System.in);
	private JTextField txtCpf;
	private JPasswordField txtSenha;

	public boolean dadosValidados() {

		FuncionarioDao funcDao = new FuncionarioDao();
		List<Funcionario> funcionarios = funcDao.listar();
		System.out.println(txtSenha.getPassword());
		for (Funcionario funcionario : funcionarios) {

			if (funcionario.getEmailFuncionario().equals(txtEmail.getText())
					&& funcionario.getCpfFuncionario().equals(txtCpf.getText())
					&& funcionario.getSenha().equals(txtSenha.getText())) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
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
	public FormLogin() {
		setTitle("Entrar - DiziCreed");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 513);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(400, 410));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(322, -16, 443, 492);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(-97, -34, 638, 623);
		lblNewLabel.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/church2.jpg"));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBounds(0, -16, 324, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(25, 211, 58, 15);
		panel_1.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEmail.setName("Email");
		txtEmail.setBounds(88, 210, 213, 19);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(36, 262, 58, 15);
		panel_1.add(lblCpf);

		txtCpf = new JFormattedTextField(Mascara.getMascaraCpf());
		txtCpf.setName("Cpf");
		txtCpf.setColumns(10);
		txtCpf.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCpf.setBounds(88, 261, 213, 19);
		panel_1.add(txtCpf);

		JLabel lblIconAvatar = new JLabel("New label");
		lblIconAvatar.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/avatar4.png"));
		lblIconAvatar.setBounds(92, 91, 82, 72);
		panel_1.add(lblIconAvatar);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setBounds(51, 12, 222, 49);
		panel_1.add(lblDizicreed);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD, 26));

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setBackground(Color.GRAY);
		btnEntrar.setBorder(null);
		btnEntrar.setBounds(167, 387, 134, 25);
		panel_1.add(btnEntrar);

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dadosValidados()) {
					JOptionPane.showMessageDialog(null, "Entrando!", "Carregando", JOptionPane.INFORMATION_MESSAGE);
					FormTelaPrincipal formTelaPrincipal = new FormTelaPrincipal();
					formTelaPrincipal.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Credenciais invalidas!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnEsqueceuSenha = new JButton("Esqueceu a senha?");
		btnEsqueceuSenha.setVisible(false);
		btnEsqueceuSenha.setEnabled(false);
		btnEsqueceuSenha.setBorder(null);
		btnEsqueceuSenha.setBackground(Color.WHITE);
		btnEsqueceuSenha.setBounds(0, 455, 184, 25);
		panel_1.add(btnEsqueceuSenha);

		JLabel lblEntrar = new JLabel("Entrar");
		lblEntrar.setFont(new Font("Liberation Sans", Font.BOLD, 26));
		lblEntrar.setBounds(51, 45, 222, 49);
		panel_1.add(lblEntrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMenuPrincipal formMenuPrincipal = new FormMenuPrincipal();
				formMenuPrincipal.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBorder(null);
		btnVoltar.setBackground(Color.GRAY);
		btnVoltar.setBounds(25, 387, 134, 25);
		panel_1.add(btnVoltar);

		txtSenha = new JPasswordField();
		txtSenha.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSenha.setEchoChar('*');
		txtSenha.setBounds(88, 308, 213, 25);
		panel_1.add(txtSenha);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(25, 312, 58, 15);
		panel_1.add(lblSenha);

	}
}
