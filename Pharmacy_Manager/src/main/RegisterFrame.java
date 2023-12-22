package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterFrame extends JFrame {
	
	
	
	JLabel registerHeadinglabel,registerRegisterLabel,registerNameLabel,registerEmailLabel,registerPasswordLabel,registerConfirmPassWordLabel;
	JLabel registerAddressLabel, registerMobileLabel;
	
	JTextField registerNameTextField,registerEmailTextField,registerPasswordTextField,registerConfirmPassWordTextField;
	JTextField registerAddressTextField, registerMobileTextField;
	
	JButton registerButton,registerLoginButton;
	
	JPanel registerHeadPannel,registerBodyPanel;
	
	public RegisterFrame() {
		
		setSize(450,680);
		setDefaultCloseOperation(3);
		setResizable(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Pharmacy Management System");
			
		Font my_font = new Font("Times New Roman", Font.PLAIN,20);
		
		
		registerHeadPannel = new JPanel();
		registerHeadPannel.setBounds(2,2,429,130);
		registerHeadPannel.setBackground(Color.darkGray);
		registerHeadPannel.setLayout(null);
		add(registerHeadPannel);
		
		registerBodyPanel = new JPanel();
		registerBodyPanel.setBounds(2,132,429,506);
		registerBodyPanel.setBackground(Color.gray);
		registerBodyPanel.setLayout(null);
		add(registerBodyPanel);
		
		
		registerHeadinglabel = new JLabel("Pharmacy Manager");
		registerHeadinglabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		registerHeadinglabel.setBounds(160,40,280, 30);
		registerHeadinglabel.setForeground(Color.white);
		registerHeadPannel.add(registerHeadinglabel);
		
		
		registerRegisterLabel = new JLabel("Register");
		registerRegisterLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		registerRegisterLabel.setBounds(150,10,280, 40);
		registerRegisterLabel.setForeground(Color.white);
		registerBodyPanel.add(registerRegisterLabel);
		
		
		registerNameLabel = new JLabel("Name  ");
		registerNameLabel.setFont(my_font);
		registerNameLabel.setBounds(20,80,280, 30);
		registerNameLabel.setForeground(Color.white);
		registerBodyPanel.add(registerNameLabel);
		
		
		registerNameTextField = new JTextField();
		registerNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerNameTextField.setBounds(180, 80,220,30);
		registerNameTextField.setBackground(Color.white);
		registerBodyPanel.add(registerNameTextField);
		
		registerEmailLabel = new JLabel("Email  ");
		registerEmailLabel.setFont(my_font);
		registerEmailLabel.setBounds(20,140,280, 30);
		registerEmailLabel.setForeground(Color.white);
		registerBodyPanel.add(registerEmailLabel);
		
		
		registerEmailTextField = new JTextField();
		registerEmailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerEmailTextField.setBounds(180, 140,220,30);
		registerEmailTextField.setBackground(Color.white);
		registerBodyPanel.add(registerEmailTextField);
		
		
		registerEmailLabel = new JLabel("Password  ");
		registerEmailLabel.setFont(my_font);
		registerEmailLabel.setBounds(20,200,280, 30);
		registerEmailLabel.setForeground(Color.white);
		registerBodyPanel.add(registerEmailLabel);
		
		
		registerEmailTextField = new JTextField();
		registerEmailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerEmailTextField.setBounds(180, 200,220,30);
		registerEmailTextField.setBackground(Color.white);
		registerBodyPanel.add(registerEmailTextField);
		
		
		registerConfirmPassWordLabel = new JLabel("Confirm Password  ");
		registerConfirmPassWordLabel.setFont(my_font);
		registerConfirmPassWordLabel.setBounds(20,260,280, 30);
		registerConfirmPassWordLabel.setForeground(Color.white);
		registerBodyPanel.add(registerConfirmPassWordLabel);
		
		
		registerConfirmPassWordTextField = new JTextField();
		registerConfirmPassWordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerConfirmPassWordTextField.setBounds(180, 260,220,30);
		registerConfirmPassWordTextField.setBackground(Color.white);
		registerBodyPanel.add(registerConfirmPassWordTextField);
		
		
		registerAddressLabel = new JLabel("Address");
		registerAddressLabel.setFont(my_font);
		registerAddressLabel.setBounds(20,320,280, 30);
		registerAddressLabel.setForeground(Color.white);
		registerBodyPanel.add(registerAddressLabel);
		
		
		registerAddressTextField = new JTextField();
		registerAddressTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerAddressTextField.setBounds(180, 320,220,30);
		registerAddressTextField.setBackground(Color.white);
		registerBodyPanel.add(registerAddressTextField);
		
		registerMobileLabel = new JLabel("Mobile");
		registerMobileLabel.setFont(my_font);
		registerMobileLabel.setBounds(20,380,280, 30);
		registerMobileLabel.setForeground(Color.white);
		registerBodyPanel.add(registerMobileLabel);
		
		
		registerMobileTextField = new JTextField();
		registerMobileTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		registerMobileTextField.setBounds(180, 380,220,30);
		registerMobileTextField.setBackground(Color.white);
		registerBodyPanel.add(registerMobileTextField);
		
		registerButton = new JButton("Register");
		registerButton.setFont(my_font);
		registerButton.setFocusable(false);
		registerButton.setBounds(180,440,100,40);
		registerButton.setBackground(Color.green);
		registerButton.setForeground(Color.white);
		registerBodyPanel.add(registerButton);
		
		
		
		registerLoginButton = new JButton("Login");
		registerLoginButton.setFont(my_font);
		registerLoginButton.setFocusable(false);
		registerLoginButton.setBounds(300,440,100,40);
		registerLoginButton.setBackground(Color.green);
		registerLoginButton.setForeground(Color.white);
		registerLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame();
			}
		});
		registerBodyPanel.add(registerLoginButton);
		
		
		
		setVisible(true);
		
		
	}
}
