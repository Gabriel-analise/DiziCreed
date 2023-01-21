package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import java.awt.Cursor;

public class FormMenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMenuPrincipal frame = new FormMenuPrincipal();
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
	public FormMenuPrincipal() {
		setTitle("DiziCreed");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 514);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(400, 410));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBounds(0, -16, 733, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setBackground(Color.WHITE);
		lblDizicreed.setBounds(203, 40, 431, 61);
		panel_1.add(lblDizicreed);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD | Font.ITALIC, 67));

		JLabel lblBemVindo = new JLabel("Bem vindo");
		lblBemVindo.setForeground(Color.RED);
		lblBemVindo.setBackground(Color.WHITE);
		lblBemVindo.setFont(new Font("Liberation Sans", Font.BOLD, 33));
		lblBemVindo.setBounds(273, 113, 222, 49);
		panel_1.add(lblBemVindo);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBorder(new MatteBorder(3, 0, 0, 3, (Color) new Color(64, 64, 64)));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin telaLogin = new FormLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnEntrar.setBounds(69, 430, 117, 25);
		panel_1.add(btnEntrar);

		JButton btnCadastrarFuncionrio = new JButton("Cadastrar novo funcionário");
		btnCadastrarFuncionrio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarFuncionrio.setBorder(new MatteBorder(3, 0, 0, 3, (Color) new Color(64, 64, 64)));
		btnCadastrarFuncionrio.setBackground(Color.WHITE);
		btnCadastrarFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCadastroFuncionario formCadastroFuncionario = new FormCadastroFuncionario();
				dispose();
				formCadastroFuncionario.setVisible(true);
			}
		});
		btnCadastrarFuncionrio.setBounds(222, 430, 227, 25);
		panel_1.add(btnCadastrarFuncionrio);

		JLabel lblMenuPrincipal = new JLabel("Menu principal");
		lblMenuPrincipal.setFont(new Font("FreeSans", Font.BOLD, 20));
		lblMenuPrincipal.setBounds(273, 398, 176, 29);
		panel_1.add(lblMenuPrincipal);

		JButton btnSair = new JButton("Sair");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setBorder(new MatteBorder(3, 0, 0, 3, (Color) new Color(64, 64, 64)));
		btnSair.setBackground(Color.WHITE);
		btnSair.setBounds(483, 430, 117, 25);
		panel_1.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(0, 398, 733, 94);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblChurch = new JLabel("");
		lblChurch.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblChurch.setBackground(Color.BLACK);
		lblChurch.setBounds(-141, -102, 1389, 686);
		panel_1.add(lblChurch);
		lblChurch.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/mainschurch7.jpeg"));
	}
}
