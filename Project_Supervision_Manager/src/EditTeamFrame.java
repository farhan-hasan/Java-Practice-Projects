import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditTeamFrame extends JFrame{
	
	Color lightColor = new Color(255,255,255);
	Color darkColor = new Color(34, 125, 128);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JLabel studentIdLabel, studentNameLabel, projectNameLabel, teamNameLabel, courseCodeLabel, courseNameLabel, semesterLabel;
	
	JTextField studentIdTextField, studentNameTextField, projectNameTextField, teamNameTextField, courseCodeTextField;
	JTextField courseNameTextField, semesterTextField;
	
	JButton addButton, updateButton, deleteButton, saveButton, doneButton;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {"Student ID","Student Name"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	Statement st;
	Connection con;
	
	boolean teamProjectChanged = false, courseChanged = false, semesterChanged = false;
	
	String parentProjectName, parentTeamName, parentCourseCode, parentCourseName, parentSemester, loginUserName;
	
	String currentStudentId, currentStudentName, currentProjectName, currentTeamName, currentCourseCode, currentCourseName, currentSemester;
	
	public EditTeamFrame(String[] data) {
		
		parentProjectName = data[0];
		parentTeamName = data[1];
		parentCourseCode = data[2];
		parentCourseName = data[3];
		parentSemester = data[4];
		loginUserName = data[5];
		
		
		currentProjectName = parentProjectName;
		currentTeamName = parentTeamName;
		currentCourseCode = parentCourseCode;
		currentCourseName = parentCourseName;
		currentSemester = parentSemester;
		
		System.out.println(parentProjectName);
		System.out.println(parentTeamName);
		System.out.println(parentCourseCode);
		System.out.println(parentCourseName);
		System.out.println(parentSemester);
		System.out.println(loginUserName);
		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();	
			
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
		setTitle("Edit Team");
		
		projectNameLabel = new JLabel("Project Name");
		projectNameLabel.setFont(labelFont);
		projectNameLabel.setBounds(20,20,280,70);
		projectNameLabel.setForeground(Color.black);
		add(projectNameLabel);
		
		projectNameTextField = new JTextField();
		projectNameTextField.setFont(textFieldFont);
		projectNameTextField.setBounds(20,70,180,25);
		projectNameTextField.setBackground(Color.white);
		add(projectNameTextField);
		
		teamNameLabel = new JLabel("Team Name");
		teamNameLabel.setFont(labelFont);
		teamNameLabel.setBounds(215,20,280,70);
		teamNameLabel.setForeground(Color.black);
		add(teamNameLabel);
		
		teamNameTextField = new JTextField();
		teamNameTextField.setFont(textFieldFont);
		teamNameTextField.setBounds(215,70,180,25);
		teamNameTextField.setBackground(Color.white);
		add(teamNameTextField);
		
		courseCodeLabel = new JLabel("Course Code");
		courseCodeLabel.setFont(labelFont);
		courseCodeLabel.setBounds(410,20,280,70);
		courseCodeLabel.setForeground(Color.black);
		add(courseCodeLabel);
		
		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(textFieldFont);
		courseCodeTextField.setBounds(410,70,180,25);
		courseCodeTextField.setBackground(Color.white);
		add(courseCodeTextField);
		
		courseNameLabel = new JLabel("Course Name");
		courseNameLabel.setFont(labelFont);
		courseNameLabel.setBounds(605,20,280,70);
		courseNameLabel.setForeground(Color.black);
		add(courseNameLabel);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(textFieldFont);
		courseNameTextField.setBounds(605,70,180,25);
		courseNameTextField.setBackground(Color.white);
		add(courseNameTextField);
		
		semesterLabel = new JLabel("Semester");
		semesterLabel.setFont(labelFont);
		semesterLabel.setBounds(800,20,280,70);
		semesterLabel.setForeground(Color.black);
		add(semesterLabel);
		
		semesterTextField = new JTextField();
		semesterTextField.setFont(textFieldFont);
		semesterTextField.setBounds(800,70,180,25);
		semesterTextField.setBackground(Color.white);
		add(semesterTextField);
		
		
		
		studentTable = new JTable(studentTableModel){

			   @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return column >= 2;
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
				int idx = studentTable.getSelectedRow();
				
				String studentId = studentTableModel.getValueAt(idx, 0).toString();
				String studentName = studentTableModel.getValueAt(idx, 1).toString();
				String projectName = parentProjectName;
				String teamName = parentTeamName;
				String courseCode = parentCourseCode;
				String courseName = parentCourseName;
				String semester = parentSemester;
				
				projectNameTextField.setText(projectName);
				teamNameTextField.setText(teamName);
				courseCodeTextField.setText(courseCode);
				courseNameTextField.setText(courseName);
				semesterTextField.setText(semester);
				studentIdTextField.setText(studentId);
				studentNameTextField.setText(studentName);
				
				
				
				
			}
		});
		add(studentTableScrollPane);
		
		studentIdLabel = new JLabel("Student ID");
		studentIdLabel.setFont(labelFont);
		studentIdLabel.setBounds(20,330,280,70);
		studentIdLabel.setForeground(Color.black);
		//add(studentIdLabel);
		
		studentIdTextField = new JTextField();
		studentIdTextField.setFont(textFieldFont);
		studentIdTextField.setBounds(20,380,180,25);
		studentIdTextField.setBackground(Color.white);
		//add(studentIdTextField);
		
		studentNameLabel = new JLabel("Student Name");
		studentNameLabel.setFont(labelFont);
		studentNameLabel.setBounds(215,330,280,70);
		studentNameLabel.setForeground(Color.black);
		//add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setFont(textFieldFont);
		studentNameTextField.setBounds(215,380,180,25);
		studentNameTextField.setBackground(Color.white);
		//add(studentNameTextField);
		
		// // setting the parent data in textfields
		projectNameTextField.setText(parentProjectName);
		teamNameTextField.setText(parentTeamName);
		courseCodeTextField.setText(parentCourseCode);
		courseNameTextField.setText(parentCourseName);
		semesterTextField.setText(parentSemester);
		
		
		addButton = new JButton("Add");
		addButton.setFont(buttonFont);
		addButton.setBounds(20,380,180,25);
		addButton.setForeground(Color.white);
		addButton.setFocusable(false);
		addButton.setBackground(darkColor);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = studentIdTextField.getText();
				String studentName = studentNameTextField.getText();
				boolean intCheck = false;
				
				String x = studentId;
				int len = x.length();
				for (char c : x.toCharArray()) {
		            if (!Character.isDigit(c)) {
		            	intCheck = true; // Found a character that is not a digit
		            	System.out.println("->" + c);
		            	break;
		            }
		        }
				
				if(intCheck==true) {
					JOptionPane.showMessageDialog(null, "Student ID should be a number");
					return;
				}
				Object newRow[] = {studentId, studentName};
				studentTableModel.addRow(newRow);
				
			}
		});
		add(addButton);
		
		updateButton = new JButton("Update");
		updateButton.setFont(buttonFont);
		updateButton.setBounds(215,380,180,25);
		updateButton.setForeground(Color.white);
		updateButton.setFocusable(false);
		updateButton.setBackground(darkColor);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = studentIdTextField.getText();
				String studentName = studentNameTextField.getText();
				int idx = studentTable.getSelectedRow();
				boolean intCheck = false;
				String x = studentId;
				for (char c : x.toCharArray()) {
		            if (!Character.isDigit(c)) {
		            	intCheck = true; // Found a character that is not a digit
		            	System.out.println("->" + c);
		            	break;
		            }
		        }
				
				if(intCheck==true) {
					JOptionPane.showMessageDialog(null, "Student ID should be a number");
					return;
				}
				
				studentTableModel.setValueAt(studentId, idx, 0);
				studentTableModel.setValueAt(studentName, idx, 1);
			}
		});
		add(updateButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(buttonFont);
		deleteButton.setBounds(410,380,180,25);
		deleteButton.setForeground(Color.white);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(darkColor);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = studentTable.getSelectedRow();
				
				studentTableModel.removeRow(idx);
				
			}
		});
		add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setBounds(20,430,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String projectName = projectNameTextField.getText();
				String teamName = teamNameTextField.getText();
				String courseCode = courseCodeTextField.getText();
				String courseName = courseNameTextField.getText();
				String semester = semesterTextField.getText();
				
				teamProjectChanged = false;
				courseChanged = false;
				semesterChanged = false;
				
				try {
					
					System.out.println("current Team= " + currentTeamName);
					System.out.println("Team = " + teamName);
					
					System.out.println("current Project= " + currentProjectName);
					System.out.println("Project = " + projectName);
					int studentLen = studentTable.getRowCount();
					for(int i=0;i<studentLen;i++) {
						String studentId = studentTableModel.getValueAt(i, 0).toString();
						boolean intCheck = false;
						String x = studentId;
						for (char c : x.toCharArray()) {
				            if (!Character.isDigit(c)) {
				            	intCheck = true; // Found a character that is not a digit
				            	System.out.println("->" + c);
				            	break;
				            }
				        }
						
						if(intCheck==true) {
							JOptionPane.showMessageDialog(null, "Student ID should be a number");
							return;
						}
						for(int j=0;j<studentLen;j++) {
							if(j==i)continue;
							if(studentId.equals(studentTableModel.getValueAt(j, 0).toString())) {
								JOptionPane.showMessageDialog(null, "Student ID must be unique");
								return;
							}
						}
					}
					
					// // Team and Project name validation
					if(!teamName.equals(currentTeamName) || !projectName.equals(currentProjectName)) {
						teamProjectChanged = true;
						String searchTeamSql = "SELECT * FROM `projects` WHERE "
								+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
								+ "lower(trim(course_name))=lower(trim('"+courseName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"')) and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "(lower(trim(team_name))=lower(trim('"+teamName+"')) or "
								+ "lower(trim(project_name))=lower(trim('"+projectName+"'))) ";
						
						
						int cntTeam=0, cntProject=0;
						ResultSet rsTeam = st.executeQuery(searchTeamSql);
						while(rsTeam.next())cntTeam++;
						
						if(cntTeam!=0) {
							JOptionPane.showMessageDialog(null, "Team name of Project name already exists for team");
							return;
						}
				
					}
					
					// // Course code and name validation
					if(!courseName.equals(currentCourseName) || !courseCode.equals(currentCourseCode)) {
						courseChanged = true;
						
						String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) or lower(trim(course_name)) = lower(trim('"+courseName+"'))";
						ResultSet rs2 = st.executeQuery(searchCourseSql);
						
						int cnt2 = 0;
						String existingCourseCode = "", existingCourseName = "";
						while(rs2.next()) {
							existingCourseCode = rs2.getString(1).toLowerCase();
							existingCourseName = rs2.getString(2).toLowerCase();
							String code = courseCode.replaceAll("\\s", "").toLowerCase();
							String name = courseName.replaceAll("\\s", "").toLowerCase();
							String ecode = existingCourseCode.replaceAll("\\s", "").toLowerCase();
							String ename = existingCourseName.replaceAll("\\s", "").toLowerCase();
							System.out.println(code);
							System.out.println(ecode);
							System.out.println(name);
							System.out.println(ename);
							if(!name.equals(ename) && code.equals(ecode)) {
								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
								return;
							}
							if(name.equals(ename) && !code.equals(ecode)) {
								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
								return;
							}
							cnt2++;
						}
						
					}
					
					// // Semester Validation
					if(!semester.equals(currentSemester)) {
						semesterChanged = true;
						String searchTeamSql = "SELECT * FROM `projects` WHERE "
								+ "lower(trim(course_code))=lower(trim('"+courseCode+"'))and "
								+ "lower(trim(course_name))=lower(trim('"+courseName+"'))and "
								+ "lower(trim(team_name))=lower(trim('"+teamName+"'))and "
								+ "lower(trim(project_name))=lower(trim('"+projectName+"'))and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"'))";
						int cntSemester = 0;
						ResultSet rsTeam = st.executeQuery(searchTeamSql);
						while(rsTeam.next())cntSemester++;
						
						if(cntSemester!=0) {
							JOptionPane.showMessageDialog(null, "Team already exists for semester");
							return;
						}
						
					}
					
					System.out.println(teamProjectChanged);
					System.out.println(courseChanged);
					
					// // Delete all existing members
					//if(teamProjectChanged==false && courseChanged==false) {
						String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
								+ "lower(trim(team_name))=lower(trim('"+currentTeamName+"')) and "
								+ "lower(trim(course_code))=lower(trim('"+currentCourseCode+"')) and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+currentSemester+"'))";
						st.executeUpdate(deleteFromTeamMembersSql);
					//}
					
					// // Delete from projects
					String deleteFromProjectSql = "DELETE FROM `projects` where "
							+ "lower(trim(team_name))=lower(trim('"+currentTeamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+currentCourseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+currentSemester+"')) and "
							+ "lower(trim(project_name))=lower(trim('"+currentProjectName+"'))";
					st.executeUpdate(deleteFromProjectSql);
					
						
					// // Insert into projects
					String insertProjectSql = "INSERT INTO `projects`(`course_code`, `course_name`, `project_name`, `team_name`, `semester`, `username`) VALUES "
							+ "('"+courseCode+"','"+courseName+"','"+projectName+"','"+teamName+"','"+semester+"','"+loginUserName+"')";
					st.executeUpdate(insertProjectSql);
						
						
					int teamLen = studentTable.getRowCount();
					
					
					// // Inserting new memebers
					if (teamLen > 0) {
						for(int i=0;i<teamLen;i++) {
							String studentId = studentTableModel.getValueAt(i, 0).toString();
							String studentName = studentTableModel.getValueAt(i, 1).toString();
							String insertMembersSql = "INSERT INTO `team_members`(`team_name`, `course_code`, `student_id`, `student_name`, `username`, `semester`) VALUES "
									+ "('"+teamName+"','"+courseCode+"','"+studentId+"','"+studentName+"','"+loginUserName+"','"+semester+"')";
							st.executeUpdate(insertMembersSql);
							
							String UpdateSql = "UPDATE `session_history` SET "
									+ "semester = '"+semester+"',"
									+ "project_name = '"+projectName+"' WHERE "
									+ "lower(trim(student_id)) = lower(trim('"+studentId+"')) and "
									+ "lower(trim(student_name)) = lower(trim('"+studentName+"')) and "
									+ "lower(trim(project_name)) = lower(trim('"+parentProjectName+"')) and "
									+ "lower(trim(course_code)) = lower(trim('"+parentCourseCode+"')) and "
									+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
									+ "lower(trim(semester)) = lower(trim('"+parentSemester+"'))";
							st.executeUpdate(UpdateSql);
							
							
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please enter students");
						return;
					}
					
					// // Session History Update
					
					
					currentProjectName = projectName;
					currentTeamName = teamName;
					currentCourseCode = courseCode;
					currentCourseName = courseName;
					currentSemester = semester;
					
				
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(saveButton);
		
		doneButton = new JButton("Done");
		doneButton.setFont(buttonFont);
		doneButton.setBounds(800,430,180,25);
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
