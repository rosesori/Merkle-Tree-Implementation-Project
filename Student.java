/*****************************************
** File:    Student.java
** Project: CSCE 314 Final Project, Fall 2020
** Author: 	Lauren Rose Soriano, Kazuoki Tokuno
** Date:    11/7/2020
** Section: 502
** E-mail:  laurensori@tamu.edu, kazuoki.tokuno@tamu.edu
**
**   This file contains the student class for Project 1.
**   It is a subclass of the Personnel class and also contains the data that will be the GPA
**   calculated from the array of classGrades in this class as well.
**
**
***********************************************/

// Inheritance Example ***
public class Student extends Personnel {

	private double gpa;
	private double[] classGrades;
	Database d;
	
	// Constructor when a student is initialized, calculates their GPA from their class grades
	public Student(String fname, String lname, double[] classGrades, Database data) {
        gpa = 0;
        d = data;
        this.classGrades = classGrades;

        this.setFirstName(fname);
        this.setLastName(lname);
		
		// Cycles through classGrades array
		for(int i=0; i<classGrades.length; i++) {
			if( (classGrades[i]>=90)&(classGrades[i]<=100) ) { // A
				gpa += 4.0;
			}
			else if ( (classGrades[i]<90)&(classGrades[i]>=80) ) { // B
				gpa += 3.0;
			}
			else if ( (classGrades[i]<80)&(classGrades[i]>=70) ) { // C
				gpa += 2.0;
			}
			else if ( (classGrades[i]<70)&(classGrades[i]>=60) ) { // D
				gpa += 1.0;
			}
			else { // F
				gpa += 0;
			}
		}
		
		// Divide by number of classes to get final GPA
		gpa /= classGrades.length;
		
		// Generate ID for themselves
		generateID();
		
		// Add this student to the "students" array in the database
		d.storeStudent(this);
	}
	
	// Getters
	public double getGPA() { return gpa; }
	public double getClassGrade(int spot) { return classGrades[spot]; }
	public double[] getClassGrades() {return classGrades;}
	// Setters
	public void recalculateGPA() { 
		// Cycles through classGrades array
		for(int i=0; i<classGrades.length; i++) {
			if( (classGrades[i]>=90)&(classGrades[i]<=100) ) { // A
				gpa += 4.0;
			}
			else if ( (classGrades[i]<90)&(classGrades[i]>=80) ) { // B
				gpa += 3.0;
			}
			else if ( (classGrades[i]<80)&(classGrades[i]>=70) ) { // C
				gpa += 2.0;
			}
			else if ( (classGrades[i]<70)&(classGrades[i]>=60) ) { // D
				gpa += 1.0;
			}
			else { // F
				gpa += 0;
			}
		}
		
		// Divide by number of classes to get final GPA
		gpa /= classGrades.length;		
	}
	public void setClassGrade(double newGrade, int spot) {
		classGrades[spot] = newGrade;
		recalculateGPA();
	}

	@Override
	//Increments database's studentCount by 1, and sets studentID to spot in studentCount
	public void generateID() {
		d.incStudentCount();
		setID(d.getStudentCount());
		d.storeID(this);
	}
}
