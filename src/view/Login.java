package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel imgDatabase;

	public Login() {

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});

		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 441, 305));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(100, 100, 584, 374));
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLogin.setBounds(265, 57, 87, 20);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setBounds(262, 150, 80, 32);
		getContentPane().add(txtSenha);

		inputLogin = new JTextField();
		inputLogin.setBounds(180, 88, 206, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(180, 193, 206, 20);
		getContentPane().add(inputSenha);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(228, 284, 114, 23);
		getContentPane().add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tituloLogin.setBounds(228, 11, 221, 20);
		getContentPane().add(tituloLogin);

		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 265, 46, 59);
		getContentPane().add(imgDatabase);
	}

	DAO dao = new DAO();

	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();

			if (conexaoBanco == null) {
				// Escolher a imagem
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/dataBaseOff.png")));
			} else {
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/dataBaseOn.png")));
			}

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void logar() {
		String read = "select * from funcionario where login=?" + "and senha=md5(?)";

		// Validação do login do usuário
		if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigatório");
			inputLogin.requestFocus();
		}

		// Validação da senha do usuário
		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigatório");
			inputSenha.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execusão do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);

				// Atribuir valores dde login e senha
				// Substituir as interrogações ? ? pelo conteúdo da caixa de texto (input)
				executarSQL.setString(1, inputLogin.getText());
				executarSQL.setString(2, inputSenha.getText());

				// Excutar os comandos SQL e de acordo com o resultado liberar os recursos na
				// tela
				ResultSet resultadoExecucao = executarSQL.executeQuery();

				// Validação do funcionário (autenticação)
				// resultadoExecucao.next() significa que o login e a senha existem, ou seja,
				// correspondem

				if (resultadoExecucao.next()) {

					Home Home = new Home();
					Home.setVisible(true);

					// Fechar a janela de Login assim que a janela Home abrir (automaticamente)
					dispose();
				}

				else {
					// Criar um alerta (pop-up) que informe ao usuário que login e/ou senha estão
					// inválidos
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido");
					inputLogin.setText(null);
					inputSenha.setText(null);
					inputLogin.requestFocus();
				}

				// Fechamento da chave do bloco try
				conexaoBanco.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

	}
}
