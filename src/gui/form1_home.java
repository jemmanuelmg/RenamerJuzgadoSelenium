package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import launcher.SeleniumLauncher;

public class form1_home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form1_home window = new form1_home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public form1_home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Image mainLogo = new ImageIcon(this.getClass().getResource("/gears-icon.png")).getImage();
		
		frame = new JFrame("Sistema Mili 2.0");
		frame.setBounds(100, 100, 357, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(mainLogo);
		
		Image img  = new ImageIcon(this.getClass().getResource("/rama-logo.png")).getImage();
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Constantia", Font.PLAIN, 19));
		btnNewButton.setBackground(new Color(0, 0, 139));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SeleniumLauncher.start();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 0, 0, 0));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Renombrar archivos");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Constantia", Font.PLAIN, 20));
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(img));
		panel_1.add(lblNewLabel);
				
	}

}
