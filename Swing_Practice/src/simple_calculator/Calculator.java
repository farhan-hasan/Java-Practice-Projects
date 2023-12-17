package simple_calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {
	
	JButton _1,_2,_3,_4,_5,_6,_7,_8,_9,_0,add,sub,div,mul,clr,equal,mod,dot,root,pow;
	JTextField tf;
	JPanel text,nums,ops;
	float res = 0;
	boolean a=false,s=false,m=false,d=false,eq=false,first_inp=true;
	boolean p=false,sq=false,md=false,op=false,neg=false,input=false;
	String in = "";
	
	public void reset() {
		res = 0;
		a=false;
		s=false;
		m=false;
		d=false;
		eq=false;
		neg=false;
		input = false;
		first_inp = true;
		op = false;
		in = "";
		return;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clr) {
			tf.setText("");
			reset();
		}
		if(e.getSource()==dot) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in += ".";
			tf.setText(in);
			input = true;
		}
		if(e.getSource()==_1) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in += "1";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_2) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in += "2";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_3) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="3";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_4) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="4";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_5) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="5"; 
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_6) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="6";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_7) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="7";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_8) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="8";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_9) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="9";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==_0) {
			if(eq==true) {
				res = 0;
				eq = false;
				first_inp=true;
			}
			in+="0";
			tf.setText(in);
			input = true;
			op = false;
		}
		if(e.getSource()==add) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = true;
			s = false;
			m = false;
			d = false;
			p = false;
			sq = false;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					res+=val;
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==sub) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = true;
			m = false;
			d = false;
			p = false;
			sq = false;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				neg = true;
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					res-=val;
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==mul) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = false;
			m = true;
			d = false;
			p = false;
			sq = false;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					res*=val;
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==div) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = false;
			m = false;
			d = true;
			p = false;
			sq = false;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(res!=0)res/=val;
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==pow) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = false;
			m = false;
			d = false;
			p = true;
			sq = false;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					res = (float) Math.pow(res, val);
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==root) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = false;
			m = false;
			d = false;
			p = false;
			sq = true;
			md = false;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(first_inp==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				if(neg==false)res+=val;
				else res-=val;
				res = (float) Math.sqrt(res);
				first_inp = false;
			}
			else {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res = (float) Math.sqrt(val);
			}
			in = "";
			tf.setText("");
		}
		if(e.getSource()==mod) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			a = false;
			s = false;
			m = false;
			d = false;
			p = false;
			sq = false;
			md = true;
			op = true;
			if(first_inp==true && input == false) {
				return;
			}
			if(eq==true) {
				eq = false;
			}
			else {
				if(first_inp==true) {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					if(neg==false)res+=val;
					else res-=val;
					first_inp = false;
				}
				else {
					String t = tf.getText();
					float val = 0;
					try{
						val = Float.parseFloat(t);
					} catch(Exception e1) {
						tf.setText("Syntax Error!");
						reset();
						return;
					}
					res%=val;
					res = Math.abs(res);
				}
				in = "";
			}
			tf.setText("");
		}
		if(e.getSource()==equal) {
			if(op==true) {
				tf.setText("Syntax Error!");
				reset();
				return;
			}
			eq = true;
			if(a==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res+=val;
			}
			if(s==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res-=val;
			}
			if(m==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res*=val;
			}
			if(d==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				if(res!=0) res/=val;
			}
			if(p==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res = (float) Math.pow(res, val);
			}
			if(md==true) {
				String t = tf.getText();
				float val = 0;
				try{
					val = Float.parseFloat(t);
				} catch(Exception e1) {
					tf.setText("Syntax Error!");
					reset();
					return;
				}
				res%=val;
				res = Math.abs(res);
			}
			if(res==0)res = Math.abs(res); 
			String r = String.format("%.2f", res);
			tf.setText(r);
			a=false;
			s=false;
			m=false;
			d=false;
			p = false;
			sq = false;
			md = false;
			neg = false;
			input = false;
			op = false;
			in = "";
		}
		
	}
	
	public Calculator() {
		setSize(400, 604);
		setTitle("Calculator");
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		
		
		
		tf = new JTextField();
		tf.setBounds(0,0,385,100);
		tf.setFont(font);
		add(tf);
		
		
		text = new JPanel();
		text.setBounds(0,0,385,100);
		text.setBackground(Color.darkGray);
		text.setLayout(null);
		//text.setVisible(true);
		add(text);
		
		nums = new JPanel();
		nums.setBounds(0,100,385,465);
		nums.setBackground(Color.darkGray);
		nums.setLayout(new GridLayout(5,3,2,2));
		//nums.setVisible(true);
		add(nums);
		
		pow = new JButton("pow");
		pow.setFont(font);
		pow.addActionListener(this);
		pow.setBackground(Color.darkGray);
		pow.setForeground(Color.WHITE);
		pow.setFocusable(false);
		nums.add(pow);
		
		root = new JButton("sqrt");
		root.setFont(font);
		root.setFocusable(false);
		root.setBackground(Color.darkGray);
		root.setForeground(Color.WHITE);
		root.addActionListener(this);
		nums.add(root);
		
		
		mod = new JButton("mod");
		mod.setFont(font);
		mod.setFocusable(false);
		mod.setBackground(Color.darkGray);
		mod.setForeground(Color.WHITE);
		mod.addActionListener(this);
		nums.add(mod);
		
		clr = new JButton("C");
		clr.setFont(font);
		clr.addActionListener(this);
		clr.setBackground(Color.darkGray);
		clr.setForeground(Color.WHITE);
		clr.setFocusable(false);
		nums.add(clr);
		
		_7 = new JButton("7");
		_7.setFont(font);
		_7.setBackground(Color.gray);
		_7.setForeground(Color.WHITE);
		_7.setFocusable(false);
		_7.addActionListener(this);
		nums.add(_7);
		
		_8 = new JButton("8");
		_8.setFont(font);
		_8.setFocusable(false);
		_8.setBackground(Color.gray);
		_8.setForeground(Color.WHITE);
		_8.addActionListener(this);
		nums.add(_8);
		
		_9 = new JButton("9");
		_9.setFont(font);
		_9.setFocusable(false);
		_9.setBackground(Color.gray);
		_9.setForeground(Color.WHITE);
		_9.addActionListener(this);
		nums.add(_9);
		
		add = new JButton("+");
		add.setFont(font);
		add.addActionListener(this);
		add.setBackground(Color.darkGray);
		add.setForeground(Color.WHITE);
		add.setFocusable(false);
		nums.add(add);
		
		_4 = new JButton("4");
		_4.setFont(font);
		_4.setBackground(Color.gray);
		_4.setForeground(Color.WHITE);
		_4.addActionListener(this);
		_4.setFocusable(false);
		nums.add(_4);
		
		_5 = new JButton("5");
		_5.setFont(font);
		_5.addActionListener(this);
		_5.setBackground(Color.gray);
		_5.setForeground(Color.WHITE);
		_5.setFocusable(false);
		nums.add(_5);
		
		_6 = new JButton("6");
		_6.setBackground(Color.gray);
		_6.setForeground(Color.WHITE);
		_6.setFont(font);
		_6.setFocusable(false);
		_6.addActionListener(this);
		nums.add(_6);
		
		sub = new JButton("-");
		sub.setFont(font);
		sub.setBackground(Color.darkGray);
		sub.setForeground(Color.WHITE);
		sub.addActionListener(this);
		sub.setFocusable(false);
		nums.add(sub);
		
		_1 = new JButton("1");
		_1.setFont(font);
		_1.setBackground(Color.gray);
		_1.addActionListener(this);
		_1.setForeground(Color.WHITE);
		_1.setFocusable(false);
		nums.add(_1);
		
		_2 = new JButton("2");
		_2.setFont(font);
		_2.addActionListener(this);
		_2.setBackground(Color.gray);
		_2.setForeground(Color.WHITE);
		_2.setFocusable(false);
		nums.add(_2);
		
		_3 = new JButton("3");
		_3.setFont(font);
		_3.addActionListener(this);
		_3.setBackground(Color.gray);
		_3.setForeground(Color.WHITE);
		_3.setFocusable(false);
		nums.add(_3);
		
		mul = new JButton("*");
		mul.setFont(font);
		mul.addActionListener(this);
		mul.setFocusable(false);
		mul.setBackground(Color.darkGray);
		mul.setForeground(Color.WHITE);
		nums.add(mul);
		
		dot = new JButton(".");
		dot.setFont(font);
		dot.addActionListener(this);
		dot.setBackground(Color.darkGray);
		dot.setForeground(Color.WHITE);
		dot.setFocusable(false);
		nums.add(dot);
		
		_0 = new JButton("0");
		_0.setFocusable(false);
		_0.addActionListener(this);
		_0.setBackground(Color.gray);
		_0.setForeground(Color.WHITE);
		_0.setFont(font);
		nums.add(_0);
		
		equal = new JButton("=");
		equal.setFont(font);
		equal.addActionListener(this);
		equal.setBackground(Color.darkGray);
		equal.setForeground(Color.WHITE);
		equal.setFocusable(false);
		nums.add(equal);
		
		div = new JButton("/");
		div.setFont(font);
		div.addActionListener(this);
		div.setBackground(Color.darkGray);
		div.setForeground(Color.WHITE);
		div.setFocusable(false);
		nums.add(div);
		
		setVisible(true);
	}
	
}
