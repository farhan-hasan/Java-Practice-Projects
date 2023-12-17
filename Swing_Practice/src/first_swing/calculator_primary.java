package first_swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// UNFINISHED PROJECT!!!!

public class calculator_primary extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton add,sub,clear,div,mul;
	
	JTextField tf1,tf2,result;
	
	JLabel j1,j2,j3;
	
	public calculator_primary () {
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setResizable(false);
		Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
		
		j1 = new JLabel("1st Number :");
		j1.setBounds(20, 100, 165, 20);
		add(j1);
		
		
		
		tf1 = new JTextField();
		tf1.setBounds(160, 100, 165, 25);
		add(tf1);
		
		j2 = new JLabel("2st Number :");
		j2.setBounds(20, 150, 165, 20);
		add(j2);
		
		tf2 = new JTextField();
		tf2.setBounds(160, 150, 165, 25);
		add(tf2);
		
		j3 = new JLabel("Result:");
		j3.setBounds(20, 200, 165, 20);
		add(j3);
		
		result= new JTextField();
		result.setBounds(160, 200, 165, 25);
		add(result);
		
		clear = new JButton("clear");
		
		clear.setBounds(195, 250, 100, 30);
		clear.setFocusable(false);
		clear.setFont(my_font);
		clear.addActionListener(this);
		add(clear);
		
		add = new JButton("Add");
		
		add.setBounds(90, 300, 100, 30);
		add.setFocusable(false);
		add.setFont(my_font);
		add.addActionListener(this);
		add(add);	
		
		sub = new JButton("Sub");
		
		sub.setBounds(90, 350, 100, 30);
		sub.setFocusable(false);
		sub.setFont(my_font);
		sub.addActionListener(this);
		add(sub);		
		
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clear) {
			tf1.setText("");
			tf2.setText("");
			result.setText("");
		}
		if(e.getSource()==add) {
			String s1 = tf1.getText();
			String s2 = tf2.getText();
			
			int a = Integer.parseInt(s1);
			int b = Integer.parseInt(s2);
			
			int c = a + b;
			
			String res = String.valueOf(c);
			
			result.setText(res);
		}
		if(e.getSource()==sub) {
			String s1 = tf1.getText();
			String s2 = tf2.getText();
			
			int a = Integer.parseInt(s1);
			int b = Integer.parseInt(s2);
			
			int c = a - b;
			
			String res = String.valueOf(c);
			
			result.setText(res);
		}
	}
}
