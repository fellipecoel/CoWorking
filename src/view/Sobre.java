package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Sobre extends JDialog {
	public Sobre() {
		getContentPane().setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setBounds(153, 11, 108, 26);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titulo);
	}

	
	public static void main(String[] args) {
		

	}
    
	
}
