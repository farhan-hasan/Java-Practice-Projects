package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

public class MainFrame extends JFrame{
	
	JTabbedPane managementPane;
	Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
	
	// Receipt tab variables
	JPanel recieptPanel, stockPanel, historyPanel, ordersPanel;
	JPanel recepitTablePanel, recepitCustomerDataPanel, receiptPayPanel;
	JLabel discountLabel, totalAmountLabel, cashPaidLabel, returnLabel, counterNumberLabel, discountTotalAmountLabel;
	JLabel customerNameLabel, customerAddressLabel, customerMobileNumberLabel, customerDetailsLabel;
	JLabel itemNameLabel, quantityLabel, companyNameLabel;
	JButton printButton, clearButton, addButton, deleteButton;
	JTextField discountTextField, totalAmountTextField, cashPaidTextField, returnTextField, discountTotalAmountTextField;
	JTextField customerNameTextField, customerAddressTextField, customerPhoneNumberTextField;
	JTextField counterNumberTextField, quantityTextField, companyNameTextField, itemNameTextField;
	JTable receiptTable;
	JScrollPane receiptTableScrollPane;
	Object receiptData[][] = {};
	String receiptColums[] = {"Serial","Name","Rate","Quantity","Amount","Available"};
	DefaultTableModel model = new DefaultTableModel(receiptData,receiptColums);
	
	// Stock tab variables
	JLabel stockCompanyNameLabel,stockDrugListLabel,stockSearchMedicineLNameabel,stockSearchLabel,stockAddMedicineLabel;
	JTextField stockSearchCompanyNameTextField,stockSearchMedicineTextField;
	JTable stockTable;
	Object stockData[][] = {};
	String stockColumnNames[] = {"Name","Rate","Quantity","Type"};
	DefaultTableModel stockModel = new DefaultTableModel(stockData,stockColumnNames);
	JLabel stockAddProductNameLabel,stockAddProductRateLabel,stockAddQuantityLabel,stockAddTypeLabel;
	JPanel stockInformationPanel;
	JButton stockAddButton,stockSeacrchButton,stockPrintButton;
	JTextField stockAddProductNameTextField,stockAddProductRateTextField,stockAddQuantityTextField,stockAddTypeTextField;
	
	// History tab variables
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
	JButton ordersAddButton,ordersDeleteButton,ordersClearButton,ordersSaveButton,ordersPrintButton;
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
		discountTextField.setBounds(150, 90, 200, 30);
		receiptPayPanel.add(discountTextField);
		
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
		cashPaidTextField.setBounds(560, 20, 200, 30);
		receiptPayPanel.add(cashPaidTextField);
		
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
		stockPanel.add(stockScrollPane);
 
 
 
		stockPrintButton = new JButton("Print");
		stockPrintButton.setFont(my_font);
		stockPrintButton.setFocusable(false);
		stockPrintButton.setBounds(270,630,100,40);
		stockPrintButton.setBackground(Color.darkGray);
		stockPrintButton.setForeground(Color.white);
		stockPanel.add(stockPrintButton);
 
 
		stockInformationPanel = new JPanel();
		stockInformationPanel.setBounds(773,50,305,681);
		stockInformationPanel.setBackground(Color.white);
		stockInformationPanel.setLayout(null);
		stockPanel.add(stockInformationPanel);
 
