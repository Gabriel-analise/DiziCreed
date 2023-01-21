package br.com.dizicreed.form;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class FormTelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTelaPrincipal frame = new FormTelaPrincipal();
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
	public FormTelaPrincipal() {
		setTitle("teste");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 514);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(400, 410));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblDizicreed = new JLabel("DIZICREED");
		lblDizicreed.setForeground(Color.RED);
		lblDizicreed.setFont(new Font("Liberation Sans", Font.BOLD, 26));
		lblDizicreed.setBounds(492, 65, 152, 31);
		contentPane.add(lblDizicreed);

		JButton btnCadastrarDizimista = new JButton("Cadastrar dizimista");
		btnCadastrarDizimista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarDizimista.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnCadastrarDizimista.setBounds(471, 126, 173, 25);
		contentPane.add(btnCadastrarDizimista);

		btnCadastrarDizimista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormCadastroDizimista formCadastroDizimista = new FormCadastroDizimista();
				formCadastroDizimista.setVisible(true);
				dispose();
			}
		});

		JButton btnListarRecebidos = new JButton("Listar recebidos");
		btnListarRecebidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnListarRecebidos.setBounds(471, 200, 173, 25);
		contentPane.add(btnListarRecebidos);
		btnListarRecebidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecebimentosList recList = new RecebimentosList();
				recList.setVisible(true);
				dispose();
			}
		});

		JButton btnSair = new JButton("");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setContentAreaFilled(false);
		btnSair.setBorder(null);
		btnSair.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/logout3.png"));
		btnSair.setBounds(606, 386, 117, 98);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormLogin formLogin = new FormLogin();
				formLogin.setVisible(true);
				dispose();
			}
		});

		JButton btnReceberDizimo = new JButton("Receber dízimo");
		btnReceberDizimo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReceberDizimo.setBounds(471, 163, 173, 25);
		contentPane.add(btnReceberDizimo);

		JButton btnEditarFuncionriosCadastrados = new JButton("Editar funcionários cadastrados");
		btnEditarFuncionriosCadastrados.setBounds(405, 237, 279, 25);
		contentPane.add(btnEditarFuncionriosCadastrados);

		btnEditarFuncionriosCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormEditarFuncionario formEditarFunc = new FormEditarFuncionario();
				formEditarFunc.setVisible(true);
				dispose();
			}
		});
		
		JButton btnEditarDizimistasCadastrados = new JButton("Editar dizimistas cadastrados");
		btnEditarDizimistasCadastrados.setBounds(405, 275, 279, 25);
		contentPane.add(btnEditarDizimistasCadastrados);

		btnEditarDizimistasCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormEditarDizimista formEdtDiz = new FormEditarDizimista();
				formEdtDiz.setVisible(true);
				dispose();
			}
		});
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/church10.jpg"));
		lblImg.setBounds(-36, -48, 771, 532);
		contentPane.add(lblImg);

		btnReceberDizimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormReceberDizimo formReceberDizimo = new FormReceberDizimo();
				formReceberDizimo.setVisible(true);
				dispose();
			}
		});

	}
}
