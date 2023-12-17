package first_swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton b1,b2;
	
	JTextField tf1;
	
	//JTable table;
	
	public Frame () {
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		JOptionPane.showMessageDialog(this, "Success DB connection!", "Success", JOptionPane.INFORMATION_MESSAGE);
		setResizable(false);
		Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
		
		JLabel l1 = new JLabel("Enter you web address");
		l1.setBounds(5, 100, 150, 50);
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(l1);
		
		JLabel l2 = new JLabel("Welcome to our App");
		l2.setBounds(165, 60, 200, 50);
		l2.setFont(my_font);
		add(l2);
		
		tf1 = new JTextField();
		tf1.setBounds(5, 150, 165, 20);
		add(tf1);
		
		b1 = new JButton(new ImageIcon("I:\\Education\\Java\\Swing\\images//setting.png"));
		
		b1.setBounds(5, 180, 100, 50);
		b1.setBackground(new Color(234,82,199));
		b1.setForeground(new Color(255,255,255));
		b1.setFocusable(false);
		b1.setFont(my_font);
		b1.addActionListener(new ActionListener() { //using anno object of inteface and inner class

			@Override
			public void actionPerformed(ActionEvent e) {
				tf1.setText("Hello");
			}
			
		});
		add(b1);
		
		b2 = new JButton("Erase");
		
		b2.setBounds(110, 180, 100, 50);
		b2.setBackground(new Color(234,82,199));
		b2.setForeground(new Color(255,255,255));
		b2.setFocusable(false);
		b2.setFont(my_font);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf1.setText("");
			}
			
		});
		add(b2);
		
		
		
		setVisible(true);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == b1) {
//			tf1.setText("Hello");
//		}
//		if(e.getSource() == b2) {
//			tf1.setText("");
//		}
//	}
}
