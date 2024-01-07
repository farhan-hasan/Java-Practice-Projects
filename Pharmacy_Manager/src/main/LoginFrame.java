package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
 
public class LoginFrame extends JFrame {
 
 
	JLabel loginHeadinglabel,loginLoginLabel,loginEmailLabel,loginPasswordLabel;
	JTextField loginEmailTextField,loginPasswordTextField;
	JButton loginButton,loginRegisterButton;
	JPanel loginHeadPannel,loginBodyPanel;
 
	public LoginFrame() {
		setSize(450,680);
		setDefaultCloseOperation(3);
		setResizable(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Pharmacy Management System");
		
		
 
		Font my_font = new Font("Times New Roman", Font.PLAIN,20);
 
		loginHeadPannel = new JPanel();
		loginHeadPannel.setBounds(2,2,429,130);
		loginHeadPannel.setBackground(Color.darkGray);
		loginHeadPannel.setLayout(null);
		add(loginHeadPannel);
 
		loginBodyPanel = new JPanel();
		loginBodyPanel.setBounds(2,132,429,506);
		loginBodyPanel.setBackground(Color.gray);
		loginBodyPanel.setLayout(null);
		add(loginBodyPanel);
 
 
		loginHeadinglabel = new JLabel("Pharmacy Manager");
		loginHeadinglabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginHeadinglabel.setBounds(160,40,280, 30);
		loginHeadinglabel.setForeground(Color.white);
		loginHeadPannel.add(loginHeadinglabel);
 
 
		loginLoginLabel = new JLabel("Login");
		loginLoginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		loginLoginLabel.setBounds(170,10,280, 40);
		loginLoginLabel.setForeground(Color.white);
		loginBodyPanel.add(loginLoginLabel);
 
		loginEmailLabel = new JLabel("Email");
		loginEmailLabel.setFont(my_font);
		loginEmailLabel.setBounds(20,80,280, 30);
		loginEmailLabel.setForeground(Color.white);
		loginBodyPanel.add(loginEmailLabel);
 
 
		loginEmailTextField = new JTextField();
		loginEmailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginEmailTextField.setBounds(180, 80,220,30);
		loginEmailTextField.setBackground(Color.white);
		//loginEmailTextField.setText(a);
		loginBodyPanel.add(loginEmailTextField);
 
		loginPasswordLabel = new JLabel("Password  ");
		loginPasswordLabel.setFont(my_font);
		loginPasswordLabel.setBounds(20,140,280, 30);
		loginPasswordLabel.setForeground(Color.white);
		loginBodyPanel.add(loginPasswordLabel);
 
 
		loginPasswordTextField = new JTextField();
		loginPasswordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginPasswordTextField.setBounds(180, 140,220,30);
		loginPasswordTextField.setBackground(Color.white);
		//loginPasswordTextField.setText(b);
		loginBodyPanel.add(loginPasswordTextField);
 
		loginButton = new JButton("Login");
		loginButton.setFont(my_font);
		loginButton.setFocusable(false);
		loginButton.setBounds(180,200,100,40);
		loginButton.setBackground(Color.green);
		loginButton.setForeground(Color.white);
		loginBodyPanel.add(loginButton);
 
 
		loginRegisterButton = new JButton("Register");
		loginRegisterButton.setFont(my_font);
		loginRegisterButton.setFocusable(false);
		loginRegisterButton.setBounds(300,200,100,40);
		loginRegisterButton.setBackground(Color.green);
		loginRegisterButton.setForeground(Color.white);
		loginRegisterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterFrame();
			}
		});
		loginBodyPanel.add(loginRegisterButton);
 
 
		setVisible(true);
	}
}
