package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Frame extends JFrame{
	
	JTabbedPane customerProvide;
	JLabel recieptLabel,stockLabel,historyLabel,ordersLabel,counternumLabel,customernameLabel,customeradd,customerphn;
	JLabel customer,discount,total_amount,cashpaid,returne,productname,quantity,productcompany;
	JPanel receiptpanel,stockpanel,historypanel,orderspanel,tablepanel,enddrawerpannel,inputpanel;
	JTextField quantitytxt,productcompanytxt;
	JTextField productnametxt;
	JTable table;
	JButton print,clear;
	
	JTextField customernametxt, customeraddtxt,customerphntxt;
	JTextField discounttxt,totalamounttxt,cashpaidtxt,returnetxt;
	JPanel head,foot;
 
	Object data[][] = {};
	String columnNames[] = {"Serial","Names","Rate","QTY","Amount","Available"};
 
	DefaultTableModel model = new DefaultTableModel(data,columnNames);
	
	
	
	public Frame(){
		
		setSize(1400,950);
		setDefaultCloseOperation(3);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("Pharmacy Management System");
		
		
		Font font = new Font("Times New Roman",Font.BOLD,20);
		
		customerProvide = new JTabbedPane();
		recieptLabel = new JLabel("Receipt");
		receiptpanel = new JPanel();
		receiptpanel.add(recieptLabel);
		receiptpanel.setLayout(null);
		customerProvide.add("Receipt",receiptpanel);
		
		
		stockLabel = new JLabel("Stock");
		stockpanel = new JPanel();
		stockpanel.add(stockLabel);
		stockpanel.setLayout(null);
		customerProvide.add("Stock",stockpanel);
		
		
		historyLabel = new JLabel();
		historypanel = new JPanel();
		historypanel.add(historyLabel);
		historypanel.setLayout(null);
		customerProvide.add("History",historypanel);
		
		
		ordersLabel = new JLabel("Orders");
		orderspanel = new JPanel();
		orderspanel.add(ordersLabel);
		orderspanel.setLayout(null);
		customerProvide.add("Orders",orderspanel);
		
		
		head = new JPanel();
		head.setBounds(10,10,1080,700);
		head.setBackground(Color.LIGHT_GRAY);
		head.setLayout(null);
		receiptpanel.add(head);
 
		foot = new JPanel();
		foot.setBounds(10,720,1080,220);
		foot.setBackground(Color.white);
		foot.setLayout(null);
		receiptpanel.add(foot);
		
		
		enddrawerpannel = new JPanel();
		enddrawerpannel.setBounds(1090,10,290,930);
		enddrawerpannel.setBackground(Color.darkGray);
		enddrawerpannel.setLayout(null);
		receiptpanel.add(enddrawerpannel);
		
		inputpanel= new JPanel();
		inputpanel.setBounds(10,600,1060,200);
		inputpanel.setBackground(Color.lightGray);
		inputpanel.setLayout(null);
		head.add(inputpanel);
		
		
 
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10,10,1060,590);
		head.add(sp);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
 
				String serial = model.getValueAt(idx, 0).toString();
				String name = model.getValueAt(idx, 1).toString();
				String rate = model.getValueAt(idx, 2).toString();
				String QTY = model.getValueAt(idx, 3).toString();
				String amount = model.getValueAt(idx, 4).toString();
				String available = model.getValueAt(idx, 5).toString();
			}
		});
		
		productname = new JLabel("Product Name :");
		productname.setFont(font);
		productname.setBounds(10,20, 170, 30);
		productname.setForeground(Color.black);
		inputpanel.add(productname);
		
		
		productnametxt = new JTextField();
		productnametxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		productnametxt.setBounds(150,20,200,30);
		productnametxt.setBackground(Color.white);
		inputpanel.add(productnametxt);
		
		
		quantity = new JLabel("Quantity :");
		quantity.setFont(font);
		quantity.setBounds(360,20, 170, 30);
		quantity.setForeground(Color.black);
		inputpanel.add(quantity);
		
		quantitytxt = new JTextField();
		quantitytxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		quantitytxt.setBounds(460,20,200,30);
		quantitytxt.setBackground(Color.white);
		inputpanel.add(quantitytxt);
		
		
		productcompany = new JLabel("Product Company :");
		productcompany.setFont(font);
		productcompany.setBounds(680,20, 170, 30);
		productcompany.setForeground(Color.black);
		inputpanel.add(productcompany);
		
		productcompanytxt = new JTextField();
		productcompanytxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		productcompanytxt.setBounds(850,20,220,30);
		productcompanytxt.setBackground(Color.white);
		inputpanel.add(productcompanytxt);
		
		
		counternumLabel = new JLabel("Counter Number : ");
		counternumLabel.setFont(font);
		counternumLabel.setBounds(10,10, 170, 30);
		counternumLabel.setForeground(Color.black);
		foot.add(counternumLabel);
		
		customernameLabel = new JLabel(" Name :");
		customernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		customernameLabel.setBounds(10,60, 170, 30);
		customernameLabel.setForeground(Color.green);
		foot.add(customernameLabel);
		
	
		
		customernametxt = new JTextField();
		customernametxt.setFont(new Font("Times New Roman", Font.BOLD, 30));
		customernametxt.setBounds(10, 95,220,50);
		customernametxt.setBackground(Color.white);
		foot.add(customernametxt);
		
		
		customeradd = new JLabel(" Address :");
		customeradd.setFont(new Font("Times New Roman", Font.BOLD, 30));
		customeradd.setBounds(275,60, 170, 30);
		customeradd.setForeground(Color.green);
		foot.add(customeradd);
		
	
		
		customeraddtxt = new JTextField();
		customeraddtxt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		customeraddtxt.setBounds(275, 95,220,50);
		customeraddtxt.setBackground(Color.white);
		foot.add(customeraddtxt);
		
		
		customerphn = new JLabel(" Mobile No :");
		customerphn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		customerphn.setBounds(540,60, 170, 30);
		customerphn.setForeground(Color.green);
		foot.add(customerphn);
		
		
		customerphntxt = new JTextField();
		customerphntxt.setFont(new Font("Times New Roman", Font.BOLD, 30));
		customerphntxt.setBounds(540, 95,220,50);
		customerphntxt.setBackground(Color.white);
		foot.add(customerphntxt);
		
		
		customer = new JLabel("(Customer)");
		customer.setFont(new Font("Times New Roman", Font.BOLD, 40));
		customer.setBounds(770,65,200,100);
		customer.setForeground(Color.green);
		foot.add(customer);
		
		
		discount = new JLabel("Discount:");
		discount.setFont(font);
		discount.setBounds(20,60, 170, 30);
		discount.setForeground(Color.red);
		enddrawerpannel.add(discount);
		
		
		discounttxt = new JTextField();
		discounttxt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		discounttxt.setBounds(20, 95,220,50);
		discounttxt.setBackground(Color.white);
		enddrawerpannel.add(discounttxt);
		
		
		total_amount = new JLabel("Total Amount :");
		total_amount.setFont(font);
		total_amount.setBounds(20,155, 170, 30);
		total_amount.setForeground(Color.red);
		enddrawerpannel.add(total_amount);
		
		
		totalamounttxt = new JTextField();
		totalamounttxt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		totalamounttxt.setBounds(20, 195,220,50);
		totalamounttxt.setBackground(Color.white);
		enddrawerpannel.add(totalamounttxt);
		
		
		cashpaid = new JLabel("Cash Paid :");
		cashpaid.setFont(font);
		cashpaid.setBounds(20,255, 170, 30);
		cashpaid.setForeground(Color.red);
		enddrawerpannel.add(cashpaid);
		
		
		cashpaidtxt = new JTextField();
		cashpaidtxt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		cashpaidtxt.setBounds(20, 295,220,50);
		cashpaidtxt.setBackground(Color.white);
		enddrawerpannel.add(cashpaidtxt);
		
		
		returne = new JLabel("Return :");
		returne.setFont(font);
		returne.setBounds(20,355, 170, 30);
		returne.setForeground(Color.red);
		enddrawerpannel.add(returne);
		
		
		returnetxt = new JTextField();
		returnetxt.setFont(new Font("Times New Roman", Font.BOLD, 25));
		returnetxt.setBounds(20, 395,220,50);
		returnetxt.setBackground(Color.white);
		enddrawerpannel.add(returnetxt);
		
		
		
		print = new JButton("Print");
		print.setFont(font);
		print.setFocusable(false);
		print.setBounds(20,495,220,40);
		print.setBackground(Color.orange);
		print.setForeground(Color.white);
		enddrawerpannel.add(print);
		
		
		
		clear = new JButton("Clear");
		clear.setFont(font);
		clear.setFocusable(false);
		clear.setBounds(20,545,220,40);
		clear.setBackground(Color.red);
		clear.setForeground(Color.white);
		enddrawerpannel.add(clear);
			
		add(customerProvide);
		setVisible(true);
	}
}
