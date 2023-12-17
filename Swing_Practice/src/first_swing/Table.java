package first_swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Table extends JFrame {
	JTable table;
	
	JPanel head,foot;
	
	JTextField slT,nmT,idT;
	
	JButton add,update,del;
	
	JLabel serial,name,id;
	
	Object data[][] = {
			{1,"Abir",1122},
			{2,"Hasan",2233},
			{3,"Foyez",3333}
	};
	// // Table
	String columnNames[] = {"Serial","Names","ID"};
	
	DefaultTableModel model = new DefaultTableModel(data,columnNames);
	
	public Table() {
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
		
		
		setVisible(true);
	}

	
}