		// Search area
		stockSearchLabel = new JLabel("Search Here");
		stockSearchLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		stockSearchLabel.setBounds(10,10, 170, 30);
		stockSearchLabel.setForeground(Color.black);
		stockInformationPanel.add(stockSearchLabel);
 
 
		stockCompanyNameLabel = new JLabel("Company Name ");
		stockCompanyNameLabel.setFont(my_font);
		stockCompanyNameLabel.setBounds(10,50, 170, 30);
		stockCompanyNameLabel.setForeground(Color.black);
		stockInformationPanel.add(stockCompanyNameLabel);
 
 
		stockSearchCompanyNameTextField = new JTextField();
		stockSearchCompanyNameTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockSearchCompanyNameTextField.setBounds(10,90,270,30);
		stockSearchCompanyNameTextField.setBackground(Color.white);
		stockInformationPanel.add(stockSearchCompanyNameTextField);
 
 
		stockSearchMedicineLNameabel = new JLabel("Medicine Name");
		stockSearchMedicineLNameabel.setFont(my_font);
		stockSearchMedicineLNameabel.setBounds(10,120, 170, 30);
		stockSearchMedicineLNameabel.setForeground(Color.black);
		stockInformationPanel.add(stockSearchMedicineLNameabel);
 
 
		stockSearchMedicineTextField = new JTextField();
		stockSearchMedicineTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockSearchMedicineTextField.setBounds(10,160,270,30);
		stockSearchMedicineTextField.setBackground(Color.white);
		stockInformationPanel.add(stockSearchMedicineTextField);
 
 
		stockSeacrchButton = new JButton("Search");
		stockSeacrchButton.setFont(my_font);
		stockSeacrchButton.setFocusable(false);
		stockSeacrchButton.setBounds(90,200,100,40);
		stockSeacrchButton.setBackground(Color.darkGray);
		stockSeacrchButton.setForeground(Color.white);
		stockInformationPanel.add(stockSeacrchButton);
 
 
		// Add area
		stockAddMedicineLabel = new JLabel("Add Medicine");
		stockAddMedicineLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		stockAddMedicineLabel.setBounds(10,260, 200, 30);
		stockAddMedicineLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddMedicineLabel);
 
 
 
		stockAddProductNameLabel = new JLabel("Name");
		stockAddProductNameLabel.setFont(my_font);
		stockAddProductNameLabel.setBounds(10,300, 170, 30);
		stockAddProductNameLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddProductNameLabel);
 
 
 
		stockAddProductNameTextField = new JTextField();
		stockAddProductNameTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockAddProductNameTextField.setBounds(10,340,270,30);
		stockAddProductNameTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddProductNameTextField);
 
 
		stockAddProductRateLabel = new JLabel("Rate");
		stockAddProductRateLabel.setFont(my_font);
		stockAddProductRateLabel.setBounds(10,380, 170, 30);
		stockAddProductRateLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddProductRateLabel);
 
		stockAddProductRateTextField = new JTextField();
		stockAddProductRateTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockAddProductRateTextField.setBounds(10,420,270,30);
		stockAddProductRateTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddProductRateTextField);
 
		stockAddQuantityLabel = new JLabel("Quantity");
		stockAddQuantityLabel.setFont(my_font);
		stockAddQuantityLabel.setBounds(10,460, 170, 30);
		stockAddQuantityLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddQuantityLabel);
 
 
		stockAddQuantityTextField = new JTextField();
		stockAddQuantityTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockAddQuantityTextField.setBounds(10,500,270,30);
		stockAddQuantityTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddQuantityTextField);
 
 
		stockAddTypeLabel = new JLabel("Type");
		stockAddTypeLabel.setFont(my_font);
		stockAddTypeLabel.setBounds(10,540, 170, 30);
		stockAddTypeLabel.setForeground(Color.black);
		stockInformationPanel.add(stockAddTypeLabel);
 
 
		stockAddTypeTextField = new JTextField();
		stockAddTypeTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		stockAddTypeTextField.setBounds(10,580,270,30);
		stockAddTypeTextField.setBackground(Color.white);
		stockInformationPanel.add(stockAddTypeTextField);
 
 
		stockAddButton = new JButton("ADD");
		stockAddButton.setFont(my_font);
		stockAddButton.setFocusable(false);
		stockAddButton.setBounds(90,630,100,40);
		stockAddButton.setBackground(Color.darkGray);
		stockAddButton.setForeground(Color.white);
		stockInformationPanel.add(stockAddButton);
		
		
				
		managementPane.add("Stock",stockPanel);
		
		// History Tab
		historyPanel = new JPanel();
		historyPanel.setBackground(Color.white);
		historyPanel.setLayout(null);
		
//		JLabel historyTimePeriodLabel, historyCounterNumberLabel, historyTotalItemsLabel, historyTotalQuantityLabel, historyTotalDiscountLabel;
//		JLabel historyTotalMRPLabel, historyTotalBuyingPriceLabel, historyTotalMarginLabel;
//		JTextField historyTimePeriodTextField, historyCounterNumberTextField, historyTotalItemsTextField, historyTotalQuantityTextField;
//		JTextField historyTotalDiscountTextField, historyTotalMRPTextField, historyTotalBuyingPriceTextField, historyTotalMarginTextField;
//		JDateChooser historyFrom, historyTo;
//		JLabel historyFromLabel, historyToLabel;
//		JComboBox<Integer> historycounterNumberComboBox;
//		JButton historySearch;
		
		
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
		
		
		managementPane.add("History",historyPanel);
		
		// Orders Tab
		ordersPanel = new JPanel();
		ordersPanel.setBackground(Color.white);
		ordersPanel.setLayout(null);
		
		ordersTable = new JTable(OrdersModel);
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
		ordersPanel.add(ordersAddButton);
 
 
		ordersDeleteButton = new JButton("Delete");
		ordersDeleteButton.setFont(my_font);
		ordersDeleteButton.setFocusable(false);
		ordersDeleteButton.setBounds(290,610,220,40);
		ordersDeleteButton.setBackground(Color.gray);
		ordersDeleteButton.setForeground(Color.white);
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
		ordersPanel.add(ordersPrintButton);
		
		ordersClearButton = new JButton("Clear");
		ordersClearButton.setFont(my_font);
		ordersClearButton.setFocusable(false);
		ordersClearButton.setBounds(420,680,220,40);
		ordersClearButton.setBackground(Color.gray);
		ordersClearButton.setForeground(Color.white);
		ordersPanel.add(ordersClearButton);
		
		
		
		managementPane.add("Orders",ordersPanel);
		
		setVisible(true);
	}
}
