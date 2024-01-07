import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class LoginFrame extends JFrame {
	
	JLabel loginLabel, userNameEmailLabel, passWordLabel, orLabel;
	JTextField userNameEmailTextField;
	JPasswordField passWordTextField;
	JButton loginButton, registerButton;
	
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	Connection con;
	Statement st;
	
	public LoginFrame() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getContentPane().setBackground(new Color(175, 244, 198));
		setSize(800,600);
		setDefaultCloseOperation(3);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Project Supervision Manager");
		
		loginLabel = new JLabel("Login");
		loginLabel.setFont(headingFont);
		loginLabel.setBounds(330,20,280,100);
		loginLabel.setForeground(Color.black);
		add(loginLabel);
		
		userNameEmailLabel = new JLabel("Username/Email");
		userNameEmailLabel.setFont(labelFont);
		userNameEmailLabel.setBounds(260,130,280,70);
		userNameEmailLabel.setForeground(Color.black);
		add(userNameEmailLabel);
		
		userNameEmailTextField = new JTextField();
		userNameEmailTextField.setFont(textFieldFont);
		userNameEmailTextField.setBounds(260,180,240,30);
		userNameEmailTextField.setBackground(Color.white);
		add(userNameEmailTextField);
		
		
		passWordLabel = new JLabel("Password");
		passWordLabel.setFont(labelFont);
		passWordLabel.setBounds(260,200,280,70);
		passWordLabel.setForeground(Color.black);
		add(passWordLabel);
		
		passWordTextField = new JPasswordField();
		passWordTextField.setFont(textFieldFont);
		passWordTextField.setBounds(260,250,240,30);
		passWordTextField.setBackground(Color.white);
		add(passWordTextField);
		
		loginButton = new JButton("Login");
		loginButton.setFont(buttonFont);
		loginButton.setBounds(260,320,240,30);
		loginButton.setForeground(Color.white);
		loginButton.setFocusable(false);
		loginButton.setBackground(new Color(20, 174, 92));
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userNameEmailTextField.getText();
				String pass = passWordTextField.getText();
				String sql = "SELECT `username`, `email`, `password` FROM `register` WHERE (username = '"+username+"' or email = '"+username+"') and password = '"+pass+"'";
				
				
				try {
					ResultSet rs = st.executeQuery(sql);
					int cnt=0;
					while(rs.next()) {
						username = rs.getString(1);
						cnt++;
					}
					
					if(cnt==0) {
						JOptionPane.showMessageDialog(null, "Invalid Credentials");
					}
					else {
						
						new MainFrame(username);
						dispose();
						return;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(loginButton);
		
		
		orLabel = new JLabel("OR");
		orLabel.setFont(labelFont);
		orLabel.setBounds(365,370,240,30);
		orLabel.setForeground(Color.black);
		add(orLabel);
		
		
		registerButton = new JButton("Register");
		registerButton.setFont(buttonFont);
		registerButton.setBounds(260,420,240,30);
		registerButton.setForeground(Color.white);
		registerButton.setFocusable(false);
		registerButton.setBackground(new Color(20, 174, 92));
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterFrame();
				
			}
		});
		add(registerButton);
		
		
		setVisible(true);
	}
	
}
