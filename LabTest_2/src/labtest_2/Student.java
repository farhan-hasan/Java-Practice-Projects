package labtest_2;

import java.util.Scanner;

public class Student implements PrintDetails {
	private String name,section;
	private int id;
	private double cgpa;
	Scanner input = new Scanner(System.in);
	Student() {
		System.out.println("Enter Name: ");
		this.setName(input.nextLine());
		
		System.out.println("Enter Section: ");
		this.setSection(input.nextLine());
		
		System.out.println("Enter ID: ");
		this.setId(input.nextInt());
		
		System.out.println("Enter CGPA: ");
		this.setCgpa(input.nextDouble());
	}
	
	Student(String name, int id, String section, double cgpa) {
		this.name = name;
		this.id = id;
		this.cgpa = cgpa;
		this.section = section;	
	}
	
	// // Getter Functions
	String getName() {
		return this.name;
	}
	
	String getSection() {
		return this.section;
	}
	
	int getId() {
		return this.id;
	}
	
	double getCgpa() {
		return this.cgpa;
	}
	
	// // Setter Functions
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	
	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public void showDetails() {
		System.out.println(this.name + "'s Information:");
		System.out.println("Name: " + this.name);
		System.out.println("ID: " + this.id);
		System.out.println("Section: " + this.section);
		System.out.println("CGPA: " + this.cgpa);
	}
}
