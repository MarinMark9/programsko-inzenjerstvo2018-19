package app;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class Sucelje extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sucelje frame = new Sucelje();
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
	public Sucelje() {
		setTitle("Tra\u017Eilica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 13, 160, 463);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOdaberiParametre = new JLabel("Odaberi parametre:");
		lblOdaberiParametre.setBounds(22, 84, 114, 40);
		panel.add(lblOdaberiParametre);
		
		JButton btnTrai = new JButton("Tra\u017Ei");
		btnTrai.setBounds(33, 412, 97, 25);
		panel.add(btnTrai);
		
		JLabel lblNaslov = new JLabel("App");
		lblNaslov.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNaslov.setBounds(51, 36, 85, 35);
		panel.add(lblNaslov);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(184, 13, 599, 463);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Tablica
			//data
		String[][] data = {
				{"rule jedan", "rule dva", "55"}
		};
		
			//header
		//String[] columnHeaders = {"Rule #1", "Rule #2", "Broj ponavljanja"};
		String[] columnHeaders = {"Rule #1"};
		
			//model
		DefaultTableModel dtm = new DefaultTableModel(data, columnHeaders);
		
			//create table
			JTable table = new JTable(dtm);
			table.setBounds(152, 208, 297, 151);
			JScrollPane sp = new JScrollPane(table);
			sp.setLocation(12, 13);
			sp.setSize(575, 437);
			sp.setViewportView(table);
			panel_1.add(sp);
			//panel_1.add(table);
	}
}
