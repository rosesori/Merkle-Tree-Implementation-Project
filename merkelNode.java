/*****************************************
** File:    merkelNode.java
** Project: CSCE 314 Final Project, Fall 2020
** Author: 	Lauren Rose Soriano, Kazuoki Tokuno
** Date:    11/7/2020
** Section: 502
** E-mail:  laurensori@tamu.edu, kazuoki.tokuno@tamu.edu
**
**   This file contains merkelNode.java which are the all the nodes
**   on a merkel tree that contain a hashed value of its children.
**
**
***********************************************/


public class merkelNode {
	private int hashValue;
	private merkelNode leftMerkNode = null;
	private merkelNode rightMerkNode = null;
	Student stud = null;
	
	// Constructor 
	public merkelNode() {

	}
	
	// Setters and getters
	public merkelNode getLT() {return leftMerkNode; }
	public merkelNode getRT() { return rightMerkNode; }
	public Student getStudent() { return stud; }
	public int getHashKey() {return hashValue; }
	
	/* If this merkel node is mid tree (not at the bottom and connected to a Student),
	   then it will be connected by reference to it's two children (which should be merkelNodes). */
	public void add(merkelNode leftMT, merkelNode rightMT) {
		leftMerkNode = leftMT;
		rightMerkNode = rightMT; 
		hashMN();
	}
	
	/* If this merkel node is at the bottom of the tree (thus connected to a Student node),
	   then it will be connected by reference to only a Student. */
	public void add(Student std) {
		stud = std; 
		hashS();
	}
	
	// Hash functions if it's hashing a student
	public void hashS() {
		hashValue = (int)(stud.getGPA()*stud.getID()) % 16;
	}
	// Hash function if it's hashing two merkelNodes
	public void hashMN() {
		hashValue = (leftMerkNode.getHashKey()+rightMerkNode.getHashKey()) % 16;
	}
}
