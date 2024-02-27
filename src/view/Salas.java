package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class Salas extends JDialog {
	private JTextField inputOcupa;

	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;

	public Salas() {
		setTitle("Salas");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 614, 403));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setBounds(46, 58, 76, 14);
		getContentPane().add(tipoSala);

		JLabel codSala = new JLabel("Codigo:");
		codSala.setBounds(24, 221, 46, 14);
		getContentPane().add(codSala);

		JLabel andarSala = new JLabel("Andar:");
		andarSala.setBounds(299, 221, 46, 14);
		getContentPane().add(andarSala);

		JLabel ocupSala = new JLabel("Ocupação  máxima:");
		ocupSala.setBounds(251, 262, 110, 14);
		getContentPane().add(ocupSala);

		JLabel numSala = new JLabel("Número:");
		numSala.setBounds(24, 189, 46, 14);
		getContentPane().add(numSala);

		inputOcupa = new JTextField();
		inputOcupa.setColumns(10);
		inputOcupa.setBounds(353, 259, 200, 20);
		getContentPane().add(inputOcupa);

		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(240, 240, 240));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		imgCreate.setBounds(361, 290, 65, 54);
		getContentPane().add(imgCreate);

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarSala();
			}
		});

		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(240, 240, 240));
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		imgUpdate.setBounds(448, 290, 65, 54);
		getContentPane().add(imgUpdate);

		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(240, 240, 240));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		imgDelete.setBounds(534, 291, 65, 54);
		getContentPane().add(imgDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 72, 418, 78);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(215, 176, 46, 27);
		getContentPane().add(btnPesquisar);

		inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] { "", "Sala de Reunião", "Sala de Conferências",
				"Espaço de Evento", "Escritório Privado" }));
		inputCategoria.setBounds(110, 54, 418, 22);
		getContentPane().add(inputCategoria);

		inputCategoria.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buscarSalasNaTabela();
			}
		});

		inputCodigo = new JComboBox();
		inputCodigo.setModel(new DefaultComboBoxModel(new String[] { "", "REU", "CONF", "EVENT", "PRIV" }));
		inputCodigo.setBounds(74, 217, 161, 22);
		getContentPane().add(inputCodigo);

		inputAndar = new JComboBox();
		inputAndar.setModel(
				new DefaultComboBoxModel(new String[] { "", "Subsolo", "Terreo", "1° Andar", "2° Andar", "3° Andar" }));
		inputAndar.setBounds(341, 217, 172, 22);
		getContentPane().add(inputAndar);

		inputNum = new JTextField();
		inputNum.setBounds(80, 186, 125, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);

		btnPesquisar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnBuscarSala();
			}
		});

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JTable tblSalas;
	private JComboBox inputCategoria;
	private JComboBox inputCodigo;
	private JComboBox inputAndar;
	private JTextField inputNum;

	private void adicionarSala() {
		String create = "insert into salas (andarSala, numeroSala, tipoSala, codigoSala, ocupacaoSala) values"
				+ " (?, ?, ?, ?, ?);";

		// Validação da categoria (tipo) da sala
		if (inputCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Categoria da sala obrigatório!");
			inputCategoria.requestFocus();
		}

		// Validação do codigo da sala
		else if (inputCodigo.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Código da sala obrigatório!");
			inputCodigo.requestFocus();
		}

		// Validação do andar da sala
		else if (inputAndar.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Andar da sala obrigatória!");
			inputAndar.requestFocus();
		}

		// Validação da ocupação máxima da sala
		else if (inputOcupa.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ocupação máxima obrigatório!");
			inputOcupa.requestFocus();
		}

		else if (inputNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Número da sala obrigatório!");
			inputNum.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execusão do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

				// Substituir os pontos de interrogação pelo conteúdo das caixas de texto
				// (inputs)
				executarSQL.setString(1, inputCategoria.getSelectedItem().toString());
				executarSQL.setString(2, inputNum.getText());
				executarSQL.setString(3, inputAndar.getSelectedItem().toString());
				executarSQL.setString(4, inputOcupa.getText());
				executarSQL.setString(5, inputCodigo.getSelectedItem().toString());

				// Executar os comandos SQL e inserir a sala no banco de dados
				executarSQL.executeUpdate();

				JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");
				limparCampos();

				conexaoBanco.close();
			}

			catch (SQLIntegrityConstraintViolationException error) {
				JOptionPane.showMessageDialog(null, "Sala já cadastrada ");
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void buscarSalasNaTabela() {
		String readTabela = "select tipoSala as Categoria, andarSala as Andar, numeroSala as Número from salas where tipoSala = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);

			// Substituir o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputCategoria.getSelectedItem().toString());

			// Executar o comando SQL
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utilização da biblioteca rs2xml para "popular"
			// a tabela
			tblSalas.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCaixasTexto() {

		// Criar uma variável para receber a linha da tabela
		int setarLinha = tblSalas.getSelectedRow();
		inputNum.setText(tblSalas.getModel().getValueAt(setarLinha, 2).toString());

	}

	// Criar método para buscar funcionário pelo botão Pesquisar
	private void btnBuscarSala() {
		String readBtn = "select * from salas where numeroSala = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução do comando SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			// Substituir o ponto de interrogação pelo conteúdo da caixa de texto (nome)
			executarSQL.setString(1, inputNum.getText());

			// Executar o comando SQL e exibir o resultado no formulário funcionário (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				// Preencher os campos do formulário
				inputAndar.setSelectedItem(resultadoExecucao.getString(2));
				inputCodigo.setSelectedItem(resultadoExecucao.getString(5));
				inputOcupa.setText(resultadoExecucao.getString(6));

			}

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void atualizarSala() {
		String update = "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), email = ?,"
				+ " perfil = ? where idFuncionario = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(update);

			// Substituir os pontos de interrogação pelo conteúdo das caixas de texto
			// (inputs)
			executarSQL.setString(1, inputCategoria.getSelectedItem().toString());
			executarSQL.setString(2, inputCodigo.getSelectedItem().toString());
			executarSQL.setString(3, inputAndar.getSelectedItem().toString());
			executarSQL.setString(4, inputOcupa.getText());
			executarSQL.setString(5, inputNum.getText());
			

			// Executar os comandos SQL e atualizar a salas no banco de dados
			executarSQL.executeUpdate();

			JOptionPane.showMessageDialog(null, " atualizado com sucesso!");
			limparCampos();

			conexaoBanco.close();
		}

		catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nO login e/ou e-mail já estão sendo usados");
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limparCampos() {
		inputCategoria.setSelectedIndex(-1);

		inputCodigo.setSelectedIndex(-1);
		inputAndar.setSelectedIndex(-1);
		inputOcupa.setText(null);
		inputNum.setText(null);
		inputCategoria.requestFocus();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
