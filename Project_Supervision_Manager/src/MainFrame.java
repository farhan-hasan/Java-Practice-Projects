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
	JPanel myCoursesPanel, newCoursePanel, myProjectsPanel, newProjectPanel;
	
	JButton myProjectsSignOutButton, myCoursesSignOutButton;
	
	// My Courses variables
	
	String myCoursesTeamObj[] = {"","","","","","",""};
	
	JButton myCoursesSearchButton, myCoursesDeleteAllButton, myCoursesExpandButton, myCoursesMarkSheetButton;
	JButton myCoursesAttendanceSheetButton, myCoursesEditSectionButton, myCoursesDeleteSectionButton;
	
	JLabel myCoursesSemesterLabel;
	
	JTextField myCoursesSemesterTextField;
	
	JTable myCoursesCourseTable;
	JScrollPane myCoursesCourseTableScrollPane;
	Object myCoursesCourseTableData[][] = {};
	String myCoursesCourseTableColumns[] = {"Course Code","Course Name"};
	DefaultTableModel myCoursesCourseTableModel = new DefaultTableModel(myCoursesCourseTableData,myCoursesCourseTableColumns);
	
	JTable myCoursesDetailsTable;
	JScrollPane myCoursesDetailsTableScrollPane;
	Object myCoursesDetailsTableData[][] = {};
	String myCoursesDetailsTableColumns[] = {"Course Code","Course Name","Section","Batch","Department"};
	DefaultTableModel myCoursesDetailsTableModel = new DefaultTableModel(myCoursesDetailsTableData,myCoursesDetailsTableColumns);
	
	String myCoursesExpandedSemester = "";
	
	// New Course variables
	
	JButton newCourseAddButton, newCourseUpdateButton, newCourseDeleteButton, newCourseSaveButton;
	
	JLabel newCourseCourseCodeLabel, newCourseCourseNameLabel, newCourseSectionLabel, newCourseDepartmentLabel;
	JLabel newCourseSemesterLabel, newCourseBatchLabel, newCourseStudentIdLabel, newCourseStudentNameLabel;
	
	JTextField newCourseCourseCodeTextField, newCourseCourseNameTextField, newCourseSectionTextField;
	JTextField newCourseDepartmentTextField, newCourseBatchTextField, newCourseSemesterTextField, newCourseStudentIdTextField, newCourseStudentNameTextField;
	
	JTable newCourseStudentTable;
	JScrollPane newCourseStudentTableScrollPane;
	Object newCourseStudentTableData[][] = {};
	String newCourseStudentTableColumns[] = {"Student ID","Student Name"};
	DefaultTableModel newCourseStudentTableModel = new DefaultTableModel(newCourseStudentTableData,newCourseStudentTableColumns);

	// My Projects variables
	
	String editTeamObj[] = {"","","","","",""};
	
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
	String expandedSemester = "";
	
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
		
		// // My Courses Tab
		
		myCoursesTeamObj[6] = username;
		
		myCoursesPanel = new JPanel();
		myCoursesPanel.setLayout(null);
		myCoursesPanel.setBackground(lightColor);
		
		
		myCoursesSemesterLabel = new JLabel("Semester [eg: Spring-2024]");
		myCoursesSemesterLabel.setFont(labelFont);
		myCoursesSemesterLabel.setBounds(20,20,280,70);
		myCoursesSemesterLabel.setForeground(Color.black);
		myCoursesPanel.add(myCoursesSemesterLabel);
		
		myCoursesSemesterTextField = new JTextField();
		myCoursesSemesterTextField.setFont(textFieldFont);
		myCoursesSemesterTextField.setBounds(20,70,180,25);
		myCoursesSemesterTextField.setBackground(Color.white);
		myCoursesPanel.add(myCoursesSemesterTextField);
		
		myCoursesSearchButton = new JButton("Search");
		myCoursesSearchButton.setFont(buttonFont);
		myCoursesSearchButton.setBounds(210,70,90,25);
		myCoursesSearchButton.setForeground(Color.white);
		myCoursesSearchButton.setFocusable(false);
		myCoursesSearchButton.setBackground(darkColor);
		myCoursesSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String semester = "";
				
				semester = myCoursesSemesterTextField.getText();
				
				if(semester.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter semester");
					return;
				}
				
				myCoursesTeamObj[5] = semester;
				
				int	courseLen = myCoursesCourseTable.getRowCount();
				
				if (courseLen > 0) {
				    for (int i = courseLen - 1; i > -1; i--) {
				    	myCoursesCourseTableModel.removeRow(i);
				    }
				}

				
				try {
					String searchSQL = "SELECT DISTINCT `course_code`, `course_name` FROM `course_taken` WHERE lower(trim(semester)) = lower(trim('"+semester+"')) and lower(trim(username)) = lower(trim('"+loginUserName+"'))";
					
					ResultSet rs = st.executeQuery(searchSQL);
					
					while(rs.next()) {
						String courseCode = rs.getString(1);
						String courseName = rs.getString(2);
						Object newRow[] = {courseCode, courseName};
						myCoursesCourseTableModel.addRow(newRow);
					}
					
					//myCoursesCourseTableModel.addRow(newRow);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		myCoursesPanel.add(myCoursesSearchButton);
		
		myCoursesSignOutButton = new JButton("Sign Out");
		myCoursesSignOutButton.setFont(buttonFont);
		myCoursesSignOutButton.setBounds(800,20,180,25);
		myCoursesSignOutButton.setForeground(Color.white);
		myCoursesSignOutButton.setFocusable(false);
		myCoursesSignOutButton.setBackground(red);
		myCoursesSignOutButton.addActionListener(new ActionListener() {
			
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
		myCoursesPanel.add(myCoursesSignOutButton);
		
		myCoursesDeleteAllButton = new JButton("Delete All");
		myCoursesDeleteAllButton.setFont(buttonFont);
		myCoursesDeleteAllButton.setBounds(800,70,180,25);
		myCoursesDeleteAllButton.setForeground(Color.white);
		myCoursesDeleteAllButton.setFocusable(false);
		myCoursesDeleteAllButton.setBackground(red);
		myCoursesDeleteAllButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String semester = "";
				
				semester = myCoursesSemesterTextField.getText();
				
				if(semester.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a semester");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all data of this semester?", "Delete all?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						String deleteFromProjectSql = "DELETE FROM `course_taken` where "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
						st.executeUpdate(deleteFromProjectSql);
						
						String deleteFromTeamMembersSql = "DELETE FROM `student_list` where "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
						st.executeUpdate(deleteFromTeamMembersSql);
						
						
						int courseLen = myCoursesCourseTable.getRowCount();
						if (courseLen > 0) {
						    for (int i = courseLen - 1; i > -1; i--) {
						    	myCoursesCourseTableModel.removeRow(i);
						    }
						}
						
						int teamLen = myCoursesDetailsTable.getRowCount();
						if (teamLen > 0) {
						    for (int i = teamLen - 1; i > -1; i--) {
						    	myCoursesDetailsTableModel.removeRow(i);
						    }
						}
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
				    return;
				}	
				
			}
		});
		myCoursesPanel.add(myCoursesDeleteAllButton);
		
		myCoursesCourseTable = new JTable(myCoursesCourseTableModel);
		myCoursesCourseTable.setOpaque(true);
		myCoursesCourseTable.setFillsViewportHeight(true);
		myCoursesCourseTable.setBackground(Color.white);
		myCoursesCourseTableScrollPane = new JScrollPane(myCoursesCourseTable);
		myCoursesCourseTableScrollPane.setBounds(20, 110, 962, 220);
		myCoursesCourseTable.getTableHeader().setBackground(darkColor);
		myCoursesCourseTable.getTableHeader().setForeground(Color.white);
		myCoursesCourseTable.getTableHeader().setFont(labelFont);
		//myCoursesCourseTable.add(myCoursesCourseTableScrollPane);
		myCoursesCourseTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = myCoursesCourseTable.getSelectedRow();
				
				
			}
		});
		myCoursesPanel.add(myCoursesCourseTableScrollPane);
		
		myCoursesExpandButton = new JButton("Expand");
		myCoursesExpandButton.setFont(buttonFont);
		myCoursesExpandButton.setBounds(20,345,90,25);
		myCoursesExpandButton.setForeground(Color.white);
		myCoursesExpandButton.setFocusable(false);
		myCoursesExpandButton.setBackground(darkColor);
		myCoursesExpandButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				
				idx = myCoursesCourseTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a course");
					return;
				}
				
				
				
				int teamLen = myCoursesDetailsTable.getRowCount();
				
				if (teamLen > 0) {
				    for (int i = teamLen - 1; i > -1; i--) {
				    	myCoursesDetailsTableModel.removeRow(i);
				    }
				}
				
				String courseCode = myCoursesCourseTableModel.getValueAt(idx, 0).toString();
				String courseName = myCoursesCourseTableModel.getValueAt(idx, 1).toString();
				myCoursesExpandedSemester = myCoursesSemesterTextField.getText() ;
				
				try {
					String searchSQL = "SELECT * FROM `course_taken` WHERE "
							+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
							+ "lower(trim(course_name)) = lower(trim('"+courseName+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+myCoursesExpandedSemester+"'))";
					
					ResultSet rs = st.executeQuery(searchSQL);
					
					while(rs.next()) {
						String coursecode = rs.getString(1);
						String coursename = rs.getString(2);
						String section = rs.getString(3);
						String batch = rs.getString(4);
						String department = rs.getString(5);
						Object newRow[] = {coursecode, coursename, section, batch, department};
						myCoursesDetailsTableModel.addRow(newRow);
					}
				} catch (SQLException e2) {
						e2.printStackTrace();
				}
			}
		});
		myCoursesPanel.add(myCoursesExpandButton);
		
		myCoursesDetailsTable = new JTable(myCoursesDetailsTableModel);
		myCoursesDetailsTable.setOpaque(true);
		myCoursesDetailsTable.setFillsViewportHeight(true);
		myCoursesDetailsTable.setBackground(Color.white);
		myCoursesDetailsTableScrollPane = new JScrollPane(myCoursesDetailsTable);
		myCoursesDetailsTableScrollPane.setBounds(20, 385, 962, 220);
		myCoursesDetailsTable.getTableHeader().setBackground(darkColor);
		myCoursesDetailsTable.getTableHeader().setForeground(Color.white);
		myCoursesDetailsTable.getTableHeader().setFont(labelFont);
		//myCoursesCourseTable.add(myCoursesCourseTableScrollPane);
		myCoursesDetailsTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = myCoursesDetailsTable.getSelectedRow();
				
				
			}
		});
		myCoursesPanel.add(myCoursesDetailsTableScrollPane);
		
		
		myCoursesMarkSheetButton = new JButton("Mark Sheet");
		myCoursesMarkSheetButton.setFont(buttonFont);
		myCoursesMarkSheetButton.setBounds(20,640,180,25);
		myCoursesMarkSheetButton.setForeground(Color.white);
		myCoursesMarkSheetButton.setFocusable(false);
		myCoursesMarkSheetButton.setBackground(darkColor);
		myCoursesMarkSheetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				idx = myCoursesDetailsTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				String courseCode = myCoursesDetailsTableModel.getValueAt(idx, 0).toString();
				String courseName = myCoursesDetailsTableModel.getValueAt(idx, 1).toString();
				String section = myCoursesDetailsTableModel.getValueAt(idx, 2).toString();
				String batch = myCoursesDetailsTableModel.getValueAt(idx, 3).toString();
				String department = myCoursesDetailsTableModel.getValueAt(idx, 4).toString();
				
				myCoursesTeamObj[0] = courseCode;
				myCoursesTeamObj[1] = courseName;
				myCoursesTeamObj[2] = section;
				myCoursesTeamObj[3] = batch;
				myCoursesTeamObj[4] = department;
				//myCoursesTeamObj[5] on searh button and myCoursesTeamObj[6] at the beginning
				
				new MarkSheet(myCoursesTeamObj);
			}
		});
		myCoursesPanel.add(myCoursesMarkSheetButton);
		
		myCoursesAttendanceSheetButton = new JButton("Attendance Sheet");
		myCoursesAttendanceSheetButton.setFont(buttonFont);
		myCoursesAttendanceSheetButton.setBounds(220,640,180,25);
		myCoursesAttendanceSheetButton.setForeground(Color.white);
		myCoursesAttendanceSheetButton.setFocusable(false);
		myCoursesAttendanceSheetButton.setBackground(darkColor);
		myCoursesAttendanceSheetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				idx = myCoursesDetailsTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				String courseCode = myCoursesDetailsTableModel.getValueAt(idx, 0).toString();
				String courseName = myCoursesDetailsTableModel.getValueAt(idx, 1).toString();
				String section = myCoursesDetailsTableModel.getValueAt(idx, 2).toString();
				String batch = myCoursesDetailsTableModel.getValueAt(idx, 3).toString();
				String department = myCoursesDetailsTableModel.getValueAt(idx, 4).toString();
				
				myCoursesTeamObj[0] = courseCode;
				myCoursesTeamObj[1] = courseName;
				myCoursesTeamObj[2] = section;
				myCoursesTeamObj[3] = batch;
				myCoursesTeamObj[4] = department;
				//myCoursesTeamObj[5] on searh button and myCoursesTeamObj[6] at the beginning
				
				new SessionHistoryFrame(myCoursesTeamObj);
				
			}
		});
		myCoursesPanel.add(myCoursesAttendanceSheetButton);
		
		myCoursesEditSectionButton = new JButton("Edit Section");
		myCoursesEditSectionButton.setFont(buttonFont);
		myCoursesEditSectionButton.setBounds(420,640,180,25);
		myCoursesEditSectionButton.setForeground(Color.white);
		myCoursesEditSectionButton.setFocusable(false);
		myCoursesEditSectionButton.setBackground(darkColor);
		myCoursesEditSectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int idx = -1;
				idx = myCoursesDetailsTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				String courseCode = myCoursesDetailsTableModel.getValueAt(idx, 0).toString();
				String courseName = myCoursesDetailsTableModel.getValueAt(idx, 1).toString();
				String section = myCoursesDetailsTableModel.getValueAt(idx, 2).toString();
				String batch = myCoursesDetailsTableModel.getValueAt(idx, 3).toString();
				String department = myCoursesDetailsTableModel.getValueAt(idx, 4).toString();
				
				myCoursesTeamObj[0] = courseCode;
				myCoursesTeamObj[1] = courseName;
				myCoursesTeamObj[2] = section;
				myCoursesTeamObj[3] = batch;
				myCoursesTeamObj[4] = department;
				//myCoursesTeamObj[5] on searh button and myCoursesTeamObj[6] at the beginning
				
				new EditSectionFrame(myCoursesTeamObj);
				
			}
		});
		myCoursesPanel.add(myCoursesEditSectionButton);
		
		myCoursesDeleteSectionButton = new JButton("Delete Section");
		myCoursesDeleteSectionButton.setFont(buttonFont);
		myCoursesDeleteSectionButton.setBounds(800,640,180,25);
		myCoursesDeleteSectionButton.setForeground(Color.white);
		myCoursesDeleteSectionButton.setFocusable(false);
		myCoursesDeleteSectionButton.setBackground(red);
		myCoursesDeleteSectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				
				
				idx = myCoursesDetailsTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the team?", "Delete Team?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}
				
				String projectName = myCoursesDetailsTableModel.getValueAt(idx, 0).toString();
				String teamName = myCoursesDetailsTableModel.getValueAt(idx, 1).toString();
				String courseCode = myCoursesDetailsTableModel.getValueAt(idx, 2).toString();
				String courseName = myCoursesDetailsTableModel.getValueAt(idx, 3).toString();
				
				
				try {
					String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+expandedSemester+"'))";
					st.executeUpdate(deleteFromTeamMembersSql);
					
					String deleteFromProjectSql = "DELETE FROM `projects` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+expandedSemester+"')) and "
							+ "lower(trim(project_name))=lower(trim('"+projectName+"'))";
					st.executeUpdate(deleteFromProjectSql);
					
					
					myCoursesDetailsTableModel.removeRow(myCoursesDetailsTable.getSelectedRow());
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		myCoursesPanel.add(myCoursesDeleteSectionButton);
		
		
		mainPane.add("My Courses",myCoursesPanel);
		
		
		/// New Course Tab
		
		newCoursePanel = new JPanel();
		newCoursePanel.setLayout(null);
		newCoursePanel.setBackground(lightColor);
		
		
		newCourseCourseCodeLabel = new JLabel("Course Code");
		newCourseCourseCodeLabel.setFont(labelFont);
		newCourseCourseCodeLabel.setBounds(20,20,280,70);
		newCourseCourseCodeLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseCourseCodeLabel);
		
		newCourseCourseCodeTextField = new JTextField();
		newCourseCourseCodeTextField.setFont(textFieldFont);
		newCourseCourseCodeTextField.setBounds(20,70,180,25);
		newCourseCourseCodeTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseCourseCodeTextField);
		
		newCourseCourseNameLabel = new JLabel("Course Name");
		newCourseCourseNameLabel.setFont(labelFont);
		newCourseCourseNameLabel.setBounds(215,20,280,70);
		newCourseCourseNameLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseCourseNameLabel);
		
		newCourseCourseNameTextField = new JTextField();
		newCourseCourseNameTextField.setFont(textFieldFont);
		newCourseCourseNameTextField.setBounds(215,70,180,25);
		newCourseCourseNameTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseCourseNameTextField);
		
		newCourseSectionLabel = new JLabel("Section");
		newCourseSectionLabel.setFont(labelFont);
		newCourseSectionLabel.setBounds(410,20,280,70);
		newCourseSectionLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseSectionLabel);
		
		newCourseSectionTextField = new JTextField();
		newCourseSectionTextField.setFont(textFieldFont);
		newCourseSectionTextField.setBounds(410,70,180,25);
		newCourseSectionTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseSectionTextField);
		
		newCourseBatchLabel = new JLabel("Batch");
		newCourseBatchLabel.setFont(labelFont);
		newCourseBatchLabel.setBounds(605,20,280,70);
		newCourseBatchLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseBatchLabel);
		
		newCourseBatchTextField = new JTextField();
		newCourseBatchTextField.setFont(textFieldFont);
		newCourseBatchTextField.setBounds(605,70,180,25);
		newCourseBatchTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseBatchTextField);
		
		newCourseDepartmentLabel = new JLabel("Department");
		newCourseDepartmentLabel.setFont(labelFont);
		newCourseDepartmentLabel.setBounds(800,20,280,70);
		newCourseDepartmentLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseDepartmentLabel);
		
		newCourseDepartmentTextField = new JTextField();
		newCourseDepartmentTextField.setFont(textFieldFont);
		newCourseDepartmentTextField.setBounds(800,70,180,25);
		newCourseDepartmentTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseDepartmentTextField);
		
		newCourseSemesterLabel = new JLabel("Semester");
		newCourseSemesterLabel.setFont(labelFont);
		newCourseSemesterLabel.setBounds(800,330,280,70);
		newCourseSemesterLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseSemesterLabel);
		
		newCourseSemesterTextField = new JTextField();
		newCourseSemesterTextField.setFont(textFieldFont);
		newCourseSemesterTextField.setBounds(800,380,180,25);
		newCourseSemesterTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseSemesterTextField);
		
		newCourseStudentTable = new JTable(newCourseStudentTableModel);
		newCourseStudentTable.setOpaque(true);
		newCourseStudentTable.setFillsViewportHeight(true);
		newCourseStudentTable.setBackground(Color.white);
		newCourseStudentTableScrollPane = new JScrollPane(newCourseStudentTable);
		newCourseStudentTableScrollPane.setBounds(20, 110, 962, 220);
		newCourseStudentTable.getTableHeader().setBackground(darkColor);
		newCourseStudentTable.getTableHeader().setForeground(Color.white);
		newCourseStudentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		newCourseStudentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = newCourseStudentTable.getSelectedRow();
				
				String studentId = newCourseStudentTableModel.getValueAt(idx, 0).toString();
				String studentName = newCourseStudentTableModel.getValueAt(idx, 1).toString();
				
				newCourseStudentIdTextField.setText(studentId);
				newCourseStudentNameTextField.setText(studentName);
				
			}
		});
		newCoursePanel.add(newCourseStudentTableScrollPane);
		
		newCourseStudentIdLabel = new JLabel("Student ID");
		newCourseStudentIdLabel.setFont(labelFont);
		newCourseStudentIdLabel.setBounds(20,330,280,70);
		newCourseStudentIdLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseStudentIdLabel);
		
		newCourseStudentIdTextField = new JTextField();
		newCourseStudentIdTextField.setFont(textFieldFont);
		newCourseStudentIdTextField.setBounds(20,380,180,25);
		newCourseStudentIdTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseStudentIdTextField);
		
		newCourseStudentNameLabel = new JLabel("Student Name");
		newCourseStudentNameLabel.setFont(labelFont);
		newCourseStudentNameLabel.setBounds(215,330,280,70);
		newCourseStudentNameLabel.setForeground(Color.black);
		newCoursePanel.add(newCourseStudentNameLabel);
		
		newCourseStudentNameTextField = new JTextField();
		newCourseStudentNameTextField.setFont(textFieldFont);
		newCourseStudentNameTextField.setBounds(215,380,180,25);
		newCourseStudentNameTextField.setBackground(Color.white);
		newCoursePanel.add(newCourseStudentNameTextField);
		
		newCourseAddButton = new JButton("Add");
		newCourseAddButton.setFont(buttonFont);
		newCourseAddButton.setBounds(20,420,180,25);
		newCourseAddButton.setForeground(Color.white);
		newCourseAddButton.setFocusable(false);
		newCourseAddButton.setBackground(darkColor);
		newCourseAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = newCourseStudentIdTextField.getText();
				String studentName = newCourseStudentNameTextField.getText();
				Object newRow[] = {studentId, studentName};
				newCourseStudentTableModel.addRow(newRow);
				
			}
		});
		newCoursePanel.add(newCourseAddButton);
		
		newCourseUpdateButton = new JButton("Update");
		newCourseUpdateButton.setFont(buttonFont);
		newCourseUpdateButton.setBounds(215,420,180,25);
		newCourseUpdateButton.setForeground(Color.white);
		newCourseUpdateButton.setFocusable(false);
		newCourseUpdateButton.setBackground(darkColor);
		newCourseUpdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentId = newCourseStudentIdTextField.getText();
				String studentName = newCourseStudentNameTextField.getText();
				int idx = newCourseStudentTable.getSelectedRow();
				
				newCourseStudentTableModel.setValueAt(studentId, idx, 0);
				newCourseStudentTableModel.setValueAt(studentName, idx, 1);
				
			}
		});
		newCoursePanel.add(newCourseUpdateButton);
		
		newCourseDeleteButton = new JButton("Delete");
		newCourseDeleteButton.setFont(buttonFont);
		newCourseDeleteButton.setBounds(410,420,180,25);
		newCourseDeleteButton.setForeground(Color.white);
		newCourseDeleteButton.setFocusable(false);
		newCourseDeleteButton.setBackground(darkColor);
		newCourseDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = newCourseStudentTable.getSelectedRow();
				
				newCourseStudentTableModel.removeRow(idx);
				
			}
		});
		newCoursePanel.add(newCourseDeleteButton);
		
		newCourseSaveButton = new JButton("Save");
		newCourseSaveButton.setFont(buttonFont);
		newCourseSaveButton.setBounds(605,420,180,25);
		newCourseSaveButton.setForeground(Color.white);
		newCourseSaveButton.setFocusable(false);
		newCourseSaveButton.setBackground(darkColor);
		newCourseSaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int len = newCourseStudentTable.getRowCount();
				
				if(len==0) {
					JOptionPane.showMessageDialog(null, "No students found");
					return;
				}
				
				String section = newCourseSectionTextField.getText();
				String batch = newCourseBatchTextField.getText();
				String department = newCourseDepartmentTextField.getText();
				String courseCode = newCourseCourseCodeTextField.getText();
				String courseName = newCourseCourseNameTextField.getText();
				String semester = newCourseSemesterTextField.getText();
				
				
				if(section.equals("") || batch.equals("") || courseCode.equals("") || courseName.equals("") || semester.equals("") || department.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter data in missing fields");
					return;
				}
				
				try {
					
					// // Course Code and Name check
					String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) or lower(trim(course_name)) = lower(trim('"+courseName+"'))";
					ResultSet rs2 = st.executeQuery(searchCourseSql);
					
					int cnt2 = 0;
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
						cnt2++;
					}
					
					
					
					
					String insertCourseSql = "INSERT INTO `course`(`course_code`, `course_name`) VALUES "
							+ "('"+courseCode+"','"+courseName+"')";
					if(cnt2==0)st.executeUpdate(insertCourseSql);
					
					String deleteFromStudentListSql = "DELETE FROM `student_list` where "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"'))";
					st.executeUpdate(deleteFromStudentListSql);
					
					String deleteFromCourseTakenSql = "DELETE FROM `course_taken` where "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"'))";
					st.executeUpdate(deleteFromCourseTakenSql);
					
					String insertCourseTakenSql = "INSERT INTO `course_taken`(`course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) VALUES "
							+ "('"+courseCode+"','"+courseName+"','"+section+"','"+batch+"','"+department+"','"+semester+"','"+loginUserName+"')";
					st.executeUpdate(insertCourseTakenSql);
					
					for(int i=0;i<len;i++) {
						String studentId = newCourseStudentTableModel.getValueAt(i, 0).toString();
						String studentName = newCourseStudentTableModel.getValueAt(i, 1).toString();
						String insertStudentsSql = "INSERT INTO `student_list`(`student_id`, `student_name`, `course_code`, `course_name`, `section`, `batch`, `department`, `semester`, `username`) VALUES "
								+ "('"+studentId+"','"+studentName+"','"+courseCode+"','"+courseName+"','"+section+"','"+batch+"','"+department+"','"+semester+"','"+username+"')";
						st.executeUpdate(insertStudentsSql);
						
						System.out.println(studentId);
						System.out.println(studentName);
					}
					
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		newCoursePanel.add(newCourseSaveButton);
		
		
		
		
		mainPane.add("New Course",newCoursePanel);
		
		
		
		
		
		/// My Projects Tab
		
		editTeamObj[5] = username;
		
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
				
				String semester = "";
				
				semester = myProjectsSemesterTextField.getText();
				
				if(semester.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter semester");
					return;
				}
				
				editTeamObj[4] = semester;
				
				int	courseLen = myProjectsCourseTable.getRowCount();
				
				if (courseLen > 0) {
				    for (int i = courseLen - 1; i > -1; i--) {
				    	myProjectsCourseTableModel.removeRow(i);
				    }
				}

				
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
		myProjectsDeleteAllButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String semester = "";
				
				semester = myProjectsSemesterTextField.getText();
				
				if(semester.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a semester");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all data of this semester?", "Delete all?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						String deleteFromProjectSql = "DELETE FROM `projects` where "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
						st.executeUpdate(deleteFromProjectSql);
						
						String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
								+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
								+ "lower(trim(semester))=lower(trim('"+semester+"'))";
						st.executeUpdate(deleteFromTeamMembersSql);
						
						
						int courseLen = myProjectsCourseTable.getRowCount();
						if (courseLen > 0) {
						    for (int i = courseLen - 1; i > -1; i--) {
						    	myProjectsCourseTableModel.removeRow(i);
						    }
						}
						
						int teamLen = myProjectsTeamTable.getRowCount();
						if (teamLen > 0) {
						    for (int i = teamLen - 1; i > -1; i--) {
						    	myProjectsTeamTableModel.removeRow(i);
						    }
						}
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
				    return;
				}	
				
			}
		});
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
				int idx = -1;
				
				idx = myProjectsCourseTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a course");
					return;
				}
				
				
				
				int teamLen = myProjectsTeamTable.getRowCount();
				
				if (teamLen > 0) {
				    for (int i = teamLen - 1; i > -1; i--) {
				    	myProjectsTeamTableModel.removeRow(i);
				    }
				}
				
				String courseCode = myProjectsCourseTableModel.getValueAt(idx, 0).toString();
				String courseName = myProjectsCourseTableModel.getValueAt(idx, 1).toString();
				expandedSemester = myProjectsSemesterTextField.getText() ;
				
				try {
					String searchSQL = "SELECT * FROM `projects` WHERE "
							+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
							+ "lower(trim(course_name)) = lower(trim('"+courseName+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+expandedSemester+"'))";
					
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
				int idx = -1;
				idx = myProjectsTeamTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				String projectName = myProjectsTeamTableModel.getValueAt(idx, 0).toString();
				String teamName = myProjectsTeamTableModel.getValueAt(idx, 1).toString();
				String courseCode = myProjectsTeamTableModel.getValueAt(idx, 2).toString();
				String courseName = myProjectsTeamTableModel.getValueAt(idx, 3).toString();
				
				editTeamObj[0] = projectName;
				editTeamObj[1] = teamName;
				editTeamObj[2] = courseCode;
				editTeamObj[3] = courseName;
				//editTeamObj[4] on searh button and editTeamObj[5] at the beginning
				
				new NewSessionFrame(editTeamObj);
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
				int idx = -1;
				idx = myProjectsTeamTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				String projectName = myProjectsTeamTableModel.getValueAt(idx, 0).toString();
				String teamName = myProjectsTeamTableModel.getValueAt(idx, 1).toString();
				String courseCode = myProjectsTeamTableModel.getValueAt(idx, 2).toString();
				String courseName = myProjectsTeamTableModel.getValueAt(idx, 3).toString();
				
				editTeamObj[0] = projectName;
				editTeamObj[1] = teamName;
				editTeamObj[2] = courseCode;
				editTeamObj[3] = courseName;
				//editTeamObj[4] on searh button and editTeamObj[5] at the beginning
				
				new SessionHistoryFrame(editTeamObj);
				
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
				
				int idx = -1;
				idx = myProjectsTeamTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				String projectName = myProjectsTeamTableModel.getValueAt(idx, 0).toString();
				String teamName = myProjectsTeamTableModel.getValueAt(idx, 1).toString();
				String courseCode = myProjectsTeamTableModel.getValueAt(idx, 2).toString();
				String courseName = myProjectsTeamTableModel.getValueAt(idx, 3).toString();
				
				editTeamObj[0] = projectName;
				editTeamObj[1] = teamName;
				editTeamObj[2] = courseCode;
				editTeamObj[3] = courseName;
				//editTeamObj[4] on searh button and editTeamObj[5] at the beginning
				
				
				
				new EditTeamFrame(editTeamObj);
				
			}
		});
		myProjectsPanel.add(myProjectsEditTeamButton);
		
		myProjectsDeleteTeamButton = new JButton("Delete Team");
		myProjectsDeleteTeamButton.setFont(buttonFont);
		myProjectsDeleteTeamButton.setBounds(800,640,180,25);
		myProjectsDeleteTeamButton.setForeground(Color.white);
		myProjectsDeleteTeamButton.setFocusable(false);
		myProjectsDeleteTeamButton.setBackground(red);
		myProjectsDeleteTeamButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = -1;
				
				
				idx = myProjectsTeamTable.getSelectedRow();
				
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "Please select a team");
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the team?", "Delete Team?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}
				
				String projectName = myProjectsTeamTableModel.getValueAt(idx, 0).toString();
				String teamName = myProjectsTeamTableModel.getValueAt(idx, 1).toString();
				String courseCode = myProjectsTeamTableModel.getValueAt(idx, 2).toString();
				String courseName = myProjectsTeamTableModel.getValueAt(idx, 3).toString();
				
				
				try {
					String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+expandedSemester+"'))";
					st.executeUpdate(deleteFromTeamMembersSql);
					
					String deleteFromProjectSql = "DELETE FROM `projects` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+expandedSemester+"')) and "
							+ "lower(trim(project_name))=lower(trim('"+projectName+"'))";
					st.executeUpdate(deleteFromProjectSql);
					
					
					myProjectsTeamTableModel.removeRow(myProjectsTeamTable.getSelectedRow());
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
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
					String searchCourseSql = "SELECT `course_code`, `course_name` FROM `course` WHERE lower(trim(course_code)) = lower(trim('"+courseCode+"')) or lower(trim(course_name)) = lower(trim('"+courseName+"'))";
					ResultSet rs2 = st.executeQuery(searchCourseSql);
					
					int cnt2 = 0;
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
						cnt2++;
					}
					
					
					
					
					// // Checking if team and project already exists
					if(!prevTeamName.equals(teamName) || !prevProjectName.equals(projectName) || !prevCourseCode.equals(courseCode)) {
						String searchSql1 = "SELECT `course_code`, `project_name`, `team_name` FROM `projects` WHERE "
								+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
								+ "lower(trim(team_name)) = lower(trim('"+teamName+"'))";
						String searchSql3 = "SELECT `course_code`, `project_name`, `team_name` FROM `projects` WHERE "
								+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
								+ "lower(trim(project_name)) = lower(trim('"+projectName+"'))";
						
						
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
					
					String insertCourseSql = "INSERT INTO `course`(`course_code`, `course_name`) VALUES "
							+ "('"+courseCode+"','"+courseName+"')";
					if(cnt2==0)st.executeUpdate(insertCourseSql);
					
					String deleteFromTeamMembersSql = "DELETE FROM `team_members` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"'))";
					st.executeUpdate(deleteFromTeamMembersSql);
					
					String deleteFromProjectSql = "DELETE FROM `projects` where "
							+ "lower(trim(team_name))=lower(trim('"+teamName+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"')) and "
							+ "lower(trim(project_name))=lower(trim('"+projectName+"'))";
					st.executeUpdate(deleteFromProjectSql);
					
					String insertProjectSql = "INSERT INTO `projects`(`course_code`, `course_name`, `project_name`, `team_name`, `semester`, `username`) VALUES "
							+ "('"+courseCode+"','"+courseName+"','"+projectName+"','"+teamName+"','"+semester+"','"+loginUserName+"')";
					st.executeUpdate(insertProjectSql);
					
					for(int i=0;i<len;i++) {
						String studentId = newProjectStudentTableModel.getValueAt(i, 0).toString();
						String studentName = newProjectStudentTableModel.getValueAt(i, 1).toString();
						String insertMembersSql = "INSERT INTO `team_members`(`team_name`, `course_code`, `student_id`, `student_name`, `username`, `semester`) VALUES "
								+ "('"+teamName+"','"+courseCode+"','"+studentId+"','"+studentName+"','"+loginUserName+"','"+semester+"')";
						st.executeUpdate(insertMembersSql);
						
						System.out.println(studentId);
						System.out.println(studentName);
					}
					
					
					
					
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
