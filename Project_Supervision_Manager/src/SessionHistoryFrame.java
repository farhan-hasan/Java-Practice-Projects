import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class SessionHistoryFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JDateChooser dateFrom, dateTo;
	
	JLabel dateFromLabel, dateToLabel, attendanceLabel, performanceLabel, taskSubmissionLabel, teamWorkLabel;
	
	JTextField dateTextField, attendanceTextField, performanceTextField, taskSubmissionTextField, teamWorkTextField;
	
	JButton addButton, updateButton, deleteButton, saveButton, printButton, doneButton;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {"Date","Student ID","Student Name", "Attendance", "Performance", "Team Work", "Task Submission"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	public SessionHistoryFrame() {
		getContentPane().setBackground(lightColor);
		setSize(1024,520);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "Are you sure you, all unsaved data will be lost", "Close Window?", 
		            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
		            dispose();
		        }
		    }
		});
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("New Session");
		
		
		dateFromLabel = new JLabel("From");
		dateFromLabel.setFont(labelFont);
		dateFromLabel.setBounds(20,20,280,70);
		dateFromLabel.setForeground(Color.black);
		add(dateFromLabel);
		
		dateFrom = new JDateChooser();
		dateFrom.setBounds(20,70,180,25);
		JTextFieldDateEditor editorFrom = (JTextFieldDateEditor) dateFrom.getDateEditor();
		editorFrom.setEditable(false);
		add(dateFrom);
		
		// printing date as string
//		LocalDate localDate = LocalDate.now();
//		Date DOB = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		date.setDate(DOB);
//		
//		DOB = (Date) date.getDate();
//		String dateStr = DateFormat.getDateInstance().format(DOB);
//		System.out.println(dateStr);
		
		dateToLabel = new JLabel("To");
		dateToLabel.setFont(labelFont);
		dateToLabel.setBounds(215,20,280,70);
		dateToLabel.setForeground(Color.black);
		add(dateToLabel);
		
		dateTo = new JDateChooser();
		dateTo.setBounds(215,70,180,25);
		JTextFieldDateEditor editorTo = (JTextFieldDateEditor) dateTo.getDateEditor();
		editorTo.setEditable(false);
		add(dateTo);
		
		// printing date as string
//		LocalDate localDate = LocalDate.now();
//		Date DOB = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		date.setDate(DOB);
//		
//		DOB = (Date) date.getDate();
//		String dateStr = DateFormat.getDateInstance().format(DOB);
//		System.out.println(dateStr);
		
		studentTable = new JTable(studentTableModel);
		studentTable.setOpaque(true);
		studentTable.setFillsViewportHeight(true);
		studentTable.setBackground(Color.white);
		studentTableScrollPane = new JScrollPane(studentTable);
		studentTableScrollPane.setBounds(20, 110, 962, 220);
		studentTable.getTableHeader().setBackground(darkColor);
		studentTable.getTableHeader().setForeground(Color.white);
		studentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		studentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = studentTable.getSelectedRow();
				
				
			}
		});
		add(studentTableScrollPane);
		
		attendanceLabel = new JLabel("Attendance");
		attendanceLabel.setFont(labelFont);
		attendanceLabel.setBounds(20,330,280,70);
		attendanceLabel.setForeground(Color.black);
		add(attendanceLabel);
		
		attendanceTextField = new JTextField();
		attendanceTextField.setFont(textFieldFont);
		attendanceTextField.setBounds(20,380,180,25);
		attendanceTextField.setBackground(Color.white);
		add(attendanceTextField);
		
		performanceLabel = new JLabel("Performance");
		performanceLabel.setFont(labelFont);
		performanceLabel.setBounds(215,330,280,70);
		performanceLabel.setForeground(Color.black);
		add(performanceLabel);
		
		performanceTextField = new JTextField();
		performanceTextField.setFont(textFieldFont);
		performanceTextField.setBounds(215,380,180,25);
		performanceTextField.setBackground(Color.white);
		add(performanceTextField);
		
		teamWorkLabel = new JLabel("Team Work");
		teamWorkLabel.setFont(labelFont);
		teamWorkLabel.setBounds(410,330,280,70);
		teamWorkLabel.setForeground(Color.black);
		add(teamWorkLabel);
		
		teamWorkTextField = new JTextField();
		teamWorkTextField.setFont(textFieldFont);
		teamWorkTextField.setBounds(410,380,180,25);
		teamWorkTextField.setBackground(Color.white);
		add(teamWorkTextField);
		
		taskSubmissionLabel = new JLabel("Task Submission");
		taskSubmissionLabel.setFont(labelFont);
		taskSubmissionLabel.setBounds(605,330,280,70);
		taskSubmissionLabel.setForeground(Color.black);
		add(taskSubmissionLabel);
		
		taskSubmissionTextField = new JTextField();
		taskSubmissionTextField.setFont(textFieldFont);
		taskSubmissionTextField.setBounds(605,380,180,25);
		taskSubmissionTextField.setBackground(Color.white);
		add(taskSubmissionTextField);
		
//		addButton = new JButton("Add");
//		addButton.setFont(buttonFont);
//		addButton.setBounds(20,420,180,25);
//		addButton.setForeground(Color.white);
//		addButton.setFocusable(false);
//		addButton.setBackground(darkColor);
//		add(addButton);
		
		updateButton = new JButton("Update");
		updateButton.setFont(buttonFont);
		updateButton.setBounds(20,420,180,25);
		updateButton.setForeground(Color.white);
		updateButton.setFocusable(false);
		updateButton.setBackground(darkColor);
		add(updateButton);
		
//		deleteButton = new JButton("Delete");
//		deleteButton.setFont(buttonFont);
//		deleteButton.setBounds(410,420,180,25);
//		deleteButton.setForeground(Color.white);
//		deleteButton.setFocusable(false);
//		deleteButton.setBackground(darkColor);
//		add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setBounds(215,420,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		add(saveButton);
		
		doneButton = new JButton("Done");
		doneButton.setFont(buttonFont);
		doneButton.setBounds(800,420,180,25);
		doneButton.setForeground(Color.white);
		doneButton.setFocusable(false);
		doneButton.setBackground(darkColor);
		doneButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you, all unsaved data will be lost", "Close Window?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				} else {
				    return;
				}
				
			}
		});
		add(doneButton);
		
		printButton = new JButton("Print");
		printButton.setFont(buttonFont);
		printButton.setBounds(800,70,180,25);
		printButton.setForeground(Color.white);
		printButton.setFocusable(false);
		printButton.setBackground(darkColor);
		
		add(printButton);
		
		setVisible(true);
	}
	
}
