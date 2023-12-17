package time;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ConverterFrame extends JFrame {
	
	JButton secondButton,minuteButton,hourButton,reset;
	JTextField hourTextField,minuteTextField,secondTextField,outputTextField;
	JPanel header, footer;
	JLabel hourLabel, minuteLabel, secondLabel, outputLabel;
	Font myfont = new Font("Arial", Font.PLAIN, 15);
	
	public ConverterFrame () {
		setSize(500,500);
		setLayout(null);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		
		header = new JPanel();
		header.setBounds(0, 0, 500, 250);
		header.setBackground(Color.white);
		header.setLayout(null);
		add(header);
		
		hourLabel = new JLabel("Hour:");
		hourLabel.setBounds(30, 30, 200, 20);
		hourLabel.setFont(myfont);
		header.add(hourLabel);
		
		hourTextField = new JTextField();
		hourTextField.setBounds(200, 30, 100, 20);
		header.add(hourTextField);
		
		minuteLabel = new JLabel("Minute:");
		minuteLabel.setBounds(30, 80, 200, 20);
		minuteLabel.setFont(myfont);
		header.add(minuteLabel);
		
		minuteTextField = new JTextField();
		minuteTextField.setBounds(200, 80, 100, 20);
		header.add(minuteTextField);
		
		secondLabel = new JLabel("Second:");
		secondLabel.setBounds(30, 130, 200, 20);
		secondLabel.setFont(myfont);
		header.add(secondLabel);
		
		secondTextField = new JTextField();
		secondTextField.setBounds(200, 130, 100, 20);
		header.add(secondTextField);
		
		secondButton = new JButton("Seconds");
		secondButton.setBounds(30, 180, 100, 20);
		secondButton.setFocusable(false);
		secondButton.setFont(myfont);
		secondButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputs[] = {"","",""}; 
				
				String h,m,s;
				inputs[0] = hourTextField.getText();
				inputs[1] = minuteTextField.getText();
				inputs[2] = secondTextField.getText();
				int flag = 0;
				int cnt = 0;
				String input = "";
				for(int i=0;i<inputs.length;i++) {
					if(inputs[i].length()!=0) {
						cnt++;
						flag = i+1;
						input = inputs[i];
					}
				}
				if(cnt>1) {
					JOptionPane.showMessageDialog(null, "Please input in one field");
					return;
				}
				double t = Double.parseDouble(input);
				if(flag==1) {
					t*=3600;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==2) {
					t*=60;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==3) {
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				
			}
		});
		header.add(secondButton);
		
		minuteButton = new JButton("Minute");
		minuteButton.setBounds(150, 180, 100, 20);
		minuteButton.setFocusable(false);
		minuteButton.setFont(myfont);
		minuteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputs[] = {"","",""}; 
				
				String h,m,s;
				inputs[0] = hourTextField.getText();
				inputs[1] = minuteTextField.getText();
				inputs[2] = secondTextField.getText();
				int flag = 0;
				int cnt = 0;
				String input = "";
				for(int i=0;i<inputs.length;i++) {
					if(inputs[i].length()!=0) {
						cnt++;
						flag = i+1;
						input = inputs[i];
					}
				}
				if(cnt>1) {
					JOptionPane.showMessageDialog(null, "Please input in one field");
					return;
				}
				double t = Double.parseDouble(input);
				if(flag==1) {
					t*=60;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==2) {
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==3) {
					t/=60;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				
			}
		});
		header.add(minuteButton);
		
		hourButton = new JButton("Hours");
		hourButton.setBounds(270, 180, 100, 20);
		hourButton.setFocusable(false);
		hourButton.setFont(myfont);
		hourButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputs[] = {"","",""}; 
				
				String h,m,s;
				inputs[0] = hourTextField.getText();
				inputs[1] = minuteTextField.getText();
				inputs[2] = secondTextField.getText();
				int flag = 0;
				int cnt = 0;
				String input = "";
				for(int i=0;i<inputs.length;i++) {
					if(inputs[i].length()!=0) {
						cnt++;
						flag = i+1;
						input = inputs[i];
					}
				}
				if(cnt>1) {
					JOptionPane.showMessageDialog(null, "Please input in one field");
					return;
				}
				double t = Double.parseDouble(input);
				if(flag==1) {
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==2) {
					t/=60;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				else if(flag==3) {
					t/=3600;
					String out = String.format("%.2f", t);
					outputTextField.setText(out);
				}
				
			}
		});
		header.add(hourButton);
		
		
		footer = new JPanel();
		footer.setBounds(0, 250, 500, 250);
		footer.setBackground(Color.white);
		footer.setLayout(null);
		add(footer);
		
		outputLabel = new JLabel("Converted time here:");
		outputLabel.setBounds(30,30, 200, 20);
		outputLabel.setFont(myfont);
		footer.add(outputLabel);
		
		outputTextField = new JTextField();
		outputTextField.setBounds(200, 30, 100, 20);
		footer.add(outputTextField);
		
		reset = new JButton("Reset");
		reset.setBounds(200, 80, 100, 20);
		reset.setFocusable(false);
		reset.setFont(myfont);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				secondTextField.setText("");
				minuteTextField.setText("");
				hourTextField.setText("");
				
			}
		});
		footer.add(reset);
		
		
		setVisible(true);
	}
	
}
