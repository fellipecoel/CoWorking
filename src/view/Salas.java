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
		numSala.setBounds(24, 262, 46, 14);
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
				//adicionarFuncionario();
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
		btnPesquisar.setBounds(181, 187, 46, 27);
		getContentPane().add(btnPesquisar);

		inputID = new JTextField();
		inputID.setBounds(74, 187, 97, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);

		JLabel idSala = new JLabel("ID:");
		idSala.setBounds(55, 187, 67, 14);
		getContentPane().add(idSala);

		inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] { "", "Sala de Reunião", "Sala de Conferências",
				"Espaço de Evento", "Escritório Privado" }));
		inputCategoria.setBounds(110, 54, 418, 22);
		getContentPane().add(inputCategoria);

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
		inputNum.setBounds(80, 259, 125, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);

		btnPesquisar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//btnBuscarFuncionario();
			}
		});

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//setarCaixaTexto();
			}
		});
	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JTable tblSalas;
	private JTextField inputID;
	private JComboBox inputCategoria;
	private JComboBox inputCodigo;
	private JComboBox inputAndar;
	private JTextField inputNum;

	/*
	 * private void adicionarFuncionario() { String create =
	 * "insert into funcionario (nomeFunc, login, senha, perfil,  email) values (?, ?, md5(?), ?, ?);"
	 * ;
	 * 
	 * 
	 * try { // Estabelecer a conexão Connection conexaoBanco = dao.conectar();
	 * 
	 * // Preparar a execusão do script SQL PreparedStatement executarSQL =
	 * conexaoBanco.prepareStatement(create);
	 * 
	 * // Substituir os pontos de interrogação pelo conteúdo das caixas de texto //
	 * (inputs) executarSQL.setString(1, inputNome.getText());
	 * executarSQL.setString(2, inputLogin.getText()); executarSQL.setString(3,
	 * inputSenha.getText());
	 * 
	 * executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
	 * 
	 * executarSQL.setString(5, inputOcupa.getText());
	 * 
	 * // Executae os comandos SQL e inserir o funcionário no banco de dados
	 * executarSQL.executeUpdate();
	 * 
	 * JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
	 * 
	 * limparCampos(); }
	 * 
	 * catch (SQLIntegrityConstraintViolationException erro) {
	 * JOptionPane.showMessageDialog(null,
	 * "Login em uso. \nEscolha outro nome de usuário.");
	 * 
	 * }
	 * 
	 * catch (Exception e) { System.out.println(e); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * private void setarCaixaTexto() {
	 * 
	 * // Criar uma variável para receber a linha da tabela int setarLinha =
	 * tblSalas.getSelectedRow();
	 * 
	 * inputNome.setText(tblSalas.getModel().getValueAt(setarLinha,1).toString());
	 * inputID.setText(tblSalas.getModel().getValueAt(setarLinha,0).toString());
	 * 
	 * //inputID.setText(tblSalas.getModel().getValueAt(setarLinha,
	 * //2).toString()); }
	 * 
	 * private void btnBuscarFuncionario() {
	 * 
	 * String readBtn = "select * from funcionario where idFuncionario = ?;";
	 * 
	 * try {
	 * 
	 * Connection conexaoBanco = dao.conectar();
	 * 
	 * PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);
	 * 
	 * 
	 * 
	 * executarSQL.setString(1, inputID.getText());
	 * 
	 * //Executar o comando SQL e exibir o resultado no fomulario funcionario (todos
	 * os seus dados) ResultSet resultadoExecucao = executarSQL.executeQuery();
	 * 
	 * if(resultadoExecucao.next()) { //preencher os campos do formulario
	 * inputLogin.setText(resultadoExecucao.getString(3));
	 * inputSenha.setText(resultadoExecucao.getString(4));
	 * inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
	 * inputOcupa.setText(resultadoExecucao.getString(6)); //Propositalmete a
	 * professora não colocou o email
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * catch (Exception e) { System.out.println(e);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * private void limparCampos() { inputNome.setText(null);
	 * inputLogin.setText(null); inputSenha.setText(null); inputOcupa.setText(null);
	 * // Para limpar componente JcomboBox inputPerfil.setSelectedItem(null); //
	 * Posicionar o cursor de volta no campo Nome inputNome.requestFocus(); }
	 * 
	 * 
	 * private void atualizar() { String updateBtn =
	 * "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), perfil = ?, email = ? where idFuncionario = ?;"
	 * ;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * try {
	 * 
	 * Connection conexaoBanco = dao.conectar();
	 * 
	 * PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);
	 * 
	 * executarSQL.setString(1, inputNome.getText()); executarSQL.setString(2,
	 * inputLogin.getText()); executarSQL.setString(3, inputSenha.getText());
	 * executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
	 * executarSQL.setString(5, inputOcupa.getText()); executarSQL.setString(6,
	 * inputID.getText());
	 * 
	 * executarSQL.executeUpdate();
	 * 
	 * JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
	 * 
	 * limparCampos();
	 * 
	 * conexaoBanco.close();
	 * 
	 * } catch (SQLIntegrityConstraintViolationException error) {
	 * JOptionPane.showMessageDialog(null,
	 * "Login e/ou email em uso. \nEscolha novos dados."); limparCampos();
	 * 
	 * }
	 * 
	 * catch (Exception e) { System.out.println(e);
	 * 
	 * } } }
	 * 
	 */

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
