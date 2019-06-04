package app;
import fpgrowth.*; 
//import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
//import javax.swing.JComboBox;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JList;
//import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
//import javax.swing.ListSelectionModel;

public class Sucelje extends JFrame {

	private JPanel contentPane;
//	private JTable table;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
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
	}*/

	/**
	 * Create the frame.
	 */
	public Sucelje() {
		setTitle("Tra\u017Eilica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1000, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 13, 170, 650);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOdaberiParametre = new JLabel("Odaberi parametre:");
		lblOdaberiParametre.setBounds(22, 84, 150, 40);
		panel.add(lblOdaberiParametre);
		
		JButton btnTrai = new JButton("Tra\u017Ei");
		btnTrai.setBounds(33, 412, 97, 25);
		panel.add(btnTrai);
		
		JLabel lblNaslov = new JLabel("App");
		lblNaslov.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNaslov.setBounds(51, 36, 100, 35);
		panel.add(lblNaslov);
		JTextArea txt = new JTextArea(10, 10);
		txt.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(184, 13, 995, 650);
		panel_1.add(txt);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Tablica
			//data
		/*String[][] data = {
				{"rule jedan", "desc1"},
				{"rule dva", "desc2 "}
		};*/
		
			//header
		//String[] columnHeaders = {"Rule", "Rule description"};
		//String[] columnHeaders = {"Rule #1"};
		
			//model
		//DefaultTableModel dtm = new DefaultTableModel(data, columnHeaders);
		
			//create table
		/*	JTable table = new JTable(dtm);
			table.setBounds(152, 208, 297, 151);
			*/JScrollPane sp = new JScrollPane(/*table*/txt);
			sp.setLocation(12, 13);
			sp.setSize(975, 635);
			//sp.setViewportView(table);
			panel_1.add(sp);
			//panel_1.add(table);
			this.setVisible(true);
			btnTrai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int threshold = 2;
					/*String query = "select zona.`Zone/Beat`, a.`Event Clearance Group`, a.`Event Clearance Description` "
			        		+ "from zona inner join (select grupa.`Event Clearance Group`, zlocin.* from zlocin inner join grupa "
			        		+ "where zlocin.`Event Clearance Code` = grupa.`Event Clearance Code`) as a where zona.`Hundred Block Location` = a.`Hundred Block Location`";*/
					String query = ("select `Zone/Beat`, `Event Clearance Group`, `Event Clearance Date` from (select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location`, `Zone/Beat` from "
							+ "(select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location` from zlocin) as b "
							+ "natural join (select distinct `Hundred Block Location`, `Zone/Beat` from zona) as c) as a "
							+ "natural join (select distinct `Event Clearance Code`, `Event Clearance Group` from grupa) as d");
			        String[] header = {"Zone/Beat", "Event Clearance Group", "Event Clearance Date"};
			        ArrayList<Rule> resultRules = new FPGrowth(query, 50, header).returnResult();
			        int cnt = 0;
			        for (Rule s : resultRules) {
			        	cnt++;
			        	txt.append("Rule #" + cnt + '\n' + s.toString() + '\n');
			        }
			        System.out.println("Lines: " + cnt);
				}
			}); 
	}
}
