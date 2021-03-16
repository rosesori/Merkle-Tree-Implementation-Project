/*****************************************
** File:    Professor.java
** Project: CSCE 314 Final Project, Fall 2020
** Author: 	Lauren Rose Soriano, Kazuoki Tokuno
** Date:    11/7/2020
** Section: 502
** E-mail:  laurensori@tamu.edu, kazuoki.tokuno@tamu.edu
**
**   This file contains the professor class for Project 1.
**   It is a subclass of the Personnel class and will hold data about
**	 professors
**
***********************************************/

// Inheritance Example ***
public class Professor extends Personnel {
	int numberStudents;
	int numClassSections;
	Database d;
	
	Professor(Database data, String first, String last) {
		d = data;
		generateID();
		this.setFirstName(first);
		this.setLastName(last);
	}
	
	int getNumStduents() { return numberStudents; }
	void setNumStudents(int studentSize) { numberStudents = studentSize; }
	int getSections() { return numClassSections; }
	void setSections() {}
	
	void setGrade(Student stud, int subject, double grade) {
        stud.setClassGrade(grade, subject);
    }
	
	@Override
	public void generateID() {
		d.incProfCount();
		setID(d.getProfCount()+100);
		d.storeID(this);
	}
}
