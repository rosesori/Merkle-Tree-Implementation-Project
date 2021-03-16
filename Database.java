import java.util.ArrayList;

public class Database {
	private int studentCount;
    private int profCount;
    private int hashkey;
    private ArrayList<Personnel> people = new ArrayList<Personnel>();
    private ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Integer> keyHashes = new ArrayList<Integer>();

    public Database() {
    	studentCount = 0;
    	profCount = 0;
    }
    
    // Getters and Setters
    public int getStudentCount() {
        return studentCount;
    }
    public void incStudentCount() {
        this.studentCount++;
    }
    public int getProfCount() {
        return profCount;
    }
    public void incProfCount() {
        this.profCount++;
    }
    public int getHashkey() {
        return hashkey;
    }
    public void setHashkey(int hashkey) {
        this.hashkey = hashkey;
    }
    
    public void storeStudent(Student s) {
    	students.add(s);
    }
    public ArrayList<Student> getStudents() {
    	return students;
    }
    
    public ArrayList<Personnel> getPeople() {
    	return people;
    }
    // Generics Example ***
    public <T extends Personnel> void storeID(T person) {
    	people.add(person);
    }
}
