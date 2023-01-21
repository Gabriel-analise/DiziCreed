package br.com.dizicreed.form;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.table.TableModel;

import br.com.DiziCreed.tableModel.RecebimentoTableModel;
import br.com.dizicreed.dao.FuncionarioDao;
import br.com.dizicreed.dao.RecebimentoDao;
import br.com.dizicreed.model.Funcionario;
import br.com.dizicreed.model.Recebimento;
import br.com.dizicreed.util.Mascara;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class RecebimentosList extends JFrame {

	private JPanel contentPane;
	private JTable tbDados;
	private RecebimentoTableModel recebimentoTableModel;
	private RecebimentoDao recebimentoDao;
	private List<Recebimento> recebimentos;
	private JTextField txtFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecebimentosList frame = new RecebimentosList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo exportar tabela para Excel
	public void toExcel(JTable table, File file) throws IOException {
		TableModel model = table.getModel();
		FileWriter excel = new FileWriter(file);

		for (int i = 0; i < model.getColumnCount(); i++) {
			String nomeColuna = model.getColumnName(i).replaceAll("\\s+", "");
			excel.write(nomeColuna.toUpperCase() + "\t");
		}

		excel.write("\n");

		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				String linha = model.getValueAt(i, j).toString();
				linha = linha.replaceAll("\\s+", "");
				excel.write(linha.toString() + "\t");
			}
			excel.write("\n");
		}

		excel.close();
		System.out.println("Arquivo " + file + " exportado com sucesso!");
	}

	public void cadastraNovoDizimo() {
		FormReceberDizimo formReceber = new FormReceberDizimo();
		formReceber.setRecebimento(null);
		formReceber.setVisible(true);
	}

	public void consultaDizimo() {
		RecebimentoDao recDao = new RecebimentoDao();
		recebimentos = recDao.listar();
		recebimentoTableModel.removeAllItems();
		if (txtFiltro.getText().length() > 0) {
			for (Recebimento recebimento : recebimentos) {
				int filtroComprimento = txtFiltro.getText().length();
				int CpfComprimento = recebimento.getCpfRecebimento().length();
				if (filtroComprimento <= CpfComprimento) {
					if (recebimento.getCpfRecebimento().substring(0, txtFiltro.getText().length())
							.equals(txtFiltro.getText())) {
						recebimentoTableModel.addItem(recebimento);
					}

				}

			}
		} else {
			for (Recebimento recebimento : recebimentos) {
				recebimentoTableModel.addItem(recebimento);
			}

		}
	}

	public Recebimento getRecebimentoSelecionado() {
		int linha = tbDados.getSelectedRow();
		String cpf = tbDados.getValueAt(linha, 0).toString();
		String nome = tbDados.getValueAt(linha, 1).toString();
		String email = tbDados.getValueAt(linha, 2).toString();
		String valor = tbDados.getValueAt(linha, 5).toString();

		for (Recebimento recebimento : recebimentos) {
			if (recebimento.getCpfRecebimento().equals(cpf) && recebimento.getNomeRecebimento().equals(nome)
					&& recebimento.getEmailRecebimento().equals(email)
					&& recebimento.getValorRecebimento() == Double.parseDouble(valor)) {
				return recebimento;
			}
		}
		return null;
	}

	public void editaRecebimento() {
		Recebimento recebimento = getRecebimentoSelecionado();
		if (recebimento != null) {
			FormEditarRecebimento formEdtReceb = new FormEditarRecebimento();
			formEdtReceb.setRecebimento(recebimento);
			formEdtReceb.setVisible(true);
		}
	}

	/**
	 * Create the frame.
	 */
	public RecebimentosList() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/dev8/Documentos/Facur/Images/igrrja.jpg"));
		setBounds(new Rectangle(0, 0, 0, 0));
		setResizable(false);
		setTitle("Recebimentos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 663);
		contentPane = new JPanel();

		// centraliza a tela
		setLocationRelativeTo(null);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 1258, 380);
		contentPane.add(scrollPane);

		recebimentoDao = new RecebimentoDao();
		recebimentos = recebimentoDao.listar();

		// Declaração da tabela
		recebimentoTableModel = new RecebimentoTableModel(recebimentos);
		tbDados = new JTable(recebimentoTableModel);

		// Fonte do cabeçalho
		tbDados.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));

		// tamanho da fonte das linhas dos registros
		tbDados.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		// tamanho da linha dos registros
		tbDados.setRowHeight(50);

		scrollPane.setViewportView(tbDados);

		// Declaração do botão Novo
		JButton btnNovo = new JButton("Novo");
		btnNovo.setContentAreaFilled(false);
		btnNovo.setBackground(Color.LIGHT_GRAY);
		btnNovo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNovo.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/nun.png"));
		btnNovo.setBounds(47, 543, 152, 65);
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNovo.setToolTipText("Cadastrar um novo dízimo");
		btnNovo.addActionListener(new ActionListener() {
			// Método que é acionando ao clicar no botão Novo
			public void actionPerformed(ActionEvent e) {
				cadastraNovoDizimo();
				dispose();
			}
		});

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setVisible(false);
		lblFiltro.setBounds(12, 12, 187, 50);
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblFiltro);
		contentPane.add(btnNovo);

		// Declaração do botão Remover
		JButton btnSair = new JButton("Sair");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnSair.setContentAreaFilled(false);
		btnSair.setBounds(214, 558, 122, 50);
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSair.setToolTipText("Fechar o sistema");
		btnSair.addMouseListener(new MouseAdapter() {
			// Método que é acionando ao clicar no botão Sair
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnSair);

		txtFiltro = new JFormattedTextField(Mascara.getMascaraCpf());
		txtFiltro.setVisible(false);
		txtFiltro.setBounds(10, 62, 551, 25);
		txtFiltro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFiltro.setToolTipText("Informe o Cpf do dizimista que você deseja encontrar!");
		contentPane.add(txtFiltro);

		// Declaração do botão Pesquisa
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.setVisible(false);
		btnPesquisa.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/SearchChurch.png"));
		btnPesquisa.setBounds(588, 50, 208, 50);
		btnPesquisa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPesquisa.setToolTipText("Filtrar os recebidos pelo cpf de quem doou!");
		btnPesquisa.addActionListener(new ActionListener() {
			// Método que é acionando ao clicar no botão Pesquisa
			public void actionPerformed(ActionEvent e) {
				consultaDizimo();
			}
		});
		contentPane.add(btnPesquisa);

		JButton btnExportarDados = new JButton("");
		btnExportarDados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExportarDados.setContentAreaFilled(false);
		btnExportarDados.setBorder(new MatteBorder(0, 0, 0, 0, (Color) Color.WHITE));
		btnExportarDados.setIcon(new ImageIcon("/home/dev8/Documentos/Facur/Images/EXCEL2.png"));
		btnExportarDados.setBounds(42, 99, 85, 48);
		btnExportarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int option = fc.showSaveDialog(tbDados);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filename = fc.getSelectedFile().getName();
					String path = fc.getSelectedFile().getParentFile().getPath();
					int len = filename.length();
					String ext = "";
					String file = "";
					if (len > 4) {
						ext = filename.substring(len - 4, len);
					}
					if (ext.equals(".xls")) {
						file = path + "\\" + filename;
					} else {
						file = path + "\\" + filename + ".xls";
					}
					try {
						toExcel(tbDados, new File(file));
					} catch (IOException ex) {
						Logger.getLogger(FormReceberDizimo.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}
		});
		btnExportarDados.setToolTipText("Exportar os dados para excel!");
		btnExportarDados.setFont(new Font("Dialog", Font.PLAIN, 20));
		contentPane.add(btnExportarDados);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.setBounds(366, 558, 122, 50);
		btnVoltar.setToolTipText("Fechar o sistema");
		btnVoltar.setFont(new Font("Dialog", Font.PLAIN, 20));
		contentPane.add(btnVoltar);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormTelaPrincipal formP = new FormTelaPrincipal();
				formP.setVisible(true);
				dispose();

			}
		});

		tbDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editaRecebimento();
					dispose();
				}

			}
		});
	}
}
