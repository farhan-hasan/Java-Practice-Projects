package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class MainFrame extends JFrame{
	
	JTabbedPane managementPane;
	JPanel recieptPanel, stockPanel, historyPanel, ordersPanel;
	JLabel discountLabel, totalAmountLabel, cashPaidLabel, returnLabel, counterNumberLabel;
	JLabel customerNameLabel, customerAddressLabel, customerMobileNumberLabel;
	JButton printButton, clearButton, addButton, deleteButton;
	JTextField discountTextField, totalAmountTextField, cashPaidTextField, returnTextField;
	JTextField customerNameTextField, customerAddressTextField, customerPhoneNumberTextField;
	JTextField quantityTextField, companyNameTextField, productNameTextField;
	JTable receiptTable;
	Object receiptData[][] = {};
	String receiptColums[] = {"Serial","Name","Rate","Quantity","Amount","Available"};
	
	DefaultTableModel model = new DefaultTableModel(receiptData,receiptColums);
	
	
	public MainFrame(){
		
		setSize(1100,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		managementPane = new JTabbedPane();
		managementPane.setBackground(Color.white); // tabs color
		managementPane.setBounds(0, 0, 1100, 800);
		add(managementPane);
		
		setVisible(true);
	}
}
