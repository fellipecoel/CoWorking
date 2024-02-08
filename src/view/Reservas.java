package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Reservas extends JDialog {


		
		public Reservas() {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			getContentPane().setBackground(new Color(192, 192, 192));
			getContentPane().setLayout(null);
			setTitle("Reservas");
			setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
			setBounds(new Rectangle(300, 100, 767, 486));

			
			

	}
	
		
			public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Reservas dialog = new Reservas();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				});
}

}
