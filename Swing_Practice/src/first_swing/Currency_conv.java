package first_swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Currency_conv extends JFrame implements ActionListener{
	
	JButton usd,eur,gbp,inr,sar,sgd,cad,qr,aud,clear;
	
	JTextField tf1,tf2;
	
	JLabel jl1,jl2;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clear) {
			tf1.setText("");
			tf2.setText("");
		}
		if(e.getSource()==usd) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.0091);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==cad) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			float c = (float) (a * 0.013);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==aud) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.014);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==sgd) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.012);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==inr) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.76);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==qr) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.033);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==sar) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.034);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==eur) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.0086);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
		if(e.getSource()==gbp) {
			String s1 = tf1.getText();
			
			float a = Float.parseFloat(s1);
			
			float c = (float) (a * 0.0075);
			
			String res = String.format("%.2f", c);
			tf2.setText(res);
		}
	}
	
	public Currency_conv() {
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setResizable(true);
		Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
		
		jl1 = new JLabel("Enter BDT amount :");
		jl1.setBounds(20, 100, 165, 20);
		add(jl1);
		
		
		
		tf1 = new JTextField();
		tf1.setBounds(160, 100, 165, 25);
		add(tf1);
		
		jl2 = new JLabel("Converted amount :");
		jl2.setBounds(20, 150, 165, 20);
		add(jl2);
		
		tf2 = new JTextField();
		tf2.setBounds(160, 150, 165, 25);
		add(tf2);
		
		usd = new JButton("USD");
		
		usd.setBounds(20, 200, 100,30);
		usd.setFocusable(false);
		usd.setFont(my_font);
		usd.addActionListener(this);
		add(usd);
		
		cad = new JButton("CAD");
		
		cad.setBounds(140, 200, 100, 30);
		cad.setFocusable(false);
		cad.setFont(my_font);
		cad.addActionListener(this);
		add(cad);
		
		aud = new JButton("AUD");
		
		aud.setBounds(260, 200, 100, 30);
		aud.setFocusable(false);
		aud.setFont(my_font);
		aud.addActionListener(this);
		add(aud);
		
		sgd = new JButton("SGD");
		
		sgd.setBounds(20, 260, 100, 30);
		sgd.setFocusable(false);
		sgd.setFont(my_font);
		sgd.addActionListener(this);
		add(sgd);
		
		gbp = new JButton("GBP");
		
		gbp.setBounds(140, 260, 100, 30);
		gbp.setFocusable(false);
		gbp.setFont(my_font);
		gbp.addActionListener(this);
		add(gbp);
		
		inr = new JButton("INR");
		
		inr.setBounds(260, 260, 100, 30);
		inr.setFocusable(false);
		inr.setFont(my_font);
		inr.addActionListener(this);
		add(inr);
		
		eur = new JButton("EUR");
		
		eur.setBounds(20, 320, 100, 30);
		eur.setFocusable(false);
		eur.setFont(my_font);
		eur.addActionListener(this);
		add(eur);
		
		sar = new JButton("SAR");
		
		sar.setBounds(140, 320, 100, 30);
		sar.setFocusable(false);
		sar.setFont(my_font);
		sar.addActionListener(this);
		add(sar);
		
		qr = new JButton("QR");
		
		qr.setBounds(260, 320, 100, 30);
		qr.setFocusable(false);
		qr.setFont(my_font);
		qr.addActionListener(this);
		add(qr);
		
		clear = new JButton("Clear");
		clear.setBounds(140, 380, 100, 30);
		clear.setFocusable(false);
		clear.setFont(my_font);
		clear.addActionListener(this);
		add(clear);
		
		
		setVisible(true);
	}
	
	
}
