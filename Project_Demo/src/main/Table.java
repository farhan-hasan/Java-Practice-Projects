 package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.protocol.Message;

public class Table extends JFrame {
	JTable table;
	
	JPanel head,foot;
	
	JTextField slT,nmT,idT,srchT;
	
	JButton add,update,del,search,print;
	
	JLabel serial,name,id,srch;
	
	Object data[][] = {};
	// // Table
	String columnNames[] = {"Serial","Names","ID"};
	
	DefaultTableModel model = new DefaultTableModel(data,columnNames);
	
	
	
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	String serialFromTF,nameFromTF,idFromTF;
	public Table() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc","root","");
			st = con.createStatement();
			
			rs = st.executeQuery("SELECT * FROM T");
			while(rs.next()) {
				int serial = rs.getInt(1);
				String s = Integer.toString(serial);
				String n = rs.getString(2).toString();
				int id = rs.getInt(3);
				String i = Integer.toString(id);
				Object newRow[] = {s,n,i};
				model.addRow(newRow);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// // sum query
//		try {
//			PreparedStatement statement =  con.prepareStatement("select sum(serial) from t ");
//		    ResultSet result = statement.executeQuery();
//		    
//		    while(result.next()) {
//		    	System.out.println(result.getString(1));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(null);
		
		Font my_font = new Font("Times New Roman", Font.PLAIN, 20);
		
		
		head = new JPanel();
		head.setBounds(0,0,485,200);
		head.setBackground(Color.darkGray);
		head.setLayout(null);
		add(head);
		
		foot = new JPanel();
		foot.setBounds(0,200,485,260);
		foot.setBackground(Color.gray);
		foot.setLayout(null);
		add(foot);
		
		
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10,10,465,280);
		//sp.setPreferredSize(new java.awt.Dimension(400, 200));
		head.add(sp);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = table.getSelectedRow();
				
				String s = model.getValueAt(idx, 0).toString();
				String n = model.getValueAt(idx, 1).toString();
				String i = model.getValueAt(idx, 2).toString();
				
				slT.setText(s);
				nmT.setText(n);
				idT.setText(i);
				
				serialFromTF = s;
				nameFromTF = n;
				idFromTF = i;
				
			}
		});
		
		
		
		slT = new JTextField();
		slT.setBounds(150,33,200,20);
		foot.add(slT);
		
		serial = new JLabel("Serial:");
		serial.setBounds(50,33,200,20);
		serial.setFont(my_font);
		foot.add(serial);
		
		
		nmT = new JTextField();
		nmT.setBounds(150,83,200,20);
		foot.add(nmT);
		
		name = new JLabel("Name:");
		name.setBounds(50,83,200,20);
		name.setFont(my_font);
		foot.add(name);
		
		idT = new JTextField();
		idT.setBounds(150,133,200,20);
		foot.add(idT);
		
		id = new JLabel("ID:");
		id.setBounds(50,133,200,20);
		id.setFont(my_font);
		foot.add(id);
		
		// Add Button
		add = new JButton("Add");
		add.setFont(my_font);
		add.setBounds(80,183,100,20);
		add.setBackground(Color.darkGray);
		add.setForeground(Color.WHITE);
		add.setFocusable(false);
		foot.add(add);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = slT.getText();
				String n = nmT.getText();
				String i = idT.getText();
				Object newRow[] = {s,n,i};
				model.addRow(newRow);
				
				try {					//INSERT INTO `t`(`serial`, `name`, `id`) VALUES ('s','n','i')
					int up = st.executeUpdate("INSERT INTO T(serial, name, id) VALUES ('" + s + "','" + n + "','" + i + "')");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		update = new JButton("Update");
		update.setFont(my_font);
		update.setBounds(190,183,100,20);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = slT.getText();
				String n = nmT.getText();
				String i = idT.getText();
				int idx = table.getSelectedRow();
				
				try {
					int up = st.executeUpdate ("UPDATE T SET serial='" + s + "', name ='" + n + "', id ='" + i + "' WHERE serial = '" + serialFromTF + "'");
					if(up==1) {
						JOptionPane.showMessageDialog(null, "Row updated!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				model.setValueAt(s, idx, 0);
				model.setValueAt(n, idx, 1);
				model.setValueAt(i, idx, 2);
			}
		});
		update.setBackground(Color.darkGray);
		update.setForeground(Color.WHITE);
		update.setFocusable(false);
		foot.add(update);
		
		del = new JButton("Delete");
		del.setFont(my_font);
		del.setBounds(300,183,100,20);
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int idx = table.getSelectedRow();
					
					String s = model.getValueAt(idx, 0).toString();
					String n = model.getValueAt(idx, 1).toString();
					String i = model.getValueAt(idx, 2).toString();
					
					int up = st.executeUpdate("DELETE FROM T WHERE serial='" + s + "'");
					if(up==1) {
						JOptionPane.showMessageDialog(null, "Row deleted from DB!");
					}
					
					model.removeRow(table.getSelectedRow());
					
					
				} catch(Exception e1) {
					if(model.getRowCount()==0)
						JOptionPane.showMessageDialog(null, "No data found!");
					else 
						JOptionPane.showMessageDialog(null, "Please select a row!");
				}
			}
		});
		del.setBackground(Color.darkGray);
		del.setForeground(Color.WHITE);
		del.setFocusable(false);
		foot.add(del);
		
		search = new JButton("Search");
		search.setFont(my_font);
		search.setBounds(50,223,100,20);
		search.setBackground(Color.darkGray);
		search.setForeground(Color.WHITE);
		search.setFocusable(false);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(srchT.getText()));
			}
		});
		foot.add(search);
		
		
		srchT = new JTextField();
		srchT.setBounds(150,223,200,20);
		foot.add(srchT);
		
		print = new JButton("Print");
		print.setFont(my_font);
		print.setBounds(350,223,100,20);
		print.setBackground(Color.darkGray);
		print.setForeground(Color.WHITE);
		print.setFocusable(false);
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Student list");
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Nothing to print");
				}
			}
		});
		foot.add(print);
		
		
		setVisible(true);
		
		// Creating a table
//		try {
//			int up = st.executeUpdate("CREATE TABLE newTable(serial INT,name varchar(20),id INT)");
//			if(up==1) {
//				JOptionPane.showMessageDialog(null, "Table created");
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			//e1.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Table already exits!");
//		}
	}

	
}
