import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.time.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class NewSessionFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JTextField date;
	
	JLabel dateLabel, attendanceLabel, performanceLabel, taskSubmissionLabel, teamWorkLabel;
	
	JLabel projectNameLabel, teamNameLabel, courseCodeLabel, courseNameLabel;
	
	JTextField projectNameTextField, teamNameTextField, courseCodeTextField, courseNameTextField;
	
	JTextField dateTextField, attendanceTextField, performanceTextField, taskSubmissionTextField, teamWorkTextField;
	
	JButton addButton, updateButton, deleteButton, saveButton, printButton, doneButton;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {"Student ID","Student Name", "Attendance", "Performance", "Team Work", "Task Submission"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	String parentProjectName, parentTeamName, parentCourseCode, parentCourseName, parentSemester, loginUserName;
	
	Statement st;
	Connection con;
	
	String dateStr;
	
	public NewSessionFrame(String[] data) {
		
		
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
			
			String sql = "SELECT * FROM `team_members` WHERE "
					+ "lower(trim(team_name)) = lower(trim('"+parentTeamName+"')) "
					+ "and lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) "
					+ "and lower(trim(username)) = lower(trim('"+loginUserName+"')) "
					+ "and lower(trim(semester)) = lower(trim('"+parentSemester+"'))";
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String studentId = rs.getString(3);
				String studentName = rs.getString(4);
				System.out.println(studentId);
				System.out.println(studentName);
				Object newRow[] = {studentId, studentName};
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
		
		
		dateLabel = new JLabel("Date");
		dateLabel.setFont(labelFont);
		dateLabel.setBounds(20,20,280,70);
		dateLabel.setForeground(Color.black);
		add(dateLabel);
		
		LocalDate localDate = LocalDate.now();
		Date DOB = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		date = new JTextField();
		date.setBounds(20,70,180,25);
		String dateString = DateFormat.getDateInstance().format(DOB);
		date.setText(dateString);
		date.setEditable(false);
		add(date);
		
		projectNameLabel = new JLabel("Project Name");
		projectNameLabel.setFont(labelFont);
		projectNameLabel.setBounds(215,20,280,70);
		projectNameLabel.setForeground(Color.black);
		add(projectNameLabel);
		
		projectNameTextField = new JTextField();
		projectNameTextField.setFont(textFieldFont);
		projectNameTextField.setBounds(215,70,180,25);
		projectNameTextField.setBackground(Color.white);
		projectNameTextField.setText(parentProjectName);
		projectNameTextField.setEditable(false);
		add(projectNameTextField);
		
		teamNameLabel = new JLabel("Team Name");
		teamNameLabel.setFont(labelFont);
		teamNameLabel.setBounds(410,20,280,70);
		teamNameLabel.setForeground(Color.black);
		add(teamNameLabel);
		
		teamNameTextField = new JTextField();
		teamNameTextField.setFont(textFieldFont);
		teamNameTextField.setBounds(410,70,180,25);
		teamNameTextField.setBackground(Color.white);
		teamNameTextField.setText(parentTeamName);
		teamNameTextField.setEditable(false);
		add(teamNameTextField);
		
		courseCodeLabel = new JLabel("Course Code");
		courseCodeLabel.setFont(labelFont);
		courseCodeLabel.setBounds(605,20,280,70);
		courseCodeLabel.setForeground(Color.black);
		add(courseCodeLabel);
		
		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(textFieldFont);
		courseCodeTextField.setBounds(605,70,180,25);
		courseCodeTextField.setBackground(Color.white);
		courseCodeTextField.setText(parentCourseCode);
		courseCodeTextField.setEditable(false);
		add(courseCodeTextField);
		
		courseNameLabel = new JLabel("Course Name");
		courseNameLabel.setFont(labelFont);
		courseNameLabel.setBounds(800,20,280,70);
		courseNameLabel.setForeground(Color.black);
		add(courseNameLabel);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(textFieldFont);
		courseNameTextField.setBounds(800,70,180,25);
		courseNameTextField.setBackground(Color.white);
		courseNameTextField.setText(parentCourseName);
		courseNameTextField.setEditable(false);
		add(courseNameTextField);
		
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
				
				Object attendanceObj = studentTableModel.getValueAt(idx, 2);
				Object performanceObj = studentTableModel.getValueAt(idx, 3);
				Object teamworkObj = studentTableModel.getValueAt(idx, 4);
				Object taskSubmissionObj = studentTableModel.getValueAt(idx, 5);
				
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
				
				studentTableModel.setValueAt(attendance, idx, 2);
				studentTableModel.setValueAt(performance, idx, 3);
				studentTableModel.setValueAt(teamWork, idx, 4);
				studentTableModel.setValueAt(taskSubmission, idx, 5);
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
				
				String sessionDate = date.getText();
				
				int len = studentTable.getRowCount();
				try {
				for(int i=0;i<len;i++) {
					String studentId = "NULL";
					String studentName = "NULL";
					String attendance = "NULL";
					String performance = "NULL";
					String teamwork = "NULL";
					String taskSubmission = "NULL";
					
					Object studentIdObj = studentTableModel.getValueAt(i, 0);
					Object studentNameObj = studentTableModel.getValueAt(i, 1);
					Object attendanceObj = studentTableModel.getValueAt(i, 2);
					Object performanceObj = studentTableModel.getValueAt(i, 3);
					Object teamworkObj = studentTableModel.getValueAt(i, 4);
					Object taskSubmissionObj = studentTableModel.getValueAt(i, 5);
					
					if(studentIdObj!=null)studentId = studentIdObj.toString();
					if(studentNameObj!=null)studentName = studentNameObj.toString();
					if(attendanceObj!=null)attendance = attendanceObj.toString();
					if(performanceObj!=null)performance = performanceObj.toString();
					if(teamworkObj!=null)teamwork = teamworkObj.toString();
					if(taskSubmissionObj!=null)taskSubmission = taskSubmissionObj.toString();
					
					
					
					String searchSql = "SELECT * FROM `session_history` WHERE "
							+ "lower(trim(student_id)) = lower(trim('"+studentId+"')) and "
							+ "lower(trim(student_name)) = lower(trim('"+studentName+"')) and "
							+ "lower(trim(project_name)) = lower(trim('"+parentProjectName+"')) and "
							+ "lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+parentSemester+"')) and "
							+ "lower(trim(date)) = lower(trim('"+sessionDate+"'))";
					ResultSet rs = st.executeQuery(searchSql);
					
					int searchCnt = 0;
					while(rs.next())searchCnt++;
					
					
					if(searchCnt==0) {
						System.out.println("Check Insert");
						String insertSql = "INSERT INTO `session_history`(`student_id`, `student_name`, `attendance`, `performance`, `team_work`, `task_submission`, `project_name`, `course_code`, `username`, `semester`, `date`) VALUES "
								+ "('"+studentId+"','"+studentName+"','"+attendance+"','"+performance+"','"+teamwork+"','"+taskSubmission+"','"+parentProjectName+"','"+parentCourseCode+"','"+loginUserName+"','"+parentSemester+"','"+sessionDate+"')";
						st.executeUpdate(insertSql);
					}
					else {
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
					
//					System.out.println(studentId);
//					System.out.println(studentName);
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
		
		add(printButton);
		
		setVisible(true);
	}
	
}
