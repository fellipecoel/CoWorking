package view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.DateFormat;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Home extends JDialog {



	public JPanel panelUsuario;
	public JLabel txtUsuarioLogado;
	public JLabel txtData;

	// Construtor
	public Home() {
		addWindowListener (new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				Date dataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(dataSistema));
			}
		});
		
		
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Home");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(300, 100, 767, 486));
		getContentPane().setLayout(null);

		JButton btnUser = new JButton("");
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setBorderPainted(false);
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(21, 11, 105, 105);
		getContentPane().add(btnUser);

		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Funcionarios func = new Funcionarios();

				func.setVisible(true);
			}
		});

		JButton btnRoom = new JButton("");
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(291, 11, 114, 105);
		getContentPane().add(btnRoom);

		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Salas sala = new Salas();

				sala.setVisible(true);
			}
		});

		JButton btnReserve = new JButton("");
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setBorderPainted(false);
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(569, 22, 124, 105);
		getContentPane().add(btnReserve);
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 375, 750, 72);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setBounds(10, 23, 150, 14);
		panelUsuario.add(txtUsuarioLogado);
		
		 txtData = new JLabel("");
		txtData.setBounds(450, 23, 205, 35);
		panelUsuario.add(txtData);

		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Reservas reserva = new Reservas();

				reserva.setVisible(true);
			}
		});


	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
	}
}
