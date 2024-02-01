package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Sobre extends JDialog {
	public Sobre() {
		setTitle("Sobre");
		setBounds(new Rectangle(100, 100, 613, 405));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//Será explicado ainda hoje, após criamos outra janela
		setModal(true);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setForeground(new Color(0, 0, 0));
		titulo.setBounds(10, 29, 588, 34);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titulo);
		
		JLabel descricao1 = new JLabel("O software CoWorking trata-se de um protótipo cujo objetivo é ");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setForeground(new Color(0, 0, 0));
		descricao1.setBackground(new Color(255, 255, 255));
		descricao1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descricao1.setBounds(10, 82, 588, 47);
		getContentPane().add(descricao1);
		
		JLabel descricao2 = new JLabel(" possibilitar ogerenciamento de reserva de salas em um espaço colaborativo.\r\n");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setForeground(new Color(0, 0, 0));
		descricao2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descricao2.setBackground(Color.WHITE);
		descricao2.setBounds(30, 127, 568, 54);
		getContentPane().add(descricao2);
		
		JLabel versao = new JLabel("Versão: 1.0.0\r\n");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setForeground(new Color(0, 0, 0));
		versao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		versao.setBackground(Color.WHITE);
		versao.setBounds(162, 178, 280, 54);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setForeground(new Color(0, 0, 0));
		atualizacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		atualizacao.setBackground(Color.WHITE);
		atualizacao.setBounds(189, 228, 253, 42);
		getContentPane().add(atualizacao);
		
		JLabel imgMIT = new JLabel("");
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(542, 313, 46, 47);
		getContentPane().add(imgMIT);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
