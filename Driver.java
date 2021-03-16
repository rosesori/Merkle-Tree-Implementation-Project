import java.util.ArrayList;
import java.util.Scanner;

/*****************************************
** File:    Driver.java
** Project: CSCE 314 Final Project, Fall 2020
** Author: 	Lauren Rose Soriano, Kazuoki Tokuno
** Date:    11/7/2020
** Section: 502
** E-mail:  laurensori@tamu.edu, kazuoki.tokuno@tamu.edu
**
**   This file contains the main driver program for Project 1.
** This program reads the file specified as the first command line
** argument, counts the number of words, spaces, and characters, and
** displays the results in the format specified in the project description.
**
* Application and Description:
* ----------------------------------------------
* 	Our merkle tree will be owned by a university whose students and their class grades are stored in the data structure,
* and each merkle tree will be used for each department in a college (for example, the department of electrical engineering). 
* The purpose of storing the data this way would be to be able to detect when changes to their grades, and therefore their GPA, are inputted. 
* At this particular university, the GPA's of students are updated every semester. The official grades of their classes are updated twice a semester, 
* once halfway through (called a midterm report), and then again at the end of the semester. If the merkle tree detects that its data is changed not 
* at the designated day/time for grades to be updated, then it will alert the head of that department who will check to make sure that the change made 
* by a professor was done under correct university standards/regulations for editing grades.
* 	The purpose of using a merkle tree for each department would be to efficiently store the grades/GPA data of its students and detect when changes are made. 
* Each student's ID is encrypted using the hashing function of the merkle tree, which protects their identity and grade safety if someone were to hack into the
* system and tamper with it. The university finds it important to follow their protocols of only updating grades twice a semester, but they do allow certain 
* edits when a professor might have messed up grading and needs to make a change. Using a merkle tree makes sure that the head of the department checks these 
* edits, so that they are done for responsible reasoning and that the availability to edit isn't taken advantage of.
*
**
***********************************************/

public class Driver {
	
	public static int height(int numberOfStudents) {
		return (int)(Math.log(numberOfStudents) / Math.log(2));
	}
	
	public static void main(String[] args) {
		//Create our main database
		Database data = new Database();
		
		//Initialize Students
		double[] s1_classGrades = {92, 84, 76, 96};
        	Student s1 = new Student("Charlie", "Brown", s1_classGrades, data);
        double[] s2_classGrades = {65, 89, 78, 91};
        	Student s2 = new Student("Son", "Goku", s2_classGrades, data);
        double[] s3_classGrades = {82, 74, 94, 88};
        	Student s3 = new Student("Michael", "Angelo", s3_classGrades, data);
        double[] s4_classGrades = {59, 82, 75, 79};
        	Student s4 = new Student("Pablo", "Escabar", s4_classGrades, data);
		
		int treeHeight = height(data.getStudentCount());
		
		//Initialize Professor
		Professor prof = new Professor(data, "Shawn", "Lupoli");
		
		//Initialize bottom merkel nodes that are connected to students
		merkelNode node1 = new merkelNode();
			node1.add(s1);
		merkelNode node2 = new merkelNode();
			node2.add(s2);
		merkelNode node3 = new merkelNode();
			node3.add(s3);
		merkelNode node4 = new merkelNode();
			node4.add(s4);
		
		// Collections Example ***
		ArrayList<merkelNode> treeNodes = new ArrayList<merkelNode>();
			treeNodes.add(node1);
			treeNodes.add(node2);
			treeNodes.add(node3); 
			treeNodes.add(node4);
		
		// Work up the tree and make merkel nodes
		int spot = 0; //spot we're at in treeNodes ArrayList
		for (int i=0; i<=treeHeight; i++) {
			merkelNode tempNode = new merkelNode();
				tempNode.add(treeNodes.get(spot), treeNodes.get(spot+1));
				spot += 2;
			treeNodes.add(tempNode);
		}
		
		//Set the database's hashKey as the root's hashValue
		data.setHashkey((treeNodes.get(treeNodes.size()-1)).getHashKey());
		
		//Store all the hashes in the database
		for(int i=0; i<treeNodes.size(); i++) {
			Integer newI = new Integer(treeNodes.get(i).getHashKey());
			data.keyHashes.add(newI);
		}
		
		//Script that prompts the user when they run this program
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome!\n");
		System.out.println("(1) Change a student's grade. ");
		System.out.println("(2) View personnel's names and IDs. ");
		System.out.print("\nEnter number of what action you would like to do: ");
			int selection = sc.nextInt();
		
		if (selection == 1) { //Change a student's grade
			//Select a specific student
			System.out.print("\nEnter student's ID that you would like to change a grade of (#1-" + data.getStudentCount() + "): ");
				int selectedStudent = sc.nextInt() - 1;
			//Show that student's grades:
			for(int g=1; g<=4; g++) {
				System.out.println("(" + g + "): " + data.getStudents().get(selectedStudent).getClassGrade(g-1));
			}
			//Select a specific grade
			System.out.print("\nEnter number selection of which grade you would like to change: ");
				int selectedSubject = sc.nextInt();
			//Enter new grade
			System.out.print("\nEnter new grade you would like to change it to (0.00-100.00): ");
				double newGrade = sc.nextDouble();
			//Change the grade
			prof.setGrade(data.getStudents().get(selectedStudent), selectedSubject-1, newGrade);
			
			//Change the hash values of the entire tree
			for(int i=0; i<treeNodes.size(); i++) {
				if(!(treeNodes.get(i).getStudent() == null)) { //is a merkel node attached to a student
					treeNodes.get(i).hashS();
				} else { //is a merkel node mid-tree
					treeNodes.get(i).hashMN();
				}
			}
			
		}
		else if (selection == 2) {
			//Show student's names and ID 
			//Generics Example ***
			for(int i=0; i < data.getPeople().size(); i++) {
                System.out.println("ID: " + data.getPeople().get(i).getID() + " Name: " + data.getPeople().get(i).getFirstName() + " " + data.getPeople().get(i).getLastName());
            }
		}
		else {
			System.out.println("Wasn't a correct selection! Enter the number only.");
		}
		
		//Prompt that our hash key changed AKA the goal of the program
		if (data.getHashkey() != (treeNodes.get(treeNodes.size()-1)).getHashKey()) {
			System.out.println("\nThe root hashkey changed.");
			//Find which student changed
			for(int i=1; i<=data.getStudentCount(); i++) {
				if(data.keyHashes.get(i-1) != treeNodes.get(i-1).getHashKey()) { //found the different one
					System.out.println("Student with ID of " + i + " changed.");
				}
			}
		} else {
			System.out.println("\nThe root hashkey did not change, so there wasn't a change in the tree.");
		}
		
		System.out.println("\nGoodbye, please let us pass :)");
		
		
		sc.close();
	}

}
