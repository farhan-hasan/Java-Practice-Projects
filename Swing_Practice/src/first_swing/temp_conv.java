package first_swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class temp_conv extends JFrame implements ActionListener{
	
	JButton cel,far,kel,clr;
	
	JTextField C,F,K,OUT;
	
	JLabel cL,fL,kL,outL,title;
	
	JPanel top,mid,bot;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clr) {
			C.setText("");
			F.setText("");
			K.setText("");
			OUT.setText("");
			return;
		}
		float res = 0;
		String t1 = C.getText();
		String t2 = F.getText();
		String t3 = K.getText();
		String[] inputs = {t1,t2,t3};
		int cnt=0;
		for(int i=0;i<3;i++) {
			if(inputs[i].length()!=0)cnt++;
		}
		
		if(cnt!=1) {
			JOptionPane.showMessageDialog(this, "Cannot enter multiple values!",
		               "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else {
			if(e.getSource()==cel) {
				if(inputs[0].length()!=0) {
					res = Float.parseFloat(inputs[0]);
				}
				else if(inputs[1].length()!=0) {
					float val = Float.parseFloat(inputs[1]);
					res = (float) ((val - 32) * (0.56));
				}
				else if(inputs[2].length()!=0){
					float val = Float.parseFloat(inputs[2]);
					res = (float) (val - 273.15);
				}
				String r = String.format("%.2f °C", res);
				OUT.setText(r);
			}
			if(e.getSource()==far) {
				if(inputs[0].length()!=0) {
					float val = Float.parseFloat(inputs[0]);
					res = (float) ((val * 1.8) + 32);
				}
				else if(inputs[1].length()!=0) {
					res = Float.parseFloat(inputs[1]);
				}
				else if(inputs[2].length()!=0){
					float val = Float.parseFloat(inputs[2]);
					res = (float) ( (val - 273.15) * 1.8 + 32);
				}
				String r = String.format("%.2f °F", res);
				OUT.setText(r);
			}
			if(e.getSource()==kel) {
				if(inputs[0].length()!=0) {
					float val = Float.parseFloat(inputs[0]);
					res = (float) (val + 273.15);
				}
				else if(inputs[1].length()!=0) {
					float val = Float.parseFloat(inputs[1]);
					res = (float) ((val - 32) * (0.56) + 273.15);
				}
				else if(inputs[2].length()!=0){
					res = Float.parseFloat(inputs[2]);
				}
				String r = String.format("%.2f °K", res);
				OUT.setText(r);
			}
		}
	}
	
	
	
	public temp_conv() {
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		setResizable(true);
		setTitle("Temperature Converter");
		Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
		
		top = new JPanel();
		top.setBounds(0,0,485,100);
		top.setBackground(Color.white);
		top.setLayout(null);
		//nums.setVisible(true);
		add(top);
		
		mid = new JPanel();
		mid.setBounds(0,100,485,250);
		mid.setBackground(Color.gray);
		mid.setLayout(null);
		//nums.setVisible(true);
		add(mid);
		
		bot = new JPanel();
		bot.setBounds(0,350,485,112);
		bot.setBackground(Color.darkGray);
		bot.setLayout(new GridLayout(1,4,2,2));
		//nums.setVisible(true);
		add(bot);
		
		title = new JLabel("Temperature Converter");
		title.setBounds(150,25,500,50);
		title.setBackground(Color.white);
		title.setForeground(Color.black);
		title.setFont(my_font);
		top.add(title);
		
		cL = new JLabel("C :");
		cL.setBounds(50,20,100,50);
		cL.setForeground(Color.white);
		cL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mid.add(cL);
		
		C = new JTextField();
		C.setBounds(150,33,300,20);
		mid.add(C);
		
		fL = new JLabel("F :");
		fL.setBounds(50,80,100,50);
		fL.setForeground(Color.white);
		fL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mid.add(fL);
		
		F = new JTextField();
		F.setBounds(150,93,300,20);
		mid.add(F);
		
		kL = new JLabel("K :");
		kL.setBounds(50,135,100,50);
		kL.setForeground(Color.white);
		kL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mid.add(kL);
		
		K = new JTextField();
		K.setBounds(150,148,300,20);
		mid.add(K);
		
		outL = new JLabel("Output :");
		outL.setBounds(50,190,100,50);
		outL.setForeground(Color.white);
		outL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mid.add(outL);
		
		OUT = new JTextField();
		OUT.setBounds(150,203,300,20);
		mid.add(OUT);
		
		cel = new JButton("C");
		cel.setFont(my_font);
		cel.addActionListener(this);
		cel.setBackground(Color.darkGray);
		cel.setForeground(Color.WHITE);
		cel.setFocusable(false);
		bot.add(cel);
		
		far = new JButton("F");
		far.setFont(my_font);
		far.addActionListener(this);
		far.setBackground(Color.darkGray);
		far.setForeground(Color.WHITE);
		far.setFocusable(false);
		bot.add(far);
		
		kel = new JButton("K");
		kel.setFont(my_font);
		kel.addActionListener(this);
		kel.setBackground(Color.darkGray);
		kel.setForeground(Color.WHITE);
		kel.setFocusable(false);
		bot.add(kel);
		
		clr = new JButton("CLR");
		clr.setFont(my_font);
		clr.addActionListener(this);
		clr.setBackground(Color.darkGray);
		clr.setForeground(Color.WHITE);
		clr.setFocusable(false);
		bot.add(clr);
		
		
		setVisible(true);
	}
	
	
}
