package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Salas extends JDialog {

	
		
		public Salas() {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			getContentPane().setBackground(new Color(192, 192, 192));
			getContentPane().setLayout(null);
			setTitle("Salas");
			setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
			setBounds(new Rectangle(300, 100, 767, 486));
			
			

			
		
			
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
