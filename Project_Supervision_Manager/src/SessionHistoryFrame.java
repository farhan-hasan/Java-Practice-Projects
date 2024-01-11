import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
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
	
	JLabel searchLabel, dateFromLabel, dateToLabel, attendanceLabel, performanceLabel, taskSubmissionLabel, teamWorkLabel;
	
	JTextField dateTextField, attendanceTextField, performanceTextField, taskSubmissionTextField, teamWorkTextField;
	
	JLabel projectNameLabel, teamNameLabel, courseNameLabel;
	
	JTextField projectNameTextField, teamNameTextField, courseNameTextField;
	
	JButton searchButton, addButton, updateButton, deleteButton, saveButton, printButton, doneButton;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {"Date","Student ID","Student Name", "Attendance", "Performance", "Team Work", "Task Submission"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	
	String parentProjectName, parentTeamName, parentCourseCode, parentCourseName, parentSemester, loginUserName;
	
	Statement st;
	Connection con;
	
	LocalDate localDate = LocalDate.now();
	
	
	public SessionHistoryFrame(String[] data) {
		
		parentProjectName = data[0];
		parentTeamName = data[1];
		parentCourseCode = data[2];
		parentCourseName = data[3];
		parentSemester = data[4];
		loginUserName = data[5];
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st =  con.createStatement();	
			
			String searchSql = "SELECT * FROM `session_history` WHERE "
					+ "lower(trim(project_name)) = lower(trim('"+parentProjectName+"')) and "
					+ "lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) and "
					+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
					+ "lower(trim(semester)) = lower(trim('"+parentSemester+"'))";
			
			ResultSet rs = st.executeQuery(searchSql);
			
			while(rs.next()) {
				String date = rs.getString(11);
				String studentId = rs.getString(1);
				String studentName = rs.getString(2);
				String attendance = rs.getString(3);
				String performance = rs.getString(4);
				String teamwork = rs.getString(5);
				String taskSubmission = rs.getString(6);
				Object newRow[] = {date, studentId, studentName, attendance, performance, teamwork, taskSubmission};
				studentTableModel.addRow(newRow);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
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
		dateFrom.setDate((Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		JTextFieldDateEditor editorFrom = (JTextFieldDateEditor) dateFrom.getDateEditor();
		editorFrom.setEditable(false);
		add(dateFrom);
		
		dateToLabel = new JLabel("To");
		dateToLabel.setFont(labelFont);
		dateToLabel.setBounds(215,20,280,70);
		dateToLabel.setForeground(Color.black);
		add(dateToLabel);
		
		dateTo = new JDateChooser();
		dateTo.setBounds(215,70,180,25);
		dateTo.setDate((Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		JTextFieldDateEditor editorTo = (JTextFieldDateEditor) dateTo.getDateEditor();
		editorTo.setEditable(false);
		add(dateTo);
		
		
		searchButton = new JButton("Search");
		searchButton.setFont(buttonFont);
		searchButton.setBounds(410,70,180,25);
		searchButton.setForeground(Color.white);
		searchButton.setFocusable(false);
		searchButton.setBackground(darkColor);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fromDate = (Date) dateFrom.getDate();
				Date toDate = (Date) dateTo.getDate();
				String from = DateFormat.getDateInstance().format(fromDate);
				String to = DateFormat.getDateInstance().format(toDate);
				
				int len = studentTable.getRowCount();
				
				if (len > 0) {
				    for (int i = len - 1; i > -1; i--) {
				    	studentTableModel.removeRow(i);
				    }
				}
				
				try {
					
					String searchSql = "SELECT * FROM `session_history` WHERE "
							+ "lower(trim(project_name)) = lower(trim('"+parentProjectName+"')) and "
							+ "lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+parentSemester+"')) and "
							+ "(lower(trim(date)) between lower(trim('"+from+"')) and lower(trim('"+to+"')))";
					ResultSet rs = st.executeQuery(searchSql);
					
					while(rs.next()) {
						String date = rs.getString(11);
						String studentId = rs.getString(1);
						String studentName = rs.getString(2);
						String attendance = rs.getString(3);
						String performance = rs.getString(4);
						String teamwork = rs.getString(5);
						String taskSubmission = rs.getString(6);
						Object newRow[] = {date, studentId, studentName, attendance, performance, teamwork, taskSubmission};
						studentTableModel.addRow(newRow);
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		add(searchButton);
		
		projectNameLabel = new JLabel("Project Name");
		projectNameLabel.setFont(labelFont);
		projectNameLabel.setBounds(605,20,280,70);
		projectNameLabel.setForeground(Color.black);
		add(projectNameLabel);
		
		projectNameTextField = new JTextField();
		projectNameTextField.setFont(textFieldFont);
		projectNameTextField.setBounds(605,70,180,25);
		projectNameTextField.setBackground(Color.white);
		projectNameTextField.setText(parentTeamName);
		projectNameTextField.setEditable(false);
		add(projectNameTextField);
		
		teamNameLabel = new JLabel("Team Name");
		teamNameLabel.setFont(labelFont);
		teamNameLabel.setBounds(800,20,280,70);
		teamNameLabel.setForeground(Color.black);
		add(teamNameLabel);
		
		teamNameTextField = new JTextField();
		teamNameTextField.setFont(textFieldFont);
		teamNameTextField.setBounds(800,70,180,25);
		teamNameTextField.setBackground(Color.white);
		teamNameTextField.setText(parentCourseName);
		teamNameTextField.setEditable(false);
		add(teamNameTextField);
		
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
				
				
				String attendance = "NULL";
				String performance = "NULL";
				String teamWork = "NULL";
				String taskSubmission = "NULL";
				
				Object attendanceObj = studentTableModel.getValueAt(idx, 3);
				Object performanceObj = studentTableModel.getValueAt(idx, 4);
				Object teamworkObj = studentTableModel.getValueAt(idx, 5);
				Object taskSubmissionObj = studentTableModel.getValueAt(idx, 6);
				
				if(attendanceObj!=null)attendance = attendanceObj.toString();
				if(performanceObj!=null)performance = performanceObj.toString();
				if(teamworkObj!=null)teamWork = teamworkObj.toString();
				if(taskSubmissionObj!=null)taskSubmission = taskSubmissionObj.toString();
				
				
				attendanceTextField.setText(attendance);
				performanceTextField.setText(performance);
				teamWorkTextField.setText(teamWork);
				taskSubmissionTextField.setText(taskSubmission);
				
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
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String attendance = attendanceTextField.getText();
				String performance = performanceTextField.getText();
				String teamWork = teamWorkTextField.getText();
				String taskSubmission = taskSubmissionTextField.getText();
				int idx = studentTable.getSelectedRow();
				
				studentTableModel.setValueAt(attendance, idx, 3);
				studentTableModel.setValueAt(performance, idx, 4);
				studentTableModel.setValueAt(teamWork, idx, 5);
				studentTableModel.setValueAt(taskSubmission, idx, 6);
				
			}
		});
		add(updateButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setBounds(215,420,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = studentTable.getSelectedRow();
				String sessionDate = studentTableModel.getValueAt(idx, 0).toString();
				
				int len = studentTable.getRowCount();
				try {
				for(int i=0;i<len;i++) {
					String studentId = "NULL";
					String studentName = "NULL";
					String attendance = "NULL";
					String performance = "NULL";
					String teamwork = "NULL";
					String taskSubmission = "NULL";
					
					Object studentIdObj = studentTableModel.getValueAt(i, 1);
					Object studentNameObj = studentTableModel.getValueAt(i, 2);
					Object attendanceObj = studentTableModel.getValueAt(i, 3);
					Object performanceObj = studentTableModel.getValueAt(i, 4);
					Object teamworkObj = studentTableModel.getValueAt(i, 5);
					Object taskSubmissionObj = studentTableModel.getValueAt(i, 6);
					
					if(studentIdObj!=null)studentId = studentIdObj.toString();
					if(studentNameObj!=null)studentName = studentNameObj.toString();
					if(attendanceObj!=null)attendance = attendanceObj.toString();
					if(performanceObj!=null)performance = performanceObj.toString();
					if(teamworkObj!=null)teamwork = teamworkObj.toString();
					if(taskSubmissionObj!=null)taskSubmission = taskSubmissionObj.toString();
					
					
					System.out.println("Check Update");
					String UpdateSql = "UPDATE `session_history` SET "
							+ "attendance = '"+attendance+"',"
							+ "performance = '"+performance+"',"
							+ "team_work = '"+teamwork+"',"
							+ "task_submission = '"+taskSubmission+"' WHERE "
							+ "lower(trim(student_id)) = lower(trim('"+studentId+"')) and "
							+ "lower(trim(student_name)) = lower(trim('"+studentName+"')) and "
							+ "lower(trim(project_name)) = lower(trim('"+parentProjectName+"')) and "
							+ "lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+parentSemester+"')) and "
							+ "lower(trim(date)) = lower(trim('"+sessionDate+"'))";
					st.executeUpdate(UpdateSql);
					
					
				}
				
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
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
		printButton.setBounds(800,380,180,25);
		printButton.setForeground(Color.white);
		printButton.setFocusable(false);
		printButton.setBackground(darkColor);
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(studentTableModel.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "No data found.");
					return;
				}
				Date fromDate = (Date) dateFrom.getDate();
				Date toDate = (Date) dateTo.getDate();
				String from = DateFormat.getDateInstance().format(fromDate);
				String to = DateFormat.getDateInstance().format(toDate);
				MessageFormat header = new MessageFormat("Session : " + from + " - " + to);
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					studentTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		add(printButton);
		
		setVisible(true);
	}
	
}
