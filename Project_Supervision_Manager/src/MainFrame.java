import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.table.TableRowSorter;

public class MainFrame extends JFrame{
	
	boolean flag = false;
	ImageIcon signoutIcon = new ImageIcon("C:\\Users\\Farhan\\Downloads\\project icons\\signout.png");
	ImageIcon searchIcon = new ImageIcon("C:\\Users\\Farhan\\Downloads\\project icons\\search.png");
	
	Color lightColor = new Color(255,255,255);
	Color darkColor = new Color(34, 125, 128);
	Color red = new Color(217, 56, 61);
	Color tabColor = new Color(53, 56, 57);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
	Font headingFont = new Font("Times New Roman", Font.BOLD, 40);
	Font textFieldFont = new Font("Times New Roman", Font.BOLD, 15);
	Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);
	
	JTabbedPane mainPane;
	JPanel myCoursesPanel, newCoursePanel, myProjectsPanel, newProjectPanel;
	
	JButton myProjectsSignOutButton, myCoursesSignOutButton;
	
	JTable courseListTable;
	JScrollPane courseListTableScrollPane;
	Object courseListTableData[][] = {};
	String courseListTableColumns[] = {"Course Code","Course Name"};
	DefaultTableModel courseListTableModel = new DefaultTableModel(courseListTableData,courseListTableColumns);
	
	JTable courseListTable2;
	JScrollPane courseListTableScrollPane2;
	Object courseListTableData2[][] = {};
	String courseListTableColumns2[] = {"Course Code","Course Name"};
	DefaultTableModel courseListTableModel2 = new DefaultTableModel(courseListTableData2,courseListTableColumns2);
	
	JLabel courseListLabel,courseListLabel2;
	
	JButton courseListSearchButton, courseListSearchButton2;
	
	JTextField courseListSearchTextField, courseListSearchTextField2;
	
	// My Courses variables
	
	String myCoursesTeamObj[] = {"","","","","","",""};
	
	JButton myCourses1, myCourses2, myCourses3, myCourses4;
	JButton newCourse1, newCourse2, newCourse3, newCourse4;
	JButton myProjects1, myProjects2, myProjects3, myProjects4;
	JButton newProject1, newProject2, newProject3, newProject4;
	JButton signout1, signout2, signout3, signout4;
	
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
	
	JButton newCourseAddButton, newCourseUpdateButton, newCourseDeleteButton, newCourseSaveButton,newCourseFetchStudents;
	
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
		
		courseListTable = new JTable(courseListTableModel){

		   @Override
		   public boolean isCellEditable(int row, int column) {
		       //Only the third column
		       return column >= 2;
		   }
		};
		courseListTable.setOpaque(true);
		courseListTable.setFillsViewportHeight(true);
		courseListTable.setBackground(Color.white);
		courseListTableScrollPane = new JScrollPane(courseListTable);
		courseListTableScrollPane.setBounds(20, 500, 962, 180);
		courseListTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = myCoursesCourseTable.getSelectedRow();
				
				
			}
		});
		courseListTable.getTableHeader().setBackground(darkColor);
		courseListTable.getTableHeader().setForeground(Color.white);
		courseListTable.getTableHeader().setFont(labelFont);
		
		
		courseListTable2 = new JTable(courseListTableModel){

			   @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return column >= 2;
			   }
			};
		courseListTable2.setOpaque(true);
		courseListTable2.setFillsViewportHeight(true);
		courseListTable2.setBackground(Color.white);
		courseListTableScrollPane2 = new JScrollPane(courseListTable2);
		courseListTableScrollPane2.setBounds(20, 500, 962, 180);
		courseListTable2.getTableHeader().setBackground(darkColor);
		courseListTable2.getTableHeader().setForeground(Color.white);
		courseListTable2.getTableHeader().setFont(labelFont);
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teacher_companion","root","");
			st = con.createStatement();
			
			String searchSQL = "SELECT * FROM `course` order by course_code";
			
			
			try {
				
				ResultSet rs = st.executeQuery(searchSQL);
				while(rs.next()) {
					String courseListCode = rs.getString(1).toUpperCase();
					String courseListName = rs.getString(2).toUpperCase();
					Object newRow[] = {courseListCode,courseListName};
					courseListTableModel.addRow(newRow);
					courseListTableModel2.addRow(newRow);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		setTitle("Teacher Companion (" + loginUserName + ")");
		
		UIDefaults defaults = UIManager.getDefaults();
        defaults.put("TabbedPane.borderHightlightColor", new Color(0, 0, 0, 0));
        defaults.put("TabbedPane.darkShadow", new Color(0, 0, 0, 0));
		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = -1;
		insets.left = -1;
		insets.bottom = -1;
		insets.right = -1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);
 		UIManager.put("TabbedPane.selected", lightColor);
		
		mainPane = new JTabbedPane();
		mainPane.setBackground(lightColor); // tabs color
		mainPane.setBounds(0, 0, 1024, 768);
		mainPane.setForeground(Color.black);
		mainPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI(){
		      protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
		    });
		mainPane.setFocusable(false);
		add(mainPane);
		
		// // My Courses Tab
		
		
		
		myCoursesTeamObj[6] = username;
		
		myCoursesPanel = new JPanel();
		myCoursesPanel.setLayout(null);
		myCoursesPanel.setBackground(lightColor);
		
		myCourses1 = new JButton("My Courses");
		myCourses1.setFont(buttonFont);
		myCourses1.setBounds(20,0,180,25);
		myCourses1.setForeground(Color.black);
		myCourses1.setFocusable(false);
		myCourses1.setBackground(lightColor);
		myCourses1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(0);
			}
		});
		myCoursesPanel.add(myCourses1);
		
		newCourse1 = new JButton("New Course");
		newCourse1.setFont(buttonFont);
		newCourse1.setBounds(215,0,180,25);
		newCourse1.setForeground(Color.white);
		newCourse1.setFocusable(false);
		newCourse1.setBackground(darkColor);
		newCourse1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(1);
			}
		});
		myCoursesPanel.add(newCourse1);
		
		myProjects1 = new JButton("My Projects");
		myProjects1.setFont(buttonFont);
		myProjects1.setBounds(410,0,180,25);
		myProjects1.setForeground(Color.white);
		myProjects1.setFocusable(false);
		myProjects1.setBackground(darkColor);
		myProjects1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(2);
			}
		});
		myCoursesPanel.add(myProjects1);
		
		newProject1 = new JButton("New Project");
		newProject1.setFont(buttonFont);
		newProject1.setBounds(605,0,180,25);
		newProject1.setForeground(Color.white);
		newProject1.setFocusable(false);
		newProject1.setBackground(darkColor);
		newProject1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(3);
			}
		});
		myCoursesPanel.add(newProject1);
		
		signout1 = new JButton("Sign Out");
		signout1.setFont(buttonFont);
		signout1.setBounds(800,0,180,25);
		signout1.setForeground(Color.white);
		signout1.setFocusable(false);
		signout1.setBackground(red);
		signout1.addActionListener(new ActionListener() {
			
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
		myCoursesPanel.add(signout1);
		
		
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
				myCoursesExpandedSemester = myCoursesSemesterTextField.getText();
				try {
					String searchSQL = "SELECT * FROM `course_taken` WHERE "
							+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
							+ "lower(trim(course_name)) = lower(trim('"+courseName+"')) and "
							+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester)) = lower(trim('"+myCoursesExpandedSemester+"'))";
					
					ResultSet rs = st.executeQuery(searchSQL);
					int cntCourse=0;
					while(rs.next()) {
						String coursecode = rs.getString(1);
						String coursename = rs.getString(2);
						String section = rs.getString(3);
						String batch = rs.getString(4);
						String department = rs.getString(5);
						Object newRow[] = {coursecode, coursename, section, batch, department};
						myCoursesDetailsTableModel.addRow(newRow);
						cntCourse++;
					}
					
					if(cntCourse==0) {
						if (JOptionPane.showConfirmDialog(null, 
				            "No courses found,Do you want to remove this course from you course list?", "Delete course?", 
				            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							String deleteFromCoursesSql = "DELETE FROM `course_taken` WHERE "
									+ "lower(trim(semester)) = lower(trim('"+myCoursesExpandedSemester+"')) and "
									+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
									+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
									+ "lower(trim(course_name)) = lower(trim('"+courseName+"'))";
							st.executeUpdate(deleteFromCoursesSql);
				        }
						
						myCoursesCourseTableModel.removeRow(myCoursesCourseTable.getSelectedRow());
						
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
				
				new AttendanceFrame(myCoursesTeamObj);
				
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
				
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the section?", "Delete section?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}
				
				String courseCode = myCoursesDetailsTableModel.getValueAt(idx, 0).toString();
				String courseName = myCoursesDetailsTableModel.getValueAt(idx, 1).toString();
				String section = myCoursesDetailsTableModel.getValueAt(idx, 2).toString();
				String batch = myCoursesDetailsTableModel.getValueAt(idx, 3).toString();
				String department = myCoursesDetailsTableModel.getValueAt(idx, 4).toString();
				
				
				try {
					String deleteFromStudentListSql = "DELETE FROM `student_list` where "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+myCoursesExpandedSemester+"'))";
					st.executeUpdate(deleteFromStudentListSql);
					
					String deleteFromCourseSql = "DELETE FROM `course_taken` where "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+myCoursesExpandedSemester+"'))";
					st.executeUpdate(deleteFromCourseSql);
					
					
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
		
		myCourses1 = new JButton("My Courses");
		myCourses1.setFont(buttonFont);
		myCourses1.setBounds(20,0,180,25);
		myCourses1.setForeground(Color.white);
		myCourses1.setFocusable(false);
		myCourses1.setBackground(darkColor);
		myCourses1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(0);
			}
		});
		newCoursePanel.add(myCourses1);
		
		newCourse1 = new JButton("New Course");
		newCourse1.setFont(buttonFont);
		newCourse1.setBounds(215,0,180,25);
		newCourse1.setForeground(Color.black);
		newCourse1.setFocusable(false);
		newCourse1.setBackground(lightColor);
		newCourse1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				mainPane.setSelectedIndex(1);
			}
		});
		newCoursePanel.add(newCourse1);
		
		myProjects1 = new JButton("My Projects");
		myProjects1.setFont(buttonFont);
		myProjects1.setBounds(410,0,180,25);
		myProjects1.setForeground(Color.white);
		myProjects1.setFocusable(false);
		myProjects1.setBackground(darkColor);
		myProjects1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(2);
			}
		});
		newCoursePanel.add(myProjects1);
		
		newProject1 = new JButton("New Project");
		newProject1.setFont(buttonFont);
		newProject1.setBounds(605,0,180,25);
		newProject1.setForeground(Color.white);
		newProject1.setFocusable(false);
		newProject1.setBackground(darkColor);
		newProject1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(3);
			}
		});
		newCoursePanel.add(newProject1);
		
		signout1 = new JButton("Sign Out");
		signout1.setFont(buttonFont);
		signout1.setBounds(800,0,180,25);
		signout1.setForeground(Color.white);
		signout1.setFocusable(false);
		signout1.setBackground(red);
		signout1.addActionListener(new ActionListener() {
			
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
		newCoursePanel.add(signout1);
		
		
		
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
		
		courseListTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = courseListTable.getSelectedRow();
				String courseCode = courseListTableModel.getValueAt(idx, 0).toString();
				String courseName = courseListTableModel.getValueAt(idx, 1).toString();
				newCourseCourseCodeTextField.setText(courseCode);
				newCourseCourseNameTextField.setText(courseName);
				
			}
		});
		
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
		
		newCourseFetchStudents = new JButton("Fetch Students");
		newCourseFetchStudents.setFont(buttonFont);
		newCourseFetchStudents.setBounds(410,380,375,25);
		newCourseFetchStudents.setForeground(Color.white);
		newCourseFetchStudents.setFocusable(false);
		newCourseFetchStudents.setBackground(darkColor);
		newCourseFetchStudents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String section = newCourseSectionTextField.getText();
				String batch = newCourseBatchTextField.getText();
				String department = newCourseDepartmentTextField.getText();
				
				if(section.equals("") || batch.equals("") || department.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter Section, Batch and Department");
					return;
				}
				
				int studentLen = newCourseStudentTableModel.getRowCount();
				
				if (studentLen > 0) {
				    for (int i = studentLen - 1; i > -1; i--) {
				    	newCourseStudentTableModel.removeRow(i);
				    }
				}
				
				
				String searchSql = "SELECT * FROM `student` WHERE "
						+ "lower(trim(section)) = lower(trim('"+section+"')) and "
						+ "lower(trim(batch)) = lower(trim('"+batch+"')) and "
						+ "lower(trim(department)) = lower(trim('"+department+"'))"
								+ "order by student_id";
				
				
				
				try {
					
					ResultSet rs = st.executeQuery(searchSql);
					int cntStudent = 0;
					while(rs.next()) {
						
						String studentId = rs.getString(1);
						String studentName = rs.getString(2);
						Object newRow[] = {studentId, studentName};
						newCourseStudentTableModel.addRow(newRow);
						
						cntStudent++;
					}
					if(cntStudent==0) {
						JOptionPane.showMessageDialog(null, "No data found");
						return;
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		newCoursePanel.add(newCourseFetchStudents);
		
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
				if(studentId.equals("") || studentName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter student ID and Name.");
					return;
				}
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
				
				for(int i=0;i<len;i++) {
					String studentId = newCourseStudentTable.getValueAt(i, 0).toString();
					
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
					
					for(int j=0;j<len;j++) {
						if(j==i)continue;
						if(studentId.equals(newCourseStudentTable.getValueAt(j, 0).toString())) {
							JOptionPane.showMessageDialog(null, "Student ID must be unique");
							return;
						}
					}
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
					
					// // Course Duplication check
					
					String searchCourseTakenSql = "SELECT * FROM `course_taken` WHERE "
							+ "lower(trim(section))=lower(trim('"+section+"')) and "
							+ "lower(trim(batch))=lower(trim('"+batch+"')) and "
							+ "lower(trim(department))=lower(trim('"+department+"')) and "
							+ "lower(trim(course_code))=lower(trim('"+courseCode+"')) and "
							+ "lower(trim(username))=lower(trim('"+loginUserName+"')) and "
							+ "lower(trim(semester))=lower(trim('"+semester+"'))";
					st.executeQuery(searchCourseTakenSql);
					
					ResultSet searchRs = st.executeQuery(searchCourseTakenSql);
					int cntSearch = 0;
					while(searchRs.next()) cntSearch++;
					
					if(cntSearch!=0) {
						JOptionPane.showMessageDialog(null, "Course Already exists");
						return;
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
		
		courseListLabel = new JLabel("Course List");
		courseListLabel.setFont(labelFont);
		courseListLabel.setBounds(20,440,280,70);
		courseListLabel.setForeground(Color.black);
		newCoursePanel.add(courseListLabel);
		
		
		courseListSearchButton = new JButton("Search Course");
		courseListSearchButton.setFont(buttonFont);
		courseListSearchButton.setBounds(605,460,180,25);
		courseListSearchButton.setForeground(Color.white);
		courseListSearchButton.setFocusable(false);
		courseListSearchButton.setBackground(darkColor);
		courseListSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(courseListTableModel);
				courseListTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(courseListSearchTextField.getText().toUpperCase()));
			}
		});
		newCoursePanel.add(courseListSearchButton);
		
		
		courseListSearchTextField = new JTextField();
		courseListSearchTextField.setFont(textFieldFont);
		courseListSearchTextField.setBounds(800,460,180,25);
		courseListSearchTextField.setBackground(Color.white);
		newCoursePanel.add(courseListSearchTextField);
		
		
		
		
		// // Course List added. initialized at top.
		DefaultTableModel model = (DefaultTableModel) courseListTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		courseListTable.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(courseListSearchTextField.getText()));
		newCoursePanel.add(courseListTableScrollPane);
		
		
		
		mainPane.add("New Course",newCoursePanel);
		
		
		
		
		
		/// My Projects Tab
		
		editTeamObj[5] = username;
		
		myProjectsPanel = new JPanel();
		myProjectsPanel.setLayout(null);
		myProjectsPanel.setBackground(lightColor);
		
		myCourses1 = new JButton("My Courses");
		myCourses1.setFont(buttonFont);
		myCourses1.setBounds(20,0,180,25);
		myCourses1.setForeground(Color.white);
		myCourses1.setFocusable(false);
		myCourses1.setBackground(darkColor);
		myCourses1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(0);
			}
		});
		myProjectsPanel.add(myCourses1);
		
		newCourse1 = new JButton("New Course");
		newCourse1.setFont(buttonFont);
		newCourse1.setBounds(215,0,180,25);
		newCourse1.setForeground(Color.white);
		newCourse1.setFocusable(false);
		newCourse1.setBackground(darkColor);
		newCourse1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(1);
			}
		});
		myProjectsPanel.add(newCourse1);
		
		myProjects1 = new JButton("My Projects");
		myProjects1.setFont(buttonFont);
		myProjects1.setBounds(410,0,180,25);
		myProjects1.setForeground(Color.black);
		myProjects1.setFocusable(false);
		myProjects1.setBackground(lightColor);
		myProjects1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(2);
			}
		});
		myProjectsPanel.add(myProjects1);
		
		newProject1 = new JButton("New Project");
		newProject1.setFont(buttonFont);
		newProject1.setBounds(605,0,180,25);
		newProject1.setForeground(Color.white);
		newProject1.setFocusable(false);
		newProject1.setBackground(darkColor);
		newProject1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(3);
			}
		});
		myProjectsPanel.add(newProject1);
		
		signout1 = new JButton("Sign Out");
		signout1.setFont(buttonFont);
		signout1.setBounds(800,0,180,25);
		signout1.setForeground(Color.white);
		signout1.setFocusable(false);
		signout1.setBackground(red);
		signout1.addActionListener(new ActionListener() {
			
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
		myProjectsPanel.add(signout1);
		
		
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
				
				int teamLen = myProjectsTeamTable.getRowCount();
				
				if (teamLen > 0) {
				    for (int i = teamLen - 1; i > -1; i--) {
				    	myProjectsTeamTableModel.removeRow(i);
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
				int cntProjects = 0;
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
						cntProjects++;
					}
					
					if(cntProjects==0) {
						if (JOptionPane.showConfirmDialog(null, 
					            "No projects found, Do you want to remove this course from you course list?", "Delete course?", 
					            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								String deleteFromProjectsSql = "DELETE FROM `projects` WHERE "
										+ "lower(trim(semester)) = lower(trim('"+expandedSemester+"')) and "
										+ "lower(trim(username)) = lower(trim('"+loginUserName+"')) and "
										+ "lower(trim(course_code)) = lower(trim('"+courseCode+"')) and "
										+ "lower(trim(course_name)) = lower(trim('"+courseName+"'))";
								st.executeUpdate(deleteFromProjectsSql);
					        }
						
						int	courseLen = myProjectsCourseTable.getRowCount();
						
						if (courseLen > 0) {
						    for (int i = courseLen - 1; i > -1; i--) {
						    	myProjectsCourseTableModel.removeRow(i);
						    }
						}
						
						searchSQL = "SELECT DISTINCT `course_code`, `course_name` FROM `projects` WHERE lower(trim(semester)) = lower(trim('"+expandedSemester+"')) and lower(trim(username)) = lower(trim('"+loginUserName+"'))";
						
						rs = st.executeQuery(searchSQL);
						
						while(rs.next()) {
							courseCode = rs.getString(1);
							courseName = rs.getString(2);
							Object newRow[] = {courseCode, courseName};
							myProjectsCourseTableModel.addRow(newRow);
						}
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
		
		myCourses1 = new JButton("My Courses");
		myCourses1.setFont(buttonFont);
		myCourses1.setBounds(20,0,180,25);
		myCourses1.setForeground(Color.white);
		myCourses1.setFocusable(false);
		myCourses1.setBackground(darkColor);
		myCourses1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(0);
			}
		});
		newProjectPanel.add(myCourses1);
		
		newCourse1 = new JButton("New Course");
		newCourse1.setFont(buttonFont);
		newCourse1.setBounds(215,0,180,25);
		newCourse1.setForeground(Color.white);
		newCourse1.setFocusable(false);
		newCourse1.setBackground(darkColor);
		newCourse1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(1);
			}
		});
		newProjectPanel.add(newCourse1);
		
		myProjects1 = new JButton("My Projects");
		myProjects1.setFont(buttonFont);
		myProjects1.setBounds(410,0,180,25);
		myProjects1.setForeground(Color.white);
		myProjects1.setFocusable(false);
		myProjects1.setBackground(darkColor);
		myProjects1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(2);
			}
		});
		newProjectPanel.add(myProjects1);
		
		newProject1 = new JButton("New Project");
		newProject1.setFont(buttonFont);
		newProject1.setBounds(605,0,180,25);
		newProject1.setForeground(Color.black);
		newProject1.setFocusable(false);
		newProject1.setBackground(lightColor);
		newProject1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPane.setSelectedIndex(3);
			}
		});
		newProjectPanel.add(newProject1);
		
		signout1 = new JButton("Sign Out");
		signout1.setFont(buttonFont);
		signout1.setBounds(800,0,180,25);
		signout1.setForeground(Color.white);
		signout1.setFocusable(false);
		signout1.setBackground(red);
		signout1.addActionListener(new ActionListener() {
			
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
		newProjectPanel.add(signout1);
		
		
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
		
		courseListTable2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = courseListTable2.getSelectedRow();
				String courseCode = courseListTableModel.getValueAt(idx, 0).toString();
				String courseName = courseListTableModel.getValueAt(idx, 1).toString();
				newProjectCourseCodeTextField.setText(courseCode);
				newProjectCourseNameTextField.setText(courseName);
				
			}
		});
		
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
				
				if(studentId.equals("") || studentName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter student ID and Name.");
					return;
				}
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
				
				for(int i=0;i<len;i++) {
					String studentId = newProjectStudentTable.getValueAt(i, 0).toString();
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
					for(int j=0;j<len;j++) {
						if(j==i)continue;
						if(studentId.equals(newProjectStudentTable.getValueAt(j, 0).toString())) {
							JOptionPane.showMessageDialog(null, "Student ID must be unique");
							return;
						}
					}
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
		
		courseListLabel2 = new JLabel("Course List");
		courseListLabel2.setFont(labelFont);
		courseListLabel2.setBounds(20,440,280,70);
		courseListLabel2.setForeground(Color.black);
		newProjectPanel.add(courseListLabel2);
		
		
		courseListSearchButton2 = new JButton("Search Course");
		courseListSearchButton2.setFont(buttonFont);
		courseListSearchButton2.setBounds(605,460,180,25);
		courseListSearchButton2.setForeground(Color.white);
		courseListSearchButton2.setFocusable(false);
		courseListSearchButton2.setBackground(darkColor);
		courseListSearchButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(courseListTableModel);
				courseListTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(courseListSearchTextField.getText().toUpperCase()));
			}
		});
		newProjectPanel.add(courseListSearchButton2);
		
		
		courseListSearchTextField2 = new JTextField();
		courseListSearchTextField2.setFont(textFieldFont);
		courseListSearchTextField2.setBounds(800,460,180,25);
		courseListSearchTextField2.setBackground(Color.white);
		newProjectPanel.add(courseListSearchTextField2);
		
		// // Course List added, initialized at top
		newProjectPanel.add(courseListTableScrollPane2);
		
		
		mainPane.add("New Project",newProjectPanel);
		
		
		setVisible(true);
	}

}
