/*****************************************
** File:    Personnel.java
** Project: CSCE 314 Final Project, Fall 2020
** Author: 	Lauren Rose Soriano, Kazuoki Tokuno
** Date:    11/7/2020
** Section: 502
** E-mail:  laurensori@tamu.edu, kazuoki.tokuno@tamu.edu
**
**   This file contains the Personnel class of the Project which
**   is a super class of student and professor. It shares its variables and
**   functions with its subclasses due to its inheritant rules.
**
**
***********************************************/

public abstract class Personnel { 
	// Variables
	private String firstName;
	private String lastName;
	private int id;
	
	// Functions
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public void setFirstName(String newF) { firstName = newF; }
	public void setLastName(String newL) { lastName = newL; }
	public int getID() { return id; }
	public void setID(int newID ) {id = newID;}
	
	// Abstraction Example ***
	public abstract void generateID(); 
}
