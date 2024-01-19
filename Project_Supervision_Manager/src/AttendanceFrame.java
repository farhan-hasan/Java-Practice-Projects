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
	
	Color lightColor = new Color(255,255,255);
	Color darkColor = new Color(34, 125, 128);
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
					+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))"
							+ "order by student_id";
			
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
				
				ResultSet rsMark = st.executeQuery(loadMarkSql);
				int cntStudent = 0;
				while(rsMark.next()) {
					String one = rsMark.getString(10);
					String two = rsMark.getString(11);
					String three = rsMark.getString(12);
					String four = rsMark.getString(13);
					String five = rsMark.getString(14);
					String six = rsMark.getString(15);
					String seven = rsMark.getString(16);
					String eight = rsMark.getString(17);
					String nine = rsMark.getString(18);
					String ten = rsMark.getString(19);
					String eleven = rsMark.getString(20);
					String twelve = rsMark.getString(21);
					String thirteen = rsMark.getString(22);
					String fourteen = rsMark.getString(23);
					String fifteen = rsMark.getString(24);
					String sixteen = rsMark.getString(25);
					String seventeen = rsMark.getString(26);
					String eighteen = rsMark.getString(27);
					String nineteen = rsMark.getString(28);
					String twenty = rsMark.getString(29);
					String twentyone = rsMark.getString(30);
					String twentytwo = rsMark.getString(31);
					String twentythree = rsMark.getString(32);
					String twentyfour = rsMark.getString(33);
					String twentyfive = rsMark.getString(34);
					String twentysix = rsMark.getString(35);
					String twentyseven = rsMark.getString(36);
					String twentyeight = rsMark.getString(37);
					String twentynine = rsMark.getString(38);
					String thirty = rsMark.getString(39);
					String thirtyone = rsMark.getString(40);
					String thirtytwo = rsMark.getString(41);
					String thirtythree = rsMark.getString(42);
					String thirtyfour = rsMark.getString(43);
					String thirtyfive = rsMark.getString(44);
					String thirtysix = rsMark.getString(45);
					String thirtyseven = rsMark.getString(46);
					String thirtyeight = rsMark.getString(47);
					String thirtynine = rsMark.getString(48);
					String forty = rsMark.getString(49);
					String fortyone = rsMark.getString(50);
					String fortytwo = rsMark.getString(51);
					studentTableModel.setValueAt(one, i, 2);
					studentTableModel.setValueAt(two, i, 3);
					studentTableModel.setValueAt(three, i, 4);
					studentTableModel.setValueAt(four, i, 5);
					studentTableModel.setValueAt(five, i, 6);
					studentTableModel.setValueAt(six, i, 7);
					studentTableModel.setValueAt(seven, i, 8);
					studentTableModel.setValueAt(eight, i, 9);
					studentTableModel.setValueAt(nine, i, 10);
					studentTableModel.setValueAt(ten, i, 11);
					studentTableModel.setValueAt(eleven, i, 12);
					studentTableModel.setValueAt(twelve, i, 13);
					studentTableModel.setValueAt(thirteen, i, 14);
					studentTableModel.setValueAt(fourteen, i, 15);
					studentTableModel.setValueAt(fifteen, i, 16);
					studentTableModel.setValueAt(sixteen, i, 17);
					studentTableModel.setValueAt(seventeen, i, 18);
					studentTableModel.setValueAt(eighteen, i, 19);
					studentTableModel.setValueAt(nineteen, i, 20);
					studentTableModel.setValueAt(twenty, i, 21);
					studentTableModel.setValueAt(twentyone, i, 22);
					studentTableModel.setValueAt(twentytwo, i, 23);
					studentTableModel.setValueAt(twentythree, i, 24);
					studentTableModel.setValueAt(twentyfour, i, 25);
					studentTableModel.setValueAt(twentyfive, i, 26);
					studentTableModel.setValueAt(twentysix, i, 27);
					studentTableModel.setValueAt(twentyseven, i, 28);
					studentTableModel.setValueAt(twentyeight, i, 29);
					studentTableModel.setValueAt(twentynine, i, 30);
					studentTableModel.setValueAt(thirty, i, 31);
					studentTableModel.setValueAt(thirtyone, i, 32);
					studentTableModel.setValueAt(thirtytwo, i, 33);
					studentTableModel.setValueAt(thirtythree, i, 34);
					studentTableModel.setValueAt(thirtyfour, i, 35);
					studentTableModel.setValueAt(thirtyfive, i, 36);
					studentTableModel.setValueAt(thirtysix, i, 37);
					studentTableModel.setValueAt(thirtyseven, i, 38);
					studentTableModel.setValueAt(thirtyeight, i, 39);
					studentTableModel.setValueAt(thirtynine, i, 40);
					studentTableModel.setValueAt(forty, i, 41);
					studentTableModel.setValueAt(fortyone, i, 42);
					studentTableModel.setValueAt(fortytwo, i, 43);
					cntStudent++;
				}
				// // initializing with 0.
				if(cntStudent==0) {
					
						String insertSql = "INSERT INTO `attendance`(`student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31`, `32`, `33`, `34`, `35`, `36`, `37`, `38`, `39`, `40`, `41`, `42`) "
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
						System.out.println("check insert");
						st.executeUpdate(insertSql);
				}
			}
			// // refresh list
			
			
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
				
				ResultSet rsMark = st.executeQuery(loadMarkSql);
				while(rsMark.next()) {
					String one = rsMark.getString(10);
					String two = rsMark.getString(11);
					String three = rsMark.getString(12);
					String four = rsMark.getString(13);
					String five = rsMark.getString(14);
					String six = rsMark.getString(15);
					String seven = rsMark.getString(16);
					String eight = rsMark.getString(17);
					String nine = rsMark.getString(18);
					String ten = rsMark.getString(19);
					String eleven = rsMark.getString(20);
					String twelve = rsMark.getString(21);
					String thirteen = rsMark.getString(22);
					String fourteen = rsMark.getString(23);
					String fifteen = rsMark.getString(24);
					String sixteen = rsMark.getString(25);
					String seventeen = rsMark.getString(26);
					String eighteen = rsMark.getString(27);
					String nineteen = rsMark.getString(28);
					String twenty = rsMark.getString(29);
					String twentyone = rsMark.getString(30);
					String twentytwo = rsMark.getString(31);
					String twentythree = rsMark.getString(32);
					String twentyfour = rsMark.getString(33);
					String twentyfive = rsMark.getString(34);
					String twentysix = rsMark.getString(35);
					String twentyseven = rsMark.getString(36);
					String twentyeight = rsMark.getString(37);
					String twentynine = rsMark.getString(38);
					String thirty = rsMark.getString(39);
					String thirtyone = rsMark.getString(40);
					String thirtytwo = rsMark.getString(41);
					String thirtythree = rsMark.getString(42);
					String thirtyfour = rsMark.getString(43);
					String thirtyfive = rsMark.getString(44);
					String thirtysix = rsMark.getString(45);
					String thirtyseven = rsMark.getString(46);
					String thirtyeight = rsMark.getString(47);
					String thirtynine = rsMark.getString(48);
					String forty = rsMark.getString(49);
					String fortyone = rsMark.getString(50);
					String fortytwo = rsMark.getString(51);
					studentTableModel.setValueAt(one, i, 2);
					studentTableModel.setValueAt(two, i, 3);
					studentTableModel.setValueAt(three, i, 4);
					studentTableModel.setValueAt(four, i, 5);
					studentTableModel.setValueAt(five, i, 6);
					studentTableModel.setValueAt(six, i, 7);
					studentTableModel.setValueAt(seven, i, 8);
					studentTableModel.setValueAt(eight, i, 9);
					studentTableModel.setValueAt(nine, i, 10);
					studentTableModel.setValueAt(ten, i, 11);
					studentTableModel.setValueAt(eleven, i, 12);
					studentTableModel.setValueAt(twelve, i, 13);
					studentTableModel.setValueAt(thirteen, i, 14);
					studentTableModel.setValueAt(fourteen, i, 15);
					studentTableModel.setValueAt(fifteen, i, 16);
					studentTableModel.setValueAt(sixteen, i, 17);
					studentTableModel.setValueAt(seventeen, i, 18);
					studentTableModel.setValueAt(eighteen, i, 19);
					studentTableModel.setValueAt(nineteen, i, 20);
					studentTableModel.setValueAt(twenty, i, 21);
					studentTableModel.setValueAt(twentyone, i, 22);
					studentTableModel.setValueAt(twentytwo, i, 23);
					studentTableModel.setValueAt(twentythree, i, 24);
					studentTableModel.setValueAt(twentyfour, i, 25);
					studentTableModel.setValueAt(twentyfive, i, 26);
					studentTableModel.setValueAt(twentysix, i, 27);
					studentTableModel.setValueAt(twentyseven, i, 28);
					studentTableModel.setValueAt(twentyeight, i, 29);
					studentTableModel.setValueAt(twentynine, i, 30);
					studentTableModel.setValueAt(thirty, i, 31);
					studentTableModel.setValueAt(thirtyone, i, 32);
					studentTableModel.setValueAt(thirtytwo, i, 33);
					studentTableModel.setValueAt(thirtythree, i, 34);
					studentTableModel.setValueAt(thirtyfour, i, 35);
					studentTableModel.setValueAt(thirtyfive, i, 36);
					studentTableModel.setValueAt(thirtysix, i, 37);
					studentTableModel.setValueAt(thirtyseven, i, 38);
					studentTableModel.setValueAt(thirtyeight, i, 39);
					studentTableModel.setValueAt(thirtynine, i, 40);
					studentTableModel.setValueAt(forty, i, 41);
					studentTableModel.setValueAt(fortyone, i, 42);
					studentTableModel.setValueAt(fortytwo, i, 43);
				}
			}
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		getContentPane().setBackground(lightColor);
		setSize(1495,520);
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
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn column = null;
		for (int i = 0; i < 44; i++) {
		    column = studentTable.getColumnModel().getColumn(i);
		    if (i < 2) {
		        column.setPreferredWidth(110); //third column is bigger
		    } else {
		        column.setPreferredWidth(29);
		    }
		}
		studentTableScrollPane = new JScrollPane(studentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentTableScrollPane.setBounds(20, 120, 1441, 220); //962
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
//				for(int i=0;i<studentLen;i++) {
//					for(int j=2;j<44;j++) {
//							String dig = studentTable.getValueAt(i,j).toString();
//							int x = Integer.parseInt(dig);
//							if(x<0 || x>1) {
//								JOptionPane.showMessageDialog(null, "All cells should contain '0' or '1'");
//								return;
//							}
//					}
//				} 
					
				
				
				
				boolean intCheck = false;
				
				for(int i=0; i<studentLen; i++) {
					String studentId = studentTableModel.getValueAt(i, 0).toString();
					String studentName = studentTableModel.getValueAt(i, 1).toString();
					String one = studentTableModel.getValueAt(i, 2).toString();
					String two = studentTableModel.getValueAt(i, 3).toString();
					String three = studentTableModel.getValueAt(i, 4).toString();
					String four = studentTableModel.getValueAt(i, 5).toString();
					String five = studentTableModel.getValueAt(i, 6).toString();
					String six = studentTableModel.getValueAt(i, 7).toString();
					String seven = studentTableModel.getValueAt(i, 8).toString();
					String eight = studentTableModel.getValueAt(i, 9).toString();
					String nine = studentTableModel.getValueAt(i, 10).toString();
					String ten = studentTableModel.getValueAt(i, 11).toString();
					String eleven = studentTableModel.getValueAt(i, 12).toString();
					String twelve = studentTableModel.getValueAt(i, 13).toString();
					String thirteen = studentTableModel.getValueAt(i, 14).toString();
					String fourteen = studentTableModel.getValueAt(i, 15).toString();
					String fifteen = studentTableModel.getValueAt(i, 16).toString();
					String sixteen = studentTableModel.getValueAt(i, 17).toString();
					String seventeen = studentTableModel.getValueAt(i, 18).toString();
					String eighteen = studentTableModel.getValueAt(i, 19).toString();
					String nineteen = studentTableModel.getValueAt(i, 20).toString();
					String twenty = studentTableModel.getValueAt(i, 21).toString();
					String twentyone = studentTableModel.getValueAt(i, 22).toString();
					String twentytwo = studentTableModel.getValueAt(i, 23).toString();
					String twentythree = studentTableModel.getValueAt(i, 24).toString();
					String twentyfour = studentTableModel.getValueAt(i, 25).toString();
					String twentyfive = studentTableModel.getValueAt(i, 26).toString();
					String twentysix = studentTableModel.getValueAt(i, 27).toString();
					String twentyseven = studentTableModel.getValueAt(i, 28).toString();
					String twentyeight = studentTableModel.getValueAt(i, 29).toString();
					String twentynine = studentTableModel.getValueAt(i, 30).toString();
					String thirty = studentTableModel.getValueAt(i, 31).toString();
					String thirtyone = studentTableModel.getValueAt(i, 32).toString();
					String thirtytwo = studentTableModel.getValueAt(i, 33).toString();
					String thirtythree = studentTableModel.getValueAt(i, 34).toString();
					String thirtyfour = studentTableModel.getValueAt(i, 35).toString();
					String thirtyfive = studentTableModel.getValueAt(i, 36).toString();
					String thirtysix = studentTableModel.getValueAt(i, 37).toString();
					String thirtyseven = studentTableModel.getValueAt(i, 38).toString();
					String thirtyeight = studentTableModel.getValueAt(i, 39).toString();
					String thirtynine = studentTableModel.getValueAt(i, 40).toString();
					String forty = studentTableModel.getValueAt(i, 41).toString();
					String fortyone = studentTableModel.getValueAt(i, 42).toString();
					String fortytwo = studentTableModel.getValueAt(i, 43).toString();
					
					System.out.println(studentName);
					
					
					
					String[] inputs = {
							one,two,three,four,five,six,seven,eight,nine,ten,
							eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,
							twentyone,twentytwo,twentythree,twentyfour,twentyfive,twentysix,twentyseven,twentyeight,twentynine,thirty,
							thirtyone,thirtytwo,thirtythree,thirtyfour,thirtyfive,thirtysix,thirtyseven,thirtyeight,thirtynine,forty,
							fortyone,fortytwo
					};
					
					for(int j=0;j<42;j++) {
						String x = inputs[j];
						System.out.println(x);
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
					
					for(int k=2;k<44;k++) {
						String dig = studentTable.getValueAt(i,k).toString();
						int y = Integer.parseInt(dig);
						if(y<0 || y>1) {
							JOptionPane.showMessageDialog(null, "All cells should contain '0' or '1'");
							return;
						}
					}
					
					
					
					int intstudentId = 0;
					int intstudentName = 0;
					int intone = 0;
					int inttwo = 0;
					int intthree = 0;
					int intfour = 0;
					int intfive = 0;
					int intsix = 0;
					int intseven = 0;
					int inteight = 0;
					int intnine = 0;
					int intten = 0;
					int inteleven = 0;
					int inttwelve = 0;
					int intthirteen = 0;
					int intfourteen = 0;
					int intfifteen = 0;
					int intsixteen = 0;
					int intseventeen = 0;
					int inteighteen = 0;
					int intnineteen = 0;
					int inttwenty = 0;
					int inttwentyone = 0;
					int inttwentytwo = 0;
					int inttwentythree = 0;
					int inttwentyfour = 0;
					int inttwentyfive = 0;
					int inttwentysix = 0;
					int inttwentyseven = 0;
					int inttwentyeight = 0;
					int inttwentynine = 0;
					int intthirty = 0;
					int intthirtyone = 0;
					int intthirtytwo = 0;
					int intthirtythree = 0;
					int intthirtyfour = 0;
					int intthirtyfive = 0;
					int intthirtysix = 0;
					int intthirtyseven = 0;
					int intthirtyeight = 0;
					int intthirtynine = 0;
					int intforty = 0;
					int intfortyone = 0;
					int intfortytwo = 0;
					

					
					if(!one.equals(""))intone = Integer.parseInt(one);
					if(!two.equals(""))inttwo = Integer.parseInt(two);
					if(!three.equals(""))intthree = Integer.parseInt(three);
					if(!four.equals(""))intfour = Integer.parseInt(four);
					if(!five.equals(""))intfive = Integer.parseInt(five);
					if(!six.equals(""))intsix = Integer.parseInt(six);
					if(!seven.equals(""))intseven = Integer.parseInt(seven);
					if(!eight.equals(""))inteight = Integer.parseInt(eight);
					if(!nine.equals(""))intnine = Integer.parseInt(nine);
					if(!ten.equals(""))intten = Integer.parseInt(ten);
					if(!eleven.equals(""))inteleven = Integer.parseInt(eleven);
					if(!twelve.equals(""))inttwelve = Integer.parseInt(twelve);
					if(!thirteen.equals(""))intthirteen = Integer.parseInt(thirteen);
					if(!fourteen.equals(""))intfourteen = Integer.parseInt(fourteen);
					if(!fifteen.equals(""))intfifteen = Integer.parseInt(fifteen);
					if(!sixteen.equals(""))intsixteen = Integer.parseInt(sixteen);
					if(!seventeen.equals(""))intseventeen = Integer.parseInt(seventeen);
					if(!eighteen.equals(""))inteighteen = Integer.parseInt(eighteen);
					if(!nineteen.equals(""))intnineteen = Integer.parseInt(nineteen);
					if(!twenty.equals(""))inttwenty = Integer.parseInt(twenty);
					if(!twentyone.equals(""))inttwentyone = Integer.parseInt(twentyone);
					if(!twentytwo.equals(""))inttwentytwo = Integer.parseInt(twentytwo);
					if(!twentythree.equals(""))inttwentythree = Integer.parseInt(twentythree);
					if(!twentyfour.equals(""))inttwentyfour = Integer.parseInt(twentyfour);
					if(!twentyfive.equals(""))inttwentyfive = Integer.parseInt(twentyfive);
					if(!twentysix.equals(""))inttwentysix = Integer.parseInt(twentysix);
					if(!twentyseven.equals(""))inttwentyseven = Integer.parseInt(twentyseven);
					if(!twentyeight.equals(""))inttwentyeight = Integer.parseInt(twentyeight);
					if(!twentynine.equals(""))inttwentynine = Integer.parseInt(twentynine);
					if(!thirty.equals(""))intthirty = Integer.parseInt(thirty);
					if(!thirtyone.equals(""))intthirtyone = Integer.parseInt(thirtyone);
					if(!thirtytwo.equals(""))intthirtytwo = Integer.parseInt(thirtytwo);
					if(!thirtythree.equals(""))intthirtythree = Integer.parseInt(thirtythree);
					if(!thirtyfour.equals(""))intthirtyfour = Integer.parseInt(thirtyfour);
					if(!thirtyfive.equals(""))intthirtyfive = Integer.parseInt(thirtyfive);
					if(!thirtysix.equals(""))intthirtysix = Integer.parseInt(thirtysix);
					if(!thirtyseven.equals(""))intthirtyseven = Integer.parseInt(thirtyseven);
					if(!thirtyeight.equals(""))intthirtyeight = Integer.parseInt(thirtyeight);
					if(!thirtynine.equals(""))intthirtynine = Integer.parseInt(thirtynine);
					if(!forty.equals(""))intforty = Integer.parseInt(forty);
					if(!fortyone.equals(""))intfortyone = Integer.parseInt(fortyone);
					if(!fortytwo.equals(""))intfortytwo = Integer.parseInt(fortytwo);
					
					
					
					try {
					// // searching if the student exists
					String studentSearchSql = "SELECT * FROM `attendance` WHERE "
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
								+ "'"+studentId+"',"
								+ "'"+studentName+"',"
								+ "'"+parentCourseCode+"',"
								+ "'"+parentCourseName+"',"
								+ "'"+parentSection+"',"
								+ "'"+parentBatch+"',"
								+ "'"+parentDepartment+"',"
								+ "'"+parentSemester+"',"
								+ "'"+loginUserName+"'"
								+ ""+intone+","
								+ ""+inttwo+","
								+ ""+intthree+","
								+ ""+intfour+","
								+ ""+intfive+","
								+ ""+intsix+","
								+ ""+intseven+","
								+ ""+inteight+","
								+ ""+intnine+","
								+ ""+intten+","
								+ ""+inteleven+","
								+ ""+inttwelve+","
								+ ""+intthirteen+","
								+ ""+intfourteen+","
								+ ""+intfifteen+","
								+ ""+intsixteen+","
								+ ""+intseventeen+","
								+ ""+inteighteen+","
								+ ""+intnineteen+","
								+ ""+inttwenty+","
								+ ""+inttwentyone+","
								+ ""+inttwentytwo+","
								+ ""+inttwentythree+","
								+ ""+inttwentyfour+","
								+ ""+inttwentyfive+","
								+ ""+inttwentysix+","
								+ ""+inttwentyseven+","
								+ ""+inttwentyeight+","
								+ ""+inttwentynine+","
								+ ""+intthirty+","
								+ ""+intthirtyone+","
								+ ""+intthirtytwo+","
								+ ""+intthirtythree+","
								+ ""+intthirtyfour+","
								+ ""+intthirtyfive+","
								+ ""+intthirtysix+","
								+ ""+intthirtyseven+","
								+ ""+intthirtyeight+","
								+ ""+intthirtynine+","
								+ ""+intforty+","
								+ ""+intfortyone+","
								+ ""+intfortytwo+")";
						System.out.println("check insert");
						st.executeUpdate(insertSql);
						
					}
					else {
						
						String sql = "UPDATE `attendance` SET "
								+ "`1`="+intone+","
								+ "`2`="+inttwo+","
								+ "`3`="+intthree+","
								+ "`4`="+intfour+","
								+ "`5`="+intfive+","
								+ "`6`="+intsix+","
								+ "`7`="+intseven+","
								+ "`8`="+inteight+","
								+ "`9`="+intnine+","
								+ "`10`="+intten+","
								+ "`11`="+inteleven+","
								+ "`12`="+inttwelve+","
								+ "`13`="+intthirteen+","
								+ "`14`="+intfourteen+","
								+ "`15`="+intfifteen+","
								+ "`16`="+intsixteen+","
								+ "`17`="+intseventeen+","
								+ "`18`="+inteighteen+","
								+ "`19`="+intnineteen+","
								+ "`20`="+inttwenty+","
								+ "`21`="+inttwentyone+","
								+ "`22`="+inttwentytwo+","
								+ "`23`="+inttwentythree+","
								+ "`24`="+inttwentyfour+","
								+ "`25`="+inttwentyfive+","
								+ "`26`="+inttwentysix+","
								+ "`27`="+inttwentyseven+","
								+ "`28`="+inttwentyeight+","
								+ "`29`="+inttwentynine+","
								+ "`30`="+intthirty+","
								+ "`31`="+intthirtyone+","
								+ "`32`="+intthirtytwo+","
								+ "`33`="+intthirtythree+","
								+ "`34`="+intthirtyfour+","
								+ "`35`="+intthirtyfive+","
								+ "`36`="+intthirtysix+","
								+ "`37`="+intthirtyseven+","
								+ "`38`="+intthirtyeight+","
								+ "`39`="+intthirtynine+","
								+ "`40`="+forty+","
								+ "`41`="+fortyone+","
								+ "`42`="+fortytwo+" WHERE "
								+ "lower(trim(student_id))=lower(trim('"+studentId+"')) and "
								+ "lower(trim(student_name))=lower(trim('"+studentName+"')) and "
								+ "lower(trim(section))=lower(trim('"+parentSection+"')) and "
								+ "lower(trim(batch))=lower(trim('"+parentBatch+"')) and "
								+ "lower(trim(department))=lower(trim('"+parentDepartment+"')) and "
								+ "lower(trim(course_code))=lower(trim('"+parentCourseCode+"')) and "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+parentSemester+"'))";
						
						System.out.println("check update");
						
						st.executeUpdate(sql);
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
