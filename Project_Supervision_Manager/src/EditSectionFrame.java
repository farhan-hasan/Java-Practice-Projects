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

import com.mysql.cj.exceptions.RSAException;

public class EditSectionFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JButton addButton, updateButton, deleteButton, saveButton, doneButton;
	
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
					+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))"
							+ "order by student_id";
			
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
		setTitle("Edit Section");
		
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
					
					
					System.out.println(teamProjectChanged);
					System.out.println(courseChanged);
					int studentLen = studentTable.getRowCount();
					for(int i=0;i<studentLen;i++) {
						String studentId = studentTableModel.getValueAt(i, 0).toString();
						for(int j=0;j<studentLen;j++) {
							if(j==i)continue;
							if(studentId.equals(studentTableModel.getValueAt(j, 0).toString())) {
								JOptionPane.showMessageDialog(null, "Student ID must be unique");
								return;
							}
						}
					}
					
					String deleteFromTeamMembersSql = "DELETE FROM `student_list` where "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"'))";
					st.executeUpdate(deleteFromTeamMembersSql);
					
					
					int teamLen = studentTable.getRowCount();
					
					if (teamLen > 0) {
						for(int i=0;i<teamLen;i++) {
							String studentId = studentTableModel.getValueAt(i, 0).toString();
							String studentName = studentTableModel.getValueAt(i, 1).toString();
							String insertStudentsSql = "INSERT INTO `student_list`(`student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) VALUES "
									+ "('"+studentId+"','"+studentName+"','"+courseCode+"','"+courseName+"','"+section+"','"+batch+"','"+department+"','"+semester+"','"+loginUserName+"')";
							st.executeUpdate(insertStudentsSql);
							
							// // search in marksheet
							
							String searchMarksheetSql = "SELECT * FROM `marksheet` WHERE "
									+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
									+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
									+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
									+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
									+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
									+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
									+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
									+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
							
							int cntMark = 0;
							ResultSet rsMark = st.executeQuery(searchMarksheetSql);
							while(rsMark.next())cntMark++;
							
							if(cntMark==0) {
								String insertMarksheetSql = "INSERT INTO `marksheet`(`atendance`, `assignment`, `presentation`, `viva`, `tutorial`, `mid`, `final`, `student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) "
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
								st.executeUpdate(insertMarksheetSql);
							}
							
							
							// // Attendance search
							String searchAttendanceSql = "SELECT * FROM `attendance` WHERE "
									+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
									+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
									+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
									+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
									+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
									+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
									+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
									+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
							ResultSet rsAttendance = st.executeQuery(searchAttendanceSql);
							int cntAttendance = 0;
							while(rsAttendance.next())cntAttendance++;
							
							if(cntAttendance==0) {
								String insertAttendanceSql = "INSERT INTO `attendance`(`student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31`, `32`, `33`, `34`, `35`, `36`, `37`, `38`, `39`, `40`, `41`, `42`) "
										+ "VALUES ("
										+ "'"+studentId+"',"
										+ "'"+studentName+"',"
										+ "'"+parentCourseCode+"',"
										+ "'"+parentCourseName+"',"
										+ "'"+parentSection+"',"
										+ "'"+parentBatch+"',"
										+ "'"+parentDepartment+"',"
										+ "'"+parentSemester+"',"
										+ "'"+loginUserName+"',"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0,"
											+ "0)";
								st.executeUpdate(insertAttendanceSql);
							}
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
					
				
				} catch(SQLException e1) {
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
		
		
		
		setVisible(true);
	}
	
}
