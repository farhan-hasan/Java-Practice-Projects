package Project_demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class RegisterForm extends JFrame{
	
	Font labelFont = new Font("Arial", Font.BOLD,16);
	JPanel headerPanel, inputPanel;
	JLabel headerLabel, nameLabel, emailLabel, passLabel, conPassLabel, mobileLabel;
	JLabel addressLabel;
	JButton submitBtn;
	JTextField nameText, emailText, passText, conPassText, mobileText, addressText;
	
	public RegisterForm() {
		setSize(515,540);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		
		headerPanel = new JPanel();
		headerPanel.setBounds(5, 5, 490, 60);
		headerPanel.setBackground(new Color(13,11,133));
		add(headerPanel);
		
		headerLabel = new JLabel("Registration Form");
		headerLabel.setFont(labelFont);
		headerLabel.setForeground(Color.white);
		headerPanel.add(headerLabel);
		
		inputPanel = new JPanel();
		inputPanel.setBounds(5, 70, 490, 425);
		inputPanel.setBackground(new Color(199,227,240));
		inputPanel.setLayout(null);
		add(inputPanel);
		
		// Name
		nameLabel = new JLabel("Name : ");
		nameLabel.setFont(labelFont);
		nameLabel.setBounds(30,5,100,25);
		inputPanel.add(nameLabel);
		
		nameText = new JTextField();
		nameText.setBounds(200,5,200,25);
		nameText.setFont(labelFont);
		inputPanel.add(nameText);
		
		//Email
		emailLabel = new JLabel("Email : ");
		emailLabel.setFont(labelFont);
		emailLabel.setBounds(30,50,100,25);
		inputPanel.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setBounds(200,50,200,25);
		emailText.setFont(labelFont);
		inputPanel.add(emailText);
		
		//Pass
		passLabel = new JLabel("Password : ");
		passLabel.setFont(labelFont);
		passLabel.setBounds(30,100,100,25);
		inputPanel.add(passLabel);
		
		passText = new JTextField();
		passText.setBounds(200,100,200,25);
		passText.setFont(labelFont);
		inputPanel.add(passText);
		
		//Confirm Pass
		conPassLabel = new JLabel("Confirm Pass : ");
		conPassLabel.setFont(labelFont);
		conPassLabel.setBounds(30,150,120,25);
		inputPanel.add(conPassLabel);
		
		conPassText = new JTextField();
		conPassText.setBounds(200,150,200,25);
		conPassText.setFont(labelFont);
		inputPanel.add(conPassText);
		
		//Mobile
		mobileLabel = new JLabel("Mobile : ");
		mobileLabel.setFont(labelFont);
		mobileLabel.setBounds(30,200,100,25);
		inputPanel.add(mobileLabel);
		
		mobileText = new JTextField();
		mobileText.setBounds(200,200,200,25);
		mobileText.setFont(labelFont);
		inputPanel.add(mobileText);
		
		//Address
		addressLabel = new JLabel("Address : ");
		addressLabel.setFont(labelFont);
		addressLabel.setBounds(30,250,100,25);
		inputPanel.add(addressLabel);
		
		addressText = new JTextField();
		addressText.setBounds(200,250,200,25);
		addressText.setFont(labelFont);
		inputPanel.add(addressText);
		
		//Submit Button
		submitBtn = new JButton("Register");
		submitBtn.setBounds(180,300,100,30);
		submitBtn.setBackground(new Color(13,11,133));
		submitBtn.setFont(labelFont);
		submitBtn.setFocusable(false);
		submitBtn.setForeground(Color.white);
		//submitBtn.setBorder(new LineBorder(Color.red));
		inputPanel.add(submitBtn);
		
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = nameText.getText();
				String email = emailText.getText();
				String pass = passText.getText();
				String conPass = conPassText.getText();
				String mobile = mobileText.getText();
				String address = addressText.getText();
				
				//validation
				String userNameRegex = "^[a-zA-Z]{3,10}$";
				String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";//a@c.c pattern
				String mobileRegex = "^(\\+88)?01[2-9]\\d{8}$";
				String passRegex = "^((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%&*_+=])).{6,20}$";
				String addressRegex = "^[a-zA-Z0-9,.-]{10,20}$";
				if(!Pattern.matches(userNameRegex, username)) {
					JOptionPane.showMessageDialog(null,"Invalid name");
				}
				else if(!Pattern.matches(emailRegex, email)) {
					JOptionPane.showMessageDialog(null,"Invalid email");
				}
				else if(!Pattern.matches(passRegex, pass)) {
					JOptionPane.showMessageDialog(null,"Invalid password");
				}
				else if(!conPass.equals(pass)) {
					JOptionPane.showMessageDialog(null,"Password not matching");
				}
				else if(!Pattern.matches(mobileRegex, mobile)) {
					JOptionPane.showMessageDialog(null,"Invalid mobile");
				}
				else if(!Pattern.matches(addressRegex, address)) {
					JOptionPane.showMessageDialog(null,"Invalid address");
				}
				else {
					JOptionPane.showMessageDialog(null,"Successfully registered");
					System.out.println(username);
					System.out.println(email);
					System.out.println(pass);
					System.out.println(conPass);
					System.out.println(mobile);
					System.out.println(address);
					
				}
				
				
				
				
			}
		});
		
		
		setVisible(true);
	}
}
