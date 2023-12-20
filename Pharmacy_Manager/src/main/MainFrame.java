package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.*;

public class MainFrame extends JFrame{
	
	JTabbedPane managementPane;
	Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
	
	// Receipt tab variables
	JPanel recieptPanel, stockPanel, historyPanel, ordersPanel;
	JPanel recepitTablePanel, recepitCustomerDataPanel, receiptPayPanel;
	JLabel discountLabel, totalAmountLabel, cashPaidLabel, returnLabel, counterNumberLabel;
	JLabel customerNameLabel, customerAddressLabel, customerMobileNumberLabel, customerDetailsLabel;
	JLabel itemNameLabel, quantityLabel, companyNameLabel;
	JButton printButton, clearButton, addButton, deleteButton;
	JTextField discountTextField, totalAmountTextField, cashPaidTextField, returnTextField;
	JTextField customerNameTextField, customerAddressTextField, customerPhoneNumberTextField;
	JTextField counterNumberTextField, quantityTextField, companyNameTextField, itemNameTextField;
	JTable receiptTable;
	JScrollPane receiptTableScrollPane;
	Object receiptData[][] = {};
	String receiptColums[] = {"Serial","Name","Rate","Quantity","Amount","Available"};
	DefaultTableModel model = new DefaultTableModel(receiptData,receiptColums);
	
	
	// Orders tab variables
	JLabel ordersProductName,ordersQuantity,ordersProductCompany;
	JTextField ordersQuantityTextfield,ordersProductCompanytTextfield,ordersProductNameTextfield;
	JTable ordersTable;
	JButton ordersAddButton,ordersClearButton,ordersSaveButton,ordersPrintButton;
	Object ordersData[][] = {};
	String ordersColumnNames[] = {"Serial","Names","Company","Quantity"};
	DefaultTableModel OrdersModel = new DefaultTableModel(ordersData,ordersColumnNames);
	
	
	
	
	
	
	public MainFrame(){
		
//		try {
//			File my_style = new File("Fonts/Inter-regular.ttf");
//			my_font = Font.createFont(Font.TRUETYPE_FONT, my_style).deriveFont(30f);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
		getContentPane().setBackground(Color.white);
		setSize(1100,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setTitle("Pharmacy Manager");
		
		// Tabbed Pane
		managementPane = new JTabbedPane();
		managementPane.setBackground(Color.gray); // tabs color
		managementPane.setBounds(0, 0, 1100, 800);
		managementPane.setForeground(Color.white);
		add(managementPane);
		
		// // Receipt Tab
		recieptPanel = new JPanel();
		recieptPanel.setLayout(null);
		
		// Receipt Table Details
		recepitTablePanel = new JPanel();
		recepitTablePanel.setLayout(null);
		recepitTablePanel.setBounds(0, 0, 800, 500);
		recepitTablePanel.setBackground(Color.gray);
		
		receiptTable = new JTable(model);
		receiptTableScrollPane = new JScrollPane(receiptTable);
		receiptTableScrollPane.setBounds(0, 0, 800, 500);
		recepitTablePanel.add(receiptTableScrollPane);
		
		recieptPanel.add(recepitTablePanel);
		
		// Receipt Pay Details
		receiptPayPanel = new JPanel();
		receiptPayPanel.setLayout(null);
		receiptPayPanel.setBounds(0, 500, 800, 230);
		receiptPayPanel.setBackground(Color.white);
		
		discountLabel = new JLabel("Discount");
		discountLabel.setBounds(20, 20, 200, 30);
		discountLabel.setFont(my_font);
		receiptPayPanel.add(discountLabel);
		
		discountTextField = new JTextField();
		discountTextField.setBounds(150, 20, 200, 30);
		receiptPayPanel.add(discountTextField);
		
		totalAmountLabel = new JLabel("Total Amount");
		totalAmountLabel.setBounds(380, 20, 200, 30);
		totalAmountLabel.setFont(my_font);
		receiptPayPanel.add(totalAmountLabel);
		
		totalAmountTextField = new JTextField();
		totalAmountTextField.setBounds(560, 20, 200, 30);
		receiptPayPanel.add(totalAmountTextField);
		
		cashPaidLabel = new JLabel("Cash Paid");
		cashPaidLabel.setBounds(20, 90, 200, 30);
		cashPaidLabel.setFont(my_font);
		receiptPayPanel.add(cashPaidLabel);
		
		cashPaidTextField = new JTextField();
		cashPaidTextField.setBounds(150, 90, 200, 30);
		receiptPayPanel.add(cashPaidTextField);
		
		returnLabel = new JLabel("Return");
		returnLabel.setBounds(380, 90, 200, 30);
		returnLabel.setFont(my_font);
		receiptPayPanel.add(returnLabel);
		
		returnTextField = new JTextField();
		returnTextField.setBounds(560, 90, 200, 30);
		receiptPayPanel.add(returnTextField);
		
		printButton = new JButton("Print");
		printButton.setBounds(20, 150, 740, 50);
		printButton.setFocusable(false);
		printButton.setBackground(Color.gray);
		printButton.setForeground(Color.white);
		printButton.setFont(my_font);
		receiptPayPanel.add(printButton);
		
		
		
		
		recieptPanel.add(receiptPayPanel);
		
		
		
		// Receipt Customer Details
		recepitCustomerDataPanel = new JPanel();
		recepitCustomerDataPanel.setLayout(null);
		recepitCustomerDataPanel.setBounds(800, 0, 280, 730);
		recepitCustomerDataPanel.setBackground(Color.white);
		recieptPanel.add(recepitCustomerDataPanel);
		
		counterNumberLabel = new JLabel("Counter Number");
		counterNumberLabel.setBounds(10, 10, 300, 20);
		counterNumberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		recepitCustomerDataPanel.add(counterNumberLabel);
		
		// // need to add dynamic label expansion!!
		
		
		companyNameLabel = new JLabel("Company Name");
		companyNameLabel.setBounds(10, 50, 200, 30);
		companyNameLabel.setFont(my_font);
		recepitCustomerDataPanel.add(companyNameLabel);
		
		companyNameTextField = new JTextField();
		companyNameTextField.setBounds(10, 90, 250, 30);
		recepitCustomerDataPanel.add(companyNameTextField);
		
		itemNameLabel = new JLabel("Item Name");
		itemNameLabel.setBounds(10, 130, 200, 30);
		itemNameLabel.setFont(my_font);
		recepitCustomerDataPanel.add(itemNameLabel);
		
		itemNameTextField = new JTextField();
		itemNameTextField.setBounds(10, 170, 250, 30);
		recepitCustomerDataPanel.add(itemNameTextField);
		
		quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(10, 210, 200, 30);
		quantityLabel.setFont(my_font);
		recepitCustomerDataPanel.add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(10, 250, 250, 30);
		recepitCustomerDataPanel.add(quantityTextField);
		
		addButton = new JButton("Add");
		addButton.setBounds(10, 300, 120, 50);
		addButton.setFocusable(false);
		addButton.setBackground(Color.gray);
		addButton.setForeground(Color.white);
		addButton.setFont(my_font);
		recepitCustomerDataPanel.add(addButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(140, 300, 120, 50);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(Color.gray);
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(my_font);
		recepitCustomerDataPanel.add(deleteButton);
		
		customerDetailsLabel = new JLabel("Customer Details:");
		customerDetailsLabel.setBounds(10, 430, 300, 20);
		customerDetailsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		recepitCustomerDataPanel.add(customerDetailsLabel);
		
		
		customerNameLabel = new JLabel("Name");
		customerNameLabel.setBounds(10, 470, 200, 20);
		customerNameLabel.setFont(my_font);
		recepitCustomerDataPanel.add(customerNameLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setBounds(10, 510, 250, 30);
		recepitCustomerDataPanel.add(customerNameTextField);
		
		customerAddressLabel = new JLabel("Address");
		customerAddressLabel.setBounds(10, 550, 200, 20);
		customerAddressLabel.setFont(my_font);
		recepitCustomerDataPanel.add(customerAddressLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setBounds(10, 590, 250, 30);
		recepitCustomerDataPanel.add(customerNameTextField);
		
		customerMobileNumberLabel = new JLabel("Phone");
		customerMobileNumberLabel.setBounds(10, 630, 200, 20);
		customerMobileNumberLabel.setFont(my_font);
		recepitCustomerDataPanel.add(customerMobileNumberLabel);
		
		customerPhoneNumberTextField = new JTextField();
		customerPhoneNumberTextField.setBounds(10, 670, 250, 30);
		recepitCustomerDataPanel.add(customerPhoneNumberTextField);
		
		
		
		managementPane.add("Receipt",recieptPanel);
		
		// Stock Tab
		stockPanel = new JPanel();
		stockPanel.setLayout(null);
				
		managementPane.add("Stock",stockPanel);
		
		// History Tab
		historyPanel = new JPanel();
		historyPanel.setLayout(null);
		
		managementPane.add("History",historyPanel);
		
		// Orders Tab
		ordersPanel = new JPanel();
		ordersPanel.setLayout(null);
		
		ordersTable = new JTable(OrdersModel);
		JScrollPane ordersScrollPane = new JScrollPane(ordersTable);
		ordersScrollPane.setBounds(10,10,1060,500);
		ordersPanel.add(ordersScrollPane);
		ordersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = ordersTable.getSelectedRow();
 
				String serial = OrdersModel.getValueAt(idx, 0).toString();
				String name = OrdersModel.getValueAt(idx, 1).toString();
				String company = OrdersModel.getValueAt(idx, 2).toString();
				String quantity = OrdersModel.getValueAt(idx, 3).toString();
			}
		});
 
		ordersProductName = new JLabel("Product Name :");
		ordersProductName.setFont(my_font);
		ordersProductName.setBounds(10,550, 170, 30);
		ordersProductName.setForeground(Color.black);
		ordersPanel.add(ordersProductName);
 
 
		ordersQuantityTextfield = new JTextField();
		ordersQuantityTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersQuantityTextfield.setBounds(150,550,200,30);
		ordersQuantityTextfield.setBackground(Color.white);
		ordersPanel.add(ordersQuantityTextfield);
 
 
		ordersQuantity = new JLabel("Quantity :");
		ordersQuantity.setFont(my_font);
		ordersQuantity.setBounds(360,550, 170, 30);
		ordersQuantity.setForeground(Color.black);
		ordersPanel.add(ordersQuantity);
 
		ordersProductCompanytTextfield = new JTextField();
		ordersProductCompanytTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersProductCompanytTextfield.setBounds(460,550,200,30);
		ordersProductCompanytTextfield.setBackground(Color.white);
		ordersPanel.add(ordersProductCompanytTextfield);
 
 
		ordersProductCompany = new JLabel("Product Company :");
		ordersProductCompany.setFont(my_font);
		ordersProductCompany.setBounds(680,550, 170, 30);
		ordersProductCompany.setForeground(Color.black);
		ordersPanel.add(ordersProductCompany);
 
		ordersProductNameTextfield = new JTextField();
		ordersProductNameTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersProductNameTextfield.setBounds(850,550,220,30);
		ordersProductNameTextfield.setBackground(Color.white);
		ordersPanel.add(ordersProductNameTextfield);
 
 
		ordersPrintButton = new JButton("Save");
		ordersPrintButton.setFont(my_font);
		ordersPrintButton.setFocusable(false);
		ordersPrintButton.setBounds(10,610,220,40);
		ordersPrintButton.setBackground(Color.gray);
		ordersPrintButton.setForeground(Color.white);
		ordersPanel.add(ordersPrintButton);
 
 
 
		ordersAddButton = new JButton("Add");
		ordersAddButton.setFont(my_font);
		ordersAddButton.setFocusable(false);
		ordersAddButton.setBounds(290,610,220,40);
		ordersAddButton.setBackground(Color.gray);
		ordersAddButton.setForeground(Color.white);
		ordersPanel.add(ordersAddButton);
 
 
		ordersAddButton = new JButton("Clear");
		ordersAddButton.setFont(my_font);
		ordersAddButton.setFocusable(false);
		ordersAddButton.setBounds(560,610,220,40);
		ordersAddButton.setBackground(Color.gray);
		ordersAddButton.setForeground(Color.white);
		ordersPanel.add(ordersAddButton);
 
 
 
 
		ordersPrintButton = new JButton("Print");
		ordersPrintButton.setFont(my_font);
		ordersPrintButton.setFocusable(false);
		ordersPrintButton.setBounds(830,610,240,40);
		ordersPrintButton.setBackground(Color.gray);
		ordersPrintButton.setForeground(Color.white);
		ordersPanel.add(ordersPrintButton);
		
		
		
		managementPane.add("Orders",ordersPanel);
		
		setVisible(true);
	}
}
