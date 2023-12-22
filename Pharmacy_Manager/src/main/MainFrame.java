package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class MainFrame extends JFrame{
	
	
	JTabbedPane managementPane;
	Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
	
	// Receipt tab variables
	JPanel recieptPanel, stockPanel, historyPanel, ordersPanel;
	JPanel recepitTablePanel, recepitCustomerDataPanel, receiptPayPanel;
	JLabel discountLabel, totalAmountLabel, cashPaidLabel, returnLabel, counterNumberLabel, discountTotalAmountLabel;
	JLabel customerNameLabel, customerAddressLabel, customerMobileNumberLabel, customerDetailsLabel;
	JLabel itemNameLabel, quantityLabel, companyNameLabel;
	JButton updateButton, printButton, clearButton, addButton, deleteButton, applyDiscountButton, calculatReturnButton;
	JTextField discountTextField, totalAmountTextField, cashPaidTextField, returnTextField, discountTotalAmountTextField;
	JTextField customerNameTextField, customerAddressTextField, customerPhoneNumberTextField;
	JTextField counterNumberTextField, quantityTextField, companyNameTextField, itemNameTextField;
	JTable receiptTable;
	JScrollPane receiptTableScrollPane;
	Object receiptData[][] = {};
	String receiptColums[] = {"Serial","Name","Rate","Quantity","Amount"};
	DefaultTableModel receiptTableModel = new DefaultTableModel(receiptData,receiptColums);
	
	// Stock tab variables
	JLabel stockCompanyNameLabel,stockDrugListLabel,stockSearchMedicineLNameLabel,stockSearchLabel,stockAddMedicineLabel;
	JTextField stockSearchCompanyNameTextField,stockSearchMedicineTextField;
	JTable stockTable;
	Object stockData[][] = {};
	String stockColumnNames[] = {"Name","MRP","TP","Quantity","Company"};
	DefaultTableModel stockModel = new DefaultTableModel(stockData,stockColumnNames);
	JLabel stockAddProductNameLabel,stockAddProductTPLabel,stockAddProductMRPLabel,stockAddQuantityLabel,stockAddCompanyLabel;
	JPanel stockInformationPanel;
	JButton stockAddButton,stockSearchButton,stockPrintButton,stockDeleteButton,stockUpdateButton;
	JTextField stockAddProductNameTextField,stockAddProductTPTextField,stockAddProductMRPTextField,stockAddQuantityTextField,stockAddCompanyTextField;
	
	// History tab variables
	JPanel historyDetailsPanel;
	JLabel historyTimePeriodLabel, historyCounterNumberLabel, historyTotalItemsLabel, historyTotalQuantityLabel, historyTotalDiscountLabel;
	JLabel historyTotalMRPLabel, historyTotalBuyingPriceLabel, historyTotalMarginLabel;
	JLabel historyFromLabel, historyToLabel;
	JTextField historyTimePeriodTextField, historyCounterNumberTextField, historyTotalItemsTextField, historyTotalQuantityTextField;
	JTextField historyTotalDiscountTextField, historyTotalMRPTextField, historyTotalBuyingPriceTextField, historyTotalMarginTextField;
	JDateChooser historyFrom, historyTo;
	JComboBox<Integer> historycounterNumberComboBox;
	JButton historySearch;
	
	// Orders tab variables
	JLabel ordersProductName,ordersQuantity,ordersProductCompany;
	JTextField ordersQuantityTextfield,ordersProductCompanytTextfield,ordersProductNameTextfield;
	JTable ordersTable;
	JButton ordersAddButton,ordersDeleteButton,ordersClearButton,ordersSaveButton,ordersPrintButton,ordersUpdateButton;
	Object ordersData[][] = {};
	String ordersColumnNames[] = {"Serial","Names","Company","Quantity"};
	DefaultTableModel OrdersModel = new DefaultTableModel(ordersData,ordersColumnNames);
	

	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public MainFrame(){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy_manager","root","");
			st = con.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
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
		recepitTablePanel.setBackground(Color.white);
		
		receiptTable = new JTable(receiptTableModel);
		receiptTableScrollPane = new JScrollPane(receiptTable);
		receiptTableScrollPane.setBounds(10, 10, 790, 490);
		recepitTablePanel.add(receiptTableScrollPane);
		receiptTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = receiptTable.getSelectedRow();
				
				String item = receiptTableModel.getValueAt(idx, 1).toString();
				String quantity = receiptTableModel.getValueAt(idx, 3).toString();
				
				itemNameTextField.setText(item);
				quantityTextField.setText(quantity);
			}
		});
		
		recieptPanel.add(recepitTablePanel);
		
		// Receipt Pay Details
		receiptPayPanel = new JPanel();
		receiptPayPanel.setLayout(null);
		receiptPayPanel.setBounds(0, 500, 800, 230);
		receiptPayPanel.setBackground(Color.white);
		
		totalAmountLabel = new JLabel("Total Amount");
		totalAmountLabel.setBounds(20, 20, 200, 30);
		totalAmountLabel.setFont(my_font);
		receiptPayPanel.add(totalAmountLabel);
		
		totalAmountTextField = new JTextField();
		totalAmountTextField.setBounds(150, 20, 200, 30);
		receiptPayPanel.add(totalAmountTextField);
		
		discountLabel = new JLabel("Discount");
		discountLabel.setBounds(20, 90, 200, 30);
		discountLabel.setFont(my_font);
		receiptPayPanel.add(discountLabel);
		
		discountTextField = new JTextField();
		discountTextField.setBounds(150, 90, 100, 30);
		receiptPayPanel.add(discountTextField);
		
		applyDiscountButton = new JButton("Apply");
		applyDiscountButton.setBounds(260, 90, 90, 30);
		applyDiscountButton.setFocusable(false);
		applyDiscountButton.setBackground(Color.gray);
		applyDiscountButton.setForeground(Color.white);
		applyDiscountButton.setFont(my_font);
		receiptPayPanel.add(applyDiscountButton);
		
		discountTotalAmountLabel = new JLabel("Total Payable");
		discountTotalAmountLabel.setBounds(20, 160, 200, 30);
		discountTotalAmountLabel.setFont(my_font);
		receiptPayPanel.add(discountTotalAmountLabel);
		
		discountTotalAmountTextField = new JTextField();
		discountTotalAmountTextField.setBounds(150, 160, 200, 30);
		receiptPayPanel.add(discountTotalAmountTextField);
		
		cashPaidLabel = new JLabel("Cash Paid");
		cashPaidLabel.setBounds(380, 20, 200, 30);
		cashPaidLabel.setFont(my_font);
		receiptPayPanel.add(cashPaidLabel);
		
		cashPaidTextField = new JTextField();
		cashPaidTextField.setBounds(560, 20, 100, 30);
		receiptPayPanel.add(cashPaidTextField);
		
		calculatReturnButton = new JButton("Count");
		calculatReturnButton.setBounds(670, 20, 90, 30);
		calculatReturnButton.setFocusable(false);
		calculatReturnButton.setBackground(Color.gray);
		calculatReturnButton.setForeground(Color.white);
		calculatReturnButton.setFont(my_font);
		receiptPayPanel.add(calculatReturnButton);
		
		returnLabel = new JLabel("Return");
		returnLabel.setBounds(380, 90, 200, 30);
		returnLabel.setFont(my_font);
		receiptPayPanel.add(returnLabel);
		
		returnTextField = new JTextField();
		returnTextField.setBounds(560, 90, 200, 30);
		receiptPayPanel.add(returnTextField);
		
		printButton = new JButton("Print");
		printButton.setBounds(560, 160, 200, 30);
		printButton.setFocusable(false);
		printButton.setBackground(Color.gray);
		printButton.setForeground(Color.white);
		printButton.setFont(my_font);
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(receiptTableModel.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "No data found.");
					return;
				}
				
				MessageFormat header = new MessageFormat("Receipt");
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					receiptTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
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
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productName = itemNameTextField.getText();
				String quantity = quantityTextField.getText();
				String companyName = companyNameTextField.getText();
				if(productName.equals("") || quantity.equals("") || companyName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter data in all the fields.");
					return;
				}
				double rate = 0.0, amount = 0.0;
				int serial = receiptTableModel.getRowCount() + 1, available = 0;
				
				Object newRow[] = {serial,productName,rate,quantity,amount,available};
				
				receiptTableModel.addRow(newRow);
				
				// // DB implementation left
				itemNameTextField.setText("");
				quantityTextField.setText("");
				companyNameTextField.setText("");
			}
			
		});
		recepitCustomerDataPanel.add(addButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(140, 300, 120, 50);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(Color.gray);
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(my_font);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int serial = receiptTableModel.getRowCount();
					int idx = receiptTable.getSelectedRow();
					receiptTableModel.removeRow(receiptTable.getSelectedRow());
					for(int i=1;i<serial;i++) {
						receiptTableModel.setValueAt(i, i-1, 0);
					}
				
				} catch(Exception e1) {
					if(receiptTableModel.getRowCount()==0)
						JOptionPane.showMessageDialog(null, "No data found!");
					else 
						JOptionPane.showMessageDialog(null, "Please select a row!");
				}
				
			}
		});
		recepitCustomerDataPanel.add(deleteButton);
		
		updateButton = new JButton("Update");
		updateButton.setBounds(75, 360, 120, 50);
		updateButton.setFocusable(false);
		updateButton.setBackground(Color.gray);
		updateButton.setForeground(Color.white);
		updateButton.setFont(my_font);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String item = itemNameTextField.getText();
				String quantity = quantityTextField.getText();
				int idx = receiptTable.getSelectedRow();
				
				receiptTableModel.setValueAt(item, idx, 1);
				receiptTableModel.setValueAt(quantity, idx, 3);
				//JFrame f = new JFrame();
				
			}
		});
		recepitCustomerDataPanel.add(updateButton);
		
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
		
		// // Stock Tab
		stockPanel = new JPanel();
		stockPanel.setBackground(Color.white);
		stockPanel.setLayout(null);
		
		
		// Drug List area
		stockDrugListLabel = new JLabel("Drug List ");
		stockDrugListLabel.setFont(my_font);
		stockDrugListLabel.setBounds(20,20, 170, 30);
		stockDrugListLabel.setForeground(Color.black);
		stockPanel.add(stockDrugListLabel);
 
		stockTable = new JTable(stockModel);
		JScrollPane stockScrollPane = new JScrollPane(stockTable);
		stockScrollPane.setBounds(20,50,750,500);
		stockTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int idx = stockTable.getSelectedRow();
				
				String name = stockModel.getValueAt(idx, 0).toString();
				String mrp = stockModel.getValueAt(idx, 1).toString();
				String tp = stockModel.getValueAt(idx, 2).toString();
				String quantity = stockModel.getValueAt(idx, 3).toString();
				String company = stockModel.getValueAt(idx, 4).toString();
				
				stockAddProductNameTextField.setText(name);
				stockAddProductMRPTextField.setText(mrp);
				stockAddProductTPTextField.setText(tp);
				stockAddQuantityTextField.setText(quantity);
				stockAddCompanyTextField.setText(company);
			}
		});
		stockPanel.add(stockScrollPane);

 
 
		stockInformationPanel = new JPanel();
		stockInformationPanel.setBounds(773,50,305,681);
		stockInformationPanel.setBackground(Color.white);
		stockInformationPanel.setLayout(null);
		stockPanel.add(stockInformationPanel);
 
		// Search area
		stockSearchLabel = new JLabel("Search Here");
		stockSearchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		stockSearchLabel.setBounds(10,0, 170, 30);
		stockSearchLabel.setForeground(Color.black);
		stockInformationPanel.add(stockSearchLabel);
 
 
		stockCompanyNameLabel = new JLabel("Company Name ");
		stockCompanyNameLabel.setFont(my_font);
		stockCompanyNameLabel.setBounds(10,40, 170, 30);
		stockCompanyNameLabel.setForeground(Color.black);
		stockInformationPanel.add(stockCompanyNameLabel);
 
 
		stockSearchCompanyNameTextField = new JTextField();
		stockSearchCompanyNameTextField.setFont(my_font);
		stockSearchCompanyNameTextField.setBounds(10,80,270,30);
		stockSearchCompanyNameTextField.setBackground(Color.white);
		stockInformationPanel.add(stockSearchCompanyNameTextField);
 
 
		stockSearchMedicineLNameLabel = new JLabel("Medicine Name");
		stockSearchMedicineLNameLabel.setFont(my_font);
		stockSearchMedicineLNameLabel.setBounds(10,110, 170, 30);
		stockSearchMedicineLNameLabel.setForeground(Color.black);
		stockInformationPanel.add(stockSearchMedicineLNameLabel);
 
 
		stockSearchMedicineTextField = new JTextField();
		stockSearchMedicineTextField.setFont(my_font);
		stockSearchMedicineTextField.setBounds(10,150,270,30);
		stockSearchMedicineTextField.setBackground(Color.white);
		stockInformationPanel.add(stockSearchMedicineTextField);
 
 
 
 
		// Add area
		stockAddMedicineLabel = new JLabel("Add Medicine");
		stockAddMedicineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		stockAddMedicineLabel.setBounds(10,240, 200, 30);
		stockAddMedicineLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddMedicineLabel);
 
 
 
		stockAddProductNameLabel = new JLabel("Name");
		stockAddProductNameLabel.setFont(my_font);
		stockAddProductNameLabel.setBounds(10,280, 170, 30);
		stockAddProductNameLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddProductNameLabel);
 
 
 
		stockAddProductNameTextField = new JTextField();
		stockAddProductNameTextField.setFont(my_font);
		stockAddProductNameTextField.setBounds(10,320,270,30);
		stockAddProductNameTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddProductNameTextField);
 
 
		stockAddProductMRPLabel = new JLabel("MRP");
		stockAddProductMRPLabel.setFont(my_font);
		stockAddProductMRPLabel.setBounds(10,360, 170, 30);
		stockAddProductMRPLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddProductMRPLabel);
 
		stockAddProductMRPTextField = new JTextField();
		stockAddProductMRPTextField.setFont(my_font);
		stockAddProductMRPTextField.setBounds(10,400,125,30);
		stockAddProductMRPTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddProductMRPTextField);
		
		stockAddProductTPLabel = new JLabel("TP");
		stockAddProductTPLabel.setFont(my_font);
		stockAddProductTPLabel.setBounds(145,360, 170, 30);
		stockAddProductTPLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddProductTPLabel);
 
		stockAddProductTPTextField = new JTextField();
		stockAddProductTPTextField.setFont(my_font);
		stockAddProductTPTextField.setBounds(145,400,135,30);
		stockAddProductTPTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddProductTPTextField);
 
		stockAddQuantityLabel = new JLabel("Quantity");
		stockAddQuantityLabel.setFont(my_font);
		stockAddQuantityLabel.setBounds(10,440, 170, 30);
		stockAddQuantityLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddQuantityLabel);
 
 
		stockAddQuantityTextField = new JTextField();
		stockAddQuantityTextField.setFont(my_font);
		stockAddQuantityTextField.setBounds(10,480,270,30);
		stockAddQuantityTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddQuantityTextField);
 
 
		stockAddCompanyLabel = new JLabel("Company");
		stockAddCompanyLabel.setFont(my_font);
		stockAddCompanyLabel.setBounds(10,520,170, 30);
		stockAddCompanyLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddCompanyLabel);
 
 
		stockAddCompanyTextField = new JTextField();
		stockAddCompanyTextField.setFont(my_font);
		stockAddCompanyTextField.setBounds(10,560,270,30);
		stockAddCompanyTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddCompanyTextField);
		
		stockPrintButton = new JButton("Print");
		stockPrintButton.setFont(my_font);
		stockPrintButton.setFocusable(false);
		stockPrintButton.setBounds(30,600,130,40);
		stockPrintButton.setBackground(Color.gray);
		stockPrintButton.setForeground(Color.white);
		stockPrintButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stockModel.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "No data found.");
					return;
				}
				MessageFormat header = new MessageFormat("Stock list");
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					stockTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		stockPanel.add(stockPrintButton);
 
 
		stockAddButton = new JButton("Add");
		stockAddButton.setFont(my_font);
		stockAddButton.setFocusable(false);
		stockAddButton.setBounds(180,600,130,40);
		stockAddButton.setBackground(Color.gray);
		stockAddButton.setForeground(Color.white);
		stockAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String stockproductName = stockAddProductNameTextField.getText();
				String stockMRP = stockAddProductMRPTextField.getText();
				String stockTP = stockAddProductTPTextField.getText();
				String stockQuantity = stockAddQuantityTextField.getText();
				String stockCompanyName = stockAddCompanyTextField.getText();
				if(stockproductName.equals("") || stockAddProductMRPTextField.equals("") || stockQuantity.equals("") || stockCompanyName.equals("") ||
						stockAddProductTPTextField.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter data in all the fields.");
					return;
				}
				double stock_Rate = 0.0, stock_Quantity = 0.0;
				int serial = receiptTableModel.getRowCount() + 1, available = 0;
				
				Object newRow[] = {stockproductName,stockMRP,stockTP,stockQuantity,stockCompanyName};
				
				stockModel.addRow(newRow);
				stockAddProductNameTextField.setText("");
				stockAddProductMRPTextField.setText("");
				stockAddProductTPTextField.setText("");
				stockAddQuantityTextField.setText("");
				stockAddCompanyTextField.setText("");
			}
		});
		stockPanel.add(stockAddButton);
		
		
		stockDeleteButton = new JButton("Delete");
		stockDeleteButton.setFont(my_font);
		stockDeleteButton.setFocusable(false);
		stockDeleteButton.setBounds(330,600,130,40);
		stockDeleteButton.setBackground(Color.gray);
		stockDeleteButton.setForeground(Color.white);
		stockDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int stockDeleteIndex = stockTable.getSelectedRow();
					
					String stockName = stockModel.getValueAt(stockDeleteIndex, 0).toString();
					String stockMRP = stockModel.getValueAt(stockDeleteIndex, 1).toString();
					String stockTP = stockModel.getValueAt(stockDeleteIndex, 2).toString();
					String stockQuantity = stockModel.getValueAt(stockDeleteIndex, 3).toString();
					String stockCompany = stockModel.getValueAt(stockDeleteIndex, 4).toString();
					
					stockModel.removeRow(stockTable.getSelectedRow());	
				} catch(Exception e1) {
					if(stockModel.getRowCount()==0)
						JOptionPane.showMessageDialog(null, "No data found!");
					else 
						JOptionPane.showMessageDialog(null, "Please select a row!");
				}
			}
		});
		stockPanel.add(stockDeleteButton);
		
		stockUpdateButton = new JButton("Update");
		stockUpdateButton.setFont(my_font);
		stockUpdateButton.setFocusable(false);
		stockUpdateButton.setBounds(480,600,130,40);
		stockUpdateButton.setBackground(Color.gray);
		stockUpdateButton.setForeground(Color.white);
		stockUpdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = stockAddProductNameTextField.getText();
				String mrp = stockAddProductMRPTextField.getText();
				String tp = stockAddProductTPTextField.getText();
				String quantity = stockAddQuantityTextField.getText();
				String company = stockAddCompanyTextField.getText();
				int idx = stockTable.getSelectedRow();
				
				stockModel.setValueAt(name, idx, 0);
				stockModel.setValueAt(mrp, idx, 1);
				stockModel.setValueAt(tp, idx, 2);
				stockModel.setValueAt(quantity, idx, 3);
				stockModel.setValueAt(company, idx, 4);
				//JFrame f = new JFrame();
				
			}
		});
		stockPanel.add(stockUpdateButton);
		
		stockSearchButton = new JButton("Search");
		stockSearchButton.setFont(my_font);
		stockSearchButton.setFocusable(false);
		stockSearchButton.setBounds(630,600,130,40);
		stockSearchButton.setBackground(Color.gray);
		stockSearchButton.setForeground(Color.white);
		stockPanel.add(stockSearchButton);
		
				
		managementPane.add("Stock",stockPanel);
		
		// // History Tab
		historyPanel = new JPanel();
		historyPanel.setBackground(Color.white);
		historyPanel.setLayout(null);
		
		
		historyTimePeriodLabel = new JLabel("Time Period");
		historyTimePeriodLabel.setFont(my_font);
		historyTimePeriodLabel.setBounds(20, 20, 170, 30);
		historyTimePeriodLabel.setForeground(Color.black);
		historyPanel.add(historyTimePeriodLabel);
		
		historyFromLabel = new JLabel("From");
		historyFromLabel.setFont(my_font);
		historyFromLabel.setBounds(20, 60, 170, 30);
		historyFromLabel.setForeground(Color.black);
		historyPanel.add(historyFromLabel);
		
		historyFrom = new JDateChooser();
		historyFrom.setBounds(120, 60, 170, 30);
		historyPanel.add(historyFrom);
		
		historyToLabel = new JLabel("To");
		historyToLabel.setFont(my_font);
		historyToLabel.setBounds(360, 60, 170, 30);
		historyToLabel.setForeground(Color.black);
		historyPanel.add(historyToLabel);
		
		historyTo = new JDateChooser();
		historyTo.setBounds(430, 60, 170, 30);
		historyPanel.add(historyTo);
		
		historyCounterNumberLabel = new JLabel("Counter Number");
		historyCounterNumberLabel.setFont(my_font);
		historyCounterNumberLabel.setBounds(660, 60, 170, 30);
		historyCounterNumberLabel.setForeground(Color.black);
		historyPanel.add(historyCounterNumberLabel);
		
		Integer[] counterNumbers = {1,2,3,4,5};
		historycounterNumberComboBox = new JComboBox<Integer>(counterNumbers);
		historycounterNumberComboBox.setBounds(840, 60, 170, 30);
		historyPanel.add(historycounterNumberComboBox);
		
		
		historyDetailsPanel = new JPanel();
		historyDetailsPanel.setBackground(Color.gray);
		historyDetailsPanel.setBounds(10, 100, 1060, 625);
		historyDetailsPanel.setLayout(null);
		historyPanel.add(historyDetailsPanel);
		
		
		// history details panel
		
		historyTotalItemsLabel = new JLabel("Total Items");
		historyTotalItemsLabel.setFont(my_font);
		historyTotalItemsLabel.setBounds(20, 20, 170, 30);
		historyTotalItemsLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalItemsLabel);
		
		
		historyTotalItemsTextField = new JTextField();
		historyTotalItemsTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalItemsTextField.setBounds(20,60,200,30);
		historyTotalItemsTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalItemsTextField);
		
		
		historyTotalQuantityLabel = new JLabel("Total Quantity");
		historyTotalQuantityLabel.setFont(my_font);
		historyTotalQuantityLabel.setBounds(320,20, 170, 30);
		historyTotalQuantityLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalQuantityLabel);
		
		
		historyTotalQuantityTextField = new JTextField();
		historyTotalQuantityTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalQuantityTextField.setBounds(320,60,200,30);
		historyTotalQuantityTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalQuantityTextField);
		
		
		historyTotalDiscountLabel = new JLabel("Total Discount");
		historyTotalDiscountLabel.setFont(my_font);
		historyTotalDiscountLabel.setBounds(620,20, 170, 30);
		historyTotalDiscountLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalDiscountLabel);
		
		
		historyTotalDiscountTextField = new JTextField();
		historyTotalDiscountTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalDiscountTextField.setBounds(620,60,200,30);
		historyTotalDiscountTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalDiscountTextField);
		
		
		historyTotalMRPLabel = new JLabel("Total MRP");
		historyTotalMRPLabel.setFont(my_font);
		historyTotalMRPLabel.setBounds(20,120, 170, 30);
		historyTotalMRPLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalMRPLabel);
		
		
		historyTotalMRPTextField = new JTextField();
		historyTotalMRPTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalMRPTextField.setBounds(20,160,200,30);
		historyTotalMRPTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalMRPTextField);
		
		historyTotalBuyingPriceLabel = new JLabel("Total TP");
		historyTotalBuyingPriceLabel.setFont(my_font);
		historyTotalBuyingPriceLabel.setBounds(320,120, 170, 30);
		historyTotalBuyingPriceLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalBuyingPriceLabel);
		
		
		historyTotalBuyingPriceTextField = new JTextField();
		historyTotalBuyingPriceTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalBuyingPriceTextField.setBounds(320,160,200,30);
		historyTotalBuyingPriceTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalBuyingPriceTextField);
		
		
		historyTotalMarginLabel = new JLabel("Total Margin");
		historyTotalMarginLabel.setFont(my_font);
		historyTotalMarginLabel.setBounds(620,120, 170, 30);
		historyTotalMarginLabel.setForeground(Color.black);
		historyDetailsPanel.add(historyTotalMarginLabel);
		
		
		historyTotalMarginTextField = new JTextField();
		historyTotalMarginTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		historyTotalMarginTextField.setBounds(620,160,200,30);
		historyTotalMarginTextField.setBackground(Color.white);
		historyDetailsPanel.add(historyTotalMarginTextField);
		
		
		
		
		
		
		managementPane.add("History",historyPanel);
		
		// // Orders Tab
		ordersPanel = new JPanel();
		ordersPanel.setBackground(Color.white);
		ordersPanel.setLayout(null);
		
		ordersTable = new JTable(OrdersModel);
		ordersTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = ordersTable.getSelectedRow();
				
				String product = OrdersModel.getValueAt(idx, 1).toString();
				String quantity = OrdersModel.getValueAt(idx, 2).toString();
				String company = OrdersModel.getValueAt(idx, 3).toString();
				
				ordersProductNameTextfield.setText(product);
				ordersQuantityTextfield.setText(quantity);
				ordersProductCompanytTextfield.setText(company);
				
