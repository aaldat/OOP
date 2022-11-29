package university;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	
	@Override
	public int enroll(String first, String last) {
		int id = super.enroll(first, last);
		logger.info("New student enrolled: " + id + ", " + first + " " + last);
		return id;
	}
	
	@Override
	public int activate(String title, String teacher) {
		int id = super.activate(title, teacher);
		logger.info("New Course activated: " + id + ", " + title + " " + teacher);
		return id;
	}
	
	@Override
	public void register(int studentId, int courseId) {
		super.register(studentId, courseId);
		logger.info("Student " + studentId + " signed up for course " + courseId);
	}

	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		Student s = getStudent(studentId);
		Course c = getCourse(courseID);
		Exam e = new Exam(s, c, grade);
		s.addExam(e);
		c.addExam(e);
		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		Student s = getStudent(studentId);
		double avg = s.average();
		if(Double.isNaN(avg)) {
			return "Student " + studentId + " hasn't take any exams";
		}
		else {
			return "Student " + studentId + ": " + avg;
		}
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		Course c = getCourse(courseId);
		double avg = c.average();
		if(Double.isNaN(avg)) {
			return "No student has taken the exam in " + c.getTitle();
		}
		else {
			return "The average for the course " + c.getTitle() + " is: " + avg;
		}
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		Student[] top = top(3, Comparator.comparingDouble(Student::getScore));
		String res = "";
		for(Student s : top) {
			if(s.getScore() == Double.NEGATIVE_INFINITY) {
				continue;
			}
			res += s.getFirst() + " " + s.getLast() + " " + s.getScore() + "\n";
		}
		return res;
	}
}