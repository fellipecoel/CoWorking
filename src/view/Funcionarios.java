package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Funcionarios extends JDialog  {
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputEmail;
	private JTextField inputPerfil;
	private JPasswordField inputSenha;

	
		public Funcionarios() {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			getContentPane().setBackground(new Color(192, 192, 192));
			getContentPane().setLayout(null);
			
			JLabel nomeFunc = new JLabel("Nome:");
			nomeFunc.setBounds(27, 67, 46, 14);
			getContentPane().add(nomeFunc);
			
			JLabel loginFunc = new JLabel("Login:");
			loginFunc.setBounds(27, 132, 46, 14);
			getContentPane().add(loginFunc);
			
			JLabel emailFunc = new JLabel("E-mail:");
			emailFunc.setBounds(27, 203, 46, 14);
			getContentPane().add(emailFunc);
			
			JLabel senhaFunc = new JLabel("Senha:");
			senhaFunc.setBounds(313, 135, 46, 14);
			getContentPane().add(senhaFunc);
			
			JLabel perfilFunc = new JLabel("Perfil:");
			perfilFunc.setBounds(314, 203, 46, 14);
			getContentPane().add(perfilFunc);
			
			inputNome = new JTextField();
			inputNome.setBounds(71, 64, 469, 20);
			getContentPane().add(inputNome);
			inputNome.setColumns(10);
			
			inputLogin = new JTextField();
			inputLogin.setBounds(71, 129, 183, 20);
			getContentPane().add(inputLogin);
			inputLogin.setColumns(10);
			
			inputEmail = new JTextField();
			inputEmail.setBounds(71, 200, 183, 20);
			getContentPane().add(inputEmail);
			inputEmail.setColumns(10);
			
			inputPerfil = new JTextField();
			inputPerfil.setBounds(357, 200, 183, 20);
			getContentPane().add(inputPerfil);
			inputPerfil.setColumns(10);
			
			inputSenha = new JPasswordField();
			inputSenha.setBounds(357, 132, 183, 20);
			getContentPane().add(inputSenha);
			
			JLabel imgCreate = new JLabel("");
			imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
			imgCreate.setBounds(322, 293, 60, 52);
			getContentPane().add(imgCreate);
			
			JLabel imgUpdate = new JLabel("");
			imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
			imgUpdate.setBounds(419, 293, 60, 52);
			getContentPane().add(imgUpdate);
			
			JLabel imgDelete = new JLabel("");
			imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
			imgDelete.setBounds(510, 293, 60, 52);
			getContentPane().add(imgDelete);
			setTitle("Funcionarios");
			setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
			setBounds(new Rectangle(300, 100, 625, 395));

			
			
			
			
		}
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Funcionarios dialog = new Funcionarios();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						
					
						

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			});
			
			
			
	}
}
