package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

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
		addwindowListerner(new windowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});
		}
	
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
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tituloLogin.setBounds(228, 11, 221, 20);
		getContentPane().add(tituloLogin);
		
		JLabel imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 265, 46, 59);
		getContentPane().add(imgDatabase);
	}

	DAO dao = new DAO();
	
	private void statusConexaoBanco() {
		try {
			Connection conexaoBannco = dao.conectar();
			
			if (conexaoBanco == null) {
				//Escolher a imagem
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/dataBaseOff.png"))); 
			}
			else {
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/dataBaseOn.png")));
			}
		
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
		});

	}
}
