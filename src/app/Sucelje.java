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
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


public class Sucelje extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;

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
		textField.setBounds(23, 106, 116, 22);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Odaberi parametre:");
		label_1.setBounds(23, 141, 150, 40);
		panel.add(label_1);
		
		JCheckBox checkBox = new JCheckBox("At scene time");
		checkBox.setBounds(8, 178, 113, 25);
		panel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Event description");
		checkBox_1.setBounds(8, 201, 150, 25);
		panel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Event code");
		checkBox_2.setBounds(8, 224, 113, 25);
		panel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Block location");
		checkBox_3.setBounds(8, 247, 113, 25);
		panel.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("Initial type desc.");
		checkBox_4.setBounds(8, 269, 150, 25);
		panel.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("Zone");
		checkBox_5.setBounds(8, 290, 113, 25);
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
		panel_1.setBounds(184, 13, 995, 650);
		panel_1.add(txt);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane sp = new JScrollPane(txt);
		sp.setLocation(12, 13);
		sp.setSize(975, 635);
		panel_1.add(sp);
		this.setVisible(true);
		btnTrai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        long start = System.currentTimeMillis();
				int threshold = 1000, cnt = 0;
				
				/*String query = ("select `Zone/Beat`, `Event Clearance Group`, `Event Clearance Date` from (select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location`, `Zone/Beat` from "
						+ "(select distinct `Event Clearance Code`, `Event Clearance Date`, `Hundred Block Location` from zlocin) as b "
						+ "natural join (select distinct `Hundred Block Location`, `Zone/Beat` from zona) as c) as a "
						+ "natural join (select distinct `Event Clearance Code`, `Event Clearance Group` from grupa) as d");
		        String[] header = {"Zone/Beat", "Event Clearance Group", "Event Clearance Date"};*/
				
				String query = "select `Event Clearance Description`, `Event Clearance Date`, `Zone/Beat` from zlocin";
				String[] header = {"Event Clearance Description", "Event Clearance Date", "Zone/Beat"};
				
		        ArrayList<Rule> resultRules = new FPGrowth(query, threshold, header).returnResult();
		        
		        for (Rule s : resultRules) {
		        	cnt++;
		        	txt.append("Rule #" + cnt + '\n' + s.toString() + '\n');
		        }
		        System.out.println("Printed lines: " + cnt);
		        System.out.println("Ending time: " + (System.currentTimeMillis() - start));
			}
		}); 
	}
}
