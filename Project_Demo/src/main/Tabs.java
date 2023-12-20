package main;

import java.awt.BorderLayout;

import javax.swing.*;

public class Tabs extends JFrame{
	
	JTabbedPane reciept,stock,history,orders;
	JPanel recieptPanel,stockPanel,historyPanel,ordersPanel;
	
	public Tabs() {
		JTextArea ta=new JTextArea(390,390);  
	    JPanel p1=new JPanel();  
	    p1.add(ta);  
	    JPanel p2=new JPanel();  
	    JPanel p3=new JPanel();  
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(0,0,390,390);  
	    tp.add("main",p1);  
	    tp.add("visit",p2);  
	    tp.add("help",p3);    
	    add(tp);  
	    setSize(400,400);   
	    setVisible(true); 
	    
	}

}
