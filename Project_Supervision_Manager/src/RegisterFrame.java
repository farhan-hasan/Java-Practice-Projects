import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.*;

public class RegisterFrame extends JFrame{
	
	JButton registerButton, loginButton;
	JLabel registerLabel, firstNameLabel, lastNameLabel, userNameLabel, emailLabel, passWordLabel, orLabel;
	JLabel confirmPassWordLabel, mobileLabel, addressLabel;
	JTextField firstNameTextField, lastNameTextField, userNameTextField, emailTextField;
	JTextField  mobileTextField, addressTextField;
	JPasswordField passWordTextField, confirmPassWordTextField;
	
	Color lightColor = new Color(255,255,255);
	Color darkColor = new Color(34, 125, 128);
	
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	Connection con;
	Statement st;
	public RegisterFrame() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		getContentPane().setBackground(lightColor);
		setSize(800,600);
		setDefaultCloseOperation(3);
		setResizable(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Teacher Companion");
		
		registerLabel = new JLabel("Register");
		registerLabel.setFont(headingFont);
		registerLabel.setBounds(310,20,280,100);
		registerLabel.setForeground(Color.black);
		add(registerLabel);
		
		
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(labelFont);
		firstNameLabel.setBounds(110,130,280,70);
		firstNameLabel.setForeground(Color.black);
		add(firstNameLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setFont(textFieldFont);
		firstNameTextField.setBounds(110,180,240,30);
		firstNameTextField.setBackground(Color.white);
		add(firstNameTextField);
		
		
		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(labelFont);
		lastNameLabel.setBounds(450,130,280,70);
		lastNameLabel.setForeground(Color.black);
		add(lastNameLabel);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(textFieldFont);
		lastNameTextField.setBounds(450,180,240,30);
		lastNameTextField.setBackground(Color.white);
		add(lastNameTextField);
		
		
		userNameLabel = new JLabel("Username");
		userNameLabel.setFont(labelFont);
		userNameLabel.setBounds(110,200,280,70);
		userNameLabel.setForeground(Color.black);
		add(userNameLabel);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(textFieldFont);
		userNameTextField.setBounds(110,250,240,30);
		userNameTextField.setBackground(Color.white);
		add(userNameTextField);
		
		
		emailLabel = new JLabel("Email");
		emailLabel.setFont(labelFont);
		emailLabel.setBounds(450,200,280,70);
		emailLabel.setForeground(Color.black);
		add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setFont(textFieldFont);
		emailTextField.setBounds(450,250,240,30);
		emailTextField.setBackground(Color.white);
		add(emailTextField);
		
		passWordLabel = new JLabel("Password");
		passWordLabel.setFont(labelFont);
		passWordLabel.setBounds(110,270,280,70);
		passWordLabel.setForeground(Color.black);
		add(passWordLabel);
		
		passWordTextField = new JPasswordField();
		passWordTextField.setFont(textFieldFont);
		passWordTextField.setBounds(110,320,240,30);
		passWordTextField.setBackground(Color.white);
		add(passWordTextField);
		
		confirmPassWordLabel = new JLabel("Confirm Password");
		confirmPassWordLabel.setFont(labelFont);
		confirmPassWordLabel.setBounds(450,270,280,70);
		confirmPassWordLabel.setForeground(Color.black);
		add(confirmPassWordLabel);
		
		confirmPassWordTextField = new JPasswordField ();
		confirmPassWordTextField.setFont(textFieldFont);
		confirmPassWordTextField.setBounds(450,320,240,30);
		confirmPassWordTextField.setBackground(Color.white);
		add(confirmPassWordTextField);
		
		
		mobileLabel = new JLabel("Mobile");
		mobileLabel.setFont(labelFont);
		mobileLabel.setBounds(110,340,280,70);
		mobileLabel.setForeground(Color.black);
		add(mobileLabel);
		
		mobileTextField = new JTextField();
		mobileTextField.setFont(textFieldFont);
		mobileTextField.setBounds(110,390,240,30);
		mobileTextField.setBackground(Color.white);
		add(mobileTextField);
		
		
		addressLabel = new JLabel("Address");
		addressLabel.setFont(labelFont);
		addressLabel.setBounds(450,340,280,70);
		addressLabel.setForeground(Color.black);
		add(addressLabel);
		
		addressTextField = new JTextField();
		addressTextField.setFont(textFieldFont);
		addressTextField.setBounds(450,390,240,30);
		addressTextField.setBackground(Color.white);
		add(addressTextField);
		
		
		registerButton = new JButton("Register");
		registerButton.setFont(buttonFont);
		registerButton.setBounds(110,460,240,30);
		registerButton.setForeground(Color.white);
		registerButton.setFocusable(false);
		registerButton.setBackground(darkColor);
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String username = userNameTextField.getText();
				String email = emailTextField.getText();
				String pass = passWordTextField.getText();
				String conPass = confirmPassWordTextField.getText();
				String mobile = mobileTextField.getText();
				String address = addressTextField.getText();
				
				//validation
				String firstNameRegex = "^[a-zA-Z]{3,20}$";
				String lastNameRegex = "^[a-zA-Z]{3,20}$";
				String userNameRegex = "^[a-zA-Z0-9]{3,20}$";
				String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";//a@c.c pattern
				String mobileRegex = "^(\\+88)?01[2-9]\\d{8}$";
				String passRegex = "^((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%&*_+=])).{6,20}$";
				String addressRegex = "^[a-zA-Z0-9,.-]{10,100}$";
				
				if(!Pattern.matches(firstNameRegex, firstName)) {
					JOptionPane.showMessageDialog(null,"First name should contain (Capital, Small letters with lenght 3-10 without blank space)");
				}
				else if(!Pattern.matches(lastNameRegex, lastName)) {
					JOptionPane.showMessageDialog(null,"Last name should contain (Capital, Small letters with lenght 3-10 without blank space)");
				}
				else if(!Pattern.matches(userNameRegex, username)) {
					JOptionPane.showMessageDialog(null,"username name should contain (Capital, Small letters, numbers with lenght 3-10 without blank space)");
				}
				else if(!Pattern.matches(emailRegex, email)) {
					JOptionPane.showMessageDialog(null,"Invalid email");
				}
				else if(!Pattern.matches(passRegex, pass)) {
					System.out.println(pass);
					JOptionPane.showMessageDialog(null,"Password should contain atleast one Capital, Small letters, numbers, special characters[!,@,#,$,%,&,*,_,+,=] with lenght 3-10");
				}
				else if(!conPass.equals(pass)) {
					JOptionPane.showMessageDialog(null,"Password not matching");
				}
				else if(!Pattern.matches(mobileRegex, mobile)) {
					JOptionPane.showMessageDialog(null,"Invalid mobile");
				}
				else if(!Pattern.matches(addressRegex, address)) {
					JOptionPane.showMessageDialog(null,"Address should contain only Capital, Small letters, numbers, special characters[,.-] with lenght 10-100");
				}
				else {
					JOptionPane.showMessageDialog(null,"Successfully registered");
					String sql = "INSERT INTO `register`(`username`, `email`, `password`) VALUES ('"+username+"','"+email+"','"+pass+"')";
					try {
						st.executeUpdate(sql);
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					dispose();
					new LoginFrame();
					
				}
			}
		});
		add(registerButton);
		
		orLabel = new JLabel("OR");
		orLabel.setFont(labelFont);
		orLabel.setBounds(385,460,240,30);
		orLabel.setForeground(Color.black);
		add(orLabel);
		
		loginButton = new JButton("Login");
		loginButton.setFont(buttonFont);
		loginButton.setBounds(450,460,240,30);
		loginButton.setForeground(Color.white);
		loginButton.setFocusable(false);
		loginButton.setBackground(darkColor);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame();
				
			}
		});
		add(loginButton);
		
		
		
		
		setVisible(true);
	}
	
}
