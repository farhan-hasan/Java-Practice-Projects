import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Test extends JFrame{
	
	
	public Test() {
		getContentPane().setBackground(Color.cyan);
		setSize(1024,768);
		setDefaultCloseOperation(3);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Test");
		
		//JButton button = new JButton("Click Me");
		ImageIcon icon = new ImageIcon("path/to/icon.png");
		JButton button = new JButton(icon);
		button.setToolTipText("This is a tooltip");
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setBounds(20, 20, 100, 50);
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add(button);
		setVisible(true);
	}
	 
}
