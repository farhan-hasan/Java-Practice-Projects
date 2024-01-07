import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditTeamFrame extends JFrame{
	
	Color lightColor = new Color(175, 244, 198);
	Color darkColor = new Color(20, 174, 92);
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
	String studentTableColumns[] = {"Student ID","Student Name","Project Name","Team Name","Course Code","Course Name"};
	DefaultTableModel studentTableModel = new DefaultTableModel(studentTableData,studentTableColumns);
	
	public EditTeamFrame() {
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
		
		studentTable = new JTable(studentTableModel);
		studentTable.setOpaque(true);
		studentTable.setFillsViewportHeight(true);
		studentTable.setBackground(Color.white);
		studentTableScrollPane = new JScrollPane(studentTable);
		studentTableScrollPane.setBounds(20, 20, 962, 220);
		studentTable.getTableHeader().setBackground(darkColor);
		studentTable.getTableHeader().setForeground(Color.white);
		studentTable.getTableHeader().setFont(labelFont);
		//myProjectsCourseTable.add(myProjectsCourseTableScrollPane);
		studentTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int idx = studentTable.getSelectedRow();
				
				
			}
		});
		add(studentTableScrollPane);
		
		studentIdLabel = new JLabel("Student ID");
		studentIdLabel.setFont(labelFont);
		studentIdLabel.setBounds(20,250,280,70);
		studentIdLabel.setForeground(Color.black);
		add(studentIdLabel);
		
		studentIdTextField = new JTextField();
		studentIdTextField.setFont(textFieldFont);
		studentIdTextField.setBounds(20,300,180,25);
		studentIdTextField.setBackground(Color.white);
		add(studentIdTextField);
		
		studentNameLabel = new JLabel("Student Name");
		studentNameLabel.setFont(labelFont);
		studentNameLabel.setBounds(215,250,280,70);
		studentNameLabel.setForeground(Color.black);
		add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setFont(textFieldFont);
		studentNameTextField.setBounds(215,300,180,25);
		studentNameTextField.setBackground(Color.white);
		add(studentNameTextField);
		
//		projectNameLabel = new JLabel("Project Name");
//		projectNameLabel.setFont(labelFont);
//		projectNameLabel.setBounds(410,250,280,70);
//		projectNameLabel.setForeground(Color.black);
//		add(projectNameLabel);
//		
//		projectNameTextField = new JTextField();
//		projectNameTextField.setFont(textFieldFont);
//		projectNameTextField.setBounds(410,300,180,25);
//		projectNameTextField.setBackground(Color.white);
//		add(projectNameTextField);
//		
//		teamNameLabel = new JLabel("Team Name");
//		teamNameLabel.setFont(labelFont);
//		teamNameLabel.setBounds(605,250,280,70);
//		teamNameLabel.setForeground(Color.black);
//		add(teamNameLabel);
//		
//		teamNameTextField = new JTextField();
//		teamNameTextField.setFont(textFieldFont);
//		teamNameTextField.setBounds(605,300,180,25);
//		teamNameTextField.setBackground(Color.white);
//		add(teamNameTextField);
//		
//		semesterLabel = new JLabel("Semester");
//		semesterLabel.setFont(labelFont);
//		semesterLabel.setBounds(800,250,280,70);
//		semesterLabel.setForeground(Color.black);
//		add(semesterLabel);
//		
//		semesterTextField = new JTextField();
//		semesterTextField.setFont(textFieldFont);
//		semesterTextField.setBounds(800,300,183,25);
//		semesterTextField.setBackground(Color.white);
//		add(semesterTextField);
//		
//		courseCodeLabel = new JLabel("Course Code");
//		courseCodeLabel.setFont(labelFont);
//		courseCodeLabel.setBounds(20,320,280,70);
//		courseCodeLabel.setForeground(Color.black);
//		add(courseCodeLabel);
//		
//		courseCodeTextField = new JTextField();
//		courseCodeTextField.setFont(textFieldFont);
//		courseCodeTextField.setBounds(20,370,180,25);
//		courseCodeTextField.setBackground(Color.white);
//		add(courseCodeTextField);
//		
//		courseNameLabel = new JLabel("Course Name");
//		courseNameLabel.setFont(labelFont);
//		courseNameLabel.setBounds(215,320,280,70);
//		courseNameLabel.setForeground(Color.black);
//		add(courseNameLabel);
//		
//		courseNameTextField = new JTextField();
//		courseNameTextField.setFont(textFieldFont);
//		courseNameTextField.setBounds(215,370,180,25);
//		courseNameTextField.setBackground(Color.white);
//		add(courseNameTextField);
		
		
		addButton = new JButton("Add");
		addButton.setFont(buttonFont);
		addButton.setBounds(410,300,180,25);
		addButton.setForeground(Color.white);
		addButton.setFocusable(false);
		addButton.setBackground(darkColor);
		add(addButton);
		
		updateButton = new JButton("Update");
		updateButton.setFont(buttonFont);
		updateButton.setBounds(605,300,180,25);
		updateButton.setForeground(Color.white);
		updateButton.setFocusable(false);
		updateButton.setBackground(darkColor);
		add(updateButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(buttonFont);
		deleteButton.setBounds(800,300,180,25);
		deleteButton.setForeground(Color.white);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(darkColor);
		add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setBounds(20,350,180,25);
		saveButton.setForeground(Color.white);
		saveButton.setFocusable(false);
		saveButton.setBackground(darkColor);
		add(saveButton);
		
		doneButton = new JButton("Done");
		doneButton.setFont(buttonFont);
		doneButton.setBounds(800,350,180,25);
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
