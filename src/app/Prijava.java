package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Prijava extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblLozinka;
	private JLabel lblNeispravnaPrijava;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prijava frame = new Prijava();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prijava() {
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(391, 90, 185, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(391, 180, 185, 27);
		contentPane.add(passwordField);
		
		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		lblKorisnikoIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKorisnikoIme.setBounds(391, 61, 99, 16);
		contentPane.add(lblKorisnikoIme);
		
		lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(391, 151, 99, 16);
		contentPane.add(lblLozinka);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ime = textField.getText();
				char[] pass = passwordField.getPassword();
				String password = new String(pass);
						
				if((ime.equals("admin")) && (password.equals("admin123"))) {
					//ispravni podaci
					JOptionPane.showMessageDialog(null, "Podaci su ispravni!");
					dispose();
					Sucelje glavno = new Sucelje();
					glavno.setVisible(true);
				} else {
					passwordField.setText("");
					lblNeispravnaPrijava.setVisible(true);
					
				}
				
			}
		});
		btnPrijava.setForeground(SystemColor.controlDkShadow);
		btnPrijava.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnPrijava.setBounds(432, 273, 113, 32);
		contentPane.add(btnPrijava);
		
		JCheckBox chckbxPrikaiLozinku = new JCheckBox("Prika\u017Ei lozinku");
		chckbxPrikaiLozinku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxPrikaiLozinku.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('\u25CF');
				}
			}
		});
		chckbxPrikaiLozinku.setBounds(391, 212, 113, 25);
		contentPane.add(chckbxPrikaiLozinku);
		
		lblNeispravnaPrijava = new JLabel("Neispravna prijava!");
		lblNeispravnaPrijava.setForeground(new Color(178, 34, 34));
		lblNeispravnaPrijava.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNeispravnaPrijava.setBounds(428, 333, 135, 27);
		lblNeispravnaPrijava.setVisible(false);
		contentPane.add(lblNeispravnaPrijava);
		
		JLabel lblNewLabel = new JLabel("Tra\u017Eilica");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel.setBounds(123, 77, 155, 76);
		contentPane.add(lblNewLabel);
		
		JLabel lblPretraiInformacijeO = new JLabel("Pretra\u017Ei informacije o zlo\u010Dinima ");
		lblPretraiInformacijeO.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblPretraiInformacijeO.setBounds(69, 180, 231, 76);
		contentPane.add(lblPretraiInformacijeO);
	}
}
