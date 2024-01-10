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

public class EditSectionFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JButton addButton, updateButton, deleteButton, saveButton;
	
	JLabel courseCodeLabel, courseNameLabel, sectionLabel, departmentLabel;
	JLabel semesterLabel, batchLabel, studentIdLabel, studentNameLabel;
	
	JTextField courseCodeTextField, courseNameTextField, sectionTextField;
	JTextField departmentTextField, batchTextField, semesterTextField, studentIdTextField, studentNameTextField;
	
	JTable studentTable;
	JScrollPane studentTableScrollPane;
	Object studentTableData[][] = {};
	String studentTableColumns[] = {"Student ID","Student Name"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	Statement st;
	Connection con;
	
	boolean teamProjectChanged = false, courseChanged = false;
	
	String parentCourseCode, parentCourseName, parentSection, parentBatch, parentDepartment, parentSemester, loginUserName;
	
	String currentCourseCode, currentCourseName, currentSection, currentBatch, currentDepartment, currentSemester;
	
	public EditSectionFrame(String[] data) {
		
		parentCourseCode = data[0];
		parentCourseName = data[1];
		parentSection = data[2];
		parentBatch = data[3];
		parentDepartment = data[4];
		parentSemester = data[5];
		loginUserName = data[6];
		
		
		currentCourseCode = parentCourseCode;
		currentCourseName = parentCourseName;
		currentSection = parentSection;
		currentBatch = parentBatch;
		currentDepartment = parentDepartment;
		currentSemester = parentSemester;
		
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
			
			String sql = "SELECT * FROM `student_list` WHERE "
					+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
					+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
					+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
					+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
					+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
					+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String studentId = rs.getString(1);
				String studentName = rs.getString(2);
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
		
		courseCodeLabel = new JLabel("Course Code");
		courseCodeLabel.setFont(labelFont);
		courseCodeLabel.setBounds(20,20,280,70);
		courseCodeLabel.setForeground(Color.black);
		add(courseCodeLabel);
		
		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(textFieldFont);
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
		semesterTextField.setBounds(800,380,180,25);
		semesterTextField.setBackground(Color.white);
		add(semesterTextField);
		
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
				
				String studentId = studentTableModel.getValueAt(idx, 0).toString();
				String studentName = studentTableModel.getValueAt(idx, 1).toString();
				
				studentIdTextField.setText(studentId);
				studentNameTextField.setText(studentName);
				
			}
		});
		add(studentTableScrollPane);
		
		studentIdLabel = new JLabel("Student ID");
		studentIdLabel.setFont(labelFont);
		studentIdLabel.setBounds(20,330,280,70);
		studentIdLabel.setForeground(Color.black);
		add(studentIdLabel);
		
		studentIdTextField = new JTextField();
		studentIdTextField.setFont(textFieldFont);
		studentIdTextField.setBounds(20,380,180,25);
		studentIdTextField.setBackground(Color.white);
		add(studentIdTextField);
		
		studentNameLabel = new JLabel("Student Name");
		studentNameLabel.setFont(labelFont);
		studentNameLabel.setBounds(215,330,280,70);
		studentNameLabel.setForeground(Color.black);
		add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setFont(textFieldFont);
		studentNameTextField.setBounds(215,380,180,25);
		studentNameTextField.setBackground(Color.white);
		add(studentNameTextField);
		
		
		
		courseCodeTextField.setText(parentCourseCode);
		courseNameTextField.setText(parentCourseName);
		sectionTextField.setText(parentSection);
		batchTextField.setText(parentBatch);
		departmentTextField.setText(parentDepartment);
		semesterTextField.setText(parentSemester);
		
		
		
		addButton = new JButton("Add");
		addButton.setFont(buttonFont);
		addButton.setBounds(20,420,180,25);
		addButton.setForeground(Color.white);
		addButton.setFocusable(false);
		addButton.setBackground(darkColor);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = studentIdTextField.getText();
				String studentName = studentNameTextField.getText();
				Object newRow[] = {studentId, studentName};
				studentTableModel.addRow(newRow);
				
			}
		});
		add(addButton);
		
		updateButton = new JButton("Update");
		updateButton.setFont(buttonFont);
		updateButton.setBounds(215,420,180,25);
		updateButton.setForeground(Color.white);
		updateButton.setFocusable(false);
		updateButton.setBackground(darkColor);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = studentIdTextField.getText();
				String studentName = studentNameTextField.getText();
				int idx = studentTable.getSelectedRow();
				
				studentTableModel.setValueAt(studentId, idx, 0);
				studentTableModel.setValueAt(studentName, idx, 1);
				
			}
		});
		add(updateButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(buttonFont);
		deleteButton.setBounds(410,420,180,25);
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
		saveButton.setBounds(605,420,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String courseCode = courseCodeTextField.getText();
				String courseName = courseNameTextField.getText();
				String section = sectionTextField.getText();
				String batch = batchTextField.getText();
				String department = departmentTextField.getText();
				String semester = semesterTextField.getText();
				
				teamProjectChanged = false;
				courseChanged = false;
				
				try {
					
//					System.out.println("current Team= " + currentTeamName);
//					System.out.println("Team = " + teamName);
//					
//					System.out.println("current Project= " + currentProjectName);
//					System.out.println("Project = " + projectName);
					
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
					
					// // Course code and name validation
					if(!courseName.equals(currentCourseName) || !courseCode.equals(currentCourseCode)) {
						courseChanged = true;
						String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE "
								+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) or "
								+ "lower(trim(course_name)) = lower(trim('"+courseName+"'))";
						ResultSet rs2 = st.executeQuery(searchCourseSql);
						
						int cntCourse = 0;
						String existingCourseCode = "", existingCourseName = "";
						while(rs2.next()) {
							existingCourseCode = rs2.getString(1);
							existingCourseName = rs2.getString(2);
							if(!courseName.equals(existingCourseName) && courseCode.equals(existingCourseCode)) {
								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
								return;
							}
							if(courseName.equals(existingCourseName) && !courseCode.equals(existingCourseCode)) {
								JOptionPane.showMessageDialog(null, "Course code and name doesn't match");
								return;
							}
							cntCourse++;
						}
						
					}
					
					System.out.println(teamProjectChanged);
					System.out.println(courseChanged);
					
					if(courseChanged==false) {
						String deleteFromTeamMembersSql = "DELETE FROM `student_list` where "
								+ "lower(trim(section))=lower(trim('"+section+"')) and "
								+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
								+ "lower(trim(department))=lower(trim('"+department+"')) and "
								+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
						st.executeUpdate(deleteFromTeamMembersSql);
					}
					
					
					int teamLen = studentTable.getRowCount();
					
					if (teamLen > 0) {
						for(int i=0;i<teamLen;i++) {
							String studentId = studentTableModel.getValueAt(i, 0).toString();
							String studentName = studentTableModel.getValueAt(i, 1).toString();
							String insertStudentsSql = "INSERT INTO `student_list`(`student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) VALUES "
									+ "('"+studentId+"','"+studentName+"','"+courseCode+"','"+courseName+"','"+section+"','"+batch+"','"+department+"','"+semester+"','"+loginUserName+"')";
							st.executeUpdate(insertStudentsSql);
							
							
						}
					}
					else {
						if (JOptionPane.showConfirmDialog(null, "Do you want to delete the course from your course list?", "Delete course?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							String deleteFromCourseSql = "DELETE FROM `course_taken` where "
									+ "lower(trim(section))=lower(trim('"+section+"')) and "
									+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
									+ "lower(trim(department))=lower(trim('"+department+"')) and "
									+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
									+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
									+ "lower(trim(semester))=lower(trim('"+semester+"'))";
							st.executeUpdate(deleteFromCourseSql);
							dispose();
						} else {
						    return;
						}
					}
					
					if(courseChanged==true) {
						teamLen = studentTable.getRowCount();
						System.out.println("courseChanged check");
						if (teamLen > 0) {
							for(int i=0;i<teamLen;i++) {
								String studentId = studentTableModel.getValueAt(i, 0).toString();
								String studentName = studentTableModel.getValueAt(i, 1).toString();
								String deleteFromTeamMembersSql = "DELETE FROM `student_list` where "
										+ "lower(trim(section))=lower(trim('"+currentSection+"')) and "
										+ "lower(trim(batch))=lower(trim('"+currentBatch+"')) and "
										+ "lower(trim(department))=lower(trim('"+currentDepartment+"')) and "
										+ "lower(trim(course_code))=lower(trim('"+currentCourseCode+"')) and "
										+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
										+ "lower(trim(semester))=lower(trim('"+semester+"'))";
								st.executeUpdate(deleteFromTeamMembersSql);
								
								System.out.println("in delete"+studentId);
								System.out.println("in delete"+studentName);
							}
						}
					}
					
					currentCourseCode = courseCode;
					currentCourseName = courseName;
					currentSection = section;
					currentBatch = batch;
					currentDepartment = department;
					currentSemester = semester;
					
				
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(saveButton);
		
		
		
		setVisible(true);
	}
	
}
