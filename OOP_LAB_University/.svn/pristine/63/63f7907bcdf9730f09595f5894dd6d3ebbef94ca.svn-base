package university;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	private final static int MAX_STUD = 1000;
	private final static int MIN_MATR = 10000;
	private final static int MAX_COURSE = 50;
	private final static int MIN_COURSE = 10;
	
	private String name, first, last;
	private int i = 0, j = 0;
	private Student[] students = new Student[MAX_STUD];
	private Course[] courses = new Course[MAX_COURSE];
	
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		this.first = first;
		this.last = last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return first + " " + last ;
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		students[i] = new Student(i+MIN_MATR, first, last);
		i++;
		return (students[i-1].getMatr());
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		return students[id-MIN_MATR].toString();
	}
	
	protected Student getStudent(int id) {
		return students[id-MIN_MATR];
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		courses[j] = new Course(j+MIN_COURSE, title, teacher);
		j++;
		return (courses[j-1].getCode());
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		return courses[code-MIN_COURSE].toString();
	}
	
	protected Course getCourse(int code) {
		return courses[code-MIN_COURSE];
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Student s = getStudent(studentID);
		//System.out.println(s.toString());
		Course c = getCourse(courseCode);
		//System.out.println(c.toString());
		s.addStudyPlan(c);
		c.setEnrolled(s);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		return getCourse(courseCode).getEnrolled();
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		return getStudent(studentID).getStudyPlan();
	}
	
	
	protected Student[] top(int n, Comparator<Student> cmp) {
		n = Math.min(n, i);
		Student[] sorted = Arrays.copyOf(students, i);
		Arrays.sort(sorted,cmp.reversed());
		return Arrays.copyOf(sorted, n);
	}
}
