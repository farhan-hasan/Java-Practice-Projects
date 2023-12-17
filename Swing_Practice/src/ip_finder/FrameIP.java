package ip_finder;

import java.awt.event.ActionEvent;
import javax.swing.*;  
import java.awt.event.*;  
import java.net.*;  

public class FrameIP extends JFrame implements ActionListener{
	JButton findIp;
	JTextField url;
	JLabel urlLabel;
	
	FrameIP() {
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setResizable(false);
		setTitle("IP Finder");
		
		urlLabel = new JLabel("Enter URL:");
		urlLabel.setBounds(50, 80, 100, 20);
		add(urlLabel);
		
		url = new JTextField();
		url.setBounds(50, 100, 200, 20);
		add(url);
	
		findIp = new JButton("Find IP");
		findIp.setBounds(50, 150, 100, 30);
		findIp.setFocusable(false);
		findIp.addActionListener(this);
		add(findIp);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==findIp) {
				String i = url.getText();
				try {
					InetAddress addr = InetAddress.getByName(i);
					String ip = addr.getHostAddress();
					JOptionPane.showMessageDialog(this, ip);
				}
				catch (UnknownHostException eh){
					JOptionPane.showMessageDialog(this,eh.toString());
				}
			}
		
	}
	
}
