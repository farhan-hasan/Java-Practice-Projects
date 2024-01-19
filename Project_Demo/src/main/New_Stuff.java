package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.time.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class New_Stuff extends JFrame {
	
	JTabbedPane p1;
	JPanel panel1,panel2,panel3;
	JLabel userNameLabel,passWordLabel,genderLabel,DOBLabel;
	JTextField userNameTextField,DOBTextField;
	JPasswordField passWordTextField;
	JComboBox<String> genderComboBox;
	JDateChooser DOBChooser;
	
	public New_Stuff () {
		
		getContentPane().setBackground(Color.white);
		setSize(500,500);
		setLocationRelativeTo(null);
		//setLayout(null);
		
		p1 = new JTabbedPane();
		p1.setBackground(Color.white); // tabs color
		p1.setBounds(0, 0, 400, 400);
		add(p1);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.white); // panel color
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.white);
		panel3 = new JPanel();
		panel3.setBackground(Color.white);
		panel3.setLayout(null);
		
		// // Username
		userNameLabel = new JLabel("Username :");
		userNameLabel.setBounds(30, 30, 100, 20);
		panel1.add(userNameLabel);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(200, 30, 100, 20);
		panel1.add(userNameTextField);
		
		// // Password
		passWordLabel = new JLabel("Password :");
		passWordLabel.setBounds(30, 60, 100, 20);
		panel1.add(passWordLabel);
		
		passWordTextField = new JPasswordField();
		passWordTextField.setBounds(200, 60, 100, 20);
		panel1.add(passWordTextField);
		
		// // Gender
		genderLabel = new JLabel("Gender :");
		genderLabel.setBounds(30, 90, 100, 20);
		panel1.add(genderLabel);
		
		String[] genders = {"Male","Female"};
		genderComboBox = new JComboBox<String>(genders);
		genderComboBox.setBounds(200, 90, 100, 20);
		genderComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(genderComboBox.getSelectedItem());
				System.out.println(genderComboBox.getSelectedItem().getClass());
			}
		});
		panel1.add(genderComboBox);
		
		// // DOB
		DOBLabel = new JLabel("Date of Birth :");
		DOBLabel.setBounds(30, 120, 100, 20);
		panel1.add(DOBLabel);
		
		DOBChooser = new JDateChooser();
		DOBChooser.setBounds(200, 120, 100, 20);
		
		// Setting local date to the datechooser
		LocalDate localDate = LocalDate.now();
		Date DOB = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		DOBChooser.setDate(DOB);
		panel1.add(DOBChooser);
		
		// getting the date as a string from the datechooser
		DOB = (Date) DOBChooser.getDate();
		String DOBstr = DateFormat.getDateInstance().format(DOB);
		System.out.println(DOBstr);
		
		
		JButton iconButon = new JButton(new ImageIcon("C:\\Users\\Farhan\\Downloads\\img.png"));
		iconButon.setBounds(200, 150, 100, 20);
		panel1.add(iconButon);
		
		p1.add("Tab1",panel1);
		p1.add("Tab2",panel2);
		p1.add("Tab3",panel3);
		
		
		setVisible(true);
		
	}
	
}