//				serialFromTF = s;
//				nameFromTF = n;
//				idFromTF = i;
				
			}
			
		});
		JScrollPane ordersScrollPane = new JScrollPane(ordersTable);
		ordersScrollPane.setBounds(10,10,1060,500);
		ordersPanel.add(ordersScrollPane);
 
		// Product Name
		ordersProductName = new JLabel("Product Name :");
		ordersProductName.setFont(my_font);
		ordersProductName.setBounds(10,550, 170, 30);
		ordersProductName.setForeground(Color.black);
		ordersPanel.add(ordersProductName);
 
 
		ordersProductNameTextfield = new JTextField();
		ordersProductNameTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersProductNameTextfield.setBounds(150,550,200,30);
		ordersProductNameTextfield.setBackground(Color.white);
		ordersPanel.add(ordersProductNameTextfield);
 
		// Quantity
		ordersQuantity = new JLabel("Quantity :");
		ordersQuantity.setFont(my_font);
		ordersQuantity.setBounds(360,550, 170, 30);
		ordersQuantity.setForeground(Color.black);
		ordersPanel.add(ordersQuantity);
 
		ordersQuantityTextfield = new JTextField();
		ordersQuantityTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersQuantityTextfield.setBounds(460,550,200,30);
		ordersQuantityTextfield.setBackground(Color.white);
		ordersPanel.add(ordersQuantityTextfield);
 
		// Company Name
		ordersProductCompany = new JLabel("Product Company :");
		ordersProductCompany.setFont(my_font);
		ordersProductCompany.setBounds(680,550, 170, 30);
		ordersProductCompany.setForeground(Color.black);
		ordersPanel.add(ordersProductCompany);
 
		ordersProductCompanytTextfield = new JTextField();
		ordersProductCompanytTextfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ordersProductCompanytTextfield.setBounds(850,550,220,30);
		ordersProductCompanytTextfield.setBackground(Color.white);
		ordersPanel.add(ordersProductCompanytTextfield);
 
		// Buttons
		ordersAddButton = new JButton("Add");
		ordersAddButton.setFont(my_font);
		ordersAddButton.setFocusable(false);
		ordersAddButton.setBounds(10,610,220,40);
		ordersAddButton.setBackground(Color.gray);
		ordersAddButton.setForeground(Color.white);
		ordersAddButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String productName = ordersProductNameTextfield.getText();
				String quantity = ordersQuantityTextfield.getText();
				String companyName = ordersProductCompanytTextfield.getText();
				int serial = OrdersModel.getRowCount() + 1;
				
				if(productName.equals("") || quantity.equals("") || companyName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter data in all the fields.");
					return;
				}
				
				Object newRow[] = {serial,productName,companyName,quantity};
				
				OrdersModel.addRow(newRow);
				
				ordersProductNameTextfield.setText("");
				ordersQuantityTextfield.setText("");
				ordersProductCompanytTextfield.setText("");
			}
			
		});
		ordersPanel.add(ordersAddButton);
 
 
		ordersDeleteButton = new JButton("Delete");
		ordersDeleteButton.setFont(my_font);
		ordersDeleteButton.setFocusable(false);
		ordersDeleteButton.setBounds(290,610,220,40);
		ordersDeleteButton.setBackground(Color.gray);
		ordersDeleteButton.setForeground(Color.white);
		ordersDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int serial = OrdersModel.getRowCount();
					int idx = ordersTable.getSelectedRow();
					OrdersModel.removeRow(ordersTable.getSelectedRow());
					
					for(int i=1;i<serial;i++) {
						OrdersModel.setValueAt(i, i-1, 0);
					}
					ordersProductNameTextfield.setText("");
					ordersQuantityTextfield.setText("");
					ordersProductCompanytTextfield.setText("");
				} catch(Exception e1) {
					if(OrdersModel.getRowCount()==0)
						JOptionPane.showMessageDialog(null, "No data found!");
					else 
						JOptionPane.showMessageDialog(null, "Please select a row!");
				}
			}
		});
		ordersPanel.add(ordersDeleteButton);
 
 
		ordersSaveButton = new JButton("Save");
		ordersSaveButton.setFont(my_font);
		ordersSaveButton.setFocusable(false);
		ordersSaveButton.setBounds(560,610,220,40);
		ordersSaveButton.setBackground(Color.gray);
		ordersSaveButton.setForeground(Color.white);
		ordersPanel.add(ordersSaveButton);
 
 
		ordersPrintButton = new JButton("Print");
		ordersPrintButton.setFont(my_font);
		ordersPrintButton.setFocusable(false);
		ordersPrintButton.setBounds(830,610,240,40);
		ordersPrintButton.setBackground(Color.gray);
		ordersPrintButton.setForeground(Color.white);
		ordersPrintButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(OrdersModel.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "No data found.");
					return;
				}
				MessageFormat header = new MessageFormat("Order list");
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					ordersTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		ordersPanel.add(ordersPrintButton);
		
		ordersUpdateButton = new JButton("Update");
		ordersUpdateButton.setFont(my_font);
		ordersUpdateButton.setFocusable(false);
		ordersUpdateButton.setBounds(290,680,220,40);
		ordersUpdateButton.setBackground(Color.gray);
		ordersUpdateButton.setForeground(Color.white);
		ordersUpdateButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
 
				if(OrdersModel.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "No data found.");
					return;
				}
 
				try {
 
					String name = ordersProductNameTextfield.getText();
					String company = ordersProductCompanytTextfield.getText();
					String quantity = ordersQuantityTextfield.getText();
					int idx = ordersTable.getSelectedRow();
 
					OrdersModel.setValueAt(name, idx, 1);
					OrdersModel.setValueAt(company, idx, 2);
					OrdersModel.setValueAt(quantity, idx, 3);
					
					ordersProductNameTextfield.setText("");
					ordersProductCompanytTextfield.setText("");
					ordersQuantityTextfield.setText("");
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		ordersPanel.add(ordersUpdateButton);
 
 
		ordersClearButton = new JButton("Clear");
		ordersClearButton.setFont(my_font);
		ordersClearButton.setFocusable(false);
		ordersClearButton.setBounds(560,680,220,40);
		ordersClearButton.setBackground(Color.gray);
		ordersClearButton.setForeground(Color.white);
		ordersClearButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				while(OrdersModel.getRowCount()!=0) {
					OrdersModel.removeRow(0);
				}
				
				ordersProductNameTextfield.setText("");
				ordersProductCompanytTextfield.setText("");
				ordersQuantityTextfield.setText("");
			}
		});
		ordersPanel.add(ordersClearButton);
		
		
		
		managementPane.add("Orders",ordersPanel);
		
		setVisible(true);
	}
}
