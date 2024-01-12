import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AttendanceFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JLabel studentIdLabel, studentNameLabel, sectionLabel, batchLabel, departmentLabel, courseNameLabel, courseCodeLabel, semesterLabel;
	
	JTextField studentIdTextField, studentNameTextField, sectionTextField, batchTextField, departmentTextField;
	JTextField courseNameTextField, courseCodeTextField, semesterTextField;
	
	JButton saveButton, doneButton, printButton;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {
			"Student ID", "Student Name",
			"1","2","3","4","5","6","7","8","9","10",
			"11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30",
			"31","32","33","34","35","36","37","38","39","40",
			"41","42"
			};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	Statement st;
	Connection con;
	
	boolean teamProjectChanged = false, courseChanged = false;
	
	String parentCourseCode, parentCourseName, parentSection, parentBatch, parentDepartment, parentSemester, loginUserName;
	
	String currentStudentId, currentStudentName, currentProjectName, currentTeamName, currentCourseCode, currentCourseName, currentSemester;
	
	public AttendanceFrame(String[] data) {
		
		parentCourseCode = data[0];
		parentCourseName = data[1];
		parentSection = data[2];
		parentBatch = data[3];
		parentDepartment = data[4];
		parentSemester = data[5];
		loginUserName = data[6];
		
		
//		currentProjectName = parentProjectName;
//		currentTeamName = parentTeamName;
//		currentCourseCode = parentCourseCode;
//		currentCourseName = parentCourseName;
//		currentSemester = parentSemester;
		
		System.out.println(parentCourseCode);
		System.out.println(parentCourseName);
		System.out.println(parentSection);
		System.out.println(parentBatch);
		System.out.println(parentDepartment);
		System.out.println(parentSemester);
		System.out.println(loginUserName);
		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();	
			
			
			
			String loadStudentSql = "SELECT * FROM `student_list` WHERE "
					+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
					+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
					+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
					+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
					+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
					+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
			
			ResultSet rsStudent = st.executeQuery(loadStudentSql);
			
			while(rsStudent.next()) {
				String studentId = rsStudent.getString(1);
				String studentName = rsStudent.getString(2);
				Object newRow[] = {
						studentId, studentName, 
						"", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "",
						"", ""
						};
				studentTableModel.addRow(newRow);
			}
			
			int studentLen = studentTableModel.getRowCount();
			
			for(int i=0; i<studentLen; i++) {
				String studentId = studentTableModel.getValueAt(i, 0).toString();
				String studentName = studentTableModel.getValueAt(i, 1).toString();
				String loadMarkSql = "SELECT * FROM `attendance` WHERE "
						+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
						+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
						+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
						+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
						+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
						+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
						+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
						+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
				// // // Start from here!
				ResultSet rsMark = st.executeQuery(loadMarkSql);
				int cntStudent = 0;
				while(rsMark.next()) {
					String attendance = rsMark.getString(1);
					String assignment = rsMark.getString(2);
					String presentation = rsMark.getString(3);
					String viva = rsMark.getString(4);
					String tutorial = rsMark.getString(5);
					String mid = rsMark.getString(6);
					String finall = rsMark.getString(7);
					studentTableModel.setValueAt(attendance, i, 2);
					studentTableModel.setValueAt(assignment, i, 3);
					studentTableModel.setValueAt(presentation, i, 4);
					studentTableModel.setValueAt(viva, i, 5);
					studentTableModel.setValueAt(tutorial, i, 6);
					studentTableModel.setValueAt(mid, i, 7);
					studentTableModel.setValueAt(finall, i, 8);
					cntStudent++;
				}
				// // initializing with 0.
				if(cntStudent==0) {
					
						String insertSql = "INSERT INTO `marksheet`(`atendance`, `assignment`, `presentation`, `viva`, `tutorial`, `mid`, `final`, `student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) "
								+ "VALUES ("
									+ "0,"
									+ "0,"
									+ "0,"
									+ "0,"
									+ "0,"
									+ "0,"
									+ "0,"
								+ "'"+studentId+"',"
								+ "'"+studentName+"',"
								+ "'"+parentCourseCode+"',"
								+ "'"+parentCourseName+"',"
								+ "'"+parentSection+"',"
								+ "'"+parentBatch+"',"
								+ "'"+parentDepartment+"',"
								+ "'"+parentSemester+"',"
								+ "'"+loginUserName+"')";
						System.out.println("check insert");
						st.executeUpdate(insertSql);
				}
			}
			// // refresh list
			for(int i=0;i<studentLen;i++) {
				String studentId = studentTableModel.getValueAt(i, 0).toString();
				String studentName = studentTableModel.getValueAt(i, 1).toString();
				String loadMarkSql = "SELECT * FROM `marksheet` WHERE "
						+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
						+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
						+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
						+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
						+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
						+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
						+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
						+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
				
				ResultSet rsMark = st.executeQuery(loadMarkSql);
				while(rsMark.next()) {
					String attendance = rsMark.getString(1);
					String assignment = rsMark.getString(2);
					String presentation = rsMark.getString(3);
					String viva = rsMark.getString(4);
					String tutorial = rsMark.getString(5);
					String mid = rsMark.getString(6);
					String finall = rsMark.getString(7);
					studentTableModel.setValueAt(attendance, i, 2);
					studentTableModel.setValueAt(assignment, i, 3);
					studentTableModel.setValueAt(presentation, i, 4);
					studentTableModel.setValueAt(viva, i, 5);
					studentTableModel.setValueAt(tutorial, i, 6);
					studentTableModel.setValueAt(mid, i, 7);
					studentTableModel.setValueAt(finall, i, 8);
				}
			}
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		getContentPane().setBackground(lightColor);
		setSize(1624,520);
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
		setTitle("Attendance Sheet");
		
		courseCodeLabel = new JLabel("Course Code");
		courseCodeLabel.setFont(labelFont);
		courseCodeLabel.setBounds(20,20,280,70);
		courseCodeLabel.setForeground(Color.black);
		add(courseCodeLabel);
		
		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(textFieldFont);
		courseCodeTextField.setEditable(false);
		courseCodeTextField.setBounds(20,70,180,25);
		courseCodeTextField.setBackground(Color.white);
		add(courseCodeTextField);
		
		courseNameLabel = new JLabel("Course Name");
		courseNameLabel.setFont(labelFont);
		courseNameLabel.setBounds(215,20,280,70);
		courseNameLabel.setForeground(Color.black);
		add(courseNameLabel);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(textFieldFont);
		courseNameTextField.setEditable(false);
		courseNameTextField.setBounds(215,70,180,25);
		courseNameTextField.setBackground(Color.white);
		add(courseNameTextField);
		
		sectionLabel = new JLabel("Section");
		sectionLabel.setFont(labelFont);
		sectionLabel.setBounds(410,20,280,70);
		sectionLabel.setForeground(Color.black);
		add(sectionLabel);
		
		sectionTextField = new JTextField();
		sectionTextField.setFont(textFieldFont);
		sectionTextField.setEditable(false);
		sectionTextField.setBounds(410,70,180,25);
		sectionTextField.setBackground(Color.white);
		add(sectionTextField);
		
		batchLabel = new JLabel("Batch");
		batchLabel.setFont(labelFont);
		batchLabel.setBounds(605,20,280,70);
		batchLabel.setForeground(Color.black);
		add(batchLabel);
		
		batchTextField = new JTextField();
		batchTextField.setFont(textFieldFont);
		batchTextField.setEditable(false);
		batchTextField.setBounds(605,70,180,25);
		batchTextField.setBackground(Color.white);
		add(batchTextField);
		
		departmentLabel = new JLabel("Department");
		departmentLabel.setFont(labelFont);
		departmentLabel.setBounds(800,20,280,70);
		departmentLabel.setForeground(Color.black);
		add(departmentLabel);
		
		departmentTextField = new JTextField();
		departmentTextField.setFont(textFieldFont);
		departmentTextField.setEditable(false);
		departmentTextField.setBounds(800,70,180,25);
		departmentTextField.setBackground(Color.white);
		add(departmentTextField);
		
		semesterLabel = new JLabel("Semester");
		semesterLabel.setFont(labelFont);
		semesterLabel.setBounds(995,20,280,70);
		semesterLabel.setForeground(Color.black);
		add(semesterLabel);
		
		semesterTextField = new JTextField();
		semesterTextField.setFont(textFieldFont);
		semesterTextField.setEditable(false);
		semesterTextField.setBounds(995,70,180,25);
		semesterTextField.setBackground(Color.white);
		add(semesterTextField);
		
		
		
		studentTable = new JTable(studentTableModel);
		studentTable.setOpaque(true);
		studentTable.setFillsViewportHeight(true);
		studentTable.setBackground(Color.white);
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn column = null;
		for (int i = 0; i < 44; i++) {
		    column = studentTable.getColumnModel().getColumn(i);
		    if (i < 2) {
		        column.setPreferredWidth(110); //third column is bigger
		    } else {
		        column.setPreferredWidth(32);
		    }
		}
		studentTableScrollPane = new JScrollPane(studentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentTableScrollPane.setBounds(20, 120, 1567, 220); //962
		studentTable.getTableHeader().setBackground(darkColor);
		studentTable.getTableHeader().setForeground(Color.white);
		studentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		studentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				
				
				
			}
		});
		add(studentTableScrollPane);
		
		// // setting the parent data in textfields
		
		courseCodeTextField.setText(parentCourseCode);
		courseNameTextField.setText(parentCourseName);
		sectionTextField.setText(parentSection);
		batchTextField.setText(parentBatch);
		departmentTextField.setText(parentDepartment);
		semesterTextField.setText(parentSemester);
		
		
		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setBounds(20,380,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int studentLen = studentTable.getRowCount();
				boolean intCheck = false;
				
				for(int i=0; i<studentLen; i++) {
					String studentId = studentTableModel.getValueAt(i, 0).toString();
					String studentName = studentTableModel.getValueAt(i, 1).toString();
					String attendance = studentTableModel.getValueAt(i, 2).toString();
					String assignment = studentTableModel.getValueAt(i, 3).toString();
					String presentation = studentTableModel.getValueAt(i, 4).toString();
					String viva = studentTableModel.getValueAt(i, 5).toString();
					String tutorial = studentTableModel.getValueAt(i, 6).toString();
					String mid = studentTableModel.getValueAt(i, 7).toString();
					String finall = studentTableModel.getValueAt(i, 8).toString();
					
					System.out.println(studentName);
					
					
					
					String[] inputs = {attendance,assignment,presentation,viva,tutorial,mid,finall};
					
					for(int j=0;j<7;j++) {
						String x = inputs[j];
						int len = x.length();
						 for (char c : x.toCharArray()) {
					            if (!Character.isDigit(c)) {
					            	intCheck = true; // Found a character that is not a digit
					            	System.out.println("->" + c);
					            	break;
					            }
					        }
						if(intCheck==true)break;
					}
					
					if(intCheck==true) {
						JOptionPane.showMessageDialog(null, "Please enter integer values in the table");
						return;
					}
					
					int intAttendance = 0;
					int intAssignment = 0;
					int intPresentation = 0;
					int intViva = 0;
					int intTutorial = 0;
					int intMid = 0;
					int intFinall = 0;
					
					
					if(!attendance.equals(""))intAttendance = Integer.parseInt(attendance);
					if(!assignment.equals(""))intAssignment = Integer.parseInt(assignment);
					if(!presentation.equals(""))intPresentation = Integer.parseInt(presentation);
					if(!viva.equals(""))intViva = Integer.parseInt(viva);
					if(!tutorial.equals(""))intTutorial = Integer.parseInt(tutorial);
					if(!mid.equals(""))intMid = Integer.parseInt(mid);
					if(!finall.equals(""))intFinall = Integer.parseInt(finall);
					
					
					
					try {
					// // searching if the student exists
					String studentSearchSql = "SELECT * FROM `marksheet` WHERE "
							+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
							+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
							+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
							+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
							+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
					
					int cntSearch = 0;
					ResultSet rsSearch = st.executeQuery(studentSearchSql);
					while(rsSearch.next())cntSearch++;
					
					if(cntSearch==0) {
						String insertSql = "INSERT INTO `marksheet`(`atendance`, `assignment`, `presentation`, `viva`, `tutorial`, `mid`, `final`, `student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) "
								+ "VALUES ("
									+ ""+intAttendance+","
									+ ""+intAssignment+","
									+ ""+intPresentation+","
									+ ""+intViva+","
									+ ""+intTutorial+","
									+ ""+intMid+","
									+ ""+intFinall+","
								+ "'"+studentId+"',"
								+ "'"+studentName+"',"
								+ "'"+parentCourseCode+"',"
								+ "'"+parentCourseName+"',"
								+ "'"+parentSection+"',"
								+ "'"+parentBatch+"',"
								+ "'"+parentDepartment+"',"
								+ "'"+parentSemester+"',"
								+ "'"+loginUserName+"')";
						System.out.println("check insert");
						st.executeUpdate(insertSql);
						
					}
					else {
						String updateMarkSql = "UPDATE `marksheet` SET "
								+ "atendance= "+intAttendance+", "
								+ "assignment= "+intAssignment+", "
								+ "presentation= "+intPresentation+", "
								+ "viva= "+intViva+", "
								+ "tutorial= "+intTutorial+", "
								+ "mid= "+intMid+", "
								+ "final= "+intFinall+" WHERE "
								+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
								+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
								+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
								+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
								+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
								+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
						System.out.println("check update");
						
						st.executeUpdate(updateMarkSql);
					}
					
					
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		add(saveButton);
		
		printButton = new JButton("Print");
		printButton.setFont(buttonFont);
		printButton.setBounds(215,380,180,25);
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
				
				MessageFormat header = new MessageFormat
				("("+ parentCourseCode + "," + parentCourseName + "," 
				+ parentBatch + "(" + parentSection + ")" + "," + parentDepartment + " Department)");
				MessageFormat footer = new MessageFormat("Page{0, number, integer}");
				try {
					studentTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		add(printButton);
		
		doneButton = new JButton("Done");
		doneButton.setFont(buttonFont);
		doneButton.setBounds(410,380,180,25);
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
		
		
		
		setVisible(true);
	}
	
}
