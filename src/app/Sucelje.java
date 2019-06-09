package app;
import fpgrowth.*; 

import java.util.ArrayList;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


public class Sucelje extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	String headers = "";
	
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
		panel.setBounds(12, 13, 195, 650);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnTrai = new JButton("Tra\u017Ei");
		btnTrai.setBounds(28, 329, 97, 25);
		panel.add(btnTrai);
		
		JLabel lblNaslov = new JLabel("Rule mining");
		lblNaslov.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNaslov.setBounds(9, 31, 165, 35);
		panel.add(lblNaslov);
		
		JLabel label = new JLabel("Threshold:");
		label.setBounds(45, 82, 86, 16);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(23, 106, 125, 22);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Odaberi parametre:");
		label_1.setBounds(23, 141, 150, 40);
		panel.add(label_1);
		
		JCheckBox checkBox = new JCheckBox("At scene time");
		checkBox.setBounds(8, 178, 125, 25);
		/*checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox.isSelected()) {
					headers += "`Event Clearance Date`, ";
				} else {
					headers = headers.replace("`Event Clearance Date`,", "");
				}
			}
		});*/
		panel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Event description");
		checkBox_1.setBounds(8, 201, 150, 25);
		/*checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox_1.isSelected()) {
					headers += "`Event Clearance Description`, ";
				} else {
					headers = headers.replace("`Event Clearance Description`,", "");
				}
			}
		});*/
		panel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Event code");
		checkBox_2.setBounds(8, 224, 113, 25);
		/*checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox_2.isSelected()) {
					headers += "`Event Clearance Code`, ";
				} else {
					headers = headers.replace("`Event Clearance Code`,", "");
				}
			}
		});*/
		panel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Block location");
		checkBox_3.setBounds(8, 247, 125, 25);
		/*checkBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox_3.isSelected()) {
					headers += "`Hundred Block Location`, ";
				} else {
					headers = headers.replace("`Hundred Block Location`,", "");
				}
			}
		});*/
		panel.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("Initial type desc.");
		checkBox_4.setBounds(8, 269, 150, 25);
		/*checkBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox_4.isSelected()) {
					headers += "`Initial Type Description`, ";
				} else {
					headers = headers.replace("`Initial Type Description`,", "");
				}
			}
		});*/
		panel.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("Zone");
		checkBox_5.setBounds(8, 290, 113, 25);
		/*checkBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox_5.isSelected()) {
					headers += "`Zone/Beat`, ";
				} else {
					headers = headers.replace("`Zone/Beat `,", "");
				}
			}
		});*/
		panel.add(checkBox_5);
		
		JButton button = new JButton("Odjava");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Uspje�no ste odjavljeni!");
				dispose();
				new Prijava();
			}
		});
		button.setBounds(34, 569, 97, 25);
		panel.add(button);
		JTextArea txt = new JTextArea(10, 10);
		txt.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(209, 13, 995, 650);
		panel_1.add(txt);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane sp = new JScrollPane(txt);
		sp.setLocation(12, 13);
		sp.setSize(975, 635);
		panel_1.add(sp);
		this.setVisible(true);
		
		JLabel lblNeispravanthr = new JLabel("");
		
		btnTrai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        long start = System.currentTimeMillis();
		        int threshold = 1000, cnt = 0;
		      
	        	lblNeispravanthr.setBounds(15, 130, 150, 15);
	        	lblNeispravanthr.setFont(new Font("Tahoma", Font.PLAIN, 10));
		        try {
		        	 threshold = Integer.parseInt(textField.getText());
		        	 lblNeispravanthr.setText(" ");
		        	 lblNeispravanthr.setForeground(new Color(34, 34, 178));
			         lblNeispravanthr.setText("U redu");
		        } catch(Exception exc) {
		        	lblNeispravanthr.setText(" ");
		        	lblNeispravanthr.setForeground(new Color(178, 34, 34));
		        	lblNeispravanthr.setText("Pogrešan unos. Unesite broj");
		        }
		        panel.add(lblNeispravanthr);
		        ifChecked();
				/*String query = ("select `Zone/Beat`, `Event Clearance Group`, `Event Clearance Date` from (select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location`, `Zone/Beat` from "
						+ "(select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location` from zlocin) as b "
						+ "natural join (select distinct `Hundred Block Location`, `Zone/Beat` from zona) as c) as a "
						+ "natural join (select distinct `Event Clearance Code`, `Event Clearance Group` from grupa) as d");
		        String[] header = {"Zone/Beat", "Event Clearance Group", "Event Clearance Date"};*/
		        String querry = "";
		        querry = "SELECT " + headers + "FROM zlocin";
		        querry = querry.replace("  ", " ").replace(", FROM", " FROM");
				String query = "select `Event Clearance Description`, `Event Clearance Date`, `Zone/Beat` from zlocin";
				String[] header = headers.replace("`","").split(", ");/*{"Event Clearance Description", "Event Clearance Date", "Zone/Beat"};*/
				System.out.println(querry);
				System.out.println(headers);
		        ArrayList<Rule> resultRules = new FPGrowth(querry, threshold, header).returnResult();
		        txt.setText("");
		        for (Rule s : resultRules) {
		        	cnt++;
		        	txt.append("Rule #" + cnt + '\n' + s.toString() + '\n');
		        }
		        headers = "";
		        System.out.println("Printed lines: " + cnt);
		        System.out.println("Ending time: " + (System.currentTimeMillis() - start));
			}
			public void ifChecked() {
				if(checkBox.isSelected()) {
					headers += "`Event Clearance Date`, ";
				} else {
					headers = headers.replace("`Event Clearance Date`,", "");
				}
				if(checkBox_1.isSelected()) {
					headers += "`Event Clearance Description`, ";
				} else {
					headers = headers.replace("`Event Clearance Description`,", "");
				}
				if(checkBox_2.isSelected()) {
					headers += "`Event Clearance Code`, ";
				} else {
					headers = headers.replace("`Event Clearance Code`,", "");
				}
				if(checkBox_3.isSelected()) {
					headers += "`Hundred Block Location`, ";
				} else {
					headers = headers.replace("`Hundred Block Location`,", "");
				}
				if(checkBox_4.isSelected()) {
					headers += "`Initial Type Description`, ";
				} else {
					headers = headers.replace("`Initial Type Description`,", "");
				}
				if(checkBox_5.isSelected()) {
					headers += "`Zone/Beat`, ";
				} else {
					headers = headers.replace("`Zone/Beat `,", "");
				}
			}
		}); 
	}
	
}
