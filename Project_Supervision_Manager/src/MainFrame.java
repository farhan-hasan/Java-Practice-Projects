import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
	Color red = new Color(242, 72, 34);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JTabbedPane mainPane;
	JPanel myProjectsPanel, newProjectPanel;
	
	JButton myProjectsSignOutButton;
	
	// My Projects variables
	
	JButton myProjectsSearchButton, myProjectsDeleteAllButton, myProjectsExpandButton, myProjectsNewSessionButton;
	JButton myProjectsSessionHistoryButton, myProjectsEditTeamButton, myProjectsDeleteTeamButton;
	
	JLabel myProjectsSemesterLabel;
	
	JTextField myProjectsSemesterTextField;
	
	JTable myProjectsCourseTable;
	JScrollPane myProjectsCourseTableScrollPane;
	Object myProjectsCourseTableData[][] = {};
	String myProjectsCourseTableColumns[] = {"Course Code","Course Name"};
	DefaultTableModel myProjectsCourseTableModel = new DefaultTableModel(myProjectsCourseTableData,myProjectsCourseTableColumns);
	
	JTable myProjectsTeamTable;
	JScrollPane myProjectsTeamTableScrollPane;
	Object myProjectsTeamTableData[][] = {};
	String myProjectsTeamTableColumns[] = {"Project Name","Team Name","Course Code","Course Name"};
	DefaultTableModel myProjectsTeamTableModel = new DefaultTableModel(myProjectsTeamTableData,myProjectsTeamTableColumns);
	
	// New Project variables
	
	JButton newProjectAddButton, newProjectUpdateButton, newProjectDeleteButton, newProjectSaveButton;
	
	JLabel newProjectProjectNameLabel, newProjectTeamNameLabel, newProjectCourseCodeLabel, newProjectCourseNameLabel;
	JLabel newProjectSemesterLabel, newProjectStudentIdLabel, newProjectStudentNameLabel;
	
	JTextField newProjectProjectNameTextField, newProjectTeamNameTextField, newProjectCourseCodeTextField;
	JTextField newProjectCourseNameTextField, newProjectSemesterTextField, newProjectStudentIdTextField, newProjectStudentNameTextField;
	
	JTable newProjectStudentTable;
	JScrollPane newProjectStudentTableScrollPane;
	Object newProjectStudentTableData[][] = {};
	String newProjectStudentTableColumns[] = {"Student ID","Student Name"};
	DefaultTableModel newProjectStudentTableModel = new DefaultTableModel(newProjectStudentTableData,newProjectStudentTableColumns);
	
	String prevTeamName = "", prevProjectName = "", prevCourseName = "", prevCourseCode = "";
	
	Connection con;
	Statement st;
	
	String loginUserName;
	
	public MainFrame(String username) {
		
		prevTeamName = "";
		prevProjectName = "";
		prevCourseName = "";
		prevCourseCode = "";
		
		loginUserName = username;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getContentPane().setBackground(lightColor);
		setSize(1024,768);
		setDefaultCloseOperation(3);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Project Supervision Manager");
		
		UIDefaults defaults = UIManager.getDefaults();
        defaults.put("TabbedPane.borderHightlightColor", new Color(0, 0, 0, 0));
        defaults.put("TabbedPane.darkShadow", new Color(0, 0, 0, 0));
		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = -1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);
 		UIManager.put("TabbedPane.selected", darkColor);
		
		mainPane = new JTabbedPane();
		mainPane.setBackground(lightColor); // tabs color
		mainPane.setBounds(0, 0, 1024, 768);
		mainPane.setForeground(Color.black);
		mainPane.setFocusable(false);
		add(mainPane);
		
		
		/// My Projects Tab
		
		myProjectsPanel = new JPanel();
		myProjectsPanel.setLayout(null);
		myProjectsPanel.setBackground(lightColor);
		
		
		myProjectsSemesterLabel = new JLabel("Semester [eg: Spring-2024]");
		myProjectsSemesterLabel.setFont(labelFont);
		myProjectsSemesterLabel.setBounds(20,20,280,70);
		myProjectsSemesterLabel.setForeground(Color.black);
		myProjectsPanel.add(myProjectsSemesterLabel);
		
		myProjectsSemesterTextField = new JTextField();
		myProjectsSemesterTextField.setFont(textFieldFont);
		myProjectsSemesterTextField.setBounds(20,70,180,25);
		myProjectsSemesterTextField.setBackground(Color.white);
		myProjectsPanel.add(myProjectsSemesterTextField);
		
		myProjectsSearchButton = new JButton("Search");
		myProjectsSearchButton.setFont(buttonFont);
		myProjectsSearchButton.setBounds(210,70,90,25);
		myProjectsSearchButton.setForeground(Color.white);
		myProjectsSearchButton.setFocusable(false);
		myProjectsSearchButton.setBackground(darkColor);
		myProjectsSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String semester = myProjectsSemesterTextField.getText();

				
				try {
					String searchSQL = "SELECT DISTINCT `course_code`, `course_name` FROM `projects` WHERE lower(trim(semester)) = lower(trim('"+semester+"')) and lower(trim(username)) = lower(trim('"+loginUserName+"'))";
					
					ResultSet rs = st.executeQuery(searchSQL);
					
					while(rs.next()) {
						String courseCode = rs.getString(1);
						String courseName = rs.getString(2);
						Object newRow[] = {courseCode, courseName};
						myProjectsCourseTableModel.addRow(newRow);
					}
					
					//myProjectsCourseTableModel.addRow(newRow);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		myProjectsPanel.add(myProjectsSearchButton);
		
		myProjectsSignOutButton = new JButton("Sign Out");
		myProjectsSignOutButton.setFont(buttonFont);
		myProjectsSignOutButton.setBounds(800,20,180,25);
		myProjectsSignOutButton.setForeground(Color.white);
		myProjectsSignOutButton.setFocusable(false);
		myProjectsSignOutButton.setBackground(red);
		myProjectsSignOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Sign Out?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
					new LoginFrame();
				} else {
				    return;
				}	
			}
		});
		myProjectsPanel.add(myProjectsSignOutButton);
		
		myProjectsDeleteAllButton = new JButton("Delete All");
		myProjectsDeleteAllButton.setFont(buttonFont);
		myProjectsDeleteAllButton.setBounds(800,70,180,25);
		myProjectsDeleteAllButton.setForeground(Color.white);
		myProjectsDeleteAllButton.setFocusable(false);
		myProjectsDeleteAllButton.setBackground(red);
		myProjectsPanel.add(myProjectsDeleteAllButton);
		
		myProjectsCourseTable = new JTable(myProjectsCourseTableModel);
		myProjectsCourseTable.setOpaque(true);
		myProjectsCourseTable.setFillsViewportHeight(true);
		myProjectsCourseTable.setBackground(Color.white);
		myProjectsCourseTableScrollPane = new JScrollPane(myProjectsCourseTable);
		myProjectsCourseTableScrollPane.setBounds(20, 110, 962, 220);
		myProjectsCourseTable.getTableHeader().setBackground(darkColor);
		myProjectsCourseTable.getTableHeader().setForeground(Color.white);
		myProjectsCourseTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		myProjectsCourseTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = myProjectsCourseTable.getSelectedRow();
				
				
			}
		});
		myProjectsPanel.add(myProjectsCourseTableScrollPane);
		
		myProjectsExpandButton = new JButton("Expand");
		myProjectsExpandButton.setFont(buttonFont);
		myProjectsExpandButton.setBounds(20,345,90,25);
		myProjectsExpandButton.setForeground(Color.white);
		myProjectsExpandButton.setFocusable(false);
		myProjectsExpandButton.setBackground(darkColor);
		myProjectsExpandButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = myProjectsCourseTable.getSelectedRow();
				
				int teamLen = myProjectsTeamTable.getRowCount();
				
				if (teamLen > 0) {
				    for (int i = teamLen - 1; i > -1; i--) {
				    	myProjectsTeamTableModel.removeRow(i);
				    }
				}
				
				String courseCode = myProjectsCourseTableModel.getValueAt(idx, 0).toString();
				String courseName = myProjectsCourseTableModel.getValueAt(idx, 1).toString();
				
				try {
					String searchSQL = "SELECT * FROM `projects` WHERE "
							+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
							+ "lower(trim(course_name)) = lower(trim('"+courseName+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"'))";
					
					ResultSet rs = st.executeQuery(searchSQL);
					
					while(rs.next()) {
						String projectName = rs.getString(3);
						String teamName = rs.getString(4);
						Object newRow[] = {projectName, teamName, courseCode, courseName};
						myProjectsTeamTableModel.addRow(newRow);
					}
				} catch (SQLException e2) {
						e2.printStackTrace();
				}
			}
		});
		myProjectsPanel.add(myProjectsExpandButton);
		
		myProjectsTeamTable = new JTable(myProjectsTeamTableModel);
		myProjectsTeamTable.setOpaque(true);
		myProjectsTeamTable.setFillsViewportHeight(true);
		myProjectsTeamTable.setBackground(Color.white);
		myProjectsTeamTableScrollPane = new JScrollPane(myProjectsTeamTable);
		myProjectsTeamTableScrollPane.setBounds(20, 385, 962, 220);
		myProjectsTeamTable.getTableHeader().setBackground(darkColor);
		myProjectsTeamTable.getTableHeader().setForeground(Color.white);
		myProjectsTeamTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		myProjectsTeamTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = myProjectsTeamTable.getSelectedRow();
				
				
			}
		});
		myProjectsPanel.add(myProjectsTeamTableScrollPane);
		
		
		myProjectsNewSessionButton = new JButton("New Session");
		myProjectsNewSessionButton.setFont(buttonFont);
		myProjectsNewSessionButton.setBounds(20,640,180,25);
		myProjectsNewSessionButton.setForeground(Color.white);
		myProjectsNewSessionButton.setFocusable(false);
		myProjectsNewSessionButton.setBackground(darkColor);
		myProjectsNewSessionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewSessionFrame();
			}
		});
		myProjectsPanel.add(myProjectsNewSessionButton);
		
		myProjectsSessionHistoryButton = new JButton("Session History");
		myProjectsSessionHistoryButton.setFont(buttonFont);
		myProjectsSessionHistoryButton.setBounds(220,640,180,25);
		myProjectsSessionHistoryButton.setForeground(Color.white);
		myProjectsSessionHistoryButton.setFocusable(false);
		myProjectsSessionHistoryButton.setBackground(darkColor);
		myProjectsSessionHistoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SessionHistoryFrame();
				
			}
		});
		myProjectsPanel.add(myProjectsSessionHistoryButton);
		
		myProjectsEditTeamButton = new JButton("Edit Team");
		myProjectsEditTeamButton.setFont(buttonFont);
		myProjectsEditTeamButton.setBounds(420,640,180,25);
		myProjectsEditTeamButton.setForeground(Color.white);
		myProjectsEditTeamButton.setFocusable(false);
		myProjectsEditTeamButton.setBackground(darkColor);
		myProjectsEditTeamButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditTeamFrame();
				
			}
		});
		myProjectsPanel.add(myProjectsEditTeamButton);
		
		myProjectsDeleteTeamButton = new JButton("Delete Team");
		myProjectsDeleteTeamButton.setFont(buttonFont);
		myProjectsDeleteTeamButton.setBounds(800,640,180,25);
		myProjectsDeleteTeamButton.setForeground(Color.white);
		myProjectsDeleteTeamButton.setFocusable(false);
		myProjectsDeleteTeamButton.setBackground(red);
		myProjectsPanel.add(myProjectsDeleteTeamButton);
		
		
		
		
		
		mainPane.add("My Projects",myProjectsPanel);
		
		/// New Projects Tab
		
		newProjectPanel = new JPanel();
		newProjectPanel.setLayout(null);
		newProjectPanel.setBackground(lightColor);
		
		
		newProjectProjectNameLabel = new JLabel("Project Name");
		newProjectProjectNameLabel.setFont(labelFont);
		newProjectProjectNameLabel.setBounds(20,20,280,70);
		newProjectProjectNameLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectProjectNameLabel);
		
		newProjectProjectNameTextField = new JTextField();
		newProjectProjectNameTextField.setFont(textFieldFont);
		newProjectProjectNameTextField.setBounds(20,70,180,25);
		newProjectProjectNameTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectProjectNameTextField);
		
		newProjectTeamNameLabel = new JLabel("Team Name");
		newProjectTeamNameLabel.setFont(labelFont);
		newProjectTeamNameLabel.setBounds(215,20,280,70);
		newProjectTeamNameLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectTeamNameLabel);
		
		newProjectTeamNameTextField = new JTextField();
		newProjectTeamNameTextField.setFont(textFieldFont);
		newProjectTeamNameTextField.setBounds(215,70,180,25);
		newProjectTeamNameTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectTeamNameTextField);
		
		newProjectCourseCodeLabel = new JLabel("Course Code");
		newProjectCourseCodeLabel.setFont(labelFont);
		newProjectCourseCodeLabel.setBounds(410,20,280,70);
		newProjectCourseCodeLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectCourseCodeLabel);
		
		newProjectCourseCodeTextField = new JTextField();
		newProjectCourseCodeTextField.setFont(textFieldFont);
		newProjectCourseCodeTextField.setBounds(410,70,180,25);
		newProjectCourseCodeTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectCourseCodeTextField);
		
		newProjectCourseNameLabel = new JLabel("Course Name");
		newProjectCourseNameLabel.setFont(labelFont);
		newProjectCourseNameLabel.setBounds(605,20,280,70);
		newProjectCourseNameLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectCourseNameLabel);
		
		newProjectCourseNameTextField = new JTextField();
		newProjectCourseNameTextField.setFont(textFieldFont);
		newProjectCourseNameTextField.setBounds(605,70,180,25);
		newProjectCourseNameTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectCourseNameTextField);
		
		newProjectSemesterLabel = new JLabel("Semester");
		newProjectSemesterLabel.setFont(labelFont);
		newProjectSemesterLabel.setBounds(800,20,280,70);
		newProjectSemesterLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectSemesterLabel);
		
		newProjectSemesterTextField = new JTextField();
		newProjectSemesterTextField.setFont(textFieldFont);
		newProjectSemesterTextField.setBounds(800,70,180,25);
		newProjectSemesterTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectSemesterTextField);
		
		newProjectStudentTable = new JTable(newProjectStudentTableModel);
		newProjectStudentTable.setOpaque(true);
		newProjectStudentTable.setFillsViewportHeight(true);
		newProjectStudentTable.setBackground(Color.white);
		newProjectStudentTableScrollPane = new JScrollPane(newProjectStudentTable);
		newProjectStudentTableScrollPane.setBounds(20, 110, 962, 220);
		newProjectStudentTable.getTableHeader().setBackground(darkColor);
		newProjectStudentTable.getTableHeader().setForeground(Color.white);
		newProjectStudentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		newProjectStudentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = newProjectStudentTable.getSelectedRow();
				
				String studentId = newProjectStudentTableModel.getValueAt(idx, 0).toString();
				String studentName = newProjectStudentTableModel.getValueAt(idx, 1).toString();
				
				newProjectStudentIdTextField.setText(studentId);
				newProjectStudentNameTextField.setText(studentName);
				
			}
		});
		newProjectPanel.add(newProjectStudentTableScrollPane);
		
		newProjectStudentIdLabel = new JLabel("Student ID");
		newProjectStudentIdLabel.setFont(labelFont);
		newProjectStudentIdLabel.setBounds(20,330,280,70);
		newProjectStudentIdLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectStudentIdLabel);
		
		newProjectStudentIdTextField = new JTextField();
		newProjectStudentIdTextField.setFont(textFieldFont);
		newProjectStudentIdTextField.setBounds(20,380,180,25);
		newProjectStudentIdTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectStudentIdTextField);
		
		newProjectStudentNameLabel = new JLabel("Student Name");
		newProjectStudentNameLabel.setFont(labelFont);
		newProjectStudentNameLabel.setBounds(215,330,280,70);
		newProjectStudentNameLabel.setForeground(Color.black);
		newProjectPanel.add(newProjectStudentNameLabel);
		
		newProjectStudentNameTextField = new JTextField();
		newProjectStudentNameTextField.setFont(textFieldFont);
		newProjectStudentNameTextField.setBounds(215,380,180,25);
		newProjectStudentNameTextField.setBackground(Color.white);
		newProjectPanel.add(newProjectStudentNameTextField);
		
		newProjectAddButton = new JButton("Add");
		newProjectAddButton.setFont(buttonFont);
		newProjectAddButton.setBounds(410,380,180,25);
		newProjectAddButton.setForeground(Color.white);
		newProjectAddButton.setFocusable(false);
		newProjectAddButton.setBackground(darkColor);
		newProjectAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = newProjectStudentIdTextField.getText();
				String studentName = newProjectStudentNameTextField.getText();
				Object newRow[] = {studentId, studentName};
				newProjectStudentTableModel.addRow(newRow);
				
			}
		});
		newProjectPanel.add(newProjectAddButton);
		
		newProjectUpdateButton = new JButton("Update");
		newProjectUpdateButton.setFont(buttonFont);
		newProjectUpdateButton.setBounds(605,380,180,25);
		newProjectUpdateButton.setForeground(Color.white);
		newProjectUpdateButton.setFocusable(false);
		newProjectUpdateButton.setBackground(darkColor);
		newProjectUpdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = newProjectStudentIdTextField.getText();
				String studentName = newProjectStudentNameTextField.getText();
				int idx = newProjectStudentTable.getSelectedRow();
				
				newProjectStudentTableModel.setValueAt(studentId, idx, 0);
				newProjectStudentTableModel.setValueAt(studentName, idx, 1);
				
			}
		});
		newProjectPanel.add(newProjectUpdateButton);
		
		newProjectDeleteButton = new JButton("Delete");
		newProjectDeleteButton.setFont(buttonFont);
		newProjectDeleteButton.setBounds(800,380,180,25);
		newProjectDeleteButton.setForeground(Color.white);
		newProjectDeleteButton.setFocusable(false);
		newProjectDeleteButton.setBackground(darkColor);
		newProjectDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = newProjectStudentTable.getSelectedRow();
				
				newProjectStudentTableModel.removeRow(idx);
				
			}
		});
		newProjectPanel.add(newProjectDeleteButton);
		
		newProjectSaveButton = new JButton("Save");
		newProjectSaveButton.setFont(buttonFont);
		newProjectSaveButton.setBounds(20,420,180,25);
		newProjectSaveButton.setForeground(Color.white);
		newProjectSaveButton.setFocusable(false);
		newProjectSaveButton.setBackground(darkColor);
		newProjectSaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int len = newProjectStudentTable.getRowCount();
				
				if(len==0) {
					JOptionPane.showMessageDialog(null, "No students found");
					return;
				}
				
				String projectName = newProjectProjectNameTextField.getText();
				String teamName = newProjectTeamNameTextField.getText();
				String courseCode = newProjectCourseCodeTextField.getText();
				String courseName = newProjectCourseNameTextField.getText();
				String semester = newProjectSemesterTextField.getText();
				
				
				if(projectName.equals("") || teamName.equals("") || courseCode.equals("") || courseName.equals("") || semester.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter data in the above fields");
					return;
				}
				
				try {
					
					// // Course Code and Name check
					String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) and lower(trim(course_name)) = lower(trim('"+courseName+"'))";
					ResultSet rs2 = st.executeQuery(searchCourseSql);
					
					int cnt2 = 0;
					String existingCourseCode = "", existingCourseName = "";
					while(rs2.next()) {
						existingCourseCode = rs2.getString(1);
						existingCourseName = rs2.getString(2);
						cnt2++;
					}
					
					if (!courseName.equals("") && !courseCode.equals("")) {
						if(!courseName.equals(existingCourseName) && courseCode.equals(existingCourseCode)) {
							JOptionPane.showMessageDialog(null, "Incorrect Course code or Course name");
							return;
						}
						if(courseName.equals(existingCourseName) && !courseCode.equals(existingCourseCode)) {
							JOptionPane.showMessageDialog(null, "Incorrect Course code or Course name");
							return;
						}
					}
					
					
					// // Checking if team and project already exists
					if(!prevTeamName.equals(teamName) || !prevProjectName.equals(projectName) || !prevCourseCode.equals(courseCode)) {
						String searchSql1 = "SELECT `course_code`, `project_name`, `team_name` FROM `projects` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) and lower(trim(team_name)) = lower(trim('"+teamName+"'))";
						String searchSql3 = "SELECT `course_code`, `project_name`, `team_name` FROM `projects` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) and lower(trim(project_name)) = lower(trim('"+projectName+"'))";
//						
						
						int cnt1 = 0, cnt3 = 0;
						ResultSet rs1 = st.executeQuery(searchSql1);
						while(rs1.next())cnt1++;
						ResultSet rs3 = st.executeQuery(searchSql3);
						while(rs3.next())cnt3++;
						
			
						if(cnt1!=0) {
							JOptionPane.showMessageDialog(null, "Team already exits");
							return;
						}
						
						if(cnt3!=0) {
							JOptionPane.showMessageDialog(null, "Project already exits");
							return;
						}
					}
					
					prevProjectName = projectName;
					prevTeamName = teamName;
					prevCourseCode = courseCode;
					
					String insertCourseSql = "INSERT INTO `course`(`course_code`, `course_name`) VALUES ('"+courseCode+"','"+courseName+"')";
					if(cnt2==0)st.executeUpdate(insertCourseSql);
					
					String deleteFromTeamMembersSql = "DELETE FROM `team_members` where team_name='"+teamName+"' and course_code='"+courseCode+"'";
					st.executeUpdate(deleteFromTeamMembersSql);
					
					String deleteFromProjectSql = "DELETE FROM `projects` where team_name='"+teamName+"' and course_code='"+courseCode+"'";
					st.executeUpdate(deleteFromProjectSql);
					
					String insertProjectSql = "INSERT INTO `projects`(`course_code`, `course_name`, `project_name`, `team_name`, `semester`, `username`) VALUES ('"+courseCode+"','"+courseName+"','"+projectName+"','"+teamName+"','"+semester+"','"+loginUserName+"')";
					st.executeUpdate(insertProjectSql);
					
					for(int i=0;i<len;i++) {
						String studentId = newProjectStudentTableModel.getValueAt(i, 0).toString();
						String studentName = newProjectStudentTableModel.getValueAt(i, 1).toString();
						String insertMembersSql = "INSERT INTO `team_members`(`team_name`, `course_code`, `student_id`, `student_name`, `username`) VALUES ('"+teamName+"','"+courseCode+"','"+studentId+"','"+studentName+"','"+loginUserName+"')";
						st.executeUpdate(insertMembersSql);
						
						System.out.println(studentId);
						System.out.println(studentName);
					}
					System.out.println(len);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		newProjectPanel.add(newProjectSaveButton);
		
		
		
		
		mainPane.add("New Project",newProjectPanel);
		
		
		setVisible(true);
	}

}
