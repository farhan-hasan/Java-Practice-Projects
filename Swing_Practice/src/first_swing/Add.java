package first_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Add extends JFrame {
	
	JButton add;
	JTextField tf1,tf2;
	JLabel l1,l2;
	
	Add () {
		setSize(500,300);
		setLayout(null);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		
		l1 = new JLabel("1st input:");
		l1.setBounds(10,50,100,20);
		add(l1);
		
		tf1 = new JTextField();
		tf1.setBounds(200,50,200,20);
		add(tf1);
		
		l2 = new JLabel("2st input:");
		l2.setBounds(10,100,100,20);
		add(l2);
		
		tf2 = new JTextField();
		tf2.setBounds(200,100,200,20);
		add(tf2);
		
		add = new JButton("Add");
		add.setBounds(200,150,100,30);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String a = tf1.getText();
				String b = tf2.getText();
				
				int x = Integer.parseInt(a);
				int y = Integer.parseInt(b);
				
				
				String ans = String.valueOf(x);
				
				JOptionPane.showMessageDialog(null, x+y);
			}
		});
		add(add);
		
		
		
		setVisible(true);
	}
	
}
