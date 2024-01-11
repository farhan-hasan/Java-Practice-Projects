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

public class MarkSheet extends JFrame{
	
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
	String studentTableColumns[] = {"Student ID","Student Name","Attendance","Assignment","Presentation","Viva","Tutorial","Mid","Final"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	Statement st;
	Connection con;
	
	boolean teamProjectChanged = false, courseChanged = false;
	
	String parentCourseCode, parentCourseName, parentSection, parentBatch, parentDepartment, parentSemester, loginUserName;
	
	String currentStudentId, currentStudentName, currentProjectName, currentTeamName, currentCourseCode, currentCourseName, currentSemester;
	
	public MarkSheet(String[] data) {
		
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
		
//		System.out.println(parentCourseCode);
//		System.out.println(parentCourseName);
//		System.out.println(parentSection);
//		System.out.println(parentBatch);
//		System.out.println(parentDepartment);
//		System.out.println(parentSemester);
//		System.out.println(loginUserName);
		
		
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
				Object newRow[] = {studentId, studentName, "", "", "", "", "", "", ""};
				studentTableModel.addRow(newRow);
			}
			
			int studentLen = studentTableModel.getRowCount();
			
			for(int i=0; i<studentLen; i++) {
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
		setTitle("Marksheet");
		
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
		semesterLabel.setBounds(800,330,280,70);
		semesterLabel.setForeground(Color.black);
		add(semesterLabel);
		
		semesterTextField = new JTextField();
		semesterTextField.setFont(textFieldFont);
		semesterTextField.setEditable(false);
		semesterTextField.setBounds(800,380,180,25);
		semesterTextField.setBackground(Color.white);
		add(semesterTextField);
		
		
		
		studentTable = new JTable(studentTableModel){

		   @Override
		   public boolean isCellEditable(int row, int column) {
		       //Only the third column
		       return column == 2 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7 || column == 8;
		   }
		};
		studentTable.setOpaque(true);
		studentTable.setFillsViewportHeight(true);
		studentTable.setBackground(Color.white);
		studentTableScrollPane = new JScrollPane(studentTable);
		studentTableScrollPane.setBounds(20, 120, 962, 220);
		studentTable.getTableHeader().setBackground(darkColor);
		studentTable.getTableHeader().setForeground(Color.white);
		studentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		studentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				
				
				
			}
		});
		add(studentTableScrollPane);
		
//		studentIdLabel = new JLabel("Student ID");
//		studentIdLabel.setFont(labelFont);
//		studentIdLabel.setBounds(20,330,280,70);
//		studentIdLabel.setForeground(Color.black);
//		add(studentIdLabel);
//		
//		studentIdTextField = new JTextField();
//		studentIdTextField.setFont(textFieldFont);
//		studentIdTextField.setBounds(20,380,180,25);
//		studentIdTextField.setBackground(Color.white);
//		add(studentIdTextField);
//		
//		studentNameLabel = new JLabel("Student Name");
//		studentNameLabel.setFont(labelFont);
//		studentNameLabel.setBounds(215,330,280,70);
//		studentNameLabel.setForeground(Color.black);
//		add(studentNameLabel);
//		
//		studentNameTextField = new JTextField();
//		studentNameTextField.setFont(textFieldFont);
//		studentNameTextField.setBounds(215,380,180,25);
//		studentNameTextField.setBackground(Color.white);
//		add(studentNameTextField);
		
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
					
//					INSERT INTO `marksheet`(`atendance`, `assignment`, `presentation`, `viva`, `tutorial`, `mid`, `final`, `student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) VALUES (0,0,0,0,0,0,0,'','','','','','','','','')
					
					
					
					
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
		
		
		
//		saveButton = new JButton("Save");
//		saveButton.setFont(buttonFont);
//		saveButton.setBounds(20,430,180,25);
//		saveButton.setForeground(Color.white);
//		saveButton.setFocusable(false);
//		saveButton.setBackground(darkColor);
//		saveButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				String projectName = projectNameTextField.getText();
//				//String teamName = teamNameTextField.getText();
//				String courseCode = courseCodeTextField.getText();
//				String courseName = courseNameTextField.getText();
//				String semester = semesterTextField.getText();
//				
//				teamProjectChanged = false;
//				courseChanged = false;
//				
//				try {
//					
//					System.out.println("current Team= " + currentTeamName);
//					System.out.println("Team = " + teamName);
//					
//					System.out.println("current Project= " + currentProjectName);
//					System.out.println("Project = " + projectName);
//					
//					// // Team and Project name validation
//					if(!teamName.equals(currentTeamName) || !projectName.equals(currentProjectName)) {
//						teamProjectChanged = true;
//						String searchTeamSql = "SELECT * FROM `projects` WHERE "
//								+ "lower(trim(course_code))=lower(trim('"+courseCode+"'))and "
//								+ "lower(trim(course_name))=lower(trim('"+courseName+"'))and "
//								+ "lower(trim(team_name))=lower(trim('"+teamName+"'))and "
//								+ "lower(trim(project_name))=lower(trim('"+projectName+"'))and "
//								+ "lower(trim(semester))=lower(trim('"+semester+"'))and "
//								+ "lower(trim(username))=lower(trim('"+loginUserName+"'))";
//						
//						
//						int cntTeam=0, cntProject=0;
//						ResultSet rsTeam = st.executeQuery(searchTeamSql);
//						while(rsTeam.next())cntTeam++;
//						
//						if(cntTeam==0) {
//							JOptionPane.showMessageDialog(null, "Team Doesn't exists");
//							return;
//						}
//				
//					}
//					
//					// // Course code and name validation
//					if(!courseName.equals(currentCourseName) || !courseCode.equals(currentCourseCode)) {
//						courseChanged = true;
//						String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE "
//								+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) or "
//								+ "lower(trim(course_name)) = lower(trim('"+courseName+"'))";
//						ResultSet rs2 = st.executeQuery(searchCourseSql);
//						
//						int cntCourse = 0;
//						String existingCourseCode = "", existingCourseName = "";
//						while(rs2.next()) {
//							existingCourseCode = rs2.getString(1);
//							existingCourseName = rs2.getString(2);
//							if(!courseName.equals(existingCourseName) && courseCode.equals(existingCourseCode)) {
//								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
//								return;
//							}
//							if(courseName.equals(existingCourseName) && !courseCode.equals(existingCourseCode)) {
//								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
//								return;
//							}
//							cntCourse++;
//						}
//						
//					}
//					
//					System.out.println(teamProjectChanged);
//					System.out.println(courseChanged);
//					
//					if(teamProjectChanged==false && courseChanged==false) {
//						String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
//								+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
//								+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
//								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
//								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
//						st.executeUpdate(deleteFromTeamMembersSql);
//					}
//					
//					
//					int teamLen = studentTable.getRowCount();
//					
//					if (teamLen > 0) {
//						for(int i=0;i<teamLen;i++) {
//							String studentId = studentTableModel.getValueAt(i, 0).toString();
//							String studentName = studentTableModel.getValueAt(i, 1).toString();
//							String insertMembersSql = "INSERT INTO `team_members`(`team_name`, `course_code`, `student_id`, `student_name`, `username`, `semester`) VALUES "
//									+ "('"+teamName+"','"+courseCode+"','"+studentId+"','"+studentName+"','"+loginUserName+"','"+semester+"')";
//							st.executeUpdate(insertMembersSql);
//							
//							
//						}
//					}
//					else {
//						if (JOptionPane.showConfirmDialog(null, "Do you want to delete the team from team list?", "Delete team?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//							String deleteFromProjectSql = "DELETE FROM `projects` where "
//									+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
//									+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
//									+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
//									+ "lower(trim(semester))=lower(trim('"+semester+"')) and "
//									+ "lower(trim(project_name))=lower(trim('"+projectName+"'))";
//							st.executeUpdate(deleteFromProjectSql);
//							dispose();
//						} else {
//						    return;
//						}
//					}
//					
//					if(teamProjectChanged==true || courseChanged==true) {
//						teamLen = studentTable.getRowCount();
//						
//						if (teamLen > 0) {
//							for(int i=0;i<teamLen;i++) {
//								String studentId = studentTableModel.getValueAt(i, 0).toString();
//								String studentName = studentTableModel.getValueAt(i, 1).toString();
//								String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
//										+ "lower(trim(team_name))=lower(trim('"+currentTeamName+"')) and "
//										+ "lower(trim(course_code))=lower(trim('"+currentCourseCode+"')) and "
//										+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
//										+ "lower(trim(semester))=lower(trim('"+currentSemester+"')) and "
//										+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
//										+ "lower(trim(student_name))=lower(trim('"+studentName+"'))";
//								st.executeUpdate(deleteFromTeamMembersSql);
//								
//								System.out.println("in delete"+studentId);
//								System.out.println("in delete"+studentName);
//							}
//						}
//					}
//					
//					currentProjectName = projectName;
//					currentTeamName = teamName;
//					currentCourseCode = courseCode;
//					currentCourseName = courseName;
//					currentSemester = semester;
//					
//				
//				} catch(SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		add(saveButton);
//		
//		doneButton = new JButton("Done");
//		doneButton.setFont(buttonFont);
//		doneButton.setBounds(800,430,180,25);
//		doneButton.setForeground(Color.white);
//		doneButton.setFocusable(false);
//		doneButton.setBackground(darkColor);
//		doneButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (JOptionPane.showConfirmDialog(null, "Are you sure you, all unsaved data will be lost", "Close Window?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//					dispose();
//				} else {
//				    return;
//				}
//				
//			}
//		});
//		add(doneButton);
		
		
		
		setVisible(true);
	}
	
}
